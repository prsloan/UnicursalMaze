
public class DirectedNode {
	
	int number; //each node will be initialized to 0, to be filled later
	DirectedNode next;
	DirectedNode previous;
	boolean isStart;
	boolean isEnd;
	int x, y;
	
	
	public DirectedNode(){
		next = null;
		previous = null;
		this.number=0;
		isStart=false;
		isEnd=false;
	}
	
	
	
	public DirectedNode(DirectedNode next, DirectedNode previous){
		this.next = next;
		this.previous = previous;
		this.number=0;
		isStart=false;
		isEnd=false;
	}
	
	/*
	 * Need to check if it is the last, or else null pointer exception perhaps
	 */
	public DirectedNode getNext(){
		return next;
	}
	
	/*
	 * Need to check if it is the first, or else null pointer exception perhaps
	 */
	public DirectedNode getPrevious(){
		return previous;
		
	}
	
	public void setStart(){
		isStart=true;
	}

	public void setEnd(){
		isEnd=true;
	}
	
	public void setNext(DirectedNode next){
		this.next=next;
	}
	
	public void setPrevious(DirectedNode previous){
		this.previous=previous;
	}
	
	public void setNumber(int num){
		number=num;
	}
	
	public int getNumber(){
		return number;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
