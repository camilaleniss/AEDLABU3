package model;

public class BinaryTree<K extends Comparable<K>, V> implements IBinaryTree<K,V> {
	
	private NodeBinaryTree<K, V> root;

	public void insert(K key, V value) {
		// TODO Auto-generated method stub
		
	}

	public V delete(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	public V search(K key) {
		NodeBinaryTree<K, V> node = search(root, key);
		return node == null? null : node.getValue();
	}

	private NodeBinaryTree<K, V> search(NodeBinaryTree<K, V> node, K key) {
		if (node==null || key== node.getKey())
				return node;
		if(key.compareTo(node.getKey()) < 0)
			return search(node.getLeft(), key);
		return search(node.getRight(), key);
	}
	

}
