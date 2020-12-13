package card;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Deck implements java.io.Serializable{

	private static final long serialVersionUID = 2311970105828218463L;
	ArrayList<Card> deck;
	
	public Deck() {
		deck = new ArrayList<Card>();
	}
	
	public void addCard(Card c) {
		deck.add(c);
	}
	
	public void removeCard(Card c) {
		deck.remove(c);
	}
	
	
	public void replace(Card oldCard, Card newCard) {
		deck.set(deck.indexOf(oldCard), newCard);
	}
	
	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	public void shuffle(){
		Collections.shuffle(deck);
	}
	
	public int getRows() {
		int mod = deck.size()%3;
		return ((deck.size()-mod)/3+1);
	}
	
	public void generateCard() {
		deck.removeAll(deck);
		deck.add(new Card("Le Fou",0,"Nouveau départ, insouciance",new File("img/0-fou.jpg")));
		deck.add(new Card("Le Bateleur",1,"Talent, compétence, confiance en soi",new File("img/1-bateleur.jpg")));
		deck.add(new Card("La Papesse",2,"Intuition, spiritualité, monde intérieur",new File("img/2-papesse.jpg")));
		deck.add(new Card("L'Impératrice",3,"Abondance, créativité",new File("img/3-imperatrice.jpg")));
		deck.add(new Card("L'Empereur",4,"Structure, ordre, respect, autorité",new File("img/4-empereur.jpg")));
		deck.add(new Card("Le Pape",5,"Tradition, conformisme, enseignement",new File("img/5-pape.jpg")));
		deck.add(new Card("L'Amoureux",6,"Amour, union, dualité",new File("img/6-amoureux.jpg")));
		deck.add(new Card("Le Chariot",7,"Contrôle, volonté, progrès, avancées",new File("img/7-chariot.jpg")));
		deck.add(new Card("La Justice",8,"Honnêteté, équité, vérité",new File("img/8-justice.jpg")));
		deck.add(new Card("L'Hermite",9,"Solitude, calme, recherche spirituelle",new File("img/9-hermite.jpg")));
		deck.add(new Card("La Roue de Fortune",10,"Chance, changement, amélioration",new File("img/10-roue-fortune.jpg")));
		deck.add(new Card("La Force",11,"Endurance, motivation, discipline",new File("img/11-force.jpg")));
		deck.add(new Card("Le Pendu",12,"Lâcher-prise, zénitude",new File("img/12-pendu.jpg")));
		deck.add(new Card("La Mort",13,"Transition, changement, point final",new File("img/13-mort.jpg")));
		deck.add(new Card("La Tempérance",14,"Modération, équilibre, bien-être",new File("img/14-temperance.jpg")));
		deck.add(new Card("Le Diable",15,"Négativité, addiction, jalousie",new File("img/15-diable.jpg")));
		deck.add(new Card("La Maison de Dieu",16,"Changement brutal, coup dur",new File("img/16-maison-dieu.jpg")));
		deck.add(new Card("L'Etoile",17,"Espoir, optimisme, souhait exaucé",new File("img/17-etoile.jpg")));
		deck.add(new Card("La Lune",18,"Inconscience, illusions",new File("img/18-lune.jpg")));
		deck.add(new Card("Le Soleil",19,"Optimisme, joie, naissance",new File("img/19-soleil.jpg")));
		deck.add(new Card("Le Jugement",20,"Mission, vocation, réveil / éveil",new File("img/20-jugement.jpg")));
		deck.add(new Card("Le Monde",21,"Accomplissement, voyage",new File("img/21-monde.jpg")));
	}
	


	
}
