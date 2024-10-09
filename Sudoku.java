public class Sudoku {
    /**
     * The Sudoku solver.
     *
     * @author Elliot
     * @author Ella, Toby
     */

    public class Sudoku{

        /**
         * Prints the solution to the puzzle in the specified directory.
         */
        public static void main(String[] args) {
            String puzzle = new In("sudoku1.txt").readAll();
            Square[][] grid = createSquares(puzzle);
            solve(grid);
            StdOut.println(toString(grid));
        }

        /**
         * Returns a new Location object with the specified row and column.
         */
        static Location createLocation(int r, int c) {
            // TODO You have to write this
            Location loc = new Location();
            loc.row = r;
            loc.column = c;
            return loc;
        }

        /**
         * Returns an array of the eight Locations in the same row as here.
         */
        static Location[] findRow(Location here) {
            // TODO You have to write this
            Location[] locR = new Location[8];
//        space[0] = new Location();

            for (int i = 0; i < 9; i++) {
                if (i < here.column) {
                    locR[i] = createLocation(here.row, i);
                }
                if (i > here.column) {
                    locR[i - 1] = createLocation(here.row, i);
                }
            }
            return locR;
        }

        /**
         * Returns an array of the eight Locations in the same column as here.
         */
        static Location[] findColumn(Location here) {
            // TODO You have to write this
            Location[] locC = new Location[8];
//        space[0] = new Location();

            for (int i = 0; i < 9; i++) {
                if (i < here.row) {
                    locC[i] = createLocation(i, here.column);
                }
                if (i > here.row) {
                    locC[i - 1] = createLocation(i, here.column);
                }
            }
            return locC;
        }

        /**
         * Returns an array of the eight Locations in the same 3x3 block as here.
         */
        static Location[] findBlock(Location here) {
            // TODO You have to write this
            Location[] loc = new Location[8];

            int k = 0;

            int boundaries = (here.row / 3) * 3;
            int boundaries2 = (here.column / 3) * 3;

            for (int i = boundaries; i < boundaries + 3; i++) {
                for (int j = boundaries2; j < boundaries2 + 3; j++) {
                    if (i != here.row || j != here.column) {
                        loc[k++] = createLocation(i, j);

                    }
                }
            }


            return loc;
        }

        /**
         * Returns a 9x9 array of Squares, each of which has value 0 and knows about the other squares in its row,
         * column, and block.
         */
        static Square[][] createSquares() {
            // TODO You have to write this
            Square[][] grid = new Square[9][9];

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    grid[i][j] = new Square();
                    grid[i][j].value = 0;
                }
            }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    Location loc = createLocation(i, j);
                    Location[] row = findRow(loc);
                    Location[] column = findColumn(loc);
                    Location[] block = findBlock(loc);
                    grid[i][j].row = new Square[8];
                    grid[i][j].column = new Square[8];
                    grid[i][j].block = new Square[8];
                    for (int k = 0; k < 8; k++) {
                        grid[i][j].row[k] = grid[row[k].row][row[k].column];
                        grid[i][j].column[k] = grid[column[k].row][column[k].column];
                        grid[i][j].block[k] = grid[block[k].row][block[k].column];
                    }
                }
            }

            return grid;
        }

        /**
         * Returns a String representing grid, showing the numbers (or . for squares with value 0).
         */
        static String toString(Square[][] grid) {
            // TODO You have to write this
            String s = "";
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (grid[i][j].value == 0) {
                        s = s + ".";
                    } else {
                        s = s + grid[i][j].value;
                    }
                }
                s = s + "\n";
            }

            return s;
        }

        /**
         * Returns a 9x9 array of Squares, each of which has value 0 and knows about the other squares in its row,
         * column, and block. Any numbers in diagram are filled in to the grid; empty squares should be given as
         * periods.
         */
        static Square[][] createSquares(String diagram) {
            // TODO You have to write this
            Square[][] grid = createSquares();
            int k = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if(diagram.charAt(k) == '\n'){
                        k++;
                    }
                    if (diagram.charAt(k) != '.') {
                        grid[i][j].value = diagram.charAt(k) - '0';
                    }
                    k++;
                }
            }
            return grid;
        }

        /**
         * Returns a boolean array of length 10. For each digit, the corresponding entry in the array is true
         * if that number does not appear elsewhere in s's row, column, or block.
         */
        static boolean[] findValidNumbers(Square s) {
            // TODO You have to write this
            boolean[] valid = new boolean[10];

            for (int v = 1; v < 10; v++) {
                valid[v] = true;
                for (int k = 0; k < 8; k++) {
                    if (s.row[k].value == v || s.column[k].value == v || s.block[k].value == v) {
                        valid[v] = false;
                        break;
                    }
                }
            }

            return valid;
        }

        /**
         * Returns true if grid can be solved. If so, grid is modified to fill in that solution.
         */
        static boolean solve(Square[][] grid) {
            // TODO You have to write this
            // Here's an outline of the algorithm:
            // for each square
            //     if its value is 0
            //         for each valid number that could be filled in
            //             if you can solve the rest of the grid
            //                 return true
            //         nothing worked: set value back to 0 and return false
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (grid[i][j].value == 0) {
                        boolean[] valid = findValidNumbers(grid[i][j]);
                        for (int v = 1; v < 10; v++) {
                            if (valid[v]) {
                                grid[i][j].value = v;
                                if (solve(grid)) {
                                    return true;
                                }
                            }
                        }
                        grid[i][j].value =  0;
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
