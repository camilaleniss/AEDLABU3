package model;

public interface IBinaryTree<K extends Comparable<K>, V> {

	public void insert(K key, V value);
	
	public V delete(K key);
	
	public V search(K key);
	
	public boolean isInTree(K key);
	
	public K getMin();
		
	public K getMax();
	
	
	public K getPredecessor(K key);
	
	public K getSuccessor(K key);
}
