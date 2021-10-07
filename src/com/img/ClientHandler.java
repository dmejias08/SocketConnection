package com.img;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;



    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        in = new DataInputStream(client.getInputStream());
        out = new DataOutputStream(client.getOutputStream());
    }

    @Override
    public void run() {

    }
}
