package project;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONStreamContext;
import com.alibaba.fastjson.JSONWriter;

import java.io.Writer;

public class JSONWriterTest {
	
	private JSONWriter startObject;
	private JSONWriter propertyKey;
	private JSONWriter propertyValue;
	private JSONWriter startArray;
	private JSONWriter arrayValue;
	private JSONWriter mini;
	private JSONWriter maxi;
	private JSONWriter nullP;

	@Before
	public void setup() {
		JSONStreamContext so = new JSONStreamContext(null, 1001);
		JSONStreamContext pk = new JSONStreamContext(null, 1002);
		JSONStreamContext pv = new JSONStreamContext(null, 1003);
		JSONStreamContext sa = new JSONStreamContext(null, 1004);
		JSONStreamContext av = new JSONStreamContext(null, 1005);
		JSONStreamContext mi = new JSONStreamContext(null, 1000);
		JSONStreamContext ma = new JSONStreamContext(null, 1006);
		Writer out = new StringWriter();
	    JSONStreamContext startObjectContext = new JSONStreamContext(so, 1001);
		this.startObject = new JSONWriter(out, startObjectContext);
		JSONStreamContext propertyKeyContext = new JSONStreamContext(pk, 1002);
		this.propertyKey = new JSONWriter(out, propertyKeyContext);
		JSONStreamContext propertyValueContext = new JSONStreamContext(pv, 1003);
		this.propertyValue = new JSONWriter(out, propertyValueContext);
		JSONStreamContext startArrayContext = new JSONStreamContext(sa, 1004);
		this.startArray = new JSONWriter(out, startArrayContext);
		JSONStreamContext arrayValueContext = new JSONStreamContext(av, 1005);
		this.arrayValue = new JSONWriter(out, arrayValueContext);
		JSONStreamContext miniContext = new JSONStreamContext(mi, 1000);
		this.mini = new JSONWriter(out, miniContext);
		JSONStreamContext maxiContext = new JSONStreamContext(ma, 1006);
		this.maxi = new JSONWriter(out, maxiContext);
		JSONStreamContext nullPContext = new JSONStreamContext(null, 1001);
		this.nullP = new JSONWriter(out, nullPContext);
	}
	
	// BEGIN STRUCTURE
	
	// ISP
	
	@Test
	public void testBeginStructureMin() {
		startObject.beginStructure();
		Writer out = startObject.getWriter();
		assertTrue(out.toString().equals(""));
	}
	
	@Test
	public void testBeginStructureMiddle() {
		propertyKey.beginStructure();
		Writer out = propertyKey.getWriter();
		assertTrue(out.toString().equals(":"));
	}
	
	@Test
	public void testBeginStructureMax() {
		arrayValue.beginStructure();
		Writer out = arrayValue.getWriter();
		assertTrue(out.toString().equals(","));
	}
	
	@Test(expected = JSONException.class)
	public void testBeginStructureBeforeMin() {
		mini.beginStructure();
	}
	
	@Test(expected = JSONException.class)
	public void testBeginStructureAfterMax() {
		maxi.beginStructure();
	}
	
	// CFG / DFG
	
	@Test(expected = JSONException.class)
	public void testBeginStructureAllNodes() {
		startObject.beginStructure();
		Writer out = startObject.getWriter();
		assertTrue(out.toString().equals(""));
		propertyKey.beginStructure();
		out = propertyKey.getWriter();
		assertTrue(out.toString().equals(":"));
		propertyValue.beginStructure();
		out = propertyValue.getWriter();
		assertTrue(out.toString().equals(""));
		startArray.beginStructure();
		out = startArray.getWriter();
		assertTrue(out.toString().equals(""));
		arrayValue.beginStructure();
		out = arrayValue.getWriter();
		assertTrue(out.toString().equals(","));
	}
	
	@Test(expected = JSONException.class)
	public void testBeginStructureAllNUses() {
		startObject.beginStructure();
		Writer out = startObject.getWriter();
		assertTrue(out.toString().equals(""));
		propertyKey.beginStructure();
		out = propertyKey.getWriter();
		assertTrue(out.toString().equals(":"));
		propertyValue.beginStructure();
		out = propertyValue.getWriter();
		assertTrue(out.toString().equals(""));
		startArray.beginStructure();
		out = startArray.getWriter();
		assertTrue(out.toString().equals(""));
		arrayValue.beginStructure();
		out = arrayValue.getWriter();
		assertTrue(out.toString().equals(","));
	}
	
	// Logic
	
	@Test(expected = JSONException.class)
	public void testBeginStructureAllPredicates() {
		startObject.beginStructure();
		Writer out = startObject.getWriter();
		assertTrue(out.toString().equals(""));
		propertyKey.beginStructure();
		out = propertyKey.getWriter();
		assertTrue(out.toString().equals(":"));
		propertyValue.beginStructure();
		out = propertyValue.getWriter();
		assertTrue(out.toString().equals(""));
		startArray.beginStructure();
		out = startArray.getWriter();
		assertTrue(out.toString().equals(""));
		arrayValue.beginStructure();
		out = arrayValue.getWriter();
		assertTrue(out.toString().equals(","));
	}
	
