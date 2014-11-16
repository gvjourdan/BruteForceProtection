package test.no.trawling;

import no.trawling.IntegerDirection;
import java.lang.management.*;

import org.eclipse.jdt.core.compiler.InvalidInputException;

public class TestIntegerDirection {
	public static void main(String [] args){
		int numberOfHits = 50000000;
		IntegerDirection noTrawling = new IntegerDirection(10, 10, 10);
		ThreadMXBean threadMXBean=ManagementFactory.getThreadMXBean();
		long startTime = threadMXBean.getCurrentThreadCpuTime();
		for (int i = 0; i < numberOfHits; i++) {
		try {
			noTrawling.isBlocked(i);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		long endTime = threadMXBean.getCurrentThreadCpuTime();
		double difference = (endTime - startTime) / 1000000000.0;
		double hitsPerSecond = numberOfHits/difference;
		System.out.println("Hits per second: " + String.format("%.12f", hitsPerSecond));
	}
}