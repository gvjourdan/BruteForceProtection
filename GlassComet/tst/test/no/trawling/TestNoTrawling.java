package test.no.trawling;

import no.trawling.NoTrawling;

public class TestNoTrawling {
	public static void main(String [] args){
		
		NoTrawling noTrawling = new NoTrawling();
		System.out.println(noTrawling.isBlockedUsername("test1"));
		
	}
}
