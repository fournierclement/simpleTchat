package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import client.ChatClient;
import client.ChatIF;
import client.ClientConsole;

public class ServerConsole implements ChatIF {
	  //Class variables *************************************************
	  
	  /**
	   * The default port to connect on.
	   */
	  final public static int DEFAULT_PORT = 5555;
	  
	  //Instance variables **********************************************
	  
	  /**
	   * The instance of the server that created this ConsoleChat.
	   */
	  ChatServer server;
	  ConnectionToClient con;

	  
	  //Constructors ****************************************************

	  /**
	   * Constructs an instance of the ServerConsole UI.
	   *
	   * @param host The host to connect to.
	   * @param port The port to connect on.
	   */
	  public ServerConsole(int port) 
	  {
	    try 
	    {
	      server= new ChatServer(port, this);
	    } 
	    catch(IOException exception) 
	    {
	      System.out.println("Error: Can't setup connection!"
	                + " Terminating server.");
	      System.exit(1);
	    }
	  }

	  
	  //Instance methods ************************************************
	  
	  /**
	   * This method waits for input from the console.  Once it is 
	   * received, it sends it to the server's message handler.
	   */
	  public void accept() 
	  {
	    try
	    {
	      BufferedReader fromConsole = 
	        new BufferedReader(new InputStreamReader(System.in));
	      String message;

	      while (true) 
	      {
	        message = fromConsole.readLine();
	        server.handleMessageFromServerUI(message);
	      }
	    } 
	    catch (Exception ex) 
	    {
	      System.out.println
	        ("Unexpected error while reading from console!");
	    }
	  }

	  /**
	   * This method overrides the method in the ChatIF interface.  It
	   * displays a message onto the screen.
	   *
	   * @param message The string to be displayed.
	   */
	  public void display(String message) 
	  {
	    System.out.println("> " + message);
	  }

	  public void sendToClient(String message) throws IOException {
		con.sendToClient(message); 
	  }
	  
	  //Class methods ***************************************************
	  
	  /**
	   * This method is responsible for the creation of the Server UI.
	   *
	   * @param args[0] The host login to connect with.
	   * @param args[1] The host to connect to.
	   */
	  public static void main(String[] args) 
	  {
	    int port = 0;  //The port number
	    try
	    {
	      port = Integer.parseInt(args[1]);
	      
	    }
	    catch(ArrayIndexOutOfBoundsException e)
	    {
	      port = DEFAULT_PORT;
	    }
	    ServerConsole chat = new ServerConsole(port);
	    chat.accept();  //Wait for console data
	  }
}
