package no.trawling.server;


import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.trawling.library.IntegerDirection;
import no.trawling.library.NoTrawling;
import no.trawling.library.StringDirection;

import org.eclipse.jdt.core.compiler.InvalidInputException;

/**
 * Servlet implementation class GlassComet
 */
@WebServlet("/GlassComet")
public class GlassComet extends HttpServlet {
	private IntegerDirection id;
	private StringDirection password;
	private StringDirection username;
	private StringDirection ip;
	private StringDirection sqanswer;
	private static final long serialVersionUID = 1L;
	private long startTime;

    public GlassComet() {
        super();
        startTime = System.currentTimeMillis() / 1000l;
		String defaultWindowSize = "5";
		String defaultMaxHits = "5";
		String defaultTimePenalty = "5";
		String defaultGarbageCollectTimeInSeconds = "5";
		
		Properties prop = new Properties();
		String propFileName = "config.properties";
		
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TODO handle empty string in config file
		int windowSizeId = Integer.parseInt(prop.getProperty("windowSizeId",defaultWindowSize));
		int maxHitsId = Integer.parseInt(prop.getProperty("maxHitsId",defaultMaxHits));
		int timePenaltyId = Integer.parseInt(prop.getProperty("timePenaltyId",defaultTimePenalty));
		int windowSizePassword = Integer.parseInt(prop.getProperty("windowSizePassword",defaultWindowSize));
		int maxHitsPassword = Integer.parseInt(prop.getProperty("maxHitsPassword",defaultMaxHits));
		int timePenaltyPassword = Integer.parseInt(prop.getProperty("timePenaltyPassword",defaultTimePenalty));
		int windowSizeUsername = Integer.parseInt(prop.getProperty("windowSizeUsername",defaultWindowSize));
		int maxHitsUsername = Integer.parseInt(prop.getProperty("maxHitsUsername",defaultMaxHits));
		int timePenaltyUsername = Integer.parseInt(prop.getProperty("timePenaltyUsername",defaultTimePenalty));
		int windowSizeIp = Integer.parseInt(prop.getProperty("windowSizeIp",defaultWindowSize));
		int maxHitsIp = Integer.parseInt(prop.getProperty("maxHitsIp",defaultMaxHits));
		int timePenaltyIp = Integer.parseInt(prop.getProperty("timePenaltyIp",defaultTimePenalty));
		int windowSizeSqanswer = Integer.parseInt(prop.getProperty("windowSizeSqanswer",defaultWindowSize));
		int maxHitsSqanswer = Integer.parseInt(prop.getProperty("maxHitsSqanswer",defaultMaxHits));
		int timePenaltySqanswer = Integer.parseInt(prop.getProperty("timePenaltySqanswer",defaultTimePenalty));
		int garbageCollectTimeInSeconds = Integer.parseInt(prop.getProperty("garbageCollectTimeInSeconds",defaultGarbageCollectTimeInSeconds));
		
		id = new IntegerDirection(windowSizeId,maxHitsId,timePenaltyId);
		password = new StringDirection(windowSizePassword,maxHitsPassword,timePenaltyPassword);
		username = new StringDirection(windowSizeUsername,maxHitsUsername,timePenaltyUsername);
		ip = new StringDirection(windowSizeIp,maxHitsIp,timePenaltyIp);
		sqanswer = new StringDirection(windowSizeSqanswer,maxHitsSqanswer,timePenaltySqanswer);

		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		class GarbageCleanup implements Runnable {
			public void run() {
				id.cleanUp();
				password.cleanUp();
				username.cleanUp();
				ip.cleanUp();
				sqanswer.cleanUp();
			}
		};

		GarbageCleanup garbageCleanup = new GarbageCleanup();
		scheduler.scheduleAtFixedRate(garbageCleanup, garbageCollectTimeInSeconds, garbageCollectTimeInSeconds, SECONDS);
	}
	
