package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.RBNode;
import model.RBTree;

class TestRBTree {

private RBTree<Integer, Integer> rb;
	
	TestRBTree(){
		rb = new RBTree<>();
	}
	
	void setUpStage1() {
		rb.insert(10, 1);
	}
	
	void setUpStage2() {
		//Construct the tree but It does not matter about the parents
		rb = new RBTree<>();
		RBNode<Integer, Integer> node = new RBNode<>(7,4, rb.getNIL());
		node.setBlack(true);
		RBNode<Integer, Integer> nodeaux= new RBNode<> (5,5, rb.getNIL());
		node.setLeft(nodeaux);
		nodeaux = new RBNode<>(8,6, rb.getNIL());
		node.setRight(nodeaux);
		nodeaux= node; 
		node = new RBNode<>(2,2, rb.getNIL());
		node.setRight(nodeaux);
		nodeaux= new RBNode<>(1,3, rb.getNIL());
		nodeaux.setBlack(true);
		node.setLeft(nodeaux);
		nodeaux=node;
		node = new RBNode<>(11,1, rb.getNIL());
		node.setBlack(true);
		node.setLeft(nodeaux);
		nodeaux= new RBNode<>(14,7, rb.getNIL());
		nodeaux.setBlack(true);
		node.setRight(nodeaux);
		node.getRight().setRight(new RBNode<>(15,8, rb.getNIL()));
		//SET PARENTS
		nodeaux= rb.getNIL();
		node.setParent(nodeaux);
		nodeaux= node;
		node.getLeft().setParent(nodeaux);
		node.getRight().setParent(nodeaux);
		nodeaux=node.getRight();
		node.getRight().getRight().setParent(nodeaux);
		nodeaux=node.getLeft();
		node.getLeft().getLeft().setParent(nodeaux);
		node.getLeft().getRight().setParent(nodeaux);
		nodeaux=node.getLeft().getRight();
		node.getLeft().getRight().getLeft().setParent(nodeaux);
		node.getLeft().getRight().getRight().setParent(nodeaux);
		//Insert the tree
		rb.setRoot(node);
	}
	
	void setUpStage3() {
		//Construct the tree but It does not matter about the parents
		RBNode<Integer, Integer> node = new RBNode<>(14, 7, rb.getNIL());
		RBNode<Integer, Integer> nodeaux = new RBNode<>(13, 9, rb.getNIL());
		nodeaux.setBlack(true);
		node.setLeft(nodeaux);
		nodeaux= new RBNode<> (15,8, rb.getNIL());
		node.setRight(nodeaux);
		nodeaux= node; 
		node= new RBNode<> (12,3, rb.getNIL());
		node.setBlack(true);
		node.setRight(nodeaux);
		nodeaux= new RBNode<> (10,6, rb.getNIL());
		nodeaux.setBlack(true);
		node.setLeft(nodeaux);
		nodeaux=node;
		node = new RBNode<>(8,1,rb.getNIL());
		node.setBlack(true);
		node.setRight(nodeaux);
		nodeaux= new RBNode<>(6,2, rb.getNIL());
		nodeaux.setBlack(true);
		node.setLeft(nodeaux);
		nodeaux= new RBNode<>(5,4, rb.getNIL());
		nodeaux.setBlack(true);
		node.getLeft().setLeft(nodeaux);
		nodeaux= new RBNode<>(7,5, rb.getNIL());
		nodeaux.setBlack(true);
		node.getLeft().setLeft(nodeaux);
		//Set parents
		nodeaux=node;
		node.setParent(rb.getNIL());
		node.getLeft().setParent(nodeaux);
		node.getRight().setParent(nodeaux);
		nodeaux=node.getLeft();
		node.getLeft().getLeft().setParent(nodeaux);
		node.getLeft().getRight().setParent(nodeaux);
		nodeaux= node.getRight();
		node.getRight().getRight().setParent(nodeaux);
		node.getRight().getLeft().setParent(nodeaux);
		nodeaux=node.getRight().getRight();
		node.getRight().getRight().getRight().setParent(nodeaux);
		node.getRight().getRight().getLeft().setParent(nodeaux);
		//Insert the tree
		rb.setRoot(node);
	}
	
	void setUpStage4() {
		//Construct the tree but It does not matter about the parents
		RBNode<Integer, Integer> node = new RBNode<>(32, 1, rb.getNIL());
		node.setBlack(true);
		RBNode<Integer, Integer> nodeaux = new RBNode<>(21, 2, rb.getNIL());
		nodeaux.setBlack(true);
		node.setLeft(nodeaux);
		nodeaux= new RBNode<>(15, 4, rb.getNIL());
		node.getLeft().setLeft(nodeaux);
		nodeaux= new RBNode<>(64, 3, rb.getNIL());
		nodeaux.setBlack(true);
		node.setRight(nodeaux);
		nodeaux= new RBNode<>(75, 5, rb.getNIL());
		node.getRight().setRight(nodeaux);
		//Set parents
		nodeaux=node;
		node.getLeft().setParent(nodeaux);
		node.getRight().setParent(nodeaux);
		nodeaux=node.getLeft();
		node.getLeft().getLeft().setParent(nodeaux);
		nodeaux=node.getRight();
		node.getRight().getRight().setParent(nodeaux);
		//Insert Tree
		rb.setRoot(node);
	}
	
