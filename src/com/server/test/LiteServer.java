package com.server.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LiteServer {
	private boolean started;
	public boolean startUp(boolean started){
		this.started = started;
		try {  
            ServerSocket server = new ServerSocket(8888);  
            System.out.println("服务启动，端口：8888");
            ExecutorService executor = Executors.newCachedThreadPool();
            while (started) {  
                Socket socket = server.accept(); 
                LiteConnection session = new LiteConnection(socket);  
                executor.execute(session);
//                new Thread(session).start();  
            } 
			executor.shutdown();
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		return started;
	}
    public static void main(String[] args) {  
    	LiteServer server = new LiteServer();
        server.startUp(true);
    }  
}