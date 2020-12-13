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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import card.Card;
import card.Deck;
import main.Main;

public class ShowCardsPanel extends JPanel implements ActionListener{
	Deck deck;
	
	JLabel searchText = new JLabel("Faire une recherche:");
	JTextField searchTextField = new JTextField(10);
	Object[] elements = new Object[] {"Nom","Valeur","Description"};
	JComboBox searchOptions = new JComboBox(elements);
	JButton searchBtn = new JButton("Rechercher");
	
	ShowCardsPanel(Deck deck){
		this.deck=deck;
		
		JPanel panel = new JPanel(new BorderLayout());
		JPanel searchPanel = new JPanel();
		JPanel cardsPanel = new JPanel();
		

		searchBtn.addActionListener(this);
		
		searchPanel.add(searchText);
		searchPanel.add(searchTextField);
		searchPanel.add(searchOptions);
		searchPanel.add(searchBtn);
		
		
		
		cardsPanel.setLayout(new GridLayout(deck.getRows(),3));
		this.setSize(600,600);
		for(Card c : deck.getDeck()) {
			JPanel cardPanel = new JPanel();
			cardPanel.setLayout(new BorderLayout());
			
			JPanel infoPanel = new JPanel();

    		JLabel card = new JLabel(c.name);
    		infoPanel.add(card);
    		JLabel value = new JLabel(String.valueOf(c.value));
    		infoPanel.add(value);
    		JButton modify = new JButton("O");
    		JButton delete = new JButton("X");
    		modify.setMargin(new java.awt.Insets(1, 1, 1, 1));
    		modify.addActionListener(new ModifyCard(deck,c));
    		delete.setMargin(new java.awt.Insets(1, 1, 1, 1));
    		delete.addActionListener(new DeleteCard(deck,c));
    		
    		infoPanel.add(modify);
    		infoPanel.add(delete);

    		JLabel img = new JLabel();
		    try {
		        ImageIcon ii=new ImageIcon(scaleImage(120, 210, ImageIO.read(new File(c.f.getAbsolutePath()))));
		        img.setIcon(ii);
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		    img.setHorizontalAlignment(JLabel.CENTER);
		    cardPanel.add(infoPanel,BorderLayout.NORTH);
		    cardPanel.add(img,BorderLayout.CENTER);
		    
		    cardsPanel.add(cardPanel);
    	}
		JScrollPane scrollPane = new JScrollPane(cardsPanel,   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(585,490));
		panel.add(searchPanel,BorderLayout.NORTH);
		panel.add(scrollPane,BorderLayout.CENTER);
		this.add(panel);
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


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchBtn) {
			System.out.println(searchOptions.getSelectedItem().toString());
			String search = searchTextField.getText();
			System.out.println(search);
			Deck newDeck = new Deck();
			for(Card c : Main.deck.getDeck()) {
				switch(searchOptions.getSelectedItem().toString()) {
					case "Nom" : {
						if(c.name.contains(search)) {
							Card toAdd = c;
							newDeck.addCard(toAdd);
						}
						break;
					}
					case "Valeur" : {
						if(c.value==Integer.valueOf(search)) {
							Card toAdd = c;
							newDeck.addCard(toAdd);
						}
						break;
					}
					case "Description" : {
						if(c.description.contains(search)) {
							Card toAdd = c;
							newDeck.addCard(toAdd);
						}
						break;
					}
				
				}
			}
			JPanel panel = new ShowCardsPanel(newDeck);
			changePanel(panel);
			
		}
	}

	private void changePanel(JPanel panel) {
		Main.window.getContentPane().removeAll();
		Main.window.getContentPane().add(panel);
		Main.window.getContentPane().invalidate();
		Main.window.getContentPane().validate();
		Main.window.getContentPane().repaint();
	}
	
}
