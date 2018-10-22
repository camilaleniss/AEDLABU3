package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.AVLTree;

class TestAVLTree {
	private AVLTree<Integer,Integer> AVL;
	
	void setup1() {
		AVL=new AVLTree<Integer, Integer>();
	}
	@Test
	void testInsert() {
		setup1();
		AVL.insert(30,1);
		assertTrue(AVL.getRoot().getKey()==30);
		
		AVL.insert(15,2);
		AVL.insert(40,3);
		assertTrue(AVL.getRoot().getLeft().getKey()==15);
		assertTrue(AVL.getRoot().getRight().getKey()==40);
		
		AVL.insert(45,19);
		AVL.insert(46,19);
		assertTrue(AVL.getRoot().getRight().getLeft().getKey()==40);
		assertTrue(AVL.getRoot().getRight().getRight().getKey()==46);
		
		AVL.insert(12,1);
		AVL.insert(7,2);
		assertTrue(AVL.getRoot().getLeft().getLeft().getKey()==7);
		assertTrue(AVL.getRoot().getLeft().getRight().getKey()==15);
	}

}
