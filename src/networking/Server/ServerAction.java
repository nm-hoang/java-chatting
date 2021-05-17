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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author H
 */

public class ServerAction {
    private String IPAddress = "127.0.0.1";
    private int PORT = 2004;
    private  BufferedReader inFromClient;
    private  DataOutputStream outToClient;
    private ServerSocket welcomeSocket;
    private  Socket connectionSocket;
  public void StartConnection(){
        try {
            welcomeSocket = new ServerSocket(PORT);
            System.out.println("Setup Server");
            while(true){
                
                connectionSocket = welcomeSocket.accept();
                inFromClient = new BufferedReader(new
                    InputStreamReader(connectionSocket.getInputStream()));
                outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                
                //Receive message from client
                String receive = inFromClient.readLine();
                try{
                    System.out.println(receive);
                }catch(Exception ex){
                    System.out.println(ex);
                }
                //Send message to client
                outToClient.writeBytes("Send from server");
                
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
