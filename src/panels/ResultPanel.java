package panels;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import card.Card;
import card.Deck;

public class ResultPanel extends JPanel{
	
	Deck result;

	public ResultPanel(Deck result){

		this.result=result;
		
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		
		JPanel resultPanel = new JPanel(new GridLayout(2,1));
		JPanel scorePanel = new JPanel();
		JLabel score = new JLabel("Voici votre score: "+getScore());
		scorePanel.add(score);
		JPanel tiragePanel = new JPanel();
		JLabel tirage = new JLabel("Vous avez tiré:");
		tiragePanel.add(tirage);
		resultPanel.add(scorePanel);
		resultPanel.add(tiragePanel);
		
		JPanel cardsPanel = new JPanel();
		for(Card c : result.getDeck()) {
			JPanel cardPanel = new JPanel();
			cardPanel.setLayout(new BorderLayout());
			
			JPanel infoPanel = new JPanel();

    		JLabel card = new JLabel(c.name);
    		infoPanel.add(card);
    		JLabel value = new JLabel(String.valueOf(c.value));
    		infoPanel.add(value);

    		JLabel img = new JLabel();
		    try {
		        ImageIcon ii=new ImageIcon(scaleImage(80, 150, ImageIO.read(new File(c.f.getAbsolutePath()))));
		        img.setIcon(ii);
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		    img.setHorizontalAlignment(JLabel.CENTER);
		    cardPanel.add(infoPanel,BorderLayout.NORTH);
		    cardPanel.add(img,BorderLayout.CENTER);
		    
		    cardsPanel.add(cardPanel);
		}
		JPanel descsPanel = new JPanel(new GridLayout(result.getDeck().size()+1,1));
		JPanel descIntroPanel = new JPanel();
		JLabel descIntro = new JLabel("Voici les points et qualités sur lesquels votre futur va être impacté");
		descIntroPanel.add(descIntro);
		descsPanel.add(descIntroPanel);
		for(Card c : result.getDeck()) {
		    JPanel descPanel = new JPanel();

			JLabel desc = new JLabel(c.description);
			descPanel.add(desc);
			descsPanel.add(descPanel);
		
		}
		mainPanel.add(resultPanel,BorderLayout.NORTH);
		mainPanel.add(cardsPanel,BorderLayout.CENTER);
		this.add(mainPanel);
		this.add(descsPanel);
	    

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
	
	public String getScore() {
		int score=0;
		for (Card c : result.getDeck()) {
			if(c.value>=10) {
				score+=(c.value%10)+(c.value-(c.value%10))/10;
			}
			else {
				score+=c.value;
			}
		}
		return String.valueOf(score);
	}
		
}
