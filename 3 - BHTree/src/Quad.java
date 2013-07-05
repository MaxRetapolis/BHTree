// test
public class Quad {
	private double x_cen, y_cen, quad_length;
	
	public Quad (double x_cen, double y_cen, double quad_length){
		this.x_cen = x_cen;
		this.y_cen = y_cen;
		this.quad_length = quad_length;
	}
		
	public double length() { return quad_length; }
	
	public Quad NW_Q () { // will variables be accessible?
		return new Quad(x_cen - quad_length / 4, y_cen - quad_length / 4, quad_length / 2);
	}
	
	public Quad NE_Q () {
		return new Quad(x_cen + quad_length / 4, y_cen - quad_length / 4, quad_length / 2);
	}
	
	public Quad SW_Q () {
		return new Quad(x_cen - quad_length / 4, y_cen + quad_length / 4, quad_length / 2);
	}
	
	public Quad SE_Q () {
		return new Quad(x_cen + quad_length / 4, y_cen + quad_length / 4, quad_length / 2);
	}
	
	public boolean contains(double X, double Y) {
		if(X >= x_cen - quad_length/2 && X < x_cen + quad_length/2 && 
		   Y >= y_cen - quad_length/2 && Y < x_cen + quad_length/2) {
				return true;}
			else {
				return false;}
	} // problem with right and bottom corners - they are excluded!

	public void draw(){
		StdDraw.square(x_cen, y_cen, quad_length/2);
	}

}

// extra line