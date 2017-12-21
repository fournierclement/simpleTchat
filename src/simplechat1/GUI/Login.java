package GUI;

import java.util.Observable;

import javax.swing.*;

public class Login extends Observable {

	JPanel window;
	
	Login(){
		//Create the window
		window = new JPanel();
		window.setSize(300, 400);
	}
}
