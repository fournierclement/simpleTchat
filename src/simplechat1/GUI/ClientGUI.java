package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

import client.ChatClient;

public class ClientGUI implements Observer {
	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;
	final public static String DEFAULT_ID = "Anon" + (Math.round((Math.random() * 100)));

	Login login;
	Tchat tchat;
	
	ClientGUI(){
		login("localhost", DEFAULT_PORT, DEFAULT_ID);
		
	}

	/**
	 * This method overrides the method in the ChatIF interface. It displays a
	 * message onto the screen.
	 *
	 * @param message
	 *            The string to be displayed.
	 */
	public void display(String message) {
		System.out.println(message);
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the Client UI.
	 *
	 * @param args
	 *            [0] The port to connect to.
	 * @param args
	 *            [1] The host to connect to.
	 */
	public static void main(String[] args) {
		// Ici, on veut se login.
		ClientGUI clientIF = new ClientGUI();
		clientIF.logging();
	}
	private void login(String host, int port, String id) {
		tchat = new Tchat(host, port, id);
	}
	private void logOff() {
		// Remove the Tchat panel
		// Passe to logging procedure
		logging();
	}
	private void logging() { 
		// Show the Login panel.
		login = new Login();
		login.addObserver(this);
	}


	@Override
	public void update(Observable o, Object arg) {
		String test = "test";
		switch(test) {
			case "login":
			case "logoff":
			case "quit":
		}
	}
	
}
