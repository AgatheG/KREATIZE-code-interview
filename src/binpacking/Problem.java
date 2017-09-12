package binpacking;

import java.util.ArrayList;
/**.
 *
 * @author Agathe
 *
 */
public class Problem {
	/**.
	 * the space contained within the machine bound box
	 */
	private Space machine;
	/**.
	 * a list of the six possible rotations for the part bound box
	 * each rotation is a three-dimensional vector of integers
	 *
	 */
	private ArrayList<int[]> rotations;

	/**.
	 *
	 * @param machine - a three-dimensional vector representing the three dimensions of the machine bound box
	 * @param part - a three-dimensional vector representing the three dimensions of the part bound box
	 */
	public Problem(final int[] machine, final int[] part) {
		this.machine = new Space (machine);
		this.rotations = new ArrayList<int[]>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i != j) {
					this.rotations.add(new int[]{part[i], part[j], part[3 - j - i]});
				}
			}
		}
	}

	/**.
	 *
	 * @return the space contained within the machine bound box
	 */
	public Space getS() {
		return this.machine;
	}
	/**.
	 *
	 * @return a three-dimensional vector representing the part bound box
	 */
	public int[] getPart() {
		return this.rotations.get(0);
	}
	/**.
	 * solve the problem
	 * @return the number of parts that can be fitted
	 */
	public int solve() {
		int nb = 0;
		ArrayList<Space> list = new ArrayList<Space>();
		list.add(this.machine);
		while (!list.isEmpty()) {
			Space sp = list.remove(0);
			int[] bestFit = sp.bestFit(this.rotations);

			if (bestFit[0] > 0) {
				nb += (sp.getX() / bestFit[0]) * (sp.getY() / bestFit[1]) * (sp.getZ() / bestFit[2]);

				if (sp.getX() % bestFit[0] != 0) {
					list.add(new Space(new int[] {sp.getX() % bestFit[0], sp.getY(), sp.getZ()}));
				}
				if (sp.getX() % bestFit[1] != 0) {
					list.add(new Space(new int[] {sp.getX() / bestFit[0], sp.getY() % bestFit[1], sp.getZ()}));
				}
				if (sp.getX() % bestFit[2] != 0) {
					list.add(new Space(new int[] {sp.getX() / bestFit[0], sp.getY() / bestFit[1], sp.getZ() % bestFit[2]}));
				}
			}
		}
		return nb;
	}

}
