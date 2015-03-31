package algo;

public class Point {
	public long x,y;
	
	public Point() {
		x = y = 0;
	}
	
	public Point(long x, long y){
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return x + " " + y;
	}
}
