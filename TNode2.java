package application;

public class TNode2 {

	private Datestack data;
	private TNode2 left;
	private TNode2 right;
	private int height;

	TNode2(Datestack data) {
		this.data = data;
		this.left = null;
		this.right = null;

	}
	TNode2(Datestack data, TNode2 left, TNode2 right) {

		this.data = data;
		this.left = null;
		this.right = null;

	}

	public Datestack getData() {
		return data;
	}

	public void setData(Datestack data) {
		this.data = data;
	}

	public TNode2 getLeft() {
		return left;
	}

	public void setLeft(TNode2 left) {
		this.left = left;
	}

	public TNode2 getRight() {
		return right;
	}

	public void setRight(TNode2 right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return data + " " + left + " " + right + " --->>data/left/right";
	}

}
