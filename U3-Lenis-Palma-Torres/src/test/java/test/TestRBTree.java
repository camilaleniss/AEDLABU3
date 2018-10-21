package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.RBTree;

class TestRBTree {

private RBTree<Integer, Integer> rb;
	
	TestRBTree(){
		rb = new RBTree<>();
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
	
}
