package model;

public class NodeBinaryTree<K extends Comparable<K>, V>{

	private K key;
	private V value;
	private NodeBinaryTree<K, V> parent;
	private NodeBinaryTree<K, V> left;
	private NodeBinaryTree<K, V> right;
	
	public 	NodeBinaryTree(K key, V value) {
		this.key = key;
		this.value = value;
		left=null;
		right=null;
		parent = null;
	}
	
	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public NodeBinaryTree<K, V> getParent() {
		return parent;
	}

	public void setParent(NodeBinaryTree<K, V> parent) {
		this.parent = parent;
	}

	public NodeBinaryTree<K, V> getLeft() {
		return left;
	}

	public void setLeft(NodeBinaryTree<K, V> left) {
		this.left = left;
	}

	public NodeBinaryTree<K, V> getRight() {
		return right;
	}

	public void setRight(NodeBinaryTree<K, V> right) {
		this.right = right;
	}

	public boolean isLeaf() {
		return (left==null && right==null);
	}
	
}
