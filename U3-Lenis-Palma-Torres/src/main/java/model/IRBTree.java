package model;

public interface IRBTree<K extends Comparable<K>, V> extends IBinaryTree<K, V>{

	public void leftRotate(RBNode<K,V> x);
	
	public void rightRotate(RBNode<K,V> x);
	
}
