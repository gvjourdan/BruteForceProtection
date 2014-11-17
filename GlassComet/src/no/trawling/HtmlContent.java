package no.trawling;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

class HtmlContent extends JFrame {
	private static final long serialVersionUID = 1L;
	HtmlContent(){
		String html = "<html>\n" +
		          "<head><title>" + "GlassComet" + 
				  "</title></head>\n" +
		          "<body bgcolor=\"#f0f0f0\">\n" +
		          "<h1 align=\"center\">" + "Hit count: " + 0 + "</h1>\n" +
		          "<h1 align=\"center\">" + "Blocked count:" + 0 + "</h1>\n" +
		          "<h1 align=\"center\">" + "Allowed count:" + 0 + "</h1>\n" +
		          "<h1 align=\"center\">" + "Hit count:" + 0 + "</h1>\n" +
		          "<h1 align=\"center\">" + "Blocked count:" + 0 + "</h1>\n" +
		          "<h1 align=\"center\">" + "Allowed count:" + 0 + "</h1>\n" +
		          "<h1 align=\"center\">" + "Hit count:" + 0 + "</h1>\n" +
		          "<h1 align=\"center\">" + "Blocked count:" + 0 + "</h1>\n" +
		          "<h1 align=\"center\">" + "Allowed count:" + 0 + "</h1>\n" +
		          "<h1 align=\"center\">" + "Hit count:" + 0 + "</h1>\n" +
		          "<h1 align=\"center\">" + "Blocked count:" + 0 + "</h1>\n" +
		          "<h1 align=\"center\">" + "Allowed count:" + 0 + "</h1>\n" +
		          "<h1 align=\"center\">" + "Hit count:" + 0 + "</h1>\n" +
		          "<h1 align=\"center\">" + "Blocked count:" + 0 + "</h1>\n" +
		          "<h1 align=\"center\">" + "Allowed count:" + 0 + "</h1>\n" +
		          "</body>"+
		          "</html>";
				JEditorPane ed1=new JEditorPane("text/html",html); 
				add(ed1);
				setVisible(true); 
				setSize(600,600); 
				setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	void display(IntegerDirection id, StringDirection password, StringDirection username, StringDirection ip, StringDirection sqanswer){
		try { 
			String html = "<html>\n" +
	          "<head><title>" + "GlassComet" + 
			  "</title></head>\n" +
	          "<body bgcolor=\"#f0f0f0\">\n" +
	          "<h1 align=\"center\">" + "Hit count: " + id.getHitsCount() + "</h1>\n" +
	          "<h1 align=\"center\">" + "Blocked count:" + id.getBlocksCount() + "</h1>\n" +
	          "<h1 align=\"center\">" + "Allowed count:" + (id.getHitsCount() - id.getBlocksCount()) + "</h1>\n" +
	          "<h1 align=\"center\">" + "Hit count:" + password.getHitsCount() + "</h1>\n" +
	          "<h1 align=\"center\">" + "Blocked count:" + password.getBlocksCount() + "</h1>\n" +
	          "<h1 align=\"center\">" + "Allowed count:" + (password.getHitsCount() - password.getBlocksCount()) + "</h1>\n" +
	          "<h1 align=\"center\">" + "Hit count:" + username.getHitsCount() + "</h1>\n" +
	          "<h1 align=\"center\">" + "Blocked count:" + username.getBlocksCount() + "</h1>\n" +
	          "<h1 align=\"center\">" + "Allowed count:" + (username.getHitsCount() - username.getBlocksCount()) + "</h1>\n" +
	          "<h1 align=\"center\">" + "Hit count:" + ip.getHitsCount() + "</h1>\n" +
	          "<h1 align=\"center\">" + "Blocked count:" + ip.getBlocksCount() + "</h1>\n" +
	          "<h1 align=\"center\">" + "Allowed count:" + (ip.getHitsCount() - ip.getBlocksCount()) + "</h1>\n" +
	          "<h1 align=\"center\">" + "Hit count:" + sqanswer.getHitsCount() + "</h1>\n" +
	          "<h1 align=\"center\">" + "Blocked count:" + sqanswer.getBlocksCount() + "</h1>\n" +
	          "<h1 align=\"center\">" + "Allowed count:" + (sqanswer.getHitsCount() - sqanswer.getBlocksCount()) + "</h1>\n" +
	          "</body>"+
	          "</html>";
			JEditorPane ed1=new JEditorPane("text/html",html); 
			this.getContentPane().removeAll();
			add(ed1);
			revalidate(); 
			repaint();
		    System.out.println("updated");
		} 
		catch(Exception e) { 
			e.printStackTrace(); 
			System.out.println("Some problem has occured"+e.getMessage()); 
		}
	}
}