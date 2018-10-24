package model;

import java.util.ArrayList;

public class RBTree<K extends Comparable<K>, V> implements IRBTree<K, V> {

	private RBNode<K, V> root;
	private RBNode<K, V> nil;

	public RBTree() {
		nil = new RBNode<K, V>(null, null, null);
		nil.setBlack(true);
		root = nil;
	}
	
	public RBNode<K,V> getRoot(){
		return root;
	}
	
	public void setRoot(RBNode<K,V> root) {
		this.root=root;
	}
	
	public RBNode<K,V> getNIL(){
		return nil;
	}

	@Override
	public V search(K key) {
		RBNode<K, V> node = search(root, key);
		return node == nil ? null : node.getValue();
	}

	@Override
	public boolean isInTree(K key) {
		return search(root, key) != nil;
	}

	private RBNode<K, V> search(RBNode<K, V> node, K key) {
		if (key == null)
			return nil;
		if (node == nil || key.equals(node.getKey()))
			return node;
		if (key.compareTo(node.getKey()) < 0)
			return search(node.getLeft(), key);
		return search(node.getRight(), key);
	}

	public V search(K key, V value) {
		RBNode<K, V> node = search(root, key, value);
		return node == nil ? null : node.getValue();
	}
	
	private RBNode<K,V> search(RBNode<K,V> node, K key, V value){
		if (key == null)
			return nil;
		if (node == nil || key.equals(node.getKey()) && value.equals(node.getValue()))
			return node;
		if (key.compareTo(node.getKey()) <= 0)
			return search(node.getLeft(), key, value);
		return search(node.getRight(), key, value);
	}
	
	@Override
	public K getMin() {
		return root == nil ? null : getMin(root).getKey();
	}

	private RBNode<K, V> getMin(RBNode<K, V> x) {
		if (x.getLeft() == nil)
			return x;
		return getMin(x.getLeft());
	}

	@Override
	public K getMax() {
		return root == nil ? null : getMax(root).getKey();
	}

	private RBNode<K, V> getMax(RBNode<K, V> x) {
		if (x.getRight() == nil)
			return x;
		return getMin(x.getRight());
	}

	@Override
	public K getPredecessor(K key) {
		return search(root, key) != nil ? getPredecessor(search(root, key)).getKey() : null;
	}

	private RBNode<K, V> getPredecessor(RBNode<K, V> x) {
		if (x.getLeft() != nil)
			return getMax(x.getLeft());
		RBNode<K, V> y = x.getParent();
		while (y != nil && x == y.getLeft()) {
			x = y;
			y = y.getParent();
		}
		return y;
	}

	@Override
	public K getSuccessor(K key) {
		return search(root, key) != nil ? getSuccessor(search(root, key)).getKey() : null;
	}

	private RBNode<K, V> getSuccessor(RBNode<K, V> x) {
		if (x.getRight() != nil)
			return getMin(x.getRight());
		RBNode<K, V> y = x.getParent();
		while (y != nil && x == y.getRight()) {
			x = y;
			y = y.getParent();
		}
		return y;
	}

	@Override
	public void insert(K key, V value) {
		RBNode<K, V> node = new RBNode<>(key, value, nil);
		insert(node);
	}

	private void insert(RBNode<K, V> z) {
		RBNode<K, V> y = nil;
		RBNode<K, V> x = root;
		while (x != nil) {
			y = x;
			if (z.getKey().compareTo(x.getKey()) <= 0)
				x = x.getLeft();
			else
				x = x.getRight();
		}
		z.setParent(y);
		if (y == nil)
			root = z;
		else if (z.getKey().compareTo(y.getKey()) <= 0)
			y.setLeft(z);
		else
			y.setRight(z);
		
		insertFixup(z);

	}

