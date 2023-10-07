package com.example;
import java.io.*;
import java.net.*;


public class AppServer{
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;


    public Socket attenti(){
        try{
            System.out.println("1 SERVER partito in esecuzione ...");

            server = new ServerSocket(6789);

            client = server.accept();

            server.close();

            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));

            outVersoClient = new DataOutputStream(client.getOutputStream());
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.exit(1);
        }
        return client;
    }


    public void comunica(){
        
            try{
                do{

                    System.out.println("3 benvenuto client, scrivi una frase e la trasformo in maiuscolo. Attendo...");
                    stringaRicevuta = inDalClient.readLine();
                    System.out.println("6 Ricevuta la stringa dal cliente: " + stringaRicevuta);
        
                    stringaModificata = stringaRicevuta.toUpperCase();
                    System.out.println("7 Invio la stringa modificata al client...");
                    outVersoClient.writeBytes(stringaModificata + '\n');
                    
                }while(!stringaRicevuta.equalsIgnoreCase("bye"));
                
                System.out.println("9 SERVER: fine elaborazione!");
                client.close();

            }catch(Exception e){
                
            }
    }

    public static void main(String args[]){
        AppServer servente = new AppServer();
        servente.attenti();
        servente.comunica();
    }
}