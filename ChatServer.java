import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	public ChatServer(int port) throws IOException {
	ServerSocket s = new ServerSocket(port);
	while (true) 
		new ChatHandler(s.accept()).start();
	}
	public static void main(String[] args) throws IOException {
	if (args.length != 1)
		throw new RuntimeException("Syntax: java ChatServer <port>");
	new ChatServer(Integer.parseInt(args[0]));
	} 

}
