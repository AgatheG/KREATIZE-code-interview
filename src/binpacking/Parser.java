package binpacking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**.
 *
 * @author Agathe
 *
 */
public class Parser {
	/**.
	 * problems - a list of the optimization problems
	 */
	private ArrayList<Problem> problems;
	/**.
	 * parser that translates the data from .csv files into a list of Problem 
	 * @param filename - name of the file containing several problem instances
	 */
	public Parser(String filename){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			int machineX, machineY, machineZ, partX, partY, partZ; // dimensions of (respectively) the machine and part bound box
			String line = reader.readLine();
			this.problems = new ArrayList<Problem>();

			do {
				machineX = Integer.parseInt(line.split(",")[0]);
				machineY = Integer.parseInt(line.split(",")[1]);
				machineZ = Integer.parseInt(line.split(",")[2]);

				line = reader.readLine();
				partX = Integer.parseInt(line.split(",")[0]);
				partY = Integer.parseInt(line.split(",")[1]);
				partZ = Integer.parseInt(line.split(",")[2]);

				this.problems.add(new Problem(new int[]{machineX, machineY, machineZ}, new int[]{partX, partY, partZ}));
				line = reader.readLine();
			} while (line != null);

			reader.close();
		} catch (IOException e) {
			System.out.println("The file could not be opened");
		}
	}

	/**.
	 * solve the problems of the instance
	 */
	public void solve() {
		System.out.println(this.problems.size() + " problems have been solved");
		System.out.println();
		int count = 1; 
		for (Problem pb : this.problems) {
			// the bin does not fit 
			if (pb.solve() == 0) { 
				System.out.println("Problem " + count +  " : " + "no part " + "(length : " + pb.getPart()[0] + ", width : "+ pb.getPart()[1] + ", height : " + pb.getPart()[2] + ") can be fitted in the machine (length : " + pb.getS().getX() + ", width : " + pb.getS().getY() + ", height : " + pb.getS().getZ() +")" );
			}
			// otherwise
			else {
			System.out.println("Problem " + count + " : " + pb.solve() + " parts " + "(length : " + pb.getPart()[0] + ", width : "+ pb.getPart()[1] + ", height : " + pb.getPart()[2] + ") can be fitted in the machine (length : " + pb.getS().getX() + ", width : " + pb.getS().getY() + ", height : " + pb.getS().getZ() +")" );
			}
			System.out.println();
			count ++;
		}
	}
	
	public static void main(String[] args) {
		Parser p = new Parser("data.csv");
		p.solve();
	}
}
