package cs445.a3;

import java.util.List;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sudoku {
    static boolean isFullSolution(int[][] board) {
        // TODO: Complete this method
        if(reject(board))
        return false;
        for(int i=0; i < 9; i++){
          for(int v=0; v<9; v++){
            if(board[i][v]==0)
            return false;
          }
        }
        return true;
    }

    static boolean reject(int [][] board)
	{
		for (int i=0; i<9; i++)
			for(int j=0; j<9; j++)
				if(board[i][j]<0 || board[i][j]>9 || (board[i][j]!=0 && (!validRow(i,j,board) || !validCol(i,j,board) || !validBlock(i,j,board))))
					return true;
		return false;
}

    static boolean validRow(int i, int v, int[][] board){
    	for (int r=0; r<9; r++)
    	if(r!=i && board[r][v]==board[i][v])
   		return false;
      return true;
    }

    static boolean validCol(int i, int v, int[][] board)
   	{
   		for(int c=0; c<9; c++)
   			if(c!=v && (board[i][c]==board[i][v]))
   				return false;
   		return true;
}

static boolean validBlock(int i, int v, int[][] board)
   	{
   		for(int r=(i/3)*3; r<(i/3)*3 +3; r++)
   			for(int c=(v/3)*3; c<(v/3)*3+3; c++)
   				if(r !=i && c!=v && (board[r][c]==board[i][v]))
   					return false;
   		return true;
}

    static int[][] extend(int[][] board, int[] pos) {
        // TODO: Complete this method
  		int[][]board2 = new int[9][9];
  		for(int i=0; i<9; i++)
  		for(int v=0; v<9; v++)
  		board2[i][v]=board[i][v];
  		for (int i=0; i<9; i++){
			for(int v=0; v<9; v++){
  				if(board[i][v]==0){
  					board2[i][v]=1;
  					pos[0]=i;
  					pos[1]=v;
  					return board2;
  					}
  			}
  		}
  		return null;
  }

    static int[][] next(int[][] board, int[] pos) {
        // TODO: Complete this method
        int board2[][] = new int[9][9];
        int x = pos[0];
        int y = pos[1];
        for(int i=0; i<9; i++)
        for(int v=0; v<9; v++)
        board2[i][v] = board[i][v];
        if(board2[x][y]==9)
        return null;
        else{
          board2[x][y]++;
          return board2;
        }
    }

    static void testIsFullSolution() {
        // TODO: Complete this method
		blockTest(new int[][]{
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0} });
		blockTest(new int[][]{
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9} });
		blockTest(new int[][]{
		{8,2,7,1,5,4,3,9,6},
		{9,6,5,3,2,7,1,4,8},
		{3,4,1,6,8,9,7,5,2},
		{5,9,3,4,6,8,2,7,1},
		{4,7,2,5,1,3,6,8,9},
		{6,1,8,9,7,2,4,3,5},
		{7,8,6,2,2,5,9,1,4},
		{1,5,4,7,9,6,8,2,3},
		{2,3,9,8,4,1,5,6,7} });
		blockTest(new int[][]{
		{8,2,7,1,5,4,3,9,6},
		{9,6,5,3,2,7,1,4,8},
		{3,4,1,6,8,9,7,5,2},
		{5,9,3,4,6,8,2,7,1},
		{4,7,2,5,1,3,6,8,9},
		{6,1,8,9,7,2,4,3,5},
		{7,8,6,2,3,5,9,1,4},
		{1,5,4,7,9,6,8,2,3},
		{2,3,9,8,4,1,5,6,0} });
		blockTest(new int[][]{
		{8,2,7,1,5,4,3,9,6},
		{9,6,5,3,2,7,1,4,8},
		{3,4,1,6,8,9,7,5,2},
		{5,9,3,4,6,8,2,7,1},
		{4,7,2,5,1,3,6,8,9},
		{6,1,8,9,7,2,4,3,5},
		{7,8,6,2,3,5,9,1,4},
		{1,5,4,7,9,6,8,2,3},
		{2,3,9,8,4,1,5,6,7} });
    }

  static void blockTest(int[][] test) {
   if(isFullSolution(test))
   {
     System.err.println("\nFull solution:\t");
     printBoard(test);
   }
   else
   {
     System.err.println("\nNot full solution:\t");
     printBoard(test);
     }
}

