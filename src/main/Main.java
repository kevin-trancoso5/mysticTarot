package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import card.Deck;
import panels.MenuPanel;
import panels.NavBar;


public class Main {

	public static JFrame window= new JFrame("Mystic Tarot");
	public static Deck deck = null;
	public static void main(String[] args) {
		File f = new File("tmp/deck.ser");
		if(f.exists() && !f.isDirectory()) { 
			try {
		         FileInputStream fileIn = new FileInputStream("tmp/deck.ser");
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         deck = (Deck) in.readObject();
		         in.close();
		         fileIn.close();
		      } catch (IOException i) {
		         i.printStackTrace();
		         deck = new Deck();
		         return;
		      } catch (ClassNotFoundException c) {
		         System.out.println("Deck class not found");
		         deck = new Deck();
		         c.printStackTrace();
		         return;
		      }
		}
		else deck = new Deck();
		
		window.setSize(600,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
			      try {
			    	  
			          FileOutputStream fileOut =
			          new FileOutputStream("tmp/deck.ser");
			          ObjectOutputStream out = new ObjectOutputStream(fileOut);
			          out.writeObject(deck);
			          out.close();
			          fileOut.close();
			          System.out.printf("Serialized data is saved in /tmp/deck.ser");
			       } catch (IOException i) {
			          i.printStackTrace();
			       }
				  }
				});
		JMenuBar nb = new NavBar(deck);
		MenuPanel mp = new MenuPanel();
		window.add(nb);
		window.setJMenuBar(nb);
		window.getContentPane().add(mp);   
		window.validate();
		window.setVisible(true);
		

	}
	
	

}
