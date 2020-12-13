package panels;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import card.Deck;
import main.Main;

public class NavBar extends JMenuBar implements ActionListener{
	JFrame window;
	JMenuItem createCard,generateCards,seeCards,mainMenu,futur;
	Deck deck;
	
	public NavBar(Deck deck) {
		this.deck=deck;
		init();
	}
	
	void init(){
		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenu deck = new JMenu("Deck");
		JMenu tarot = new JMenu("Tarot");
		createCard = new JMenuItem("Créer une carte");
		generateCards = new JMenuItem("Générer un paquet");
		seeCards = new JMenuItem("Voir paquet");
		mainMenu = new JMenuItem("Retourner au Menu Principal");
		futur = new JMenuItem("Lire le futur");
		createCard.addActionListener(this);
		generateCards.addActionListener(this);
		seeCards.addActionListener(this);
		mainMenu.addActionListener(this);
		futur.addActionListener(this);
		menu.add(mainMenu);
		deck.add(createCard);
		deck.add(generateCards);
		deck.add(seeCards);
		tarot.add(futur);
		mb.add(menu);
		mb.add(deck);
		mb.add(tarot);
		this.add(mb);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mainMenu) {
			JPanel panel = new MenuPanel();
			changePanel(panel);
		}
		if(e.getSource() == createCard) {
			JPanel panel = new CreateCardPanel(deck);
			changePanel(panel);
		}
		if(e.getSource() == generateCards) {
			deck.generateCard();
			JOptionPane.showMessageDialog(Main.window, "Un deck de carte a été généré.");
			JPanel panel = new ShowCardsPanel(deck);
			changePanel(panel);
		}
		if(e.getSource() == seeCards) {
			
			JPanel panel = new ShowCardsPanel(deck);
			changePanel(panel);
		}
		if(e.getSource() == futur) {
			JPanel panel = new ReadFuturePanel(deck);
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