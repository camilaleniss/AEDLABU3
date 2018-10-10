package model;

public class NodeBinaryTree<T> {

	private T value;
	private NodeBinaryTree<T> parent;
	private NodeBinaryTree<T> left;
	private NodeBinaryTree<T> right;
	
	public 	NodeBinaryTree(T value) {
		this.value = value;
		left=null;
		right=null;
	}
	
	public T getValue() {
		return value;
	}

	public NodeBinaryTree<T> getRight() {
		return right;
	}

	public void setRight(NodeBinaryTree<T> right) {
		this.right = right;
	}

	public NodeBinaryTree<T> getLeft() {
		return left;
	}

	public void setLeft(NodeBinaryTree<T> left) {
		this.left = left;
	}

	public boolean isLeaf() {
		return (left==null && right==null);
	}
	
}
