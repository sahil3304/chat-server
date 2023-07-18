import java.io.*;
import java.net.*;
import java.util.*;

public class ChatHandler extends Thread {
	Socket socket; 
	DataInputStream in; 
	DataOutputStream out;
	static Set<ChatHandler> handlers = new HashSet<ChatHandler>(); 
	public ChatHandler(Socket socket) throws IOException {
		this.socket = socket;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		handlers.add(this); 
	}
	public void run() {
	String name = ""; 
	try {
		name = in.readUTF();
		System.out.println("New client " + name + " from " +socket.getInetAddress());
		broadcast(name + " entered");
		while(true)
			broadcast(name + ": " + in.readUTF()); } 
	catch (IOException e) { 
		System.out.println("-- Connection to user lost.");
	} 
	finally {
		handlers.remove(this); 
		try { 
		broadcast(name + " left");
		in.close();
		out.close();
		socket.close(); 
		} 
		catch (IOException e) {} 
	} 
	}
	static synchronized void broadcast(String message) throws IOException {
		for (ChatHandler handler : handlers) {
			handler.out.writeUTF(message);
			handler.out.flush(); 
		}
	} 
}
