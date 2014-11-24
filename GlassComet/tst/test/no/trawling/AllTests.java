package test.no.trawling;

import static org.junit.Assert.*;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import no.trawling.library.*;

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
				Assert.fail();
				e.printStackTrace();
			} catch (InvalidInputException e) {
				Assert.fail();
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
				Assert.fail();
				e.printStackTrace();
			} catch (InvalidInputException e) {
				Assert.fail();
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
				Assert.fail();
				e.printStackTrace();
			} catch (InvalidInputException e) {
				Assert.fail();
				e.printStackTrace();
			}
		}
		assertEquals(true, blocked);
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			Assert.fail();
			e.printStackTrace();
		}
		try {
			blocked = password.isBlocked("TestPassword");
		} catch (IllegalStateException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (InvalidInputException e) {
			Assert.fail();
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
				Assert.fail();
				e.printStackTrace();
			} catch (InvalidInputException e) {
				Assert.fail();
				e.printStackTrace();
			}
		}
		assertEquals(true, blocked);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			Assert.fail();
			e.printStackTrace();
		}
		try {
			blocked = password.isBlocked("TestPassword");
		} catch (IllegalStateException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (InvalidInputException e) {
			Assert.fail();
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
				Assert.fail();
				e.printStackTrace();
			} catch (InvalidInputException e) {
				Assert.fail();
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
				Assert.fail();
				e.printStackTrace();
			} catch (InvalidInputException e) {
				Assert.fail();
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
				Assert.fail();
				e.printStackTrace();
			} catch (InvalidInputException e) {
				Assert.fail();
				e.printStackTrace();
			}
		}
		assertEquals(true, blocked);
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			Assert.fail();
			e.printStackTrace();
		}
		try {
			blocked = password.isBlocked(123456);
		} catch (IllegalStateException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (InvalidInputException e) {
			Assert.fail();
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
				Assert.fail();
				e.printStackTrace();
			} catch (InvalidInputException e) {
				Assert.fail();
				e.printStackTrace();
			}
		}
		assertEquals(true, blocked);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			Assert.fail();
			e.printStackTrace();
		}
		try {
			blocked = password.isBlocked(123456);
		} catch (IllegalStateException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (InvalidInputException e) {
			Assert.fail();
			e.printStackTrace();
		}
		assertEquals(true, blocked);
	}

	@Test
	public void testIntegerDirectionPerformanceSameEntry(){
		//Minumum hits/second acceptable for a production service
		int minumumPerformance = 5000;

		int numberOfHits = 50000000;
		IntegerDirection noTrawling = new IntegerDirection(10, 10, 10);
		ThreadMXBean threadMXBean=ManagementFactory.getThreadMXBean();
		long startTime = threadMXBean.getCurrentThreadCpuTime();
		for (int i = 0; i < numberOfHits; i++) {
			try {
				noTrawling.isBlocked(123456);
			} catch (IllegalStateException e) {
				Assert.fail();
				e.printStackTrace();
			} catch (InvalidInputException e) {
				Assert.fail();
				e.printStackTrace();
			}
		}
		long endTime = threadMXBean.getCurrentThreadCpuTime();
		double difference = (endTime - startTime) / 1000000000.0;
		double hitsPerSecond = numberOfHits/difference;
		assertTrue(hitsPerSecond>minumumPerformance);
	}

	@Test
	public void testStringDirectionPerformanceSameEntry(){
		//Minumum hits/second acceptable for a production service
		int minumumPerformance = 5000;

		int numberOfHits = 50000000;
		StringDirection noTrawling = new StringDirection(10, 10, 10);
		ThreadMXBean threadMXBean=ManagementFactory.getThreadMXBean();
		long startTime = threadMXBean.getCurrentThreadCpuTime();
		for (int i = 0; i < numberOfHits; i++) {
			try {
				noTrawling.isBlocked("123456");
			} catch (IllegalStateException e) {
				Assert.fail();
				e.printStackTrace();
			} catch (InvalidInputException e) {
				Assert.fail();
				e.printStackTrace();
			}
		}
		long endTime = threadMXBean.getCurrentThreadCpuTime();
		double difference = (endTime - startTime) / 1000000000.0;
		double hitsPerSecond = numberOfHits/difference;
		assertTrue(hitsPerSecond>minumumPerformance);
	}

	@Test
	public void testIntegerDirectionPerformanceDifferentEntry(){
		//Minumum hits/second acceptable for a production service
		int minumumPerformance = 5000;

		int numberOfHits = 5000000;
		IntegerDirection noTrawling = new IntegerDirection(10, 10, 10);
		ThreadMXBean threadMXBean=ManagementFactory.getThreadMXBean();
		long startTime = threadMXBean.getCurrentThreadCpuTime();
		for (int i = 0; i < numberOfHits; i++) {
			try {
				noTrawling.isBlocked(123456 + i);
			} catch (IllegalStateException e) {
				Assert.fail();
				e.printStackTrace();
			} catch (InvalidInputException e) {
				Assert.fail();
				e.printStackTrace();
			}
		}
		long endTime = threadMXBean.getCurrentThreadCpuTime();
		double difference = (endTime - startTime) / 1000000000.0;
		double hitsPerSecond = numberOfHits/difference;
		assertTrue(hitsPerSecond>minumumPerformance);
	}

	@Test
	public void testStringDirectionPerformanceDifferentEntry(){
		//Minumum hits/second acceptable for a production service
		int minumumPerformance = 5000;

		int numberOfHits = 5000000;
		StringDirection noTrawling = new StringDirection(10, 10, 10);
		ThreadMXBean threadMXBean=ManagementFactory.getThreadMXBean();
		long startTime = threadMXBean.getCurrentThreadCpuTime();
		for (int i = 0; i < numberOfHits; i++) {
			try {
				noTrawling.isBlocked("123456" + i);
			} catch (IllegalStateException e) {
				Assert.fail();
				e.printStackTrace();
			} catch (InvalidInputException e) {
				Assert.fail();
				e.printStackTrace();
			}
		}
		long endTime = threadMXBean.getCurrentThreadCpuTime();
		double difference = (endTime - startTime) / 1000000000.0;
		double hitsPerSecond = numberOfHits/difference;
		assertTrue(hitsPerSecond>minumumPerformance);
	}

	@Test
	public void testStringDirectionCleanupAfterBlocked(){
		StringDirection password = new StringDirection(5, 5, 5);
		int x = 0;
		while (x++ < 7){
			try {
				password.isBlocked("TestPassword");
			} catch (IllegalStateException e) {
				Assert.fail();
				e.printStackTrace();
			} catch (InvalidInputException e) {
				Assert.fail();
				e.printStackTrace();
			}
		}
		password.cleanUp();
		assertEquals(1, password.getNumberOfRecords());
		try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			Assert.fail();
			e.printStackTrace();
		}
		password.cleanUp();
		assertEquals(0, password.getNumberOfRecords());
	}

	@Test
	public void testIntegerDirectionCleanupAfterBlocked(){
		IntegerDirection password = new IntegerDirection(5, 5, 5);
		int x = 0;
		while (x++ < 7){
			try {
				password.isBlocked(123456);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (InvalidInputException e) {
				Assert.fail();
				e.printStackTrace();
			}
		}
		password.cleanUp();
		assertEquals(1, password.getNumberOfRecords());
		try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			Assert.fail();
			e.printStackTrace();
		}
		password.cleanUp();
		assertEquals(0, password.getNumberOfRecords());
	}

	@Test
	public void testStringDirectionCleanupNotBlocked(){
		StringDirection password = new StringDirection(5, 5, 5);
		int x = 0;
		while (x++ < 2){
			try {
				password.isBlocked("TestPassword");
			} catch (IllegalStateException e) {
				Assert.fail();
				e.printStackTrace();
			} catch (InvalidInputException e) {
				Assert.fail();
				e.printStackTrace();
			}
		}
		password.cleanUp();
		assertEquals(1, password.getNumberOfRecords());
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			Assert.fail();
			e.printStackTrace();
		}
		password.cleanUp();
		assertEquals(0, password.getNumberOfRecords());
	}

	@Test
	public void testIntegerDirectionCleanupNotBlocked(){
		IntegerDirection password = new IntegerDirection(5, 5, 5);
		int x = 0;
		while (x++ < 2){
			try {
				password.isBlocked(123456);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (InvalidInputException e) {
				Assert.fail();
				e.printStackTrace();
			}
		}
		password.cleanUp();
		assertEquals(1, password.getNumberOfRecords());
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			Assert.fail();
			e.printStackTrace();
		}
		password.cleanUp();
		assertEquals(0, password.getNumberOfRecords());
	}

	@Test
	public void testIntegerDirectionPerformanceMultipleHitLevelsSameEntry(){
		System.out.println("\nInteger direction with the same value.");
		//Minumum hits/second acceptable for a production service
		int minumumPerformance = 5000;
		double hitsPerSecond = 0;
		int numberOfHits = 1;
		while (numberOfHits <= 10000000){
			IntegerDirection noTrawling = new IntegerDirection(10, 10, 10);
			ThreadMXBean threadMXBean=ManagementFactory.getThreadMXBean();
			long startTime = threadMXBean.getCurrentThreadCpuTime();
			for (int i = 0; i < numberOfHits; i++) {
				try {
					noTrawling.isBlocked(123456);
				} catch (IllegalStateException e) {
					Assert.fail();
					e.printStackTrace();
				} catch (InvalidInputException e) {
					Assert.fail();
					e.printStackTrace();
				}
			}
			long endTime = threadMXBean.getCurrentThreadCpuTime();
			double difference = (endTime - startTime) / 1000000000.0;
			hitsPerSecond = numberOfHits/difference;
			System.out.printf("Hits per second with " + numberOfHits + " hits: %.0f\n", hitsPerSecond);
			numberOfHits = numberOfHits * 10;
		}
		assertTrue(hitsPerSecond>minumumPerformance);
	}

	@Test
	public void testStringDirectionPerformanceMultipleHitLevelsSameEntry(){
		System.out.println("\nString direction with the same value.");
		//Minumum hits/second acceptable for a production service
		int minumumPerformance = 5000;
		double hitsPerSecond = 0;
		int numberOfHits = 1;
		while (numberOfHits <= 10000000){
			StringDirection noTrawling = new StringDirection(10, 10, 10);
			ThreadMXBean threadMXBean=ManagementFactory.getThreadMXBean();
			long startTime = threadMXBean.getCurrentThreadCpuTime();
			for (int i = 0; i < numberOfHits; i++) {
				try {
					noTrawling.isBlocked("123456");
				} catch (IllegalStateException e) {
					Assert.fail();
					e.printStackTrace();
				} catch (InvalidInputException e) {
					Assert.fail();
					e.printStackTrace();
				}
			}
			long endTime = threadMXBean.getCurrentThreadCpuTime();
			double difference = (endTime - startTime) / 1000000000.0;
			hitsPerSecond = numberOfHits/difference;
			System.out.printf("Hits per second with " + numberOfHits + " hits: %.0f\n", hitsPerSecond);
			numberOfHits = numberOfHits * 10;
		}
		assertTrue(hitsPerSecond>minumumPerformance);
	}

	@Test
	public void testIntegerDirectionPerformanceMultipleHitLevelsDifferentEntry(){
		System.out.println("\nInteger direction with different values.");
		//Minumum hits/second acceptable for a production service
		int minumumPerformance = 5000;
		double hitsPerSecond = 0;
		int numberOfHits = 1;
		while (numberOfHits <= 10000000){
			IntegerDirection noTrawling = new IntegerDirection(10, 10, 10);
			ThreadMXBean threadMXBean=ManagementFactory.getThreadMXBean();
			long startTime = threadMXBean.getCurrentThreadCpuTime();
			for (int i = 0; i < numberOfHits; i++) {
				try {
					noTrawling.isBlocked(123456 + i);
				} catch (IllegalStateException e) {
					Assert.fail();
					e.printStackTrace();
				} catch (InvalidInputException e) {
					Assert.fail();
					e.printStackTrace();
				}
			}
			long endTime = threadMXBean.getCurrentThreadCpuTime();
			double difference = (endTime - startTime) / 1000000000.0;
			hitsPerSecond = (double)numberOfHits/difference;
			System.out.printf("Hits per second with " + numberOfHits + " hits: %.0f\n", hitsPerSecond);
			numberOfHits = numberOfHits * 10;
		}
		assertTrue(hitsPerSecond>minumumPerformance);
	}

	@Test
	public void testStringDirectionPerformanceMultipleHitLevelsDifferentEntry(){
		System.out.println("\nString direction with different values.");
		//Minumum hits/second acceptable for a production service
		int minumumPerformance = 5000;
		double hitsPerSecond = 0;
		int numberOfHits = 1;
		while (numberOfHits <= 10000000){
			StringDirection noTrawling = new StringDirection(10, 10, 10);
			ThreadMXBean threadMXBean=ManagementFactory.getThreadMXBean();
			long startTime = threadMXBean.getCurrentThreadCpuTime();
			for (int i = 0; i < numberOfHits; i++) {
				try {
					noTrawling.isBlocked("123456" + i);
				} catch (IllegalStateException e) {
					Assert.fail();
					e.printStackTrace();
				} catch (InvalidInputException e) {
					Assert.fail();
					e.printStackTrace();
				}
			}
			long endTime = threadMXBean.getCurrentThreadCpuTime();
			double difference = (endTime - startTime) / 1000000000.0;
			hitsPerSecond = numberOfHits/difference;
			System.out.printf("Hits per second with " + numberOfHits + " hits: %.0f\n", hitsPerSecond);
			numberOfHits = numberOfHits * 10;
		}
		assertTrue(hitsPerSecond>minumumPerformance);
	}
}
