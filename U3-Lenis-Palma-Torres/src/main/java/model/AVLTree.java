package model;

public class AVLTree<K extends Comparable<K>, V> extends BinaryTree<K, V> {

	private int height(NodeBinaryTree<K, V> x) {
		if (x == null)
			return -1;
		else
			return Math.max(height(x.getLeft()), height(x.getRight())) + 1;
	}

	private void leftRotate(NodeBinaryTree<K, V> x) {
		NodeBinaryTree<K, V> y = x.getRight(); // set y
		x.setRight(y.getLeft()); // turn y’s left subtree into x’s right subtree
		if (y.getLeft() != null)
			y.getLeft().setParent(x);
		y.setParent(x.getParent()); // link x’s parent to y
		if (x.getParent() == null)
			root = y;
		else if (x == x.getParent().getLeft())
			x.getParent().setLeft(y);
		else
			x.getParent().setRight(y);
		y.setLeft(x); // put x on y’s left
		x.setParent(y);
	}

	private void rightRotate(NodeBinaryTree<K, V> x) {
		NodeBinaryTree<K, V> y = x.getLeft(); // set y
		x.setLeft(y.getRight()); // turn y’s right subtree into x’s left subtree
		if (y.getRight() != null)
			y.getRight().setParent(x);
		y.setParent(x.getParent()); // link x’s parent to y
		if (x.getParent() == null)
			root = y;
		else if (x == x.getParent().getRight())
			x.getParent().setRight(y);
		else
			x.getParent().setLeft(y);
		y.setRight(x); // put x on y’s right
		x.setParent(y);
	}

	private void rebalance(NodeBinaryTree<K, V> node) {
		while (node != null) {
			if (height(node.getLeft()) >= 2 + height(node.getRight())) {
				if (height(node.getLeft().getLeft()) >= height(node.getLeft().getRight())) {
					rightRotate(node);
				} else {
					leftRotate(node.getLeft());
					rightRotate(node);
				}
			} else if (height(node.getRight()) >= 2 + height(node.getLeft())) {
				if (height(node.getRight().getRight()) >= height(node.getRight().getLeft())) {
					leftRotate(node);
				} else {
					rightRotate(node.getRight());
					leftRotate(node);
				}
			}
			node = node.getParent();
		}
	}

	@Override
	protected void insert(NodeBinaryTree<K, V> z) {
		super.insert(z);
		rebalance(z);
	}

	@Override
	protected NodeBinaryTree<K, V> delete(NodeBinaryTree<K, V> z) {
		NodeBinaryTree<K, V> node = super.delete(z);
		rebalance(node.getParent());
		return node;
	}

}
