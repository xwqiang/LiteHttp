package com.server.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {  
    
    private String  path ;  
    private String  defaultPage;  
    private static Config config ;  
     
    public static Config getConfig(){  
        if(config == null){  
            config = new Config();  
            config.init();  
        }  
        return config;  
    }  
     
    private void init(){  
        Properties properties = new Properties();  
        try {  
            InputStream reader = new FileInputStream(new File("config.properties"));  
            properties.load(reader);  
            this.path = properties.getProperty("path");  
            this.defaultPage = properties.getProperty("defaultIndex");  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
     
    private Config(){  
    }  
     
    public String getPath() {  
        return path;  
    }  
    public void setPath(String path) {  
        this.path = path;  
    }  
    public String getDefaultPage() {  
        return defaultPage;  
    }  
    public void setDefaultPage(String defaultPage) {  
        this.defaultPage = defaultPage;  
    }  
     
}