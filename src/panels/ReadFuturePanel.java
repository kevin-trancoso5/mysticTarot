package panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import card.Card;
import card.Deck;
import main.Main;

public class ReadFuturePanel extends JPanel{

	Deck deck;
	Deck result=new Deck();
	int nbr=0;
	
    public ReadFuturePanel(Deck d){
    	this.deck=d;

		JPanel cardsPanel = new JPanel();
		cardsPanel.setLayout(new GridLayout(deck.getRows(),5));
		this.setSize(600,600);
		deck.shuffle();
		for(Card c : deck.getDeck()) {
			JPanel cardPanel = new JPanel();
			cardPanel.setLayout(new BorderLayout());
			JButton btn = new JButton("Choisir");
    		JLabel img = new JLabel();
		    try {
		        ImageIcon ii=new ImageIcon(scaleImage(120, 210, ImageIO.read(new File("img/dos.jpg"))));
		        img.setIcon(ii);
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		    img.setHorizontalAlignment(JLabel.CENTER);
		    cardPanel.add(btn,BorderLayout.NORTH);
		    cardPanel.add(img,BorderLayout.CENTER);
		    btn.addActionListener(new ActionListener( ) {
		        public void actionPerformed( ActionEvent e )
			        {
		        	  if(nbr<5) {
		        		  if(!result.getDeck().contains(c)) {
					          result.addCard(c);
					          nbr++;
		        		  }
		        		  else {
		        			  JOptionPane.showMessageDialog(Main.window, "Vous avez déjà choisi cette carte.");
		        		  }
		        	  }
		        	  if(nbr>=5){
		        		  JPanel panel = new ResultPanel(result);
		        		  changePanel(panel);
		        	  }
			        }
		        });
		    cardsPanel.add(cardPanel);
    	}
		JScrollPane scrollPane = new JScrollPane(cardsPanel,   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(585,490));
		this.add(scrollPane,BorderLayout.CENTER);
    }
    
	public static BufferedImage scaleImage(int w, int h, BufferedImage img) throws Exception {
	    BufferedImage bi;
	    bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(img, 0, 0, w, h, null);
	    g2d.dispose();
	    return bi;
	}
	
	private void changePanel(JPanel panel) {
	    Main.window.getContentPane().removeAll();
	    Main.window.getContentPane().add(panel);
	    Main.window.getContentPane().invalidate();
	    Main.window.getContentPane().validate();
	    Main.window.getContentPane().repaint();
	}
}