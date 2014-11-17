package no.trawling;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

class HtmlContent extends JFrame {
	private static final long serialVersionUID = 1L;
	HtmlContent(){
		String html = "<html>" +
		          "<head><title>" + "GlassComet" + 
				  "</title></head>" +
		          "<body bgcolor=\"#f0f0f0\">" +
		          "<h1 align=\"center\">" + "Id Stats:" + "</h1>" +
		          "<p align=\"center\">" + "Hit count: " + 0 + "</p>" +
		          "<p align=\"center\">" + "Blocked count:" + 0 + "</p>" +
		          "<p align=\"center\">" + "Allowed count:" + 0 + "</p>" +
		          "<h1 align=\"center\">" + "Password Stats:" + "</h1>" +
		          "<p align=\"center\">" + "Hit count:" + 0 + "</p>" +
		          "<p align=\"center\">" + "Blocked count:" + 0 + "</p>" +
		          "<p align=\"center\">" + "Allowed count:" + 0 + "</p>" +
		          "<h1 align=\"center\">" + "Username Stats:" + "</h1>" +
		          "<p align=\"center\">" + "Hit count:" + 0 + "</p>" +
		          "<p align=\"center\">" + "Blocked count:" + 0 + "</p>" +
		          "<p align=\"center\">" + "Allowed count:" + 0 + "</p>" +
		          "<h1 align=\"center\">" + "Ip Stats:" + "</h1>\n" +
		          "<p align=\"center\">" + "Hit count:" + 0 + "</p>" +
		          "<p align=\"center\">" + "Blocked count:" + 0 + "</p>" +
		          "<p align=\"center\">" + "Allowed count:" + 0 + "</p>" +
		          "<h1 align=\"center\">" + "Security Question Stats:" + "</h1>" +
		          "<p align=\"center\">" + "Hit count:" + 0 + "</p>" +
		          "<p align=\"center\">" + "Blocked count:" + 0 + "</p>" +
		          "<p align=\"center\">" + "Allowed count:" + 0 + "</p>" +
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
			String html = "<html>" +
	          "<head><title>" + "GlassComet" + 
			  "</title></head>" +
	          "<body bgcolor=\"#f0f0f0\">" +
	          "<h1 align=\"center\">" + "Id Stats:" + "</h1>" +
	          "<p align=\"center\">" + "Hit count: " + id.getHitsCount() + "</p>" +
	          "<p align=\"center\">" + "Blocked count:" + id.getBlocksCount() + "</p>" +
	          "<p align=\"center\">" + "Allowed count:" + (id.getHitsCount() - id.getBlocksCount()) + "</p>" +
	          "<h1 align=\"center\">" + "Password Stats:" + "</h1>" +
	          "<p align=\"center\">" + "Hit count:" + password.getHitsCount() + "</p>" +
	          "<p align=\"center\">" + "Blocked count:" + password.getBlocksCount() + "</p>" +
	          "<p align=\"center\">" + "Allowed count:" + (password.getHitsCount() - password.getBlocksCount()) + "</p>" +
	          "<h1 align=\"center\">" + "Username Stats:" + "</h1>" +
	          "<p align=\"center\">" + "Hit count:" + username.getHitsCount() + "</p>" +
	          "<p align=\"center\">" + "Blocked count:" + username.getBlocksCount() + "</p>" +
	          "<p align=\"center\">" + "Allowed count:" + (username.getHitsCount() - username.getBlocksCount()) + "</p>" +
	          "<h1 align=\"center\">" + "Ip Stats:" + "</h1>" +
	          "<p align=\"center\">" + "Hit count:" + ip.getHitsCount() + "</p>" +
	          "<p align=\"center\">" + "Blocked count:" + ip.getBlocksCount() + "</p>" +
	          "<p align=\"center\">" + "Allowed count:" + (ip.getHitsCount() - ip.getBlocksCount()) + "</p>" +
	          "<h1 align=\"center\">" + "Security Question Stats:" + "</h1>" +
	          "<p align=\"center\">" + "Hit count:" + sqanswer.getHitsCount() + "</p>" +
	          "<p align=\"center\">" + "Blocked count:" + sqanswer.getBlocksCount() + "</p>" +
	          "<p align=\"center\">" + "Allowed count:" + (sqanswer.getHitsCount() - sqanswer.getBlocksCount()) + "</p>" +
	          "</body>"+
	          "</html>";
			JEditorPane ed1=new JEditorPane("text/html",html); 
			this.getContentPane().removeAll();
			add(ed1);
			revalidate(); 
			repaint();
		} 
		catch(Exception e) { 
			e.printStackTrace(); 
			System.out.println("Some problem has occured"+e.getMessage()); 
		}
	}
}