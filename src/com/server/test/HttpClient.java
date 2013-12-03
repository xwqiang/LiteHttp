package com.server.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class HttpClient {
	private Socket socket = null;
	private OutputStream socketOut = null;
	private int port=8888;
	public void consoleTask() {
		try {
			socket = new Socket("localhost", port);
			socketOut = socket.getOutputStream();
			String msg ="";
			Scanner sc = new Scanner(System.in);
			msg = sc.next();
			socketOut.write(msg.getBytes());
			socketOut.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				socketOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] a){
		HttpClient c = new HttpClient();
		c.consoleTask();
	}
}
