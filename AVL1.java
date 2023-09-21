package application;

public class AVL1 {// avl tree that have the martyr info in its nodes

	private TNode root;
	private int c = 0;

	public void setRoot(TNode root) {
		this.root = root;
	}

	public TNode getRoot() {
		return root;
	}

	public int countN() {
		c = 0;
		countN(root);
		return c;
	}

	private void countN(TNode n) {
		if (n != null) {
			countN(n.getLeft());
			c++;
			countN(n.getRight());
		}
	}

	public MartyrD searchN(String name, Node lNode) {
		if (lNode == null)
			return null;

		return searchN(name, lNode.data.list_Martry.getRoot());
	}

	private MartyrD searchN(String name, TNode curr) {
		if (curr == null)
			return null;

		int comp = name.compareTo(curr.getData().getName());

		if (comp == 0)
			return curr.getData();
		else if (comp < 0)
			return searchN(name, curr.getLeft());
		else
			return searchN(name, curr.getRight());
	}

	public void deleteN(String name) {
		if (root == null) {
			return;
		}

		TNode t = sName(root, name);
		if (t == null) {
			return;
		}

		setRoot(delete(t.getData(), root));
	}

	private TNode sName(TNode node, String name) {
		if (node == null || node.getData().getName().equals(name)) {
			return node;
		}

		TNode leftResult = sName(node.getLeft(), name);
		if (leftResult != null) {
			return leftResult;
		}

		return sName(node.getRight(), name);
	}

	public TNode findNode(MartyrD data) {
		return findNode(data, getRoot());
	}

	protected TNode findNode(MartyrD data, TNode n) {
		if (n != null) {
			if (n.getData().compareTo(data) < 0)
				n = findNode(data, n.getRight());
			else if (n.getData().compareTo(data) > 0)
				n = findNode(data, n.getLeft());
		}
		return n;
	}

	public void insert(MartyrD data) {
		setRoot(insert((MartyrD) data, getRoot()));
	}

	public int height() {
		if (root == null)
			return 0;
		return height(root);
	}

	protected TNode insert(MartyrD data, TNode n) {
		if (n == null)
			n = new TNode(data);
		else if (n.getData().compareTo(data) < 0)
			n.setRight(insert(data, n.getRight()));
		else if (n.getData().compareTo(data) >= 0)
			n.setLeft(insert(data, n.getLeft()));
		updateHeight(n);
		return reBalance(n);
	}

	public void delete(MartyrD data) {
		setRoot(delete(data, getRoot()));
	}

	protected TNode delete(MartyrD data, TNode n) {
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
			deleteTwo(n);

		updateHeight(n);
		return reBalance(n);
	}

	private void deleteTwo(TNode n) {
		TNode successor = findMin(n.getRight());
		n.setData(successor.getData());
		n.setRight(delete(successor.getData(), successor));
	}

	private TNode findMin(TNode n) {
		if (n == null)
			return null;
		if (n.getLeft() == null)
			return n;
		else
			return findMin(n.getLeft());
	}

	private int height(TNode n) {
		if (n != null) {
			return n.getHeight();
		} else {
			return 0;
		}
	}

	private void updateHeight(TNode n) {
		int left;
		int right;
		if (n != null) {
			left = height(n.getLeft());
			right = height(n.getRight());
			n.setHeight(Math.max(left, right) + 1);
		}
	}

	private TNode reBalance(TNode n) {
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

	private int balanceFactor(TNode n) {
		return height(n.getLeft()) - height(n.getRight());
	}

	private TNode rotateRight(TNode n) {
		TNode x;
		x = n.getLeft();
		n.setLeft(x.getRight());
		x.setRight(n);
		updateHeight(n);
		updateHeight(x);
		return x;
	}

	private TNode rotateLeft(TNode n) {
		TNode x;
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

	private void appendTree(StringBuilder builder, TNode n) {
		if (n.getLeft() != null)
			appendTree(builder, n.getLeft());

		builder.append(n.getData()).append(" ");

		if (n.getRight() != null)
			appendTree(builder, n.getRight());
	}

	public void removeAll() {
		root = null;
		root.setHeight(0);
	}

	public String getAllMartyr() {
		StringBuilder strBuilder;
		strBuilder = new StringBuilder();
		getAllMartyrHelper(root, strBuilder);
		c++;
		return strBuilder.toString();
	}

	private void getAllMartyrHelper(TNode n, StringBuilder strBuilder) {
		if (n != null) {
			getAllMartyrHelper(n.getLeft(), strBuilder);
			strBuilder.append(n.getData()).append("\n");
			getAllMartyrHelper(n.getRight(), strBuilder);
		}
	}

	public TNode search(MartyrD data) {
		return searchNode(data, root);
	}

	private TNode searchNode(MartyrD data, TNode n) {
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

	private void traverseInOrder(TNode n) {
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

	private void print(TNode n) {
		if (n != null) {
			print(n.getLeft());
			System.out.println(n.getData());
			print(n.getRight());
		}
	}

	public TNode reBalance2(TNode n) {
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

	@Override
	public String toString() {
		StringBuilder builder;
		builder = new StringBuilder();
		appendTree(builder, getRoot());
		return builder.toString();
	}

}