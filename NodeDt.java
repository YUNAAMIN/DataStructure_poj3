package application;

public class NodeDt {// this node is especially for the stack

	private MartyrD data;
	private NodeDt next;

	public NodeDt(MartyrD order) {
		this.data = order;
		this.next = null;
	}

	public NodeDt() {
		this.data = data;
		this.next = null;
	}

	public MartyrD getData() {
		return data;
	}

	public void setData(MartyrD data) {
		this.data = data;
	}

	public NodeDt getNext() {
		return next;
	}

	public void setNext(NodeDt next) {
		this.next = next;
	}

}
