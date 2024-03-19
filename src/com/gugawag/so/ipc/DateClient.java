package com.gugawag.so.ipc;

/**
 * Client program requesting current date from server.
 *
 * Figure 3.22
 *
 * @author Silberschatz, Gagne and Galvin. Pequenas alterações feitas por Gustavo Wagner (gugawag@gmail.com)
 * Operating System Concepts  - Eighth Edition
 */ 

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class DateClient {
	public static void main(String[] args)  {
		try {
			// this could be changed to an IP name or address other than the localhost
			Socket sock = new Socket("localhost",6013);
			DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
			DataInputStream dis = new DataInputStream(sock.getInputStream());
			System.out.println("=== Cliente iniciado ===\n");

			while (true){
				System.out.print(">> ");

				Scanner teclado = new Scanner(System.in);

				dos.writeUTF(teclado.nextLine());

				String line = dis.readUTF();
				System.out.println("O servidor me disse:" + line);
			}
		}
		catch (IOException ioe) {
				System.err.println(ioe);
		}
	}
}
