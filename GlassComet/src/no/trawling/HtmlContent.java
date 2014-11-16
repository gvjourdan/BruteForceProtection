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
		          "<h1 align=\"center\">" + "Hit count:" + "</h1>\n" +
		          "<h2 align=\"center\">" + 0 + "</h2>\n" +
		          "<h1 align=\"center\">" + "Blocked count:" + "</h1>\n" +
		          "<h2 align=\"center\">" + 0 + "</h2>\n" +
		          "<h1 align=\"center\">" + "Allowed count:" + "</h1>\n" +
		          "<h2 align=\"center\">" + 0 + "</h2>\n" +
		          "</body></html>";
				JEditorPane ed1=new JEditorPane("text/html",html); 
				add(ed1);
				setVisible(true); 
				setSize(600,600); 
				setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	
	void display(StringDirection password){
		try { 
			String html = "<html>\n" +
	          "<head><title>" + "GlassComet" + 
			  "</title></head>\n" +
	          "<body bgcolor=\"#f0f0f0\">\n" +
	          "<h1 align=\"center\">" + "Hit count:" + "</h1>\n" +
	          "<h2 align=\"center\">" + password.getHitsCount() + "</h2>\n" +
	          "<h1 align=\"center\">" + "Blocked count:" + "</h1>\n" +
	          "<h2 align=\"center\">" + password.getBlocksCount() + "</h2>\n" +
	          "<h1 align=\"center\">" + "Allowed count:" + "</h1>\n" +
	          "<h2 align=\"center\">" + (password.getHitsCount() - password.getBlocksCount()) + "</h2>\n" +
	          "</body></html>";
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