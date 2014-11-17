package test.no.trawling;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import no.trawling.NoTrawling;

public class TestNoTrawling {
	public static void main(String [] args){
		int numberOfHits = 10000000;
		NoTrawling noTrawling = new NoTrawling();
		
		ThreadMXBean threadMXBean=ManagementFactory.getThreadMXBean();
		long startTime = threadMXBean.getCurrentThreadCpuTime();
		for (int i = 0; i < numberOfHits; i++) {
		try {
			noTrawling.isBlockedIpPasswordUsername("1.1.1.1", "test"+ i, "test" + i);
		} catch (IllegalStateException e) {
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
