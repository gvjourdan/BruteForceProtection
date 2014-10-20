package no.trawling;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.jdt.core.compiler.InvalidInputException;

public class NoTrawling {
	private IntegerDirection id;
	private StringDirection password;
	private StringDirection username;
	private StringDirection ip;
	private StringDirection sqanswer;
	
	public NoTrawling(){
		String defaultWindowSize = "5";
		String defaultMaxHits = "5";
		String defaultTimePenalty = "5";
		Properties prop = new Properties();
		String propFileName = "config.properties";
 
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		id = new IntegerDirection(windowSizeId,maxHitsId,timePenaltyId);
		password = new StringDirection(windowSizePassword,maxHitsPassword,timePenaltyPassword);
		username = new StringDirection(windowSizeUsername,maxHitsUsername,timePenaltyUsername);
		ip = new StringDirection(windowSizeIp,maxHitsIp,timePenaltyIp);
		sqanswer = new StringDirection(windowSizeSqanswer,maxHitsSqanswer,timePenaltySqanswer);
	}
	
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
