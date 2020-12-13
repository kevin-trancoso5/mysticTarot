package panels;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import card.Card;
import card.Deck;
import main.Main;

public class DeleteCard implements ActionListener{

	Card c;
	Deck d;

	
	DeleteCard(Deck d,Card c){
		this.d=d;
		this.c = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println(Main.deck.getRows());
		System.out.println(d.getRows());
		Main.deck.removeCard(c);
		d.removeCard(c);
		JPanel panel = new ShowCardsPanel(d);
		changePanel(panel);
	}
	
	
	private void changePanel(JPanel panel) {
	    Main.window.getContentPane().removeAll();
	    Main.window.getContentPane().add(panel);
	    Main.window.getContentPane().invalidate();
	    Main.window.getContentPane().validate();
	    Main.window.getContentPane().repaint();
	}
}