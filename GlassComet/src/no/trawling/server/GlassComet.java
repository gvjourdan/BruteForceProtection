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
          "<h1 align=\"center\">" + "Password hit count:" + "</h1>\n" +
          "<h2 align=\"center\">" + password.getHitsCount() + "</h2>\n" +
          "<h1 align=\"center\">" + "Blocked count:" + "</h1>\n" +
          "<h2 align=\"center\">" + password.getBlocksCount() + "</h2>\n" +
          "<h1 align=\"center\">" + "Allowed count:" + "</h1>\n" +
          "<h2 align=\"center\">" + (password.getHitsCount() - password.getBlocksCount()) + "</h2>\n" +
          
          "<h1 align=\"center\">" + "Id hit count:" + "</h1>\n" +
          "<h2 align=\"center\">" + id.getHitsCount() + "</h2>\n" +
          "<h1 align=\"center\">" + "Blocked count:" + "</h1>\n" +
          "<h2 align=\"center\">" + id.getBlocksCount() + "</h2>\n" +
          "<h1 align=\"center\">" + "Allowed count:" + "</h1>\n" +
          "<h2 align=\"center\">" + (id.getHitsCount() - id.getBlocksCount()) + "</h2>\n" +
          
          "<h1 align=\"center\">" + "Username hit count:" + "</h1>\n" +
          "<h2 align=\"center\">" + username.getHitsCount() + "</h2>\n" +
          "<h1 align=\"center\">" + "Blocked count:" + "</h1>\n" +
          "<h2 align=\"center\">" + username.getBlocksCount() + "</h2>\n" +
          "<h1 align=\"center\">" + "Allowed count:" + "</h1>\n" +
          "<h2 align=\"center\">" + (username.getHitsCount() - username.getBlocksCount()) + "</h2>\n" +
          
          "<h1 align=\"center\">" + "Security question answer hit count:" + "</h1>\n" +
          "<h2 align=\"center\">" + sqanswer.getHitsCount() + "</h2>\n" +
          "<h1 align=\"center\">" + "Blocked count:" + "</h1>\n" +
          "<h2 align=\"center\">" + sqanswer.getBlocksCount() + "</h2>\n" +
          "<h1 align=\"center\">" + "Allowed count:" + "</h1>\n" +
          "<h2 align=\"center\">" + (sqanswer.getHitsCount() - sqanswer.getBlocksCount()) + "</h2>\n" +
          
          "<h1 align=\"center\">" + "Ip hit count:" + "</h1>\n" +
          "<h2 align=\"center\">" + ip.getHitsCount() + "</h2>\n" +
          "<h1 align=\"center\">" + "Blocked count:" + "</h1>\n" +
          "<h2 align=\"center\">" + ip.getBlocksCount() + "</h2>\n" +
          "<h1 align=\"center\">" + "Allowed count:" + "</h1>\n" +
          "<h2 align=\"center\">" + (ip.getHitsCount() - ip.getBlocksCount()) + "</h2>\n" +
          
          "<h1 align=\"center\">" + "Server Up Time (Seconds):" + "</h1>\n" +
          "<h2 align=\"center\">" + (System.currentTimeMillis() / 1000l - startTime) + "</h2>\n" +
          
          "</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String ip = request.getParameter("ip");
		String username = request.getParameter("username");
		Integer id = Integer.getInteger(request.getParameter("id"));
		String sqanswer = request.getParameter("sqanswer");
		
		boolean isBlocked = false;
		
		boolean passwordBlocked = false;
		boolean ipBlocked = false;
		boolean usernameBlocked = false;
		boolean sqanswerBlocked = false;
		boolean idBlocked = false;
		
		if (null != password){
			try {
				passwordBlocked = this.password.isBlocked(password);
			} catch (IllegalStateException | InvalidInputException e) {
				e.printStackTrace();
			}
		}
		if (null != ip){
			try {
				ipBlocked = this.ip.isBlocked(ip);
			} catch (IllegalStateException | InvalidInputException e) {
				e.printStackTrace();
			}
		}
		if (null != username){
			try {
				usernameBlocked = this.username.isBlocked(username);
			} catch (IllegalStateException | InvalidInputException e) {
				e.printStackTrace();
			}
		}
		if (null != sqanswer){
			try {
				sqanswerBlocked = this.sqanswer.isBlocked(sqanswer);
			} catch (IllegalStateException | InvalidInputException e) {
				e.printStackTrace();
			}
		}
		if (null != id){
			try {
				idBlocked = this.id.isBlocked(id);
			} catch (IllegalStateException | InvalidInputException e) {
				e.printStackTrace();
			}
		}
		isBlocked = passwordBlocked && ipBlocked && usernameBlocked && sqanswerBlocked && idBlocked;
		
		request.setAttribute("blocked", isBlocked);
	}

}
