package application;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class AVL2 {// avl tree that contains the dates in its node and the date pointes on the
					// martyr info.

	private TNode2 root;
	GregorianCalendar maxDate;

	public void setRoot(TNode2 root) {
		this.root = root;
	}

	public TNode2 getRoot() {
		return root;
	}

	public void insert(Datestack data) {
		setRoot(insert((Datestack) data, getRoot()));
	}

	protected TNode2 insert(Datestack data, TNode2 n) {
		if (n == null)
			n = new TNode2(data);
		else if (n.getData().compareTo(data) < 0)
			n.setRight(insert(data, n.getRight()));
		else if (n.getData().compareTo(data) >= 0)
			n.setLeft(insert(data, n.getLeft()));

		updateHeight(n);
		return reBalance(n);
	}

	public void delete(Datestack data) {
		setRoot(delete(data, getRoot()));
	}

	protected TNode2 delete(Datestack data, TNode2 n) {
		if (n == null)
			return null;
		else if (n.getData().compareTo(data) < 0)
			n.setRight(delete(data, n.getRight()));
		else if (n.getData().compareTo(data) > 0)
			n.setLeft(delete(data, n.getLeft()));
		else if (n.getLeft() == null && n.getRight() == null) {
			n = null;
		} else if (n.getLeft() == null)
			n = n.getRight();
		else if (n.getRight() == null) {
			n = n.getLeft();
		} else
			deleteWithTwo(n);

		updateHeight(n);
		return reBalance(n);
	}

	private void deleteWithTwo(TNode2 n) {
		TNode2 successor;
		successor = findMin(n.getRight());
		n.setData(successor.getData());
		n.setRight(delete(successor.getData(), successor));
	}

	private TNode2 findMin(TNode2 n) {
		if (n == null)
			return null;
		if (n.getLeft() == null)
			return n;
		else
			return findMin(n.getLeft());
	}

	public int height() {
		if (root == null)
			return 0;
		return height(root);
	}

	private int height(TNode2 n) {
		if (n != null) {
			return n.getHeight();
		} else {
			return 0;
		}
	}

	private void updateHeight(TNode2 n) {
		int left;
		int right;
		if (n != null) {
			left = height(n.getLeft());
			right = height(n.getRight());
			n.setHeight(Math.max(left, right) + 1);
		}
	}

	private TNode2 reBalance(TNode2 n) {
		int bf;
		if (n != null) {
			bf = balanceFactor(n);

			if (bf > 1) {
				if (balanceFactor(n.getLeft()) >= 0)
					n = rotateRight(n);
				else {
					n.setLeft(rotateLeft(n.getLeft()));
					n = rotateRight(n);
				}
			}

			if (bf < -1) {
				if (balanceFactor(n.getRight()) <= 0)
					n = rotateLeft(n);
				else {
					n.setRight(rotateRight(n.getRight()));
					n = rotateLeft(n);
				}
			}
		}

		return n;
	}

	private int balanceFactor(TNode2 n) {
		return height(n.getLeft()) - height(n.getRight());
	}

	private TNode2 rotateRight(TNode2 n) {
		TNode2 x;
		x = n.getLeft();
		n.setLeft(x.getRight());
		x.setRight(n);
		updateHeight(n);
		updateHeight(x);
		return x;
	}

	private TNode2 rotateLeft(TNode2 n) {
		TNode2 x;
		if (n == null || n.getRight() == null) {
			return n;
		}

		x = n.getRight();
		n.setRight(x.getLeft());
		x.setLeft(n);
		updateHeight(n);
		updateHeight(x);
		return x;
	}

	private void appendStringToTree(StringBuilder builder, TNode2 n) {
		if (n.getLeft() != null)
			appendStringToTree(builder, n.getLeft());

		builder.append(n.getData()).append(" ");

		if (n.getRight() != null)
			appendStringToTree(builder, n.getRight());
	}

	public void removeAll() {
		root = null;
	}

	public String getAllMartyr() {
		StringBuilder strBuilder;
		strBuilder = new StringBuilder();
		getAllMartyrHelper(root, strBuilder);
		return strBuilder.toString();
	}

	private void getAllMartyrHelper(TNode2 n, StringBuilder strBuilder) {
		if (n != null) {
			getAllMartyrHelper(n.getLeft(), strBuilder);
			strBuilder.append(n.getData()).append("\n");
			getAllMartyrHelper(n.getRight(), strBuilder);
		}
	}

	public void traverseInOrderBackward(TNode2 n, StringBuilder builder) {
		if (n == null) {
			return;
		}

		traverseInOrderBackward(n.getRight(), builder);
		builder.append(n.getData()).append("\n");
		traverseInOrderBackward(n.getLeft(), builder);

	}

	public Datestack findNode(Datestack datestack) {

		return findNode(root, datestack);
	}

	private Datestack findNode(TNode2 n, Datestack datestack) {
		int cmp;
		if (n == null)
			return null;

		cmp = datestack.getDate().compareTo(n.getData().getDate());
		if (cmp < 0)
			return findNode(n.getLeft(), datestack);
		else if (cmp > 0)
			return findNode(n.getRight(), datestack);
		else
			return n.getData();
	}

	public String getMaxDate() {
		SimpleDateFormat form;
		GregorianCalendar[] maxDate = { null };
		int[] counter = { 0 };

		if (root == null) {
			return null;
		}

		maxcd(root, maxDate, counter);

		if (maxDate[0] == null) {
			return null;
		}

		form = new SimpleDateFormat("M/d/yyyy");
		return form.format(maxDate[0].getTime()) + "and the number of martyr in this location is-->>" + counter[0];
	}

	private void maxcd(TNode2 node, GregorianCalendar[] date, int[] maxCount) {
		if (node == null) {
			return;
		}

		maxcd(node.getLeft(), date, maxCount);

		int nodeCount = node.getData().getCount();
		if (nodeCount > maxCount[0]
				|| (nodeCount == maxCount[0] && (date[0] == null || node.getData().getDate().after(date[0])))) {
			maxCount[0] = nodeCount;
			date[0] = node.getData().getDate();
		}

		maxcd(node.getRight(), date, maxCount);
	}

	private void traverseInOrderBackward(TNode2 n, StringBuilder sb, int max) {
		SimpleDateFormat dateFormat;
		if (n == null) {
			return;
		}

		traverseInOrderBackward(n.getRight(), sb, max);

		if (n.getData().getCount() == max) {
			dateFormat = new SimpleDateFormat("M/d/yyyy");
			sb.append(dateFormat.format(n.getData().getDate().getTime())).append("\n");
		}

		traverseInOrderBackward(n.getLeft(), sb, max);
	}

	public void traverseInOrderBackward() {
		traverseInOrderBackward(root);
	}

	private void traverseInOrderBackward(TNode2 n) {
		if (n == null) {
			return;
		}

		traverseInOrderBackward(n.getRight());
		System.out.println(n.getData());
		traverseInOrderBackward(n.getLeft());
	}

	public TNode2 search(Datestack data) {
		return searchNode(data, root);
	}

	private TNode2 searchNode(Datestack data, TNode2 n) {
		if (n == null || data.compareTo(n.getData()) == 0) {
			return n;
		}

		if (data.compareTo(n.getData()) < 0) {
			return searchNode(data, n.getLeft());
		} else {
			return searchNode(data, n.getRight());
		}
	}

	public void traverseInOrder() {
		traverseInOrder(root);
	}

	private void traverseInOrder(TNode2 n) {
		if (n != null) {
			if (n.getLeft() != null)
				traverseInOrder(n.getLeft());
			System.out.println(n + " ");
			if (n.getRight() != null)
				traverseInOrder(n.getRight());
		}
	}

	public void print() {
		print(root);
	}

	private void print(TNode2 n) {
		if (n != null) {
			print(n.getLeft());
			System.out.println(n.getData());
			print(n.getRight());
		}
	}

	public String printBackward() {
		StringBuilder builder;
		builder = new StringBuilder();
		printBackward(root, builder);
		return builder.toString();
	}

	private void printBackward(TNode2 n, StringBuilder builder) {
		if (n != null) {
			printBackward(n.getRight(), builder);
			builder.append(n.getData()).append("\n");
			printBackward(n.getLeft(), builder);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder;
		builder = new StringBuilder();
		appendStringToTree(builder, getRoot());
		return builder.toString();
	}

}