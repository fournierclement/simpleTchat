package GUI;

import javax.swing.*;

import client.ChatClient;
import common.ChatIF;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatGUI implements ChatIF {
	
	final public static int DEFAULT_PORT = 5555;
	final public static String LOCALHOST = "localhost";
	final public static String DEFAULT_ID = "Anon" + (Math.round((Math.random() * 100)));
	
	public int port;
	public String host;
	public String id;
	
	public ChatClient client;
    public JTextField textInput;
    public JTextField hostField;
    public JTextField portField;
    public JTextField loginField;
    public JTextArea textArea;

    public ChatGUI(String host, int port, String id) {
    	this.host = host;
    	this.port = port;
    	this.id = id;
        
    	JFrame window = new JFrame("mon chat :3");
        window.setSize(400, 400);        
        
        JPanel panelBot = new JPanel();
        panelBot.setLayout(new BorderLayout());
            
        JPanel panelMid = new JPanel();
        panelMid.setLayout(new BorderLayout());        
        
        textInput = new JTextField();
        panelBot.add(textInput, BorderLayout.CENTER);
        
        JButton sendButton = new JButton("Send");
        panelBot.add(sendButton, BorderLayout.EAST);
        
        hostField = new JTextField(host);
        hostField.setPreferredSize( new Dimension( 80, 24 ) );
        portField = new JTextField(""+port);
        portField.setPreferredSize( new Dimension( 50, 24 ) );
        loginField = new JTextField(id);
        loginField.setPreferredSize( new Dimension( 80, 24 ) );
        JButton connectButton = new JButton("Connect");
        
        JPanel panelTop = new JPanel();
        panelTop.setLayout( new FlowLayout());
        panelTop.add(hostField);
        panelTop.add(portField);
        panelTop.add(loginField);
        panelTop.add(connectButton);
        
        textArea = new JTextArea();
        panelMid.add(textArea, BorderLayout.CENTER);
        panelMid.add(panelBot, BorderLayout.SOUTH);
        panelMid.add(panelTop, BorderLayout.NORTH);
        
        
        
        window.setContentPane(panelMid);
           
        try {
			client = new ChatClient(host, port, this, id);
		} catch (IOException e) {
			e.printStackTrace();
		}
    
        sendButton.addActionListener(
        	handleMessageALFactory()
        );
        
        connectButton.addActionListener(
        	reconnectionALFactory()
        );
        
        window.setVisible(true);    
    }
    
    public static void main(String[] args) throws IOException {
		ChatGUI chat = new ChatGUI(LOCALHOST, DEFAULT_PORT, DEFAULT_ID);
	}
    
    public ActionListener handleMessageALFactory(){
    	return new ActionListener(){
    		public void actionPerformed(ActionEvent ev){
	            String message = textInput.getText();      
	            textInput.setText("");              
	            try{
	            	client.handleMessageFromClientUI(message);
	            } catch(Exception e) {
	            	e.printStackTrace();
	            }
    		}
    	};
    }
    
    public ActionListener reconnectionALFactory(){
    	return new ActionListener() {
    		public void actionPerformed(ActionEvent ev){
    			host = hostField.getText();
    			port = Integer.parseInt(portField.getText());
    			id = loginField.getText();
    			client.handleMessageFromClientUI("#logoff");
    			client.handleMessageFromClientUI("#sethost " + host);
    			client.handleMessageFromClientUI("#setport " + port);
    			client.id = id;
    			client.handleMessageFromClientUI("#login");
    		}
    	};
    }
    
	@Override
	public void display(String message) {
		textArea.append(message+"\n");
	}
}