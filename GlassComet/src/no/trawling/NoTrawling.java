package no.trawling;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.eclipse.jdt.core.compiler.InvalidInputException;

import static java.util.concurrent.TimeUnit.*;

public class NoTrawling {
	private IntegerDirection id;
	private StringDirection password;
	private StringDirection username;
	private StringDirection ip;
	private StringDirection sqanswer;
	HtmlContent stats;
	
	public NoTrawling(){
		String defaultWindowSize = "5";
		String defaultMaxHits = "5";
		String defaultTimePenalty = "5";

		//four hours
		String defaultGarbageCollectTimeInMinutes = "240";
		
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
		int garbageCollectTimeInMinutes = Integer.parseInt(prop.getProperty("garbageCollectTimeInMinutes",defaultGarbageCollectTimeInMinutes));
		
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
		scheduler.scheduleAtFixedRate(garbageCleanup, garbageCollectTimeInMinutes, garbageCollectTimeInMinutes, MINUTES);
		stats = new HtmlContent();
		
		class UpdateStats implements Runnable {
			public void run() {
				stats.display(username);
			}
		};
		UpdateStats statsScheduler = new UpdateStats();
		scheduler.scheduleAtFixedRate(statsScheduler, 1, 1, MINUTES);
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
}