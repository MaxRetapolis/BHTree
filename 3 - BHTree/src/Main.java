import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static double T; // max time
	static double deltaT; // time step 
	static int Num_Planets; // Number of Planets
	static double Radius_Universe; // Radius of Universe (drawing scaling)
	static double G; // Constant G

	public static void main(String[] args)throws InterruptedException {

				// Constant
				G = 6.67E-11;
				String workingDir = "C:\\Users\\Big\\Dropbox\\workspace\\Planets\\nbody\\";
				String filename = "2001.mid";
				
				/* Reads two double command-line arguments T and Δt.
				Reads in the universe from standard input using StdIn, using several parallel arrays to store the data.
				Simulates the universe, starting at time t = 0.0, and continuing as long as t < T, using the leapfrog scheme described below.
				Animates the results to standard drawing using StdDraw.
				Prints the state of the universe at the end of the simulation (in the same format as the input file) to standard output using StdOut. 
				*/
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				try {
					T = Double.parseDouble(br.readLine());
					deltaT = Double.parseDouble(br.readLine());
				} catch (IOException e1) {
					e1.printStackTrace();
				} 
				
				// T = 157788000.0;
				//deltaT = 25000.0;
				
				// Read setup from planets.txt 
				try {
					FileInputStream is;
					is = new FileInputStream(new File(workingDir + "planets-elliptical.txt"));
					System.setIn(is);
				} catch (FileNotFoundException e) {
					StdOut.println("file could not be open");
					e.printStackTrace();
				}
				
				Num_Planets = (int)(StdIn.readDouble());
				Radius_Universe = StdIn.readDouble();
				StdDraw.picture(0, 0, workingDir + "starfield.jpg");
				
				Bag<Body> bag = new Bag<Body>(); // Create a bag of planets
				for(int i=1; i<=Num_Planets; i++) {
					// Create a bag of planets
					while (!StdIn.isEmpty()) { // Fill bag from STDin < planets.txt
						double X = StdIn.readDouble();
						double Y = StdIn.readDouble();
						double Vx = StdIn.readDouble();
						double Vy = StdIn.readDouble();
						double Mass = StdIn.readDouble();		
						Body item = new Body (X, Y, Vx, Vy, Mass);	
						item.picture = StdIn.readString();
						item.planet_number = i;
						bag.add(item);		
						break;
					}
				}
					StdAudio.play(workingDir + filename);
				
				
					for(double t=0; t<=T; t += deltaT) { // Time cycle
						// Double loop 
						for (Body each : bag) {
							each.initBody(); 
						}
						
						// rewrite method to support a.addForce etc
						
						// !!!!!!!!!!!!
						for (Body first : bag) {
							// StdOut.println("Pair 1 Planet Number = " + x.planet_number);					
							for (Body second : bag) {
								if (first.planet_number >= second.planet_number){
									break;
								}

								// calculate basic stuff shared by both Bodys
								double deltaX = first.X - second.X;  
								double deltaY = first.Y - second.Y;
								double r = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
								double F = (G * first.Mass * second.Mass) / Math.pow(r, 2);
													
								// calculate Body specific stuff - can be exported into a method later
								first.Fx -= (F * deltaX)/r;
								first.Fy -= (F * deltaY)/r;
								second.Fx += (F * deltaX)/r;
								second.Fy += (F * deltaY)/r;
							}
						} // end of pairwise force calculation loop
						
						for (Body item : bag) {
							item.updateBody(deltaT);
									
							
							// Drawing loop
							// StdDraw.clear();					
							StdDraw.setXscale(-Radius_Universe, Radius_Universe);
							StdDraw.setYscale(-Radius_Universe, Radius_Universe);

							StdDraw.picture(0, 0, workingDir + "starfield.jpg");
							StdDraw.picture(each.X, each.Y, workingDir + each.picture);
							StdDraw.show(2);	
						} // End of Body processing loop
						}
				}
	
	// Drawing loop - Turtle library...
	
}
