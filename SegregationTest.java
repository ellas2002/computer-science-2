//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class SegregationTest {
//
//    @Test

//    void testCountNeighbors() {
//        int[][] grid = new int[3][3];
//        grid[1][1] = 2;
//        grid[0][1] = 1;
//        grid[0][0] = 2;
//        grid[2][2] = 2;
//
//        assertEquals(0Segregation.countNeighbors(grid,1,1,1));
//        grid[1][1] = 2;
//    }
//
//    @Test
//    void ColorAll() {
//
//        int redcount = 0;
//        int bluecount = 0;
//        int blank = 0;
//
//        int size = Segregation.width * Segregation.height;
//        int blanks = size * Segregation.percent / 100;
//
//        double redNum = (size - blanks) * (Segregation.redp / 100);
//        double blueNum = (size - blanks) * (Segregation.bluep / 100);
//
//        int[][] grid = new int[50][50];
//
//        Segregation.ColorAll(grid);
//
//
//
//        for (int i = 0; i < Segregation.width; i++) {
//            for (int j = 0; j < Segregation.height; j++) {
//
//                if (grid[i][j] == 1) redcount++;
//                else if (grid[i][j] == 2) bluecount++;
//                else blank++;
//
//            }
//        }
//
//        assertEquals(redNum, redcount);
//        assertEquals(blueNum, bluecount);
//        assertEquals(blanks, blank);
//
//    }
//
//}
//
