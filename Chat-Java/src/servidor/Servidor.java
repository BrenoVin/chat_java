package servidor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

	ServerSocket serverSocket;
	Socket server;
	List<DataOutputStream> outList = new ArrayList<DataOutputStream>();

	public Servidor() throws IOException {
		serverSocket = new ServerSocket(2323, 10);

		while (true) {
			server = serverSocket.accept();
			outList.add(new DataOutputStream(server.getOutputStream()));
			new ServidorThread(server, outList);
		
		}
	
	}

	public static void main(String[] args) throws IOException {
		new Servidor();
	}
}
