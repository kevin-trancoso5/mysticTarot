package panels;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import card.Card;
import card.Deck;
import main.Main;

public class SaveResult implements ActionListener{

	Card c;
	Deck deck;

	
	SaveResult(Deck deck,Card c){
		this.deck=deck;
		this.c = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		JPanel panel = new ModifyCardPanel(deck,c);
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