package no.trawling;

public class GarbageCleanup implements Runnable {
	private IntegerDirection id;
	private StringDirection password;
	private StringDirection username;
	private StringDirection ip;
	private StringDirection sqanswer;
	
	public GarbageCleanup(IntegerDirection id, StringDirection password, StringDirection username, StringDirection ip, StringDirection sqanswer){
		this.id = id;
		this.password = password;
		this.username = username;
		this.ip = ip;
		this.sqanswer = sqanswer;
	}
	
	public void run() {
		id.cleanUp();
		password.cleanUp();
		username.cleanUp();
		ip.cleanUp();
		sqanswer.cleanUp();
	}
	
};