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
	
//	public void add(T node) {
//		NodeBinaryTree<T> toAdd = new NodeBinaryTree<T>(node);
//		if (compareTo(toAdd)>=0) {
//			if (left == null)
//				left = toAdd;
//			else
//				left.addNode(node);
//		}else {
//			if (right==null)
//				right = toAdd;
//			else
//				right.addNode(node);
//		}
//	}
	
//	public V search(K key) {
//		if (this.value == value) 
//			//VER ESTO
//			return (T) this;
//		if (isLeaf())
//			return null;
//		if (this.value.compareTo(value)>=0)
//			return (left!=null) ? left.searchValue(value) : null;
//		else
//			return (right!=null) ? right.searchValue(value) : null;	
//	}
	
//	public T delete(T value) {
//		//Falta implementarlo
//		return value;
//	}
//	
//	public T findMin() {
//		return (T) ((left==null) ? this : left.findMin());
//	}
//	
//	public T findMax() {
//		return (T) ((right==null) ? this : right.findMin());
//	}
//	
//	public T findSuccesor() {
//		return (T) this;
//	}
	
}
