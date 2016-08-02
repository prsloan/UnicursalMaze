
public class NumberGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameBoard theBoard;
		theBoard = new GameBoard(9);
		System.out.println("Testing board initialization : ");
		theBoard.printBoard();
		
		for (int i = 0; i<10000; i++){
			System.out.println(" Test # " + i);
			theBoard.randomizeBoard();
			theBoard.populateBoard();
			theBoard.printBoard();
		}
	}

}
