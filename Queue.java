package application;

public class Queue {// this is the queue class that contains in basic three methods enqueue,dequeue
					// and front or peek and other methods that maybe useful ands its depends on the
					// MartyrDs class.

	private TNode front;
	private TNode rear;
	private int size;

	public Queue() {
		front = rear = null;
		size = 0;
	}

	public void enqueue(MartyrD MartyrD) {// like add first method in the linked
		// list.
		TNode newNode;
		newNode = new TNode(MartyrD);
		if (isEmpty()) {
			front = newNode;
			rear = newNode;
		} else {
			rear.setRight(newNode);
			rear = newNode;
		}
		size++;

	}

	public MartyrD denqueue() {// remove first so its like fifo
		MartyrD item;
		if (isEmpty()) {

			throw new IllegalStateException("empty");
		}
		item = front.getData();
		front = front.getRight();

		if (front == null) {
			rear = null;
		}
		size--;
		return item;
	}

	public TNode getfrontt() {
		return front;
	}

	public void setfrontt(TNode front) {
		this.front = front;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isEmpty() {
		return size == 0;

	}

	public TNode getfront() {
		if (isEmpty())
			throw new IllegalStateException("empty");
		else
			return front;

	}

	public void print() {
		Queue temp;

		temp = new Queue();
		if (isEmpty())
			System.out.println(" the queue is empty");
		else
			while (!this.isEmpty()) {
				MartyrD rem = this.denqueue();
				temp.enqueue(rem);
			}
		while (!temp.isEmpty()) {
			MartyrD rem = temp.denqueue();
			this.enqueue(rem);
			System.out.println(rem);

		}

	}

	public void clearQueue() {
		this.front = null;
		this.rear = null;
	}

	public TNode getFront() {
		return front;
	}

	public void setFront(TNode front) {
		this.front = front;
	}

	public TNode getRear() {
		return rear;
	}

	public void setRear(TNode rear) {
		this.rear = rear;
	}

}