package card;

import java.io.File;

public class Card implements java.io.Serializable{

	private static final long serialVersionUID = 7993215729198243085L;
	
	public final String name;
	public final int value;
	public final String description;
	public final File f;
	
	public Card(String name, int value, String desc, File f) {
		this.name = name;
		this.value = value;
		this.description=desc;
		this.f = f;
	}
	
	
	
	
}
