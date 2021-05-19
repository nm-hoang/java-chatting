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
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author H
*/
public class ClientAction {
    private String IPAddress;
    private DataOutputStream outToServer;
    private BufferedReader inFromServer;
    private int PORT = 2004;
    private Socket clientSocket;
    public ClientAction() {
        try {
            this.IPAddress = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void StartConnection(){
        try {
           
            clientSocket = new Socket(IPAddress, PORT);
                inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outToServer = new DataOutputStream(clientSocket.getOutputStream());
                SendMessage("IP:" + IPAddress);
            while(true){
                ReceiveMessage();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SendMessage(String text){
       
        try {
            outToServer.writeBytes(text + "\n");
        } catch (IOException ex) {
            Logger.getLogger(ClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ReceiveMessage(){
        try {
             inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println(inFromServer.readLine());
        } catch (IOException ex) {
            Logger.getLogger(ClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
