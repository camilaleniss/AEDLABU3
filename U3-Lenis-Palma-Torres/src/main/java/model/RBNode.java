package model;

public class RBNode<K extends Comparable<K>, V> {

	private K key;
	private V value;
	private RBNode<K, V> parent;
	private RBNode<K, V> left;
	private RBNode<K, V> right;
	private boolean black;

	public RBNode(K key, V value, RBNode<K,V> nil) {
		this.key = key;
		this.value = value;
		left = nil;
		right = nil;
		parent = nil;
		black = false;
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

	public RBNode<K, V> getParent() {
		return parent;
	}

	public void setParent(RBNode<K, V> parent) {
		this.parent = parent;
	}

	public RBNode<K, V> getLeft() {
		return left;
	}

	public void setLeft(RBNode<K, V> left) {
		this.left = left;
	}

	public RBNode<K, V> getRight() {
		return right;
	}

	public void setRight(RBNode<K, V> right) {
		this.right = right;
	}

	public boolean isLeaf() {
		return (left == null && right == null);
	}

	public boolean isBlack() {
		return black;
	}

	public void setBlack(boolean black) {
		this.black = black;
	}

}