	public void insertFixup(RBNode<K, V> z) {
		while (!z.getParent().isBlack()) {
			if (z.getParent() == z.getParent().getParent().getLeft()) {
				RBNode<K, V> y = z.getParent().getParent().getRight();
				if (!y.isBlack()) {
					z.getParent().setBlack(true); // case 1
					y.setBlack(true);
					z.getParent().getParent().setBlack(false);
					z = z.getParent().getParent();
				} else {
					if (z == z.getParent().getRight()) {
						z = z.getParent(); // case 2
						leftRotate(z);
					}
					z.getParent().setBlack(true); // case 3
					z.getParent().getParent().setBlack(false);
					rightRotate(z.getParent().getParent());
				}
			} else {
				RBNode<K, V> y = z.getParent().getParent().getLeft();
				if (!y.isBlack()) {
					z.getParent().setBlack(true); // case 1
					y.setBlack(true);
					z.getParent().getParent().setBlack(false);
					z = z.getParent().getParent();
				} else {
					if (z == z.getParent().getLeft()) {
						z = z.getParent(); // case 2
						rightRotate(z);
					}
					z.getParent().setBlack(true); // case 3
					z.getParent().getParent().setBlack(false);
					leftRotate(z.getParent().getParent());
				}
			}
		}
		root.setBlack(true);
	}

	@Override
	public V delete(K key) {
		RBNode<K, V> node = search(root, key);
		if (node != nil) {
			V value = node.getValue();
			delete(node);
			return value;
		}
		return null;
	}
	
	@Override
	public V delete(K key, V value) {
		RBNode<K,V> node = search(root, key, value);
		if (node != nil) {
			delete(node);
			return value;
		}
		return null;
	}

	private void delete(RBNode<K, V> z) {
		RBNode<K, V> y = z;
		RBNode<K, V> x = nil;
//		boolean yOriginalColor = y.isBlack();
		
		
		if (z.getLeft() == nil || z.getRight() == nil)
			y = z;
		else
			y = getSuccessor(z);
		if (y.getLeft() != nil)
			x = y.getLeft();
		else
			x = y.getRight();
		x.setParent(y.getParent());
		
		if (y.getParent() == nil)
			root = x;
		else {
			if (y == y.getParent().getLeft())
				y.getParent().setLeft(x);
			else
				y.getParent().setRight(x);
		}
			
		if (y != z) {
			z.setKey(y.getKey());
			z.setValue(y.getValue());
		}
		if(y.isBlack())
			deleteFixup(x);
		
		
//		if (z.getLeft() == nil) {
//			x = z.getRight();
//			transplant(z, z.getRight());
//		} else if (z.getRight() == nil) {
//			x = z.getLeft();
//			transplant(z, z.getLeft());
//		} else {
//			y = getMin(z.getRight());
//			yOriginalColor = y.isBlack();
//			x = y.getRight();
//			if(y.getParent() == z)
//				x.setParent(y);
//			else {
//				transplant(y, y.getRight());
//				y.setRight(z.getRight());
//				y.getRight().setParent(y);
//			}
//			transplant(z, y);
//			y.setLeft(z.getLeft());
//			y.getLeft().setParent(y);
//			y.setBlack(z.isBlack());
//		}
//		if(yOriginalColor) {
//			deleteFixup(x);
//		}
	}

	private void deleteFixup(RBNode<K, V> x) {
		while(x != root && x.isBlack()){
			if(x == x.getParent().getLeft()) {
				RBNode<K, V> w = x.getParent().getRight();
				if(!w.isBlack()) {
					w.setBlack(true);
					x.getParent().setBlack(false);
					leftRotate(x.getParent());
					w = x.getParent().getRight();
				}
				if(w.getLeft().isBlack() && w.getRight().isBlack()) {
					w.setBlack(false);
					x = x.getParent();
				}
				else {
					if(w.getRight().isBlack()) {
						w.getLeft().setBlack(true);
						w.setBlack(false);
						rightRotate(w);
						w = x.getParent().getRight();
					}
					w.setBlack(x.getParent().isBlack());
					x.getParent().setBlack(true);
					w.getRight().setBlack(true);
					leftRotate(x.getParent());
					x = root;
				}
			} else {
				RBNode<K, V> w = x.getParent().getLeft();
				if(!w.isBlack()) {
					w.setBlack(true);
					x.getParent().setBlack(false);
					rightRotate(x.getParent());
					w = x.getParent().getLeft();
				}
				if(w.getRight().isBlack() && w.getLeft().isBlack()) {
					w.setBlack(false);
					x = x.getParent();
				}
				else {
					if(w.getLeft().isBlack()) {
						w.getRight().setBlack(true);
						w.setBlack(false);
						leftRotate(w);
						w = x.getParent().getLeft();
					}
					w.setBlack(x.getParent().isBlack());
					x.getParent().setBlack(true);
					w.getLeft().setBlack(true);
					rightRotate(x.getParent());
					x = root;
				}
			}
		}
		x.setBlack(true);
	}

