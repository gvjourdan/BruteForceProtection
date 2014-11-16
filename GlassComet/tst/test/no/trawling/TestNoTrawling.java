package test.no.trawling;

import no.trawling.NoTrawling;

public class TestNoTrawling {
	public static void main(String [] args){
		
		NoTrawling noTrawling = new NoTrawling();
		while (true){
			System.out.println(noTrawling.isBlockedUsername("test1"));
			System.out.println(noTrawling.isBlockedUsername("test1"));
			System.out.println(noTrawling.isBlockedUsername("test1"));
			System.out.println(noTrawling.isBlockedUsername("test1"));
			System.out.println(noTrawling.isBlockedUsername("test1"));
			System.out.println(noTrawling.isBlockedUsername("test1"));
			System.out.println(noTrawling.isBlockedUsername("test1"));
			System.out.println(noTrawling.isBlockedUsername("test1"));
			System.out.println(noTrawling.isBlockedUsername("test1"));
			System.out.println(noTrawling.isBlockedUsername("test1"));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
