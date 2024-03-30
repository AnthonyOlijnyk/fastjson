package project;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;

public class JSONArrayTest {
	
	private class TestObject extends Object {
		
		private int x;
		private int y;
		
		public TestObject(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return this.x;
		}
		
		public int getY() {
			return this.y;
		}
	}
	
	public JSONArray testArr;
	public JSONArray testArrB;
	public JSONArray testArrC;
	public ArrayList<Integer> testArrD;
	
	@Before
	public void setup() {
		List<Object> t = new ArrayList<Object>();
		t.add(new TestObject(1, 2));
		t.add(new TestObject(3, 4));
		t.add(new TestObject(5, 6));
		this.testArr = new JSONArray(t);
		List<Object> tb = new ArrayList<Object>();
		tb.add(new TestObject(20, 21));
		tb.add(new TestObject(22, 23));
		tb.add(new TestObject(24, 25));
		this.testArrB = new JSONArray(tb);
		this.testArrC = this.testArr;
		this.testArrD = new ArrayList<Integer>();
		this.testArrD.add(99);
		this.testArrD.add(98);
		this.testArrD.add(97);
	}
	
	// SET METHOD
	
	// ISP

	@Test
	public void testSetMin() {
		testArr.set(0, new TestObject(8, 9));
		int expectedX = 8;
		int expectedY = 9;
		TestObject ta = (TestObject) testArr.get(0);
		assertEquals(expectedX, ta.getX());
		assertEquals(expectedY, ta.getY());
	}
	
	@Test
	public void testSetMax() {
		testArr.set(2, new TestObject(8, 9));
		int expectedX = 8;
		int expectedY = 9;
		TestObject ta = (TestObject) testArr.get(2);
		assertEquals(expectedX, ta.getX());
		assertEquals(expectedY, ta.getY());
	}
	
	@Test
	public void testSetBeforeZero() {
		testArr.set(-1, new TestObject(8, 9));
		int expectedX = 8;
		int expectedY = 9;
		TestObject ta = (TestObject) testArr.get(3);
		assertEquals(expectedX, ta.getX());
		assertEquals(expectedY, ta.getY());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetWayBeforeZero() {
		testArr.set(-99, new TestObject(8, 9));
	}
	
	@Test
	public void testSetWayAfterMax() {
		testArr.set(100, new TestObject(8, 9));
		int expectedX = 8;
		int expectedY = 9;
		TestObject ta = (TestObject) testArr.get(100);
		TestObject tb = (TestObject) testArr.get(99);
		assertEquals(expectedX, ta.getX());
		assertEquals(expectedY, ta.getY());
		assertEquals(null, tb);
	}
	
	// CFG / DFG

	public void testSetAllNodes() {
		testArr.set(0, new TestObject(8, 9));
		testArr.set(-1, new TestObject(10, 11));
		testArr.set(6,  new TestObject(12, 13));
		int expectedAX = 8;
		int expectedAY = 9;
		int expectedBX = 10;
		int expectedBY = 11;
		int expectedCX = 12;
		int expectedCY = 13;
		TestObject ta = (TestObject) testArr.get(0);
		TestObject tb = (TestObject) testArr.get(3);
		TestObject tc = (TestObject) testArr.get(6);
		assertEquals(expectedAX, ta.getX());
		assertEquals(expectedAY, ta.getY());
		assertEquals(expectedBX, tb.getX());
		assertEquals(expectedBY, tb.getY());
		assertEquals(expectedCX, tc.getX());
		assertEquals(expectedCY, tc.getY());
	}
	
	public void testSetAllUses() {
		testArr.set(0, new TestObject(8, 9));
		testArr.set(-1, new TestObject(10, 11));
		testArr.set(6,  new TestObject(12, 13));
		int expectedAX = 8;
		int expectedAY = 9;
		int expectedBX = 10;
		int expectedBY = 11;
		int expectedCX = 12;
		int expectedCY = 13;
		TestObject ta = (TestObject) testArr.get(0);
		TestObject tb = (TestObject) testArr.get(3);
		TestObject tc = (TestObject) testArr.get(6);
		assertEquals(expectedAX, ta.getX());
		assertEquals(expectedAY, ta.getY());
		assertEquals(expectedBX, tb.getX());
		assertEquals(expectedBY, tb.getY());
		assertEquals(expectedCX, tc.getX());
		assertEquals(expectedCY, tc.getY());
	}
	
	// Logical
	
	// a&b&c
	// a -> size in range
	// b -> size not zero
	// c -> size <= index
	
	public void testSetAllPredicatesAndClauses() {
		testArr.set(0, new TestObject(8, 9));
		testArr.set(-1, new TestObject(10, 11));
		testArr.set(6,  new TestObject(12, 13));
		int expectedAX = 8;
		int expectedAY = 9;
		int expectedBX = 10;
		int expectedBY = 11;
		int expectedCX = 12;
		int expectedCY = 13;
		TestObject ta = (TestObject) testArr.get(0);
		TestObject tb = (TestObject) testArr.get(3);
		TestObject tc = (TestObject) testArr.get(6);
		assertEquals(expectedAX, ta.getX());
		assertEquals(expectedAY, ta.getY());
		assertEquals(expectedBX, tb.getX());
		assertEquals(expectedBY, tb.getY());
		assertEquals(expectedCX, tc.getX());
		assertEquals(expectedCY, tc.getY());
	}
	
	// Mutation
	
	@Test
	public void testSetMutationIndex() {
		testArr.mutatedSetIndex(0, new TestObject(8, 9));
		int expectedX = 8;
		int expectedY = 9;
		TestObject ta = (TestObject) testArr.get(0);
		assertNotEquals(expectedX, ta.getX());
		assertNotEquals(expectedY, ta.getY());
	}
	
	@Test
	public void mutatedSetSizeComparisonFlip() {
		testArr.mutatedSetSizeComparisonFlip(1, new TestObject(8, 9));
		int expectedX = 8;
		int expectedY = 9;
		TestObject ta = (TestObject) testArr.get(3);
		assertEquals(testArr.size(), 4);
		assertEquals(expectedX, ta.getX());
		assertEquals(expectedY, ta.getY());
	}
	
	// EQUALS
	
	// ISP
	
	@Test
	public void testEqualsNull() {
		assertFalse(testArr.equals(null));
	}
	
	@Test
	public void testEqualsSame() {
		assertTrue(testArr.equals(testArrC));
	}
	
	@Test
	public void testEqualsDifferent() {
		assertFalse(testArr.equals(testArrB));
	}
	
	// CFG / DFG
	
	public void testEqualsAllNodes() {
		assertTrue(testArr.equals(testArrC));
		assertFalse(testArr.equals(testArrB));
		assertFalse(testArr.equals(testArrD));
	}
	
	public void testEqualsAllUses() {
		assertFalse(testArr.equals(testArrB));
		assertFalse(testArr.equals(testArrD));
	}
	
	// Logical
	
	// a&b&c
	// a -> object not null
	// b -> instance not JSONArray
	// c -> list values are equal
	
	public void testEqualsAllPredicates() {
		assertFalse(testArr.equals(null));
		assertTrue(testArr.equals(testArrC));
		assertFalse(testArr.equals(testArrB));
		assertFalse(testArr.equals(testArrD));
	}
	
	// Mutation
	
	public void testEqualsMutatedNotEqual() {
		assertFalse(testArr.equals(testArrC));
	}
	
	public void testEqualsMutatedNotInstance() {
		assertFalse(testArr.equals(testArrD));
	}
}
