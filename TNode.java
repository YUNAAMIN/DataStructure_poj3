package application;

public class TNode {

	private MartyrD data;
	private TNode left;
	private TNode right;
	private int height;

	TNode(MartyrD data) {

		this.data = data;
		this.left = null;
		this.right = null;

	}

	TNode(MartyrD data, TNode left, TNode right) {

		this.data = data;
		this.left = null;
		this.right = null;

	}

	public MartyrD getData() {
		return data;
	}

	public void setData(MartyrD data) {
		this.data = data;
	}

	public TNode getLeft() {
		return left;
	}

	public void setLeft(TNode left) {
		this.left = left;
	}

	public TNode getRight() {
		return right;
	}

	public void setRight(TNode right) {
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
