package lab4;

import java.io.*;

public class Beer implements Serializable, Comparable<Beer>{
	protected String name;
	protected String style;
	protected double strength;
	
	public Beer(String name, String style, double strength){
		this.name = name;
		this.style = style;
		this.strength = strength;
	}
	
	public Beer(String[] Tomb) {
		name = Tomb[1];
		style = Tomb[2];
		
		//mert String tombbol kell atkonvertalni
		strength = Double.parseDouble(Tomb[3]);
	}
	
	public String getName() {
		return name;
	}
	
	public String getStyle() {
		return style;
	}
	
	public double getStrength() {
		return strength;
	}
	
	@Override
	public String toString() {
		return "\nNeve: " + name + "\nJellege: " + style + "\nAlkoholfoka: " + strength;
	}
	
	@Override
	public int compareTo(Beer b) {
		return name.compareTo(b.getName());
	}
}
