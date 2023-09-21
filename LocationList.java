package application;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LocationList {// doubly circuler linked list that contains the locations;

	private MartyrL data;
	private Node head, tail;
	private int s;

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	}

	public LocationList() {
		Node d = new Node(null);
		head = d;
	}

	public int gets() {
		return s;
	}

	public LocationList(MartyrL name) {
		this.head = null;
		this.s = 0;
	}

	public MartyrL getName() {
		return data;
	}

	public void setName(MartyrL name) {
		this.data = name;
	}

	public void printList() {
		Node currentNode;
		currentNode = head;
		while (currentNode != null) {
			System.out.print(currentNode.data + " ");
			currentNode = currentNode.next;
		}
		System.out.println();
	}

	public String getDataAsString() {
		Node curr;
		StringBuilder data;
		curr = head;
		data = new StringBuilder();

		try {
			while (curr != null) {
				data.append(curr.getData()).append(" ");
				curr = curr.getNext();
			}
			data.append("\n");
		} catch (NullPointerException ex) {
			data = new StringBuilder();
		}

		return data.toString();
	}

	public void removeAll() {
		head = null;
		tail = null;
		s = 0;
	}

	public void removeNode(MartyrL data) {
		if (head == null) {
			return;
		}

		Node current = head;
		while (current != null && !current.data.equals(data)) {
			current = current.next;
			if (current == head) {
				break;
			}
		}

		if (current == null) {
			return;
		}

		if (current == head && s == 1) {
			head = null;
		} else {
			current.prev.next = current.next;
			current.next.prev = current.prev;
			if (current == head) {
				head = current.next;
			}
		}

		s--;
	}

	public void insertSorted1(MartyrL data) {
		Node node;
		Node prev;
		Node curr;
		node = new Node(data);

		if (head.getData() == null) {
			System.out.println("test");
			head = node;
			tail = node;
		} else if (data.compareTo(head.getData()) < 0) {
			node.next = head;
			head.prev = node;
			head = node;

		} else if (data.compareTo(tail.getData()) >= 0) {
			tail.next = node;
			node.prev = tail;
			tail = node;
		} else {
			curr = head.next;

			while (curr != null && curr.data.compareTo(data) < 0) {
				curr = curr.next;
			}
			prev = curr.prev;
			prev.next = node;
			node.prev = prev;
			node.next = curr;
			curr.prev = node;

		}

	}

	public Node search(String city) {
		Node node;
		node = head;
		while (node != null) {
			if (node.data != null && node.data.getLocation().equalsIgnoreCase(city)) {
				return node;
			}
			node = node.next;
		}
		return null;

	}

	public MartyrD searchName(String name) {
		Node node;
		TNode tree;
		node = head;
		while (node != null) {
			tree = node.data.list_Martry.getRoot();
			while (tree != null) {
				if (tree.getData().getName().equalsIgnoreCase(name)) {
					return tree.getData();
				}
				tree = tree.getLeft();
			}
			node = node.next;

		}
		return null;

	}

	public void removeNode(Node node) {
		if (node == null) {
			return;
		}

		if (node == head) {
			head = node.next;
		}

		if (node == tail) {
			tail = node.prev;
		}

		if (node.prev != null) {
			node.prev.next = node.next;
		}

		if (node.next != null) {
			node.next.prev = node.prev;
		}

		node.prev = null;
		node.next = null;
		s--;
	}

	public void insertNode(MartyrL martyr) {
		Node node;
		node = new Node(martyr);
		if (head != null) {
			node.prev = tail;
			tail.next = node;
			tail = node;

		} else {
			head = node;
			tail = node;
		}
		s++;
	}

	public void writeToFile(String filePath) {
		Node curr;
		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
			if (head != null) {
				curr = head;
				do {
					writer.println(curr.data.toString());
					curr = curr.next;
				} while (curr != head);
			}

			System.out.println("Data written successfully!!!yahoooo!!!.");
		} catch (Exception e) {
			System.out.println("An error occurred in  file: " + e.getMessage());
		}
	}

}
