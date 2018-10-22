package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.AVLTree;
import model.NodeBinaryTree;

class TestAVLTree {
	private AVLTree<Integer,Integer> AVL;
	
	void setup1() {
		AVL=new AVLTree<Integer, Integer>();
	}
	@Test
	void testInsert() {
		setup1();
		//Test 1
		AVL.insert(new NodeBinaryTree<Integer, Integer>(30,1));
		assertTrue(AVL.getRoot().getKey()==30);
		//Test 2
		AVL.insert(new NodeBinaryTree<Integer, Integer>(15,2));
		AVL.insert(new NodeBinaryTree<Integer, Integer>(40,3));
		assertTrue(AVL.getRoot().getLeft().getKey()==15);
		assertTrue(AVL.getRoot().getRight().getKey()==40);
		//Test 3
		AVL.insert(new NodeBinaryTree<Integer, Integer>(45,19));
		AVL.insert(new NodeBinaryTree<Integer, Integer>(46,19));
		assertTrue(AVL.getRoot().getRight().getLeft().getKey()==40);
		assertTrue(AVL.getRoot().getRight().getRight().getKey()==46);
		//Test 4
		AVL.insert(new NodeBinaryTree<Integer, Integer>(12,1));
		AVL.insert(new NodeBinaryTree<Integer, Integer>(7,2));
		assertTrue(AVL.getRoot().getLeft().getLeft().getKey()==7);
		assertTrue(AVL.getRoot().getLeft().getRight().getKey()==15);
	}
	
	void testDelete() {
		setup1();
		AVL.insert(new NodeBinaryTree<Integer, Integer>(30,1));
		AVL.insert(new NodeBinaryTree<Integer, Integer>(15,2));
		AVL.insert(new NodeBinaryTree<Integer, Integer>(40,3));
		AVL.insert(new NodeBinaryTree<Integer, Integer>(45,19));
		AVL.insert(new NodeBinaryTree<Integer, Integer>(46,19));
		AVL.insert(new NodeBinaryTree<Integer, Integer>(12,1));
		AVL.insert(new NodeBinaryTree<Integer, Integer>(7,2));
		//Test 1
		AVL.delete(30);
		assertTrue(AVL.getRoot().getKey()==40);
		//Test 2
		AVL.delete(45);
		assertTrue(AVL.getRoot().getRight().getKey()==46);
		//Test 3
		AVL.delete(46);
		assertTrue(AVL.getRoot().getKey()==12);
		assertTrue(AVL.getRoot().getRight().getKey()==40);
		//Test4 (Revision)
		setup1();
		AVL.insert(new NodeBinaryTree<Integer, Integer>(30,1));
		AVL.insert(new NodeBinaryTree<Integer, Integer>(19,2));
		AVL.insert(new NodeBinaryTree<Integer, Integer>(40,3));
		AVL.insert(new NodeBinaryTree<Integer, Integer>(45,19));
		AVL.insert(new NodeBinaryTree<Integer, Integer>(46,19));
		AVL.insert(new NodeBinaryTree<Integer, Integer>(12,1));
		AVL.insert(new NodeBinaryTree<Integer, Integer>(7,2));
		AVL.insert(new NodeBinaryTree<Integer,Integer>(18,1));
		AVL.insert(new NodeBinaryTree<Integer,Integer> (17,1));
		AVL.insert(new NodeBinaryTree<Integer,Integer> (13,19));
		AVL.insert(new NodeBinaryTree<Integer,Integer>(16,1));
		
		
	}

}
