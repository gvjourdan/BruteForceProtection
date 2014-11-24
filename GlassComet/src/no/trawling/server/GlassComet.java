package no.trawling.server;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.trawling.library.StringDirection;

import org.eclipse.jdt.core.compiler.InvalidInputException;

/**
 * Servlet implementation class GlassComet
 */
@WebServlet("/GlassComet")
public class GlassComet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private long startTime;
	private StringDirection passwords;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GlassComet() {
        super();
        passwords = new StringDirection(10, 10, 15);
        startTime = System.currentTimeMillis() / 1000l;
        // TODO Auto-generated constructor stub
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
          "<h2 align=\"center\">" + passwords.getHitsCount() + "</h2>\n" +
          "<h1 align=\"center\">" + "Blocked count:" + "</h1>\n" +
          "<h2 align=\"center\">" + passwords.getBlocksCount() + "</h2>\n" +
          "<h1 align=\"center\">" + "Allowed count:" + "</h1>\n" +
          "<h2 align=\"center\">" + (passwords.getHitsCount() - passwords.getBlocksCount()) + "</h2>\n" +
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
			isBlocked = passwords.isBlocked(password);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
