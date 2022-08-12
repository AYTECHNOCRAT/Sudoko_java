
public class Sudoko {

    static int N = 6;
    static boolean solveSudoku(int grid[][], int row, int col)
    {
        // last second row
        if (row == N - 1 && col == N)  return true;
        // to restart
        if (col == N) {
            row++;
            col = 0;
        }
        // if value exists
        if (grid[row][col] != 0)
            return solveSudoku(grid, row, col + 1);
        for (int num = 1; num < 7; num++) {
            // Check if it is safe to place the num (1-6)  in the given row ,col ->we move to next column
            if (isSafe(grid, row, col, num)) {
                /*  assigning the num in the current (row,col)  position of the grid and  assuming our assigned num in the position is correct */
                grid[row][col] = num;
                // Checking for next possibility with next column
                if (solveSudoku(grid, row, col + 1))
                    return true;
            }
            /* removing the assigned num , since our assumption was wrong , and we go for next assumption with diff num value   */
            grid[row][col] = 0;
        }
        return false;
    }
    static void print(int[][] grid)
    {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++)
                System.out.print(grid[i][j] + " ");
            System.out.println();
        }
    }
    static boolean isSafe(int[][] grid, int row, int col,
                          int num)
    {
        // Check if we find the same num in the similar row , we return false
        for (int x = 0; x < 6; x++)
            if (grid[row][x] == num)
                return false;
        // Check if we find the same num in the similar column ,we return false
        for (int x = 0; x < 6; x++)
            if (grid[x][col] == num)
                return false;
        // Check if we find the same num in the particular 3*3 matrix, we return false
        int startRow = row - row % 2, startCol = col - col % 3;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i + startRow][j + startCol] == num)
                    return false;
        return true;
    }

    public static void main(String[] args) {
        int[][] a={
            {0,0,0,4,6,5},
            {0,5,0,1,2,3},
            {5,3,2,0,0,0},
            {1,6,4,0,0,0},
            {0,0,0,2,3,1},
            {0,2,0,5,4,2}
        };
        int[][] c={
            {0,0,0,4,6,5},
            {0,5,0,1,2,3},
            {5,3,2,0,0,0},
            {1,6,4,0,0,0},
            {0,0,0,2,3,1},
            {0,2,0,5,4,2}
        };
        int[][] b=new int[6][6];
        int v=0;
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                v=0;
                if(a[i][j]!=0) b[i][j]=0;
                else{
                    for(int p=1;p<7;p++){
                        if(isSafe(a,i,j,p)) {
                            v++;
                            //System.out.println(v);
                        }
                    }
                    b[i][j]=v;

                }
                
            }
        }
        print(b);
        System.out.println("\n Solution is \n");
        if(solveSudoku(c, 0, 0)){
            print(c);
        }
        else{
            System.out.println("No solution");
        }

    }
}