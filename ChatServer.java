import java.net.*;
import java.io.*;

public class ChatServer {
	private static boolean flag = true;
	private static String message;
	public static void main(String[] args) throws Exception{
		ChatServer server = new ChatServer();
		server.run();
	}
	public void run() throws Exception{
		ServerSocket serverSocket = new ServerSocket(444);				
		System.out.println("Waiting for client\n");
		Socket socket = serverSocket.accept();
		PrintStream printStream = new PrintStream(socket.getOutputStream());
		while(flag){
			InputStreamReader ir = new InputStreamReader(socket.getInputStream());
			BufferedReader br = new BufferedReader(ir);
			message = br.readLine();
			if(!message.equals("logout")){
				System.out.println("Recieved Message is: "+message);	//Message from client
			}
		
			if(message.equals("logout")){			
				System.out.println("\nLogging Out");
				flag = false;
				socket.close();
				serverSocket.close();
			}
			else {			
				printStream.println("Message is Recieved");
			}
		}
	}

}
