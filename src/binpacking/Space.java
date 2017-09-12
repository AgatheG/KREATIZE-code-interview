package binpacking;

import java.util.ArrayList;
/**.
 *
 * @author Agathe
 *
 */
public class Space {
	/**.
	 * dimensions - a three-dimensional vector of integers, representing the length, width, height of the space
	 */
	private int[] dimensions;
	/**.
	 *
	 * @param dim - a three-dimensional vector of integers, representing respectively the length, width, height of the space
	 */
	public Space(final int[] dim) {
		this.dimensions = dim;
	}
	/**.
	 *
	 * @return the length of the space
	 */
	public int getX() {
		return this.dimensions[0];
	}
	/**.
	 *
	 * @return the width of the space
	 */
	public int getY() {
		return this.dimensions[1];
	}
	/**.
	 *
	 * @return the height of the space
	 */
	public int getZ() {
		return this.dimensions[2];
	}
	/**.
	 *
	 * @param rot - a three dimensional vector of integers representing another volume
	 * @return true if rot can fit into the volume, false otherwise
	 */
	public boolean fit(final int[] rot) {
		if ((rot[0] <= this.getX()) && (rot[1] <= this.getY()) && (rot[2] <= this.getZ())) {
			return true;
		}
		return false;
	}
	/**.
	 *
	 * @param rotations - a list of all of the six possible rotations (each rotation being a vector of three integers) of the part bound box
	 * @return the rotation that allows to fit the greatest number of part bound box, an array of three zeros if the box cannot fit in the space 
	 */
	public int[] bestFit(ArrayList<int[]> rotations) {
		int[] bF = rotations.get(0);
		if (!this.fit(bF)) {
			return new int[3];
		}
		double criteria = bF[0] / (double) this.getX() + bF[1] / (double) this.getY() + bF[2] / (double) this.getZ();

		for (int[] rot : rotations) {
			double criteriaTemp = rot[0] / (double) this.getX() + rot[1] / (double) this.getY() + rot[2] / (double) this.getZ();
			if ((this.fit(rot)) && (criteriaTemp < criteria)) {
				bF = rot;
				criteria = criteriaTemp;
			}
		}
		return bF;
	}
}
