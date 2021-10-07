package com.img;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static ServerSocket server;
    public static Socket client;
    public static final int port = 9090;
    public static ArrayList<ClientHandler> clients = new ArrayList<>();
    public static ArrayList<Integer> clientNumber = new ArrayList<>();
    public static int contClients = 1;



    public static void main(String[] args) throws IOException {
        server = new ServerSocket(port);
        while (true){
            try {
                System.out.println("Server waiting for connection...");
                client = server.accept();
                System.out.println("Client connected");
                DataInputStream in = new DataInputStream(client.getInputStream());
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                clientNumber.add(contClients);
                System.out.println("NOMBRE DE CLIENTE: " + contClients);
                ClientHandler clientThread = new ClientHandler(client, contClients, in, out);
                clients.add(clientThread);
                Thread threadNew = new Thread(clientThread);
                threadNew.start();
                contClients++;
            }catch (Exception e){
                server.close();
                client.close();
                System.out.println("Closing connection...");
            }
        }
    }
}
