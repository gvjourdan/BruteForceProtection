package test.no.trawling;

import static org.junit.Assert.*;
import no.trawling.*;

import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.junit.*;

public class AllTests {
	
	@Test
	public void testStringDirectionBlocksAfterMinimumHits(){
		StringDirection password = new StringDirection(5, 5, 5);
		int x = 0;
		boolean blocked = false;
		while (x++ < 7){
			try {
				blocked = password.isBlocked("TestPassword");
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		assertEquals(true, blocked);
	}
	
	@Test
	public void testStringDirectionDoesNotBlock(){
		StringDirection password = new StringDirection(5, 5, 5);
		int x = 0;
		boolean blocked = false;
		while (x++ < 4){
			try {
				blocked = password.isBlocked("TestPassword");
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		assertEquals(false, blocked);
	}
	
	@Test
	public void testStringDirectionUnblocks(){
		StringDirection password = new StringDirection(5, 5, 5);
		int x = 0;
		boolean blocked = false;
		while (x++ < 8){
			try {
				blocked = password.isBlocked("TestPassword");
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		assertEquals(true, blocked);
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			blocked = password.isBlocked("TestPassword");
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(false, blocked);
	}
	
	@Test
	public void testStringDirectionDelay(){
		StringDirection password = new StringDirection(5, 5, 5);
		int x = 0;
		boolean blocked = false;
		while (x++ < 8){
			try {
				blocked = password.isBlocked("TestPassword");
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		assertEquals(true, blocked);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			blocked = password.isBlocked("TestPassword");
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, blocked);
	}
	
	@Test
	public void testIntegerDirectionBlocksAfterMinimumHits(){
		IntegerDirection password = new IntegerDirection(5, 5, 5);
		int x = 0;
		boolean blocked = false;
		while (x++ < 7){
			try {
				blocked = password.isBlocked(123456);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		assertEquals(true, blocked);
	}
	
	@Test
	public void testIntegerDirectionDoesNotBlock(){
		IntegerDirection password = new IntegerDirection(5, 5, 5);
		int x = 0;
		boolean blocked = false;
		while (x++ < 4){
			try {
				blocked = password.isBlocked(123456);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		assertEquals(false, blocked);
	}
	
	@Test
	public void testIntegerDirectionUnblocks(){
		IntegerDirection password = new IntegerDirection(5, 5, 5);
		int x = 0;
		boolean blocked = false;
		while (x++ < 8){
			try {
				blocked = password.isBlocked(123456);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		assertEquals(true, blocked);
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			blocked = password.isBlocked(123456);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(false, blocked);
	}
	
	@Test
	public void testIntegerDirectionDelay(){
		IntegerDirection password = new IntegerDirection(5, 5, 5);
		int x = 0;
		boolean blocked = false;
		while (x++ < 8){
			try {
				blocked = password.isBlocked(123456);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		assertEquals(true, blocked);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			blocked = password.isBlocked(123456);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, blocked);
	}
}
