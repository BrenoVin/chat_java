package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import chat.Cliente;

public class ServidorThread {

	Socket s;
	List<DataOutputStream> outList;
	DataInputStream in;
	String message;

	public ServidorThread(Socket s, List<DataOutputStream> outList) {
		this.s = s;
		this.outList = outList;
		try {
			in = new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Thread();
	}

	public void Thread() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {	
						message = in.readUTF();
						sendMessage();
					} catch (IOException e) {
						try {
							s.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						e.printStackTrace();
					}
				}
			}
		});

		t.start();
	}

	public void sendMessage() {
		for (DataOutputStream out : outList) {
			try {
				out.writeUTF(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
