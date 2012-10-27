package jp.michikusa.chitose.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoolsTest{
	@Test
	public void not(){
		assertTrue(Bools.not(false));
		assertFalse(Bools.not(true));
	}
	
	@Test
	public void or_2_args(){
		assertTrue(Bools.or(true, true));
		assertTrue(Bools.or(true, false));
		assertTrue(Bools.or(false, true));
		assertFalse(Bools.or(false, false));
	}
	
	@Test
	public void or_3_args(){
		assertTrue(Bools.or(true, true, true));
		assertTrue(Bools.or(false, true, true));
		assertTrue(Bools.or(true, false, true));
		assertTrue(Bools.or(true, true, false));
		assertTrue(Bools.or(false, false, true));
		assertTrue(Bools.or(false, true, false));
		assertTrue(Bools.or(true, false, false));
		assertFalse(Bools.or(false, false, false));
	}
	
	@Test
	public void and_2_args(){
		assertTrue(Bools.and(true, true));
		assertFalse(Bools.and(true, false));
		assertFalse(Bools.and(false, true));
		assertFalse(Bools.and(false, false));
	}
	
	@Test
	public void and_3_args(){
		assertTrue(Bools.and(true, true, true));
		assertFalse(Bools.and(false, true, true));
		assertFalse(Bools.and(false, false, true));
		assertFalse(Bools.and(false, true, false));
		assertFalse(Bools.and(true, false, true));
		assertFalse(Bools.and(true, false, false));
		assertFalse(Bools.and(true, true, false));
		assertFalse(Bools.and(false, false, false));
	}
	
	@Test
	public void xor_2_args(){
		assertFalse(Bools.xor(true, true));
		assertTrue(Bools.xor(false, true));
		assertTrue(Bools.xor(true, false));
		assertFalse(Bools.xor(false, false));
	}
	
	@Test
	public void xor_3_args(){
		assertTrue(Bools.xor(true, true, true));
		assertFalse(Bools.xor(false, true, true));
		assertTrue(Bools.xor(false, false, true));
		assertTrue(Bools.xor(false, true, false));
		assertFalse(Bools.xor(true, false, true));
		assertTrue(Bools.xor(true, false, false));
		assertFalse(Bools.xor(true, true, false));
		assertFalse(Bools.xor(false, false, false));
	}
	
	@Test
	public void nor_2_args(){
		assertFalse(Bools.nor(true, true));
		assertFalse(Bools.nor(true, false));
		assertFalse(Bools.nor(false, true));
		assertTrue(Bools.nor(false, false));
	}
	
	@Test
	public void nor_3_args(){
		assertFalse(Bools.nor(true, true, true));
		assertFalse(Bools.nor(false, true, true));
		assertFalse(Bools.nor(true, false, true));
		assertFalse(Bools.nor(true, true, false));
		assertFalse(Bools.nor(false, false, true));
		assertFalse(Bools.nor(false, true, false));
		assertFalse(Bools.nor(true, false, false));
		assertTrue(Bools.nor(false, false, false));
	}
	
	@Test
	public void nand_2_args(){
		assertFalse(Bools.nand(true, true));
		assertTrue(Bools.nand(true, false));
		assertTrue(Bools.nand(false, true));
		assertTrue(Bools.nand(false, false));
	}
	
	@Test
	public void nand_3_args(){
		assertFalse(Bools.nand(true, true, true));
		assertTrue(Bools.nand(false, true, true));
		assertTrue(Bools.nand(false, false, true));
		assertTrue(Bools.nand(false, true, false));
		assertTrue(Bools.nand(true, false, true));
		assertTrue(Bools.nand(true, false, false));
		assertTrue(Bools.nand(true, true, false));
		assertTrue(Bools.nand(false, false, false));
	}
}
