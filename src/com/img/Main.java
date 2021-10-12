package com.img;

public class Main {

    public static void main(String[] args) {
        Client client1 = new Client();
        Client client2 = new Client();
        Client client3 = new Client();
        Thread threadClient1 = new Thread((Runnable) client1);
        Thread threadClient2 = new Thread((Runnable) client2);
        Thread threadClient3 = new Thread((Runnable) client3);

        threadClient1.start();
        threadClient2.start();
        threadClient3.start();
    }
}
