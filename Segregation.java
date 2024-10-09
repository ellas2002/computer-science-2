
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Segregation {
    public static int width = 50;
    public static int height = 50;
    public static int red = 1;
    public static int blue = 2;
    public static double allp = 50;
    public static double redp = 50;
    public static double bluep = 100 - redp;
    public static int percent = 10;
    public static boolean[][] redraw = new boolean[width][height];
    public static int[][] grid = new int[height][width];


    /*draws grid*/

    public static void drawGrid(int[][] grid) {

        for (int col = 0; col < height; col++) {
            for (int row = 0; row < width; row++) {

                if (grid[row][col] == red) { // prints red squares

                    StdDraw.setPenColor(Color.red);
                    StdDraw.filledRectangle(col + 1, -row + height - 1, 1, 1);

                } else if (grid[row][col] == blue) { // prints blue squares

                    StdDraw.setPenColor(Color.blue);
                    StdDraw.filledRectangle(col + 1, -row + height - 1, 1, 1);

                } else { // prints blank squares

                    StdDraw.setPenColor(Color.white);
                    StdDraw.filledRectangle(col + 1, -row + height - 1, 1, 1);

                }
            }
        }

        StdDraw.setPenColor(Color.black);

        for (int col = 0; col <= height; col++) {
            for (int row = 0; row <= width; row++) {

                StdDraw.line(row, 0, row, height); // prints vertical lines
                StdDraw.line(0, col, width, col); // prints horizontal lines

            }
        }
    }

    public static void ColorAll(int[][] grid) {
        int size = width * height;
        int zero = size * percent / 100;
        double redamount = (size - zero) * (redp / 100);
        double blueamount = (size - zero) * (bluep / 100);

        for (int i = 0; i < redamount; i++) {
            int x = StdRandom.uniformInt(width);
            int y = StdRandom.uniformInt(height);
            if (grid[x][y] == red) {
                i--;
            } else (grid[x][y]) = red;
        }
        for (int i = 0; i < blueamount; i++) {
            int x = StdRandom.uniformInt(width);
            int y = StdRandom.uniformInt(height);
            if (grid[x][y] == red || grid[x][y] == blue) {
                i--;
            } else (grid[x][y]) = blue;
        }
    }

    public static double countNeighbors(int[][] grid, int row, int col, int testColor) {
        if (grid[row][col] == 0) {
            return 1;
        }

        double mycolor = 0;
        double allcolors = 0;

        for (int x = Math.max(0, row - 1); x <= Math.min(row + 1, grid.length - 1); x++) {
            for (int y = Math.max(0, col - 1); y <= Math.min(row + 1, grid.length - 1); y++) {
                if (grid[x][y] != 0) {
                    allcolors++;
                    if (grid[x][y] == testColor) {
                        mycolor++;
                    }
                }
            }
            if (allcolors - 1 == 0) return 1;
        }
        return (--mycolor) / (--allcolors);
    }

    public static void nextZero(int[][] grid, boolean[][] redraw, int row, int column, int color) {
        //double[][] color = new double[width][height];
        int x;
        int y;
        do {
            x = StdRandom.uniformInt(width);
            y = StdRandom.uniformInt(height);
        }
        while (grid[x][y] != 0);
        grid[row][column] = 0;
        if (grid[x][y] == 0 & !redraw[x][y]) {
            grid[x][y] = color;
            redraw[x][y] = true;
            redraw[row][column] = false;
        } else {
            nextZero(grid, redraw, row, column, color);
        }
    }

    public static int redrawgrid(int[][] grid, boolean[][] redraw) {
        resetredraw(grid, redraw);
        int counter = width * height;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                if ((grid[x][y] > 0) && countNeighbors(grid, x, y, grid[x][y]) < (allp / 100)) {
                    nextZero(grid, redraw, x, y, grid[x][y]);
                } else if (countNeighbors(grid, x, y, grid[x][y]) > (allp / 100)) {
                    counter--;
                }
            }
        }
        return counter;
    }

    public static void resetredraw(int[][] grid, boolean[][] redraw) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                redraw[x][y] = (grid[x][y] == red) || (grid[x][y] == blue);
            }
        }
    }

    public static void buffereing(int[][] grid) {
        StdDraw.enableDoubleBuffering();
        drawGrid(grid);
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        StdDraw.show();
        StdDraw.disableDoubleBuffering();
    }

    public static void main(String[] args) {

        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.enableDoubleBuffering();
        ColorAll(grid);
        int i = 0;

        do {
            buffereing(grid);
            i++;
            if (i == 200 || percent == 0) {
                break;
            }
        }
        while (redrawgrid(grid, redraw) > 0);
    }
}