	@Test
	void testInsert() {
		//Test 1
		rb.insert(10, 1);
		assertTrue(rb.getRoot().isBlack());
		
		//Test 2
		rb.insert(5,2);
		assertTrue(rb.getRoot().isBlack());
		assertTrue(!rb.getRoot().getLeft().isBlack());
		
		//Test 3
		rb.insert(15, 3);
		assertTrue(rb.getRoot().isBlack());
		assertTrue(!rb.getRoot().getLeft().isBlack());
		assertTrue(!rb.getRoot().getRight().isBlack());
		
		//Test 4
		rb.insert(4, 4);
		assertTrue(rb.getRoot().isBlack());
		assertTrue(rb.getRoot().getLeft().isBlack());
		assertTrue(rb.getRoot().getRight().isBlack());
		assertTrue(!rb.getRoot().getLeft().getLeft().isBlack());
		
		//Test 5
		rb.insert(4, 5);
		assertTrue(rb.getRoot().isBlack());
		assertTrue(rb.getRoot().getLeft().isBlack());
		assertTrue(rb.getRoot().getLeft().getKey()==4);
		assertTrue(rb.getRoot().getRight().isBlack());
		assertTrue(rb.getRoot().getRight().getKey()==15);
		assertTrue(!rb.getRoot().getLeft().getLeft().isBlack());
		assertTrue(rb.getRoot().getLeft().getLeft().getKey()==4);
		assertTrue(!rb.getRoot().getLeft().getRight().isBlack());
		assertTrue(rb.getRoot().getLeft().getRight().getKey()==5);
	}
	
	@Test
	void testDelete() {
		//Test 1
		assertTrue(rb.delete(10)==null);
		
		//Test 2
		setUpStage1();
		assertTrue(rb.delete(10)==1);
		assertTrue(rb.getRoot().getKey()==null);
		
		//Test 3
		setUpStage2();
		assertTrue(rb.delete(5)==5);
		assertTrue(rb.getRoot().getLeft().getRight().getLeft()==rb.getNIL());
		assertTrue(rb.getRoot().isBlack());
		assertTrue(!rb.getRoot().getLeft().isBlack());
		assertTrue(rb.getRoot().getLeft().getLeft().isBlack());
		assertTrue(rb.getRoot().getLeft().getRight().isBlack());
		assertTrue(!rb.getRoot().getLeft().getRight().getRight().isBlack());
		assertTrue(rb.getRoot().getRight().isBlack());
		assertTrue(!rb.getRoot().getRight().getRight().isBlack());
		
		//Test 3
		setUpStage2();
		assertTrue(rb.delete(7)==4);
		assertTrue(rb.getRoot().getLeft().getRight().getKey()==8);
		assertTrue(rb.getRoot().getLeft().getRight().isBlack());
		assertTrue(rb.getRoot().getLeft().getRight().getLeft().getKey()==5);
		assertTrue(!rb.getRoot().getLeft().getRight().getLeft().isBlack());
	
		//Test 4
		setUpStage2();
		assertTrue(rb.delete(20)==null);
		
		//Test 5
		setUpStage3();
		assertTrue(rb.delete(10)==6);
		RBNode<Integer, Integer> node = rb.getRoot();
		assertTrue(node.isBlack());
		assertTrue(node.getKey()==8);
		assertTrue(node.getRight().getKey()==14);
		assertTrue(node.getRight().isBlack());
		node = node.getRight();
		assertTrue(node.getLeft().getKey()==12);
		assertTrue(node.getLeft().isBlack());
		assertTrue(node.getLeft().getRight().getKey()==13);
		assertTrue(!node.getLeft().getRight().isBlack());
		assertTrue(node.getRight().getKey()==15);
		assertTrue(!node.getRight().isBlack());
		
		
		//Test 6
		setUpStage4();
		assertTrue(rb.delete(21)==2);
		node=rb.getRoot();
		assertTrue(node.getKey()==32);
		assertTrue(node.isBlack());
		assertTrue(node.getRight().getKey()==64);
		assertTrue(node.getRight().isBlack());
		assertTrue(node.getLeft().getKey()==15);
		assertTrue(node.getLeft().isBlack());
		node=node.getRight();
		assertTrue(node.getRight().getKey()==75);
		assertTrue(!node.getRight().isBlack());
		
		//Test 7
		setUpStage4();
		assertTrue(rb.delete(64)==3);
		node=rb.getRoot();
		assertTrue(node.getKey()==32);
		assertTrue(node.isBlack());
		assertTrue(node.getRight().getKey()==75);
		assertTrue(node.getRight().isBlack());
		assertTrue(node.getLeft().getKey()==21);
		assertTrue(node.getLeft().isBlack());
		node=node.getLeft();
		assertTrue(node.getLeft().getKey()==15);
		assertTrue(!node.getLeft().isBlack());
	}
	
}