	// Mutation
	
	@Test
	public void testBeginStructureMutatedSwapTopTwo() {
		propertyKey.mutatedBeginStructureSwitchTopTwo();
		Writer out = propertyKey.getWriter();
		assertFalse(out.toString().equals(":"));
		arrayValue.mutatedBeginStructureSwitchTopTwo();
		out = arrayValue.getWriter();
		assertFalse(out.toString().equals(","));
	}
	
	@Test
	public void testBeginStructureMutatedSwapMiddleTwo() {
		startObject.mutatedBeginStructureSwitchMiddleTwo();
		Writer out = startObject.getWriter();
		assertTrue(out.toString().equals(""));
		startArray.mutatedBeginStructureSwitchMiddleTwo();
		out = startArray.getWriter();
		assertTrue(out.toString().equals(""));
	}
	
	// END STRUCTURE
	
	// ISP
	
	@Test
	public void testEndStructureMin() {
		startObject.endStructure();
		JSONStreamContext c = startObject.getContext();
		assertEquals(c.getState(), 1002);
	}
	
	@Test
	public void testEndStructureMax() {
		arrayValue.endStructure();
		JSONStreamContext c = arrayValue.getContext();
		assertEquals(c.getState(), 1005);
	}
	
	@Test
	public void testEndStructureMid() {
		propertyValue.endStructure();
		JSONStreamContext c = propertyValue.getContext();
		assertEquals(c.getState(), 1003);
	}
	
	@Test
	public void testEndStructureBeforeMin() {
		mini.endStructure();
		JSONStreamContext c = mini.getContext();
		assertEquals(c.getState(), 1000);
	}
	
	@Test
	public void testEndStructureAfterMax() {
		maxi.endStructure();
		JSONStreamContext c = maxi.getContext();
		assertEquals(c.getState(), 1006);
	}
	
	// CFG DFG
	
	public void testEndStructureAllNodes() {
		startObject.endStructure();
		JSONStreamContext c = startObject.getContext();
		assertEquals(c.getState(), 1002);
		arrayValue.endStructure();
		c = arrayValue.getContext();
		assertEquals(c.getState(), 1005);
		propertyValue.endStructure();
		c = propertyValue.getContext();
		assertEquals(c.getState(), 1003);
		nullP.endStructure();
		c = nullP.getContext();
		assertEquals(c, null);
		propertyKey.endStructure();
		c = propertyKey.getContext();
		assertEquals(c.getState(), 1003);
		startArray.endStructure();
		c = startArray.getContext();
		assertEquals(c.getState(), 1005);
	}
	
	public void testEndStructureAllUses() {
		startObject.endStructure();
		JSONStreamContext c = startObject.getContext();
		assertEquals(c.getState(), 1002);
		arrayValue.endStructure();
		c = arrayValue.getContext();
		assertEquals(c.getState(), 1005);
		propertyValue.endStructure();
		c = propertyValue.getContext();
		assertEquals(c.getState(), 1003);
		nullP.endStructure();
		c = nullP.getContext();
		assertEquals(c, null);
		propertyKey.endStructure();
		c = propertyKey.getContext();
		assertEquals(c.getState(), 1003);
		startArray.endStructure();
		c = startArray.getContext();
		assertEquals(c.getState(), 1005);
	}
	
	// Logic
	
	public void testEndStructureAllPredicates() {
		startObject.endStructure();
		JSONStreamContext c = startObject.getContext();
		assertEquals(c.getState(), 1002);
		arrayValue.endStructure();
		c = arrayValue.getContext();
		assertEquals(c.getState(), 1005);
		propertyValue.endStructure();
		c = propertyValue.getContext();
		assertEquals(c.getState(), 1003);
		nullP.endStructure();
		c = nullP.getContext();
		assertEquals(c, null);
		propertyKey.endStructure();
		c = propertyKey.getContext();
		assertEquals(c.getState(), 1003);
		startArray.endStructure();
		c = startArray.getContext();
		assertEquals(c.getState(), 1005);
	}
	
	// Mutation
	
	@Test
	public void testEndStructureMutatedEquals() {
		startObject.mutatedEndStructureSwapEquals();
		JSONStreamContext c = startObject.getContext();
		assertEquals(c.getState(), 1001);
	}
	
	@Test(expected = NullPointerException.class)
	public void testEndStructureMutatedException() {
		nullP.mutatedEndStructureSwapEquals();
	}
	
	@Test
	public void testEndStructureMutatedCases() {
		startArray.mutatedEndStructureSwapTopCases();
		JSONStreamContext c = startArray.getContext();
		assertNotEquals(c.getState(), 1005);
		propertyKey.mutatedEndStructureSwapTopCases();
		c = propertyKey.getContext();
		assertNotEquals(c.getState(), 1003);
	}
	
}
