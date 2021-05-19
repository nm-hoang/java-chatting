/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.Server;

import java.awt.List;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author H
 */

public class ServerAction extends Thread {
    private String IPAddress;;
    private int PORT = 2004;
    private  BufferedReader inFromClient;
    private  DataOutputStream outToClient;
    private ServerSocket welcomeSocket = null;
    private ArrayList<String> listClient;  

    public ArrayList<String> getListClient() {
        return listClient;
    }

    public void setListClient(ArrayList<String> listClient) {
        this.listClient = listClient;
    }

   
    private  Socket connectionSocket;
    
    public void run(){
        while(true){
            StartConnection2();
        }
    }
    public ServerAction() {
        try {
            this.IPAddress = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public void Start(){
      ServerAction sa = new ServerAction();
      sa.start();
  }
  public void InitializeSocket(){
        try {
            welcomeSocket = new ServerSocket(PORT);
        } catch (IOException ex) {
            Logger.getLogger(ServerAction.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  public void StartConnection2(){
       
        try {
                if(welcomeSocket!=null){
                    
                
                
                connectionSocket = welcomeSocket.accept();
                inFromClient = new BufferedReader(new
                    InputStreamReader(connectionSocket.getInputStream()));
                outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                
                //Receive message from client
               
                //Send message to client
                SendMessage("Send from server");
                listClient = new ArrayList<String>();
                
                Thread myThread = new Thread() {
                @Override
                public void start() {
                    // do something in the actual (old) thread
                        ReceiveMessage();
                     }
                };
                myThread.start();
                }
        } catch (IOException ex) {
            Logger.getLogger(ServerAction.class.getName()).log(Level.SEVERE, null, ex);
        }
            
  }
  public void SendMessage(String message){
        try {
            outToClient.writeBytes(message + "\n");
        } catch (IOException ex) {
            Logger.getLogger(ServerAction.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  public void ReceiveMessage(){
        try {
            inFromClient = new BufferedReader(new
                    InputStreamReader(connectionSocket.getInputStream()));
            String[] message = inFromClient.readLine().split(":");
            
            if(message.length == 1){
                System.out.println("Message: "  + message[0]);
            }
            if(message.length == 2){
                if(message[0].equals("IP")){
                    listClient.add(message[1]);
                    System.out.println("list:");
                    for(int i=0; i<listClient.size(); i++){
                           System.out.println(listClient.get(i));
                    }
                }
               
               
                System.out.println(message[0]);
                System.out.println("2: " + message[1]);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerAction.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  public void CloseConnection(){
       // try {
            //connectionSocket.close();
            System.out.println("Disconnected");
      ///  } catch (IOException ex) {
       //     Logger.getLogger(ServerAction.class.getName()).log(Level.SEVERE, null, ex);
       // }
  }
}
