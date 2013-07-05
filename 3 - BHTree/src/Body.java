
public class Body {
	public double X, Y, Vx, Vy, Mass;
	// public double OldX, OldY, OldVx, OldVy,
	public double ax, ay, Fx, Fy; 
	String picture;
	public int planet_number;
	
	// Body Constructor
	public Body(double X, double Y, double Vx, double Vy, double Mass) { 
        this.X = X;
        this.Y = Y;
        this.Vx = Vx;
        this.Vy = Vy;
        this.Mass = Mass;
    }
	
	public void initBody() {
		ax = 0;
		ay = 0;
		Fx = 0;
		Fy = 0;
		} 
	
	// method addForce - called as a.addForce(b, G);
	public void addForce(Body item, double G) {
		double deltaX = this.X - item.X;  
		double deltaY = this.Y - item.Y;  
		double r = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
		double F = (G * this.Mass * item.Mass) / Math.pow(r, 2);
		this.Fx += (F * deltaX)/r;
		this.Fy += (F * deltaY)/r;
	}
	
	/* public double distanceTo(Body item){
		double deltaX = this.X - item.X;  // not too effective, double calc
		double deltaY = this.Y - item.Y;  // not too effective, double calc
		return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
	}
	*/
	
	public void updateBody(double deltaT) {
		ax = Fx / Mass;
		ay = Fy / Mass;
		Vx = Vx + ax * deltaT;
		Vy = Vy + ay * deltaT;		
		X = X + Vx * deltaT;
		Y = Y + Vy * deltaT;
	}
	
	// draw the invoking Body in Turtle graphics
	public void draw() {
	        // StdDraw.setPenColor(color); do I need different colors?
	        StdDraw.point(X, Y);
	}	
	
	public boolean in(Quad quad) {
		if(quad.contains(X, Y))
		     return true;
		     else return false;
	}

	// method centerMass - returns body with mass/ coordinates
		public Body centerMass(Body item) {
		double newX, newY, newMass;
		
		newMass = this.Mass + item.Mass;
		newX = (this.X + item.X) / 2;
		newY = (this.Y + item.Y) / 2;
		return new Body(newX, newY, 0, 0, newMass); // no velocity
	}
}
