/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.Server;

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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author H
 */

public class ServerAction {
    private String IPAddress;
    private int PORT = 2004;
    private  BufferedReader inFromClient;
    private  DataOutputStream outToClient;
    private ServerSocket welcomeSocket;
    private  Socket connectionSocket;
    private ArrayList<String> listClient;
    public ServerAction() {
        try {
            this.IPAddress = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public void StartConnection(){
      listClient = new ArrayList<String>();
        try {
            welcomeSocket = new ServerSocket(PORT);
            System.out.println("Setup Server");
            while(true){
                
                connectionSocket = welcomeSocket.accept();
                inFromClient = new BufferedReader(new
                    InputStreamReader(connectionSocket.getInputStream()));
                outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                //Send message to client
                //SendMessage("Send from server");
                
                //Receive message from client
             //   while(true){
                    ReceiveMessage();
              //  }
              
                
            }
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
  public void CloseConnection(){
       // try {
            //connectionSocket.close();
            System.out.println("Disconnected");
      ///  } catch (IOException ex) {
       //     Logger.getLogger(ServerAction.class.getName()).log(Level.SEVERE, null, ex);
       // }
  }
}
