package com.gugawag.so.ipc;

/**
 * Time-of-day server listening to port 6013.
 *
 * Figure 3.21
 *
 * @author Silberschatz, Gagne, and Galvin. Pequenas alterações feitas por Gustavo Wagner (gugawag@gmail.com)
 * Operating System Concepts  - Ninth Edition
 * Copyright John Wiley & Sons - 2013.
 */
import java.net.*;
import java.io.*;
import java.util.Date;

public class DateServer{
	public static void main(String[] args) throws IOException{

		ServerSocket sock = new ServerSocket(6013);

		System.out.println("=== Servidor iniciado ===\n");
		// escutando por conexões
		while (true) {
			Socket client = sock.accept();
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			DataInputStream dis = new DataInputStream(client.getInputStream());

			new Thread(()-> {
				while (true) {
					try {
						// Se chegou aqui, foi porque algum cliente se comunicou
						System.out.println("Servidor recebeu comunicação do ip: " + client.getInetAddress() + "-" + client.getPort() + "\n");
						String mensagem = dis.readUTF();

						// Escreve a data atual no socket
						dos.writeUTF(new Date().toString() + "-Boa noite alunos!");

						System.out.println("O cliente me disse:" + mensagem + "\n");

						// fechar o socket e volta no loop para escutar novas conexões
						//client.close();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}).start();
		}
	}
}
