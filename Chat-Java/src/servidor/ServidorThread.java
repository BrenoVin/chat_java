package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import javax.swing.JOptionPane;

import chat.Cliente;

public class ServidorThread {

	Socket s;
	List<DataOutputStream> outList;
	DataInputStream in;
	String message;
	Thread t;

	public ServidorThread(Socket s, List<DataOutputStream> outList) {
		this.s = s;
		this.outList = outList;
		try {
			in = new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			System.err.println("Não pegou o inputStream");
		}
		Thread();
	}

	public void Thread() {
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						message = in.readUTF();
						sendMessage();
					} catch (IOException e) {
						t.stop();
						System.err.println("Não está encerrando a Thread!");
						try {
							s.close();
						} catch (IOException e1) {
							System.err
									.println("Não Fechou a conexão com o cliente!");
						}
					}
				}
			}
		});
		t.start();
	}

	public void sendMessage() {
		System.out.println(outList);
		
		for (DataOutputStream out : outList) {
			try {
				out.flush();
				out.writeUTF(message);
			} catch (IOException e) {
				outList.remove(out);
				System.err
						.println("Erro ao enviar a mensagem para todos conectados!");
			}
		}
	}

}
