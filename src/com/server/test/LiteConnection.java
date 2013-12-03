package com.server.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class LiteConnection implements Runnable {  
    private String path = Config.getConfig().getPath();  
    private Socket socket ;  
    private LiteRequest request; 
    private LiteSession session;
    public LiteConnection(Socket socket){  
        super();  
        this.socket = socket;  
    }  
  
    public void run() {  
         try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));  
            OutputStream out = this.socket.getOutputStream();  
            String command = null;
            while((command = br.readLine()) != null){  
                System.out.println("浏览器的指令:"+command);  
                if(command.length() <3){  
                    break;  
                }
                String result = command.substring(0,3);  
                if(result.equalsIgnoreCase("GET")){  
                    int begin = command.indexOf("/")+1;
                    int end   = command.lastIndexOf(" ");
                    String fileName = command.substring(begin,end);  
                    doGet(fileName,out);
                }  
                 
            }  
            out.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();  
        }  
    }  
  
    private void doGet(String fileName, OutputStream out) {  
        File file = new File(path+fileName);  
        if(!file.isDirectory()){  
            if(!file.exists()){  
                file = new File(path + Config.getConfig().getDefaultPage());     
            }  
        }else {  
            file = new File(path +  Config.getConfig().getDefaultPage());  
        }  
        InputStream is  = null;  
        try {  
            is = new FileInputStream(file);  
            byte[] data  =new byte[1024];  
            int    len = 0;  
            while((len = is.read(data)) != -1){  
                out.write(data,0,len);  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally {  
            if(is != null){  
               try {  
                    is.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }finally{  
                    is = null;  
                }  
            }  
        }  
    }
}  
