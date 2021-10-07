package com.img;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static DataInputStream in;
    private static DataOutputStream out;
    private static Socket server;
    public static final int port = 9090;

    public static void main(String[] args) throws IOException {
        Scanner sn = new Scanner(System.in);
        sn.useDelimiter("\n");
        server = new Socket("localhost",port);
        in = new DataInputStream(server.getInputStream());
        out = new DataOutputStream(server.getOutputStream());

        while (true){
            System.out.println(in.readUTF());
            String request = sn.nextLine();
            out.writeUTF(request);
            if(request.equals("Exit")){
                System.out.println("Closing connection...");
                server.close();
                System.out.println("Connection closed");
                break;
            }
            String response = in.readUTF();
            System.out.println("Response: "+response);
        }
        sn.close();
        in.close();
        out.close();

    }
}
