package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.BinaryTree;
import model.NodeBinaryTree;

class TestBinaryTree {
	
	private BinaryTree<Integer, Integer> ABB;
	
	TestBinaryTree(){
		ABB = new BinaryTree<Integer, Integer>();
	}
	
	/*
	void setUpStage1() {
		ABB = new BinaryTree<Integer, Integer>();
		ABB.insert(1, 10);
	}
	
	void setUpStage2() {
		setUpStage1();
		ABB.insert(2, 5);
	}
	
	void setUpStage3() {
		setUpStage2();
		ABB.insert(3, 20);
	}
	
	void setUpStage4() {
		setUpStage3();
		ABB.insert(4, 6);
	}
	
	void setUpStage5() {
		setUpStage4();
		ABB.insert(5, 5);
	}
	*/

	@Test
	void testInsert() {
		//Test 1
		ABB.insert(10, 1);
		assertTrue(ABB.getWeight()==1);
		assertTrue(ABB.getRoot().getValue()==1);
		
		//Test 2
		ABB.insert(5, 2);
		assertTrue(ABB.getWeight()==2);
		assertTrue(ABB.getRoot().getLeft().getKey()==5);
		assertTrue(ABB.getRoot().getLeft().getValue()==2);
		assertTrue(ABB.getRoot().getLeft().getParent().getValue()==1);
		
		//Test 3
		//setUpStage2();
		ABB.insert(20, 3);
		assertTrue(ABB.getWeight()==3);
		assertTrue(ABB.getRoot().getRight().getKey()==20);
		assertTrue(ABB.getRoot().getRight().getValue()==3);
		assertTrue(ABB.getRoot().getRight().getParent().getValue()==1);
		
		//Test 4
		//setUpStage3();
		ABB.insert(6, 4);
		assertTrue(ABB.getWeight()==4);
		assertTrue(ABB.getRoot().getLeft().getRight().getKey()==6);
		assertTrue(ABB.getRoot().getLeft().getRight().getValue()==4);
		assertTrue(ABB.getRoot().getLeft().getRight().getParent().getValue()==2);
		
		//Test 5
		ABB.insert(5, 5);
		assertTrue(ABB.getWeight()==5);
		assertTrue(ABB.getRoot().getLeft().getLeft().getKey()==5);
		assertTrue(ABB.getRoot().getLeft().getLeft().getValue()==5);
		assertTrue(ABB.getRoot().getLeft().getLeft().getParent().getValue()==2);
	}
	
	@Test
	void testSearch() {
		
	}

}
