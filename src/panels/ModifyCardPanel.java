package panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import card.Card;
import card.Deck;
import main.Main;

public class ModifyCardPanel extends JPanel implements ActionListener {

	Deck deck;
	Card card;
	File f;

	JPanel form = new JPanel();

	JLabel nameLabel = new JLabel("Choisir le nom de la carte:");
	JTextField nameTextField = new JTextField();
	JPanel namePanel = new JPanel();

	JLabel valueLabel = new JLabel("Choisir la valeur de la carte:");
	JTextField valueTextField = new JTextField();
	JPanel valuePanel = new JPanel();

	JLabel descLabel = new JLabel("Choisir la description de la carte:");
	JTextField descTextField = new JTextField();
	JPanel descPanel = new JPanel();
	
	JLabel imageLabel = new JLabel("Choisir une image:");
	JTextField imageTextField = new JTextField();
	JLabel image = new JLabel();
	JButton imgButton = new JButton("Upload File");
	JPanel imgPanel = new JPanel();

	JButton submit = new JButton("Modifier sa carte");

	public ModifyCardPanel(Deck deck, Card card) {
		this.card = card;
		this.deck = deck;
		this.f = card.f;
		nameTextField.setColumns(10);
		nameTextField.setText(card.name);
		valueTextField.setColumns(10);
		valueTextField.setText(String.valueOf(card.value));
		descTextField.setColumns(20);
		descTextField.setText(String.valueOf(card.description));
		try {
			ImageIcon ii = new ImageIcon(scaleImage(120, 210, ImageIO.read(new File(f.getAbsolutePath()))));
			image.setIcon(ii);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		namePanel.add(nameLabel);
		namePanel.add(nameTextField);

		valuePanel.add(valueLabel);
		valuePanel.add(valueTextField);

		descPanel.add(descLabel);
		descPanel.add(descTextField);
		
		imgPanel.add(imageLabel);
		imgPanel.add(image);
		imgPanel.add(imgButton);

		form.setLayout(new GridLayout(3, 1));
		form.add(namePanel);
		form.add(valuePanel);
		form.add(descPanel);
		JPanel p = new JPanel(new BorderLayout());
		p.add(form, BorderLayout.NORTH);
		p.add(imgPanel, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(p, BorderLayout.PAGE_START);
		this.add(submit, BorderLayout.SOUTH);

		imgButton.addActionListener(this);
		submit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			try {
				deck.replace(card, new Card(nameTextField.getText(), Integer.parseInt(valueTextField.getText()),descTextField.getText(),f));
				JOptionPane.showMessageDialog(Main.window, "Modification réussie.");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(Main.window, "Erreur de modification de la carte.");
			}
		}
		if (e.getSource() == imgButton) {
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(null);
			f = chooser.getSelectedFile();
			try {
				ImageIcon ii = new ImageIcon(scaleImage(120, 210, ImageIO.read(new File(f.getAbsolutePath()))));
				image.setIcon(ii);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
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
