# Tarot project report

The main goal of the project is to create a software with an user interface that can read cards from the divinatory tarot.

## Modelling

Our project is divided into two parts.

First,the 'handling the deck' part, which will contain all classes and methods that will be necessary for the proper functioning of our cards/deck.

Then, the 'user interface' part, which will contain all the user can see and perform on our application.

### Handling the deck

For this part, I'm using two classes:

- **Card** (Defined by a name, a value, a description and an image File.
- **Deck** (Defined by an ArrayList of multiple Cards)

The Deck class have several methods:
|Name    |Parameters|Description       |
|--------|------|----------------------|
|addCard |Card c|add a card to the deck|
|removeCard|Card c|remove a card from the deck |
|replace|Card oldCard, Card newCard| replace the oldCard by the newCard in the deck|
|getDeck|*none*| return the deck|
|shuffle|*none*| shuffle the cards of the deck|
|getRows|*none*| return the correct number of rows needed for our display|
|generateCards|*none*|generates a collection of hand-created cards and add them to the deck|

### User Interface

Here we have different panels

- MenuPanel (display the Main Menu)
- CreateCardPanel (display the screen where you create a card)
- ModifyCardPanel (display the screen where you modify an already created card)
- ShowCardsPanel (display the list of all our cards)
- ReadFuturePanel (display our reading of future)
- ResultPanel (display the results from the reading)

We also have a NavBar which is our main tool for navigating inside our application.
The NavBar is displayed on every screen of our software.

Finally we have some classes that implements ActionListener, they're used for some user actions like deleting a card.

Here they are: 
 - ModifyCard
 - DeleteCard
 - SaveResult

##  User actions

What our user can do in our app?

First of all, when he launches the app, he's directly sent to the Main Menu, he can change the screen by using the NavBar.

Then he can generate cards, he can create his own cards, he can see his deck (and modify or delete each card), and he can read his future.

## Displaying images

To display images, I use a method that I called scaleImage that use Graphics2D to create graphics at the width and height we want.

```java
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
```
Then we can put the return of this method inside an ImageIcon that will be added to a JLabel.

## Changing panels

In order to change panel, I've decided to make our JFrame window a public static variable that can be used everywhere in the code.

Then i can use this method to change the JPanel we're seeing:

```java
	private void changePanel(JPanel panel) {
		Main.window.getContentPane().removeAll();
		Main.window.getContentPane().add(panel);
		Main.window.getContentPane().invalidate();
		Main.window.getContentPane().validate();
		Main.window.getContentPane().repaint();
	}
```

## Saving the deck

To save the deck when we close our app, I'm using Serializable on my Deck and Card classes.

Then I added to my JFrame a WindowListener that listen on when the window is closing, then it saves our deck into tmp/deck.ser.

```java
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
```

After that, inside the main function when we launch the app we use this to get this data back:

```java
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
```


## Reading the future

In our app, we shuffle the deck, and we let the user select 5 cards (not the same one multiple time), then it gives you an Energy score according to their value (when a card have a value > 10, it takes the first number added to the second one, ex: 10 = 1+0=1)

Then it shows what cards you have chosen and it displays their description (keywords).

## Difficulties

I have encountered some difficulties with JFrame as it was my first project using Swing, particulary on the Layouts, it was hard to get the display i wanted to have on my app.

## What we could add in the future

We could implement:
- a more detailed future reading
- add some design
- add more details to a card
- add some responsive to the app
- creating a profile system where you can save different decks.
