package application;

public class MartyrL implements Comparable<MartyrL> {// martyrl class that contains the location and 2 avls tree and
														// stack.

	private String location;
	AVL1 list_Martry;
	AVL2 list_Martry2;
	Stack s;

	public MartyrL(String location) {

		this.location = location;
		list_Martry = new AVL1();
		list_Martry2 = new AVL2();
		this.s = new Stack();

	}

	public MartyrL() {

		this.location = location;
		list_Martry = new AVL1();
		list_Martry2 = new AVL2();
		this.s = new Stack();

	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "******************************************MartyrL [location=" + location
				+ "]*********************************************\n" + list_Martry.getAllMartyr() + "\n"
				+ list_Martry2.getAllMartyr() + "\n";
	}

	@Override
	public int compareTo(MartyrL o) {
		if (o == null) {
			return 1;
		}
		return this.location.compareTo(o.location);
	}

}
