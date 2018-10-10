package model;

public class NodeBinaryTree<T> implements Comparable{

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
	
	public void setValue(T value) {
		this.value=value;
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

	//REVISAR ESTE M�TODO
	public int compareTo(Object arg0) {
		NodeBinaryTree node = (NodeBinaryTree) arg0;
		int v = (Integer) node.getValue();
		return (Integer)value-v;
	}
	
	public void addNode (T node) {
		NodeBinaryTree<T> toAdd = new NodeBinaryTree<T>(node);
		if (compareTo(toAdd)>=0) {
			if (left == null)
				left = toAdd;
			else
				left.addNode(node);
		}else {
			if (right==null)
				right = toAdd;
			else
				right.addNode(node);
		}
	}
	
	public T searchValue(T value) {
		if (this.value == value) 
			//VER ESTO
			return (T) this;
		if (isLeaf())
			return null;
		if (compareTo(value)>=0)
			return (left!=null) ? left.searchValue(value) : null;
		else
			return (right!=null) ? right.searchValue(value) : null;	
	}
	
	public T delete(T value) {
		//Falta implementarlo
		return value;
	}
	
	public T findMin() {
		return (T) ((left==null) ? this : left.findMin());
	}
	
	public T findMax() {
		return (T) ((right==null) ? this : right.findMin());
	}
	
	public T findSuccesor() {
		return (T) this;
	}
	
}