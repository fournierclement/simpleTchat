package GUI;

import java.util.Observable;

import javax.swing.*;

public class Tchat extends Observable {

	JFrame window;
	
	public Tchat(String host, int port, String id) {
		window = new JFrame("BorderLayout");
		
	}

}
