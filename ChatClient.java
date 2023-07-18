import java.io.*;
import java.net.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

public class ChatClient {
	String name;
	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	ChatFrame gui;
	public ChatClient(String name, String server, int port) {
		try {
			this.name = name;
			socket = new Socket(server, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));
			out.writeUTF(name + " <" + timeStamp +">\n");
			gui = new ChatFrame(this);
			while (true) {
				gui.output.append(in.readUTF() + "\n");
			}
		} 
		catch (IOException e) {}
	}
	void sendTextToChat(String str) {
		try {
			out.writeUTF(str);
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
		}
	}
	void disconnect() {
		try {
			in.close();
			out.close();
			socket.close();
		} 
		catch (IOException e) { 
		e.printStackTrace(); 
		}
	}
	public static void main(String[] args) throws IOException {
		if (args.length != 3) 
			throw new RuntimeException("Syntax: java ChatClient <name> <serverhost> <port>"); 
		new ChatClient(args[0], args[1], Integer.parseInt(args[2]));
	}
}
