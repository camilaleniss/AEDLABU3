package model;

public class BinaryTree<K extends Comparable<K>, V> implements IBinaryTree<K, V> {

	private NodeBinaryTree<K, V> root;
	
	public BinaryTree() {
		root = null;
	}

	public NodeBinaryTree<K, V> getRoot(){
		return root;
	}
	
	public K getMin() {
		return root == null ? null : getMin(root).getKey();
	}

	private NodeBinaryTree<K, V> getMin(NodeBinaryTree<K, V> x) {
		if (x.getLeft() == null)
			return x;
		return getMin(x.getLeft());
	}

	public K getMax() {
		return root == null ? null : getMax(root).getKey();
	}

	private NodeBinaryTree<K, V> getMax(NodeBinaryTree<K, V> x) {
		if (x.getRight() == null)
			return x;
		return getMin(x.getRight());
	}
	

	public K getPredecessor(K key) {
		return search(root, key) != null ? getPredecessor(search(root, key)).getKey() : null;
	}

	private NodeBinaryTree<K, V> getPredecessor(NodeBinaryTree<K, V> x) {
		if (x.getLeft() != null)
			return getMax(x.getLeft());
		NodeBinaryTree<K, V> y = x.getParent();
		while (y != null && x == y.getLeft()) {
			x = y;
			y = y.getParent();
		}
		return y;
	}

	public K getSuccessor(K key) {
		return search(root, key) != null ? getSuccessor(search(root, key)).getKey() : null;
	}

	private NodeBinaryTree<K, V> getSuccessor(NodeBinaryTree<K, V> x) {
		if (x.getRight() != null)
			return getMin(x.getRight());
		NodeBinaryTree<K, V> y = x.getParent();
		while (y != null && x == y.getRight()) {
			x = y;
			y = y.getParent();
		}
		return y;
	}
	
	public int getWeight() {
		if (root!=null)
			return getWeight(root);
		return 0;
	}
	
	public int getWeight(NodeBinaryTree<K,V> x){
		int l = (x.getLeft()!=null) ? getWeight(x.getLeft()): 0;
		int r = (x.getRight()!=null) ? getWeight(x.getRight()): 0;
		return l+r+1;
	}
	
	public V search(K key) {
		NodeBinaryTree<K, V> node = search(root, key);
		return node == null ? null : node.getValue();
	}

	public boolean isInTree(K key) {
		return search(root, key) != null;
	}

	private NodeBinaryTree<K, V> search(NodeBinaryTree<K, V> node, K key) {
		if (key == null)
			return null;
		if (node == null || key.equals(node.getKey()))
			return node;
		if (key.compareTo(node.getKey()) < 0)
			return search(node.getLeft(), key);
		return search(node.getRight(), key);
	}

	public void insert(K key, V value) {
		NodeBinaryTree<K, V> node = new NodeBinaryTree<>(key, value);
		insert(node);
	}

	private void insert(NodeBinaryTree<K, V> z) {
		NodeBinaryTree<K, V> y = null;
		NodeBinaryTree<K, V> x = root;
		while (x != null) {
			y = x;
			if (z.getKey().compareTo(x.getKey()) <= 0)
				x = x.getLeft();
			else
				x = x.getRight();
		}
		z.setParent(y);
		if (y == null)
			root = z;
		else if (z.getKey().compareTo(y.getKey()) <= 0)
			y.setLeft(z);
		else
			y.setRight(z);

	}

	public V delete(K key) {
		NodeBinaryTree<K, V> node = search(root, key);
		return node != null ? delete(node).getValue() : null;
	}

	private NodeBinaryTree<K, V> delete(NodeBinaryTree<K, V> z) {
		NodeBinaryTree<K, V> x = null;
		NodeBinaryTree<K, V> y = null;
		if (z.getLeft() == null || z.getRight() == null)
			y = z;
		else
			y = getSuccessor(z);
		if (y.getLeft() != null)
			x = y.getLeft();
		else
			x = y.getRight();
		if (x != null)
			x.setParent(y.getParent());
		if (y.getParent() == null)
			root = x;
		else if (y == y.getParent().getLeft())
			y.getParent().setLeft(x);
		else
			y.getParent().setRight(x);
		if (y != z) {
			z.setKey(y.getKey());
			z.setValue(y.getValue());
		}
		return y;
	}
	
	
}
