/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author H
 */
public class ClientAction {
    private String IPAddress = "127.0.0.1";
    private DataOutputStream outToServer;
    private BufferedReader inFromServer;
    private int PORT = 2004;
    public void StartConnection(){
        try {
           
            Socket clientSocket = new Socket(IPAddress, PORT);
            
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
    
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            SendMessage();
            
        } catch (IOException ex) {
            Logger.getLogger(ClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SendMessage(){
        String text = "send from client";
        try {
            outToServer.writeBytes(text + '\n');
        } catch (IOException ex) {
            Logger.getLogger(ClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ReceiveMessage(){
        try {
            System.out.println(inFromServer.readLine());
        } catch (IOException ex) {
            Logger.getLogger(ClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
