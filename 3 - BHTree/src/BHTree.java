
public class BHTree {
	private Body body;
	private Quad quad;
	public BHTree NW_T, NE_T, SW_T, SE_T;
	
	public BHTree(Quad q){
		quad = q;
		body = null; // is there a need to null the variables?
		NW_T = null;
		NE_T = null;
		SW_T = null;
		SE_T = null;
	}

	public void insertBody (Body item) {
		if(this.quad.contains(item.X, item.Y)) {		
			if(this.body == null) {this.body = item;}
			else { 
				if (this.NW_T == null) { // no subtrees
					this.createSubTrees();
					// check each subtree.quad insert  - can be done simpler by assigning body to the quad`s body
					if(this.NW_T.quad.contains(item.X, item.Y)) {this.NW_T.insertBody(item);}
					else if(this.NE_T.quad.contains(item.X, item.Y)) {this.NE_T.insertBody(item);}
					else if(this.SW_T.quad.contains(item.X, item.Y)) {this.SW_T.insertBody(item);}
					else if(this.SE_T.quad.contains(item.X, item.Y)) {this.SE_T.insertBody(item);}
				} 
				else {
					Body temp = this.body;
					this.body = this.body.centerMass(item);
					// find proper quad for this.body; 
						if(this.NW_T.quad.contains(temp.X, temp.Y)) {this.NW_T.insertBody(temp);}
						else if(this.NE_T.quad.contains(temp.X, temp.Y)) {this.NE_T.insertBody(temp);}
						else if(this.SW_T.quad.contains(temp.X, temp.Y)) {this.SW_T.insertBody(temp);}
						else if(this.SE_T.quad.contains(temp.X, temp.Y)) {this.SE_T.insertBody(temp);}
						
					this.insertBody(item); // close loop, insert item
						
					} 
				
			}
		} else {
			StdOut.println("Body is outside of BHTree Universe. Body ignored");
		}
		
		}
			
	
	
	public void createSubTrees () {
		this.NW_T = new BHTree(quad.NW_Q());
		this.NE_T = new BHTree(quad.NE_Q());
		this.SW_T = new BHTree(quad.SW_Q());
		this.SE_T = new BHTree(quad.SE_Q());
	}

	
	
	
	// constructors NW_Q, NE_Q, SW_Q, SE_Q
	
	
	
}
