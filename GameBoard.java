import java.util.Random;

public class GameBoard {

	Random r= new Random();
	int size;
	DirectedNode[][] board;
	DirectedNode start,end;
	
	public GameBoard(int size){
		this.size=size;
		board = new DirectedNode[size][size];
		initializeBoard();
		populateBoard();
	}
	
	/*
	 * Initially this method will create a hamiltonian walk starting at the top of the array, moving
	 * right in a zig zag pattern as such :
	 * 
	 * 
	 *            1 -- 2 -- 3 -- 4
	 *                           |
	 *            8 -- 7 -- 6 -- 5
	 * 			  |
	 * 			  9 -- 10-- 11-- 12   and so on...
	 * 
	 * The contents will remain null, and a populate board function will fill them, hence we can test 
	 * variants of the board and print it as we go.
	 */
	private void initializeBoard(){
		boolean forward = true;
		DirectedNode previous = null;
		int NodeCount = 0;
		for(int rows=0;rows<size;rows++){
			for(int cols=0;cols<size;cols++){
				board[rows][cols] = new DirectedNode();
				board[rows][cols].setX(rows);
				board[rows][cols].setY(cols);
			}
		}
		board[0][0].setStart();
		start = board[0][0];
		for(int rows=0;rows<size;rows++){
			for(int cols=0;cols<size;cols++){
				//setting forward
				if(forward){
					//as long as we aren't at the end or the edge
					if(cols<size-1){
						board[rows][cols].setNext(board[rows][cols+1]);
						board[rows][cols].setPrevious(previous);
						NodeCount++;
						
					}
					//now we are at the edge check to make sure we aren't at the bottom too
					else if(rows<size-1){
						board[rows][cols].setNext(board[rows+1][cols]);
						board[rows][cols].setPrevious(previous);
						NodeCount++;
						forward = false;
					}
					//last case is we are at an edge and the bottom, the last node
					else{
						board[rows][cols].setPrevious(previous);
						board[rows][cols].setEnd();
						NodeCount++;
						end = board[rows][cols];
					}
					previous = board[rows][cols];
				}
				//going other way through the columns
				else{
					if(cols<size-1){
						board[rows][size-1-cols].setNext(board[rows][size-cols-2]);
						board[rows][size-1-cols].setPrevious(previous);
						NodeCount++;
					}
					//now we are at the edge check to make sure we aren't at the bottom too
					else if(rows<size-1){
						board[rows][size-1-cols].setNext(board[rows+1][size-1-cols]);
						board[rows][size-1-cols].setPrevious(previous);
						NodeCount++;
						forward = true;
					}
					//last case is we are at an edge and the bottom, the last node
					else{
						board[rows][size-1-cols].setPrevious(previous);
						board[rows][size-1-cols].setEnd();
						NodeCount++;
						end = board[rows][cols];
					}
					previous = board[rows][size-1-cols];
				}
			}
		}
	}
	
	/*
	 * This will make the board random, while maintaining the hamiltonian walk.
	 * It is the heart of this thing and needs some refactoring and simplifications for surrrrre.
	 * Each time it's run, it will do one cut.
	 */
	public void randomizeBoard(){
		DirectedNode index;
		DirectedNode swapPoint;
		DirectedNode newEndPoint;
		boolean last;
		boolean cut = false;
		//chose start node or end node (1 for start 0 for 
		int rand = r.nextInt(2);
		if (rand == 1){
			index = start;
			last = false;
		}
		else{
			index = end;
			last = true;
		}
		// pick a direction to move in the grid
		// 0 = up
		// 1 = down
		// 2 = left
		// 3 = right
		while (!cut){
		rand = r.nextInt(4);
			switch(rand){
			
			case 0:
				//check boundries
				if(index.getX()>0){
					//check if not connected to the above node
					if ( (index.previous != board[index.getX()-1][index.getY()]) &&( index.next !=board[index.getX()-1][index.getY()])){
						cut = true;
						//did we come from beg or end
						if (last){
							swapForward(index, board[index.getX()-1][index.getY()].getNext(),board[index.getX()-1][index.getY()] );	
						}
						else{
							swapBackward(index, board[index.getX()-1][index.getY()].getPrevious(), board[index.getX()-1][index.getY()]);
						}
					}
				}
				break;
			case 1:
				//check boundries
				if(index.getX()<size-1){
					//check if not connected to the above node
					if ( (index.previous != board[index.getX()+1][index.getY()]) &&( index.next !=board[index.getX()+1][index.getY()])){
						cut = true;
						//did we come from beg or end
						if (last){
							swapForward(index, board[index.getX()+1][index.getY()].getNext(),board[index.getX()+1][index.getY()] );	
						}
						else{
							swapBackward(index, board[index.getX()+1][index.getY()].getPrevious(), board[index.getX()+1][index.getY()]);
						}
					}
				}
				break;
			case 2:
				//check boundries
				if(index.getY()>0){
					//check if not connected to the above node
					if ( (index.previous != board[index.getX()][index.getY()-1]) &&( index.next !=board[index.getX()][index.getY()-1])){
						cut = true;
						//did we come from beg or end
						if (last){
							swapForward(index, board[index.getX()][index.getY()-1].getNext(),board[index.getX()][index.getY()-1] );	
						}
						else{
							swapBackward(index, board[index.getX()][index.getY()-1].getPrevious(), board[index.getX()][index.getY()-1]);
						}
					}
				}
				break;
			case 3:
				//check boundries
				if(index.getY()<size-1){
					//check if not connected to the above node
					if ( (index.previous != board[index.getX()][index.getY()+1]) &&( index.next !=board[index.getX()][index.getY()+1])){
						cut = true;
						//did we come from beg or end
						if (last){
							swapForward(index, board[index.getX()][index.getY()+1].getNext(),board[index.getX()][index.getY()+1] );	
						}
						else{
							swapBackward(index, board[index.getX()][index.getY()+1].getPrevious(), board[index.getX()][index.getY()+1]);
						}
					}
				}
				break;	
			}
		}
	
	}
	
	public void swapForward(DirectedNode index, DirectedNode newEndPoint, DirectedNode swapPoint){
		newEndPoint.setEnd();
		newEndPoint.setPrevious(newEndPoint.getNext());
		newEndPoint.setNext(null);
		swapPoint.setNext(index);
		while (index != newEndPoint){
			index.setNext(index.getPrevious());
			index.setPrevious(swapPoint);
			swapPoint = index;
			index = index.getNext();
		}
		end = newEndPoint;
	}
	
	public void swapBackward(DirectedNode index, DirectedNode newEndPoint, DirectedNode swapPoint){
		newEndPoint = swapPoint.getPrevious();
		newEndPoint.setStart();
		newEndPoint.setNext(newEndPoint.getPrevious());
		newEndPoint.setPrevious(null);
		
		swapPoint.setPrevious(index);
		while (index != newEndPoint){
			index.setPrevious(index.getNext());
			index.setNext(swapPoint);
			swapPoint = index;
			index = index.getPrevious();
		}
		start = newEndPoint;
	}
	
	public void populateBoard(){
		DirectedNode index = start;
		int count = 1;
		
		do{
			index.setNumber(count);
			count++;
			index=index.getNext();
		}while (index.getNext()!=null);
		//for the last node
		
		index.setNumber(count);
	}
	
	public void printBoard(){
		for (int r=0; r<size;r++){
			for(int c=0;c<size;c++){
				System.out.print(" " + board[r][c].getNumber() + "| \t");
			}
			System.out.print("\n");
		}
	}
	
	
}
