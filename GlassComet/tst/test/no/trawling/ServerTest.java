package test.no.trawling;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ServerTest {
	public static Test suite() throws InterruptedException, IOException {
		TestSuite suite = new TestSuite(AllTests.class.getName());

		int x = 0;
		while (x++ < 1000000){
			callDirection();
		}
		//$JUnit-END$
		return suite;
	}
	private static void callDirection() throws IOException{
	    URL url = new URL("http://localhost:8080/GlassComet/GlassComet");
	    URLConnection conn = url.openConnection();
	    conn.setDoOutput(true);
	    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

	    writer.write("password=a");
	    writer.flush();
	    String line;
	    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    while ((line = reader.readLine()) != null) {
	      System.out.println(line);
	    }
	    writer.close();
	    reader.close();
	}

}
