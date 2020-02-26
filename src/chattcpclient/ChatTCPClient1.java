/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattcpclient;

import com.sun.xml.internal.ws.handler.ClientSOAPHandlerTube;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author baske
 */
public class ChatTCPClient1 implements Runnable{

    /**
     * @param args the command line arguments
     */
    private static Socket clientSocket = new Socket();
    private static BufferedReader inputline = null;
    private static PrintStream os = null;
    private static Scanner sis = null;
    public static void main(String[] args) {
        try {
            int port = 2222;
            String host = "localhost";
            
            clientSocket = new Socket(host, port);
            inputline = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintStream(clientSocket.getOutputStream());
            sis = new Scanner(clientSocket.getInputStream());
            (new Thread(new ChatTCPClient())).start();
            while(true){
              os.println(inputline.readLine().trim());  
            }    

            
        } catch (IOException ex) {
            Logger.getLogger(ChatTCPClient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unknown host");
        }
        

        
        
    }

    @Override
    public void run() {
        String responseLine;
        while((responseLine = sis.nextLine()) != null){
            System.out.println(responseLine);
            
        }
       
    }
    
}
