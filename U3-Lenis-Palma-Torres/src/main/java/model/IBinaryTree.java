package model;

public interface IBinaryTree<T> {

	public void addNode(T node);
	
	public NodeBinaryTree<T> deleteNode(T node);
	
	public NodeBinaryTree<T> searchNode(T node);
	
}
