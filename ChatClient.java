import java.io.*;
import java.net.*;
public class ChatClient {
	private static String messageOut;
	private static String messageIn;
	private static boolean flag = true;
	public static void main(String[] args) throws Exception{		
		ChatClient client = new ChatClient();
		client.run();
	}
	void run() throws Exception{
		Socket socket = new Socket("localhost",444);													//Socket with port '444' and ip localhost
		PrintStream printStream = new PrintStream(socket.getOutputStream());
		do{
			messageOut = null;
			messageIn = null;
			System.out.println("\nEnter message for server OR type 'logout' to terminate operation\n");
			InputStreamReader ir = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(ir);
			messageOut = br.readLine();																	//Reads message from console to send to server.
			printStream.println(messageOut);															//Sends message to server.
			if(messageOut.equals("logout")){
				flag = false;
				socket.close();
			}
			else{
				InputStreamReader iReader = new InputStreamReader(socket.getInputStream());
				BufferedReader bReader = new BufferedReader(iReader);
				messageIn = bReader.readLine();
				System.out.println("\n"+messageIn);															//Prints message from server.
			}
			
		}while(flag);
		
	}

}
