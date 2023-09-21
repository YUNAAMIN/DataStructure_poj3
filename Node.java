package application;

class Node {

	public MartyrL data;
	Node prev;
	Node next;

	public Node(MartyrL data) {
		this.data = data;

	}

	public MartyrL getData() {
		return data;
	}

	public void setData(MartyrL data) {
		this.data = data;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;

	}

}
