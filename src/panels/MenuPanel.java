package panels;


import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MenuPanel extends JPanel{

    JLabel jLabel1 = new JLabel("Bienvenue sur Mystic Tarot");
    JLabel spacer1 = new JLabel("______________________________________________________________");
    JLabel img = new JLabel();
    JLabel jLabel2 = new JLabel("Une application qui vous permet de créer vos cartes, et faire votre propre lecture de tarot.");
    JLabel jLabel3 = new JLabel("Ici, nous allons uniquement jouer avec les Arcanes majeures");
    JLabel jLabel4 = new JLabel("Commencez par créer une carte sur le menu ou bien génerez un jeu déjà créé!");
    JPanel txtPanel = new JPanel();
   

    public MenuPanel(){
    	this.setLayout(new GridLayout(1,1));
    	txtPanel.add(jLabel1);
		txtPanel.add(spacer1);
		txtPanel.add(jLabel2);
		txtPanel.add(jLabel3);
		txtPanel.add(jLabel4);
		txtPanel.add(img);
		
        ImageIcon ii;
		try {
			ii = new ImageIcon(scaleImage(220, 220, ImageIO.read(new File("img/logo.png"))));
	        img.setIcon(ii);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.add(txtPanel);

    	
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
    
	
}
