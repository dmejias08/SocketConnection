package com.img;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    private int name;



    public ClientHandler(Socket clientSocket, int name, DataInputStream in,DataOutputStream out) throws IOException {
        this.client = clientSocket;
        this.name = name;
        this.in = new DataInputStream(client.getInputStream());
        this.out = new DataOutputStream(client.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                String request = in.readUTF();
                if (request == "name"){
                    out.writeUTF("hello");
                }else {
                    System.out.println("CLIENT"+name+" leaving...");
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
