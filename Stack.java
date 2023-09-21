package application;

import java.util.NoSuchElementException;

class Stack {// this is the stack class that contains
	// the basic three methods pop push peek
	// and other method that may be usiful
	private NodeDt top;
	private int size;

	public void setTop(NodeDt top) {
		this.top = top;
	}

	public NodeDt getTop() {
		return top;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Stack() {
		top = null;
		size = 0;
	}

	public void push(MartyrD MartyrD) {
		NodeDt newNode;
		newNode = new NodeDt(MartyrD);
		newNode.setNext(top);
		top = newNode;
		size++;
	}

	public MartyrD pop() {
		MartyrD item;
		if (isEmpty()) {
			throw new IllegalStateException("Stack is empty");
		}
		item = top.getData();
		top = top.getNext();
		size--;
		return item;
	}

	public MartyrD peek() {
		if (isEmpty()) {
			throw new IllegalStateException("Stack is empty!!!!!");
		}
		return top.getData();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int getSize() {
		return size;
	}

	public void clear() {
		top = null;
		size = 0;
	}

	public MartyrD get(int i) throws Exception {
		NodeDt current;
		int curIndex;
		if (i >= size || i < 0) {
			throw new IndexOutOfBoundsException("Invalid index check outttt!!!: " + i);
		}
		current = top;
		curIndex = 0;

		while (current != null) {
			if (curIndex == i) {
				return current.getData();
			}
			current = current.getNext();
			curIndex++;
		}

		throw new Exception("ERRRRROOOR!!!!!!!!!!: " + i);
	}

	public void print() {
		Stack temp;
		MartyrD item;
		temp = new Stack();

		if (isEmpty()) {
			System.out.println("The stack is empty!!!!!!");
		} else {
			while (!isEmpty()) {
				item = pop();
				temp.push(item);
				System.out.println(item);
			}

			while (!temp.isEmpty()) {
				push(temp.pop());
			}
		}

	}

	public NodeDt remove(MartyrD item) {

		NodeDt prev = null;
		NodeDt curr = top;
		while (curr != null) {
			if (curr.getData().equals(item)) {
				if (prev == null) {
					top = curr.getNext();

				} else {
					prev.setNext(curr.getNext());
				}
				return curr;
			}
			prev = curr;
			curr = curr.getNext();
		}
		return null;

	}

	@Override
	public String toString() {
		NodeDt curr;
		StringBuilder sb;
		sb = new StringBuilder();
		curr = top;

		while (curr != null) {
			sb.append(curr.getData()).append(", ");
			curr = curr.getNext();
		}

		if (sb.length() > 0) {
			sb.setLength(sb.length() - 2);
		}

		return "_Stack :[" + sb.toString() + "]";
	}

}