	private void transplant(RBNode<K, V> u, RBNode<K, V> v) {
		if (u.getParent() == nil)
			root = v;
		else if (u == u.getParent().getLeft())
			u.getParent().setLeft(v);
		else
			u.getParent().setRight(v);
		v.setParent(u.getParent());
	}

	@Override
	public void leftRotate(RBNode<K, V> x) {
		RBNode<K, V> y = x.getRight(); // set y
		x.setRight(y.getLeft()); // turn y’s left subtree into x’s right subtree
		if (y.getLeft() != nil)
			y.getLeft().setParent(x);
		y.setParent(x.getParent()); // link x’s parent to y
		if (x.getParent() == nil)
			root = y;
		else if (x == x.getParent().getLeft())
			x.getParent().setLeft(y);
		else
			x.getParent().setRight(y);
		y.setLeft(x); // put x on y’s left
		x.setParent(y);
	}

	@Override
	public void rightRotate(RBNode<K, V> x) {
		RBNode<K, V> y = x.getLeft(); // set y
		x.setLeft(y.getRight()); // turn y’s right subtree into x’s left subtree
		if (y.getRight() != nil)
			y.getRight().setParent(x);
		y.setParent(x.getParent()); // link x’s parent to y
		if (x.getParent() == nil)
			root = y;
		else if (x == x.getParent().getRight())
			x.getParent().setRight(y);
		else
			x.getParent().setLeft(y);
		y.setRight(x); // put x on y’s right
		x.setParent(y);
	}
	
	@Override
	public ArrayList<V> searchEqualTo(K key) {
		ArrayList<V> array = new ArrayList<V>();
		if (isInTree(key)) {
			RBNode<K, V> node = search(root, key);
			while (node != nil && node.getKey().compareTo(key) == 0) {
				array.add(node.getValue());
				node = getPredecessor(node);
			}
		}
		return array;
	}

	@Override
	public ArrayList<V> searchLowerOrEqualTo(K key) {
		ArrayList<V> array = new ArrayList<V>();

		RBNode<K, V> node = getMin(root);

		while (node != nil && node.getKey().compareTo(key) <= 0) {
			array.add(node.getValue());
			node = getSuccessor(node);
		}

		return array;
	}

	@Override
	public ArrayList<V> searchLowerTo(K key) {
		ArrayList<V> array = new ArrayList<V>();

		RBNode<K, V> node = getMin(root);

		while (node != nil && node.getKey().compareTo(key) < 0) {
			array.add(node.getValue());
			node = getSuccessor(node);
		}

		return array;
	}
	
	@Override
	public ArrayList<V> searchBiggerOrEqualThan(K key) {
		ArrayList<V> array = new ArrayList<V>();

		RBNode<K, V> node = getMax(root);

		while (node != nil && node.getKey().compareTo(key) >= 0) {
			array.add(node.getValue());
			node = getPredecessor(node);
		}

		return array;
	}
	
	@Override
	public ArrayList<V> searchBiggerThan(K key) {
		ArrayList<V> array = new ArrayList<V>();

		RBNode<K, V> node = getMax(root);

		while (node != nil && node.getKey().compareTo(key) > 0) {
			array.add(node.getValue());
			node = getPredecessor(node);
		}

		return array;
	}
	


}