static void testRejectGrid(int[][] test)
	{
		if (reject(test))
		{
			System.err.println("\nGrid Rejected:\t" );
			printBoard(test);
		}
		else if(!reject(test))
		{
			System.err.println("\nGrid Not rejected:\t");
			printBoard(test);
		}
	}
    static void testReject() {
		System.err.println("\nTest reject()");
		testRejectGrid(new int[][]{
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0} });
    	testRejectGrid(new int[][]{
    	{1,1,9,9,9,9,9,9,9},
    	{1,1,9,9,9,9,9,9,9},
    	{1,1,9,9,9,9,9,9,9},
    	{1,1,9,9,9,9,9,9,9},
    	{1,1,9,9,9,9,9,9,9},
    	{1,1,9,9,9,9,9,9,9},
    	{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9} });
		testRejectGrid(new int[][]{
		{-1,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0} });
		testRejectGrid(new int[][]{
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,30,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0} });
		testRejectGrid(new int[][]{
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,1,0,0,0,0,0,0,0},
		{1,0,0,0,0,0,0,0,0} });
		testRejectGrid(new int[][]{
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{1,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{1,0,0,0,0,0,0,0,0} });
		testRejectGrid(new int[][]{
		{8,2,7,1,5,4,3,9,6},
		{9,6,5,3,2,7,1,4,8},
		{3,4,1,6,8,9,7,5,2},
		{5,9,3,4,6,8,2,7,1},
		{4,7,2,5,1,3,6,8,9},
		{6,1,8,9,7,2,4,3,5},
		{7,8,6,2,3,5,9,1,4},
		{1,5,4,7,9,6,8,2,3},
		{2,3,9,8,4,1,5,6,7} });
		testRejectGrid(new int[][]{
		{8,2,7,1,5,4,3,9,6},
		{9,6,5,3,2,7,1,4,8},
		{3,4,1,6,8,9,7,5,2},
		{5,9,3,4,6,8,2,7,1},
		{4,7,2,5,1,3,6,8,9},
		{6,1,8,9,7,2,4,3,5},
		{7,8,6,2,3,5,9,1,4},
		{1,5,4,7,9,6,8,2,3},
		{2,3,9,8,4,1,5,6,0} });

    }
	static void testExtendGrid(int [][]test)
	{
		System.err.println("\nExtended ");
		printBoard(test);
		System.out.println("\nto");
		int [] pos= new int[2];
		printBoard(extend(test,pos));
		
	}
    static void testExtend()
    {
    	System.err.println("\nTest extend()");
		
		testExtendGrid(new int[][] {
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0} });
		testExtendGrid(new int[][] {
		{0,1,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,5,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,9,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,1,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0} });
		testExtendGrid(new int[][] {
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,5,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,9,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,1,0,0},
		{0,0,0,0,0,0,0,0,0},
		{1,2,0,0,0,0,0,0,0} });
		testExtendGrid(new int[][]{
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9},
		{1,1,9,9,9,9,9,9,9} });
		testExtendGrid(new int[][]{
		{8,2,7,1,5,4,3,9,6},
		{9,6,5,3,2,7,1,4,8},
		{3,4,1,6,8,9,7,5,2},
		{5,9,3,4,6,8,2,7,1},
		{4,7,2,5,1,3,6,8,9},
		{6,1,8,9,7,2,4,3,5},
		{7,8,6,2,3,5,9,1,4},
		{1,5,4,7,9,6,8,2,3},
		{2,3,9,8,4,1,5,6,0} });
		testExtendGrid(new int[][]{
		{8,2,7,1,5,4,3,9,6},
		{9,6,5,3,2,7,1,4,8},
		{3,4,1,6,8,9,7,5,2},
		{5,9,3,4,6,8,2,7,1},
		{4,7,2,5,1,3,6,8,9},
		{6,1,8,9,7,2,4,3,5},
		{7,8,6,2,3,5,9,1,4},
		{1,5,4,7,9,6,8,2,3},
		{2,3,9,8,4,1,5,6,7} });

    }
	static void testNextGrid(int[][] board, int[]pos)
	{
		if(next(board,pos)!=null)
		{
			System.err.println("\nNext:");
			printBoard(board);
		}
		else
		{
			System.err.println("Cannot be Nexted");
			printBoard(board);
		}

	}
    static void testNext()
    {
    	System.out.println("\nTest Next()");
    	
    	System.out.println("Original Board:\n");

    	printBoard(new int[][] {
		{1,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0} });

		System.out.println("\nExtended board:");
		int [] pos=new int[2];
		int temp[][]=extend(new int[][] {
		{1,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0} }, pos);

		if(temp!=null)
			printBoard(temp);
		else
			System.out.println("Could not extend board");

		System.out.println("\nNexted board:");
		try{
			printBoard(next(temp,pos));
		}catch(NullPointerException e3){
			System.out.println("Could not next board");
		}


		System.out.println("\nOriginal Board:\n");

    	printBoard(new int[][] {
		{1,2,0,4,5,6,7,8,9},
		{0,0,3,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0} });

		System.out.println("\nExtended board:");
		pos=new int[2];
		temp=extend(new int[][] {
		{1,2,0,4,5,6,7,8,9},
		{0,0,3,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0} }, pos);

		if(temp!=null)
			printBoard(temp);
		else
			System.out.println("Could not extend board");

		System.out.println("\nNexted board:");
		try{
			printBoard(next(temp,pos));
		} catch (NullPointerException e){
			System.out.println("Could not next board");
		}

		System.out.println("\nOriginal Board:\n");

    	printBoard(new int[][] {
		{9,9,9,9,9,9,9,9,9},
		{9,9,9,9,9,9,9,9,9},
		{9,9,9,9,9,9,9,9,9},
		{9,9,9,9,9,9,9,9,9},
		{9,9,9,9,9,9,9,9,9},
		{9,9,9,9,9,9,9,9,9},
		{9,9,9,9,9,9,9,9,9},
		{9,9,9,9,9,9,9,9,9},
		{9,9,9,9,9,9,9,9,8} });

		System.out.println("\nExtended board:");
		pos=new int[2];
		temp=extend(new int[][] {
		{9,9,9,9,9,9,9,9,9},
		{9,9,9,9,9,9,9,9,9},
		{9,9,9,9,9,9,9,9,9},
		{9,9,9,9,9,9,9,9,9},
		{9,9,9,9,9,9,9,9,9},
		{9,9,9,9,9,9,9,9,9},
		{9,9,9,9,9,9,9,9,9},
		{9,9,9,9,9,9,9,9,9},
		{9,9,9,9,9,9,9,9,8} }, pos);

		if(temp!=null)
			printBoard(temp);
		else
			System.out.println("Could not extend board");

		System.out.println("\nNexted board:");
		try{
			printBoard(next(temp,pos));
		}catch(NullPointerException e2){
			System.out.println("Cannot next board\n");
		}

		System.out.println("\nOriginal Board:\n");

    	printBoard(new int[][] {
		{8,2,7,1,5,4,3,9,6},
		{9,6,5,3,2,7,1,4,8},
		{3,4,1,6,8,9,7,5,2},
		{5,9,3,4,6,8,2,7,1},
		{4,7,2,5,1,3,6,8,9},
		{6,1,8,9,7,2,4,3,5},
		{7,8,6,2,3,5,9,1,4},
		{1,5,4,7,9,6,8,2,3},
		{2,3,9,8,4,1,5,6,7} });

		System.out.println("\nExtended board:");
		pos=new int[2];
		temp=extend(new int[][] {
		{8,2,7,1,5,4,3,9,6},
		{9,6,5,3,2,7,1,4,8},
		{3,4,1,6,8,9,7,5,2},
		{5,9,3,4,6,8,2,7,1},
		{4,7,2,5,1,3,6,8,9},
		{6,1,8,9,7,2,4,3,5},
		{7,8,6,2,3,5,9,1,4},
		{1,5,4,7,9,6,8,2,3},
		{2,3,9,8,4,1,5,6,7} }, pos);

		if(temp!=null)
			printBoard(temp);
		else
			System.out.println("Could not extend board");

		System.out.println("\nNexted board:");
		try{
			printBoard(next(temp,pos));
		}catch(NullPointerException e2){
			System.out.println("Cannot next board\n");
		}


}

    static void printBoard(int[][] board) {
        if (board == null) {
            System.out.println("No assignment");
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----+-----+----");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 2 || j == 5) {
                    System.out.print(board[i][j] + " | ");
                } else {
                    System.out.print(board[i][j]);
                }
            }
            System.out.print("\n");
        }
    }

    static int[][] readBoard(String filename) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
        } catch (IOException e) {
            return null;
        }
        int[][] board = new int[9][9];
        int val = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    val = Integer.parseInt(Character.toString(lines.get(i).charAt(j)));
                } catch (Exception e) {
                    val = 0;
                }
                board[i][j] = val;
            }
        }
        return board;
    }

    static int[][] solve(int[][] board) {
        if (reject(board)) return null;
        if (isFullSolution(board)) return board;
        int pos[] = new int[2];
        int[][] attempt = extend(board, pos);
        while (attempt != null) {
            int[][] solution = solve(attempt);
            if (solution != null) return solution;
            attempt = next(attempt, pos);
        }
        return null;
    }

    public static void main(String[] args) {
        if (args[0].equals("-t")) {
            testIsFullSolution();
            testReject();
            testExtend();
            testNext();
        } else {
            int[][] board = readBoard(args[0]);
            printBoard(board);
            System.out.println("Solution:");
            printBoard(solve(board));
        }
    }
}