	/**
	 * Checks if any of the supplied ip address, password and username is blocked.
	 * 
	 * @param ip
	 * @param password
	 * @param userName
	 * @return boolean: True if any of parameters are blocked, False if all parameters are not blocked
	 */
	public boolean isBlockedIpPasswordUsername(String ip, String password, String userName){
		boolean isBlocked = false;
		try {
			isBlocked = this.password.isBlocked(userName) || this.username.isBlocked(password) || this.ip.isBlocked(ip);
		} catch (IllegalStateException | InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isBlocked;
	}
	
	/**
	 * Checks if either of the supplied ip address and password reset security question answer is blocked.
	 * 
	 * @param ip
	 * @param answer
	 * @return boolean: True if any of parameters are blocked, False if all parameters are not blocked
	 */
	public boolean isBlockedIpSecurityQuestionAnswer(String ip, String answer){
		boolean isBlocked = false;
		try {
			isBlocked = this.ip.isBlocked(ip) || this.sqanswer.isBlocked(answer);
		} catch (IllegalStateException | InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isBlocked;
	}
	
	/**
	 * Checks if the supplied password is blocked.
	 * 
	 * @param key
	 * @return boolean: True if any of parameters are blocked, False if all parameters are not blocked
	 */
	public boolean isBlockedPassword(String key){
		boolean isBlocked = false;
		try {
			isBlocked = password.isBlocked(key);
		} catch (IllegalStateException | InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isBlocked;
	}
	
	/**
	 * Checks if the supplied username is blocked.
	 * 
	 * @param key
	 * @return boolean: True if any of parameters are blocked, False if all parameters are not blocked
	 */
	public boolean isBlockedUsername(String key){
		boolean isBlocked = false;
		try {
			isBlocked = username.isBlocked(key);
		} catch (IllegalStateException | InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isBlocked;
	}
	
	/**
	 * Checks if the supplied ip address is blocked.
	 * 
	 * @param key
	 * @return boolean: True if any of parameters are blocked, False if all parameters are not blocked
	 */
	public boolean isBlockedIp(String key){
		boolean isBlocked = false;
		try {
			isBlocked = ip.isBlocked(key);
		} catch (IllegalStateException | InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isBlocked;
	}
	
	/**
	 * Checks if the supplied id is blocked.
	 * 
	 * @param key
	 * @return boolean: True if any of parameters are blocked, False if all parameters are not blocked
	 */
	public boolean isBlockedId(int key){
		boolean isBlocked = false;
		try {
			isBlocked = id.isBlocked(key);
		} catch (IllegalStateException | InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isBlocked;
	}
	
	/**
	 * Checks if the supplied password reset question answer is blocked.
	 * 
	 * @param key
	 * @return boolean: True if any of parameters are blocked, False if all parameters are not blocked
	 */
	public boolean isBlockedSecurityQuestionAnswer(String key){
		boolean isBlocked = false;
		try {
			isBlocked = sqanswer.isBlocked(key);
		} catch (IllegalStateException | InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isBlocked;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        PrintWriter out = response.getWriter();

        String docType =
        "<!doctype html public \"-//w3c//dtd html 4.0 " +
        "transitional//en\">\n";
        out.println(docType +
          "<html>\n" +
          "<head><title>" + "GlassComet" + "</title></head>\n" +
          "<body bgcolor=\"#f0f0f0\">\n" +
          "<h1 align=\"center\">" + "Hit count:" + "</h1>\n" +
          "<h2 align=\"center\">" + password.getHitsCount() + "</h2>\n" +
          "<h1 align=\"center\">" + "Blocked count:" + "</h1>\n" +
          "<h2 align=\"center\">" + password.getBlocksCount() + "</h2>\n" +
          "<h1 align=\"center\">" + "Allowed count:" + "</h1>\n" +
          "<h2 align=\"center\">" + (password.getHitsCount() - password.getBlocksCount()) + "</h2>\n" +
          "<h1 align=\"center\">" + "Server Up Time (Seconds):" + "</h1>\n" +
          "<h2 align=\"center\">" + (System.currentTimeMillis() / 1000l - startTime) + "</h2>\n" +
          "</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		boolean isBlocked = false;
		try {
			isBlocked = this.password.isBlocked(password);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
