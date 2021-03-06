package com.img;

import java.io.*;
import java.net.Socket;
import java.util.Stack;

public class ClientHandler implements Runnable {
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    private int name;


    public ClientHandler(Socket clientSocket, int name, DataInputStream in, DataOutputStream out) throws IOException {
        this.client = clientSocket;
        this.name = name;
        this.in = new DataInputStream(client.getInputStream());
        this.out = new DataOutputStream(client.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                String command = in.readUTF();
                System.out.println("Command: " + command);
                if (command.equals("exit")) {
                    out.writeUTF("Exit");
                    client.close();
                    in.close();
                    out.close();
                    System.out.println("Closing connection");
                    break;
                }
                BinaryTree tree = new BinaryTree();
                int result = evalBinaryTree(tree.BinaryTree(infixToPosfix(command)));
                String response = String.valueOf(result);
                out.writeUTF(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String infixToPosfix(String exp) {

        String result = "";
        // empty stack for operators
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            System.out.println(operators);
            char ch = exp.charAt(i);

            if (Character.isDigit(ch)) {
                result += ch;
                System.out.println("Soy digit " + ch);

            } else if (ch == '(') {
                operators.push(ch);
                System.out.println("Soy (");

                // pop until find "("
            } else if (ch == ')') {
                while (!operators.isEmpty() &&
                        operators.peek() != '(') {
                    result += operators.pop();
                    System.out.println("Soy " + result);
//                    operators.pop();

                }
            } else { // found an operator
                while (!operators.isEmpty() &&
                        Prec(ch) <= Prec(operators.peek())) {
                    System.out.println("soy operator " + ch);
                    result += operators.pop();
                }
                operators.push(ch);
            }
        }
        //pop all
        while (!operators.isEmpty()) {
            System.out.println("last while");
            if (operators.peek() == '(' && operators.isEmpty()) {
                break;
            } else if (operators.peek() != '(') {
                result += operators.pop();
            } else if (operators.peek() == '(') {
                Character trash = operators.pop();
            }
        }
        return result;
    }

    static int Prec(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        }
        return -1;
    }

    //Node class BinaryTree
    class Node {
        char data;
        Node left;
        Node right;

        //Constructor
        Node(char value) {
            this.data = value;
            this.left = this.right = null;
        }
    }

    //Creating BinaryTree
    class BinaryTree {
        boolean foundOperator(char op) {
            if (op == '+' || op == '-' || op == '*' || op == '/') {
                return true;
            } else {
                return false;
            }
        }
        public Node BinaryTree(String expression){

            Stack <Node> nodeStack = new Stack<Node>();
            Node node, node1, node2;

            for (int j = 0; j < expression.length(); j++) {
                char ch = expression.charAt(j);

                // Found number add to the stack
                if (foundOperator(ch) == false) {
                    node = new Node(ch);
                    nodeStack.push(node);

                } else { //Found operator
                    node = new Node(ch);

                    //Add its children
                    node1 = nodeStack.pop();
                    node2 = nodeStack.pop();

                    node.right = node1;
                    node.left = node2;

                    //add father to the stack
                    nodeStack.push(node);
                }
            }
            node = nodeStack.peek();
            return node;
        }
    }
    // evaluate binary tress
    public static int charToInt(char num) {
        int num1 = 0;

        for(int k=0; k < String.valueOf(num).length(); k++){
            num1 =  num1 *10 + ((int)String.valueOf(num).charAt(k)-48);
        }
        return num1;
    }

    public static int evalBinaryTree(Node root){

        if(root == null){
            return 0;
        }

        if (root.left == null && root.right == null){
            return charToInt(root.data);
        }

        int leftSubTree = evalBinaryTree(root.left);

        int rightSubTree = evalBinaryTree(root.right);

        if (root.data == '+'){
            return leftSubTree + rightSubTree;
        }
        if (root.data == '-'){
            return rightSubTree - leftSubTree ;
        }
        if (root.data == '*'){
            return leftSubTree * rightSubTree;
        }
        return leftSubTree /rightSubTree ;

    }


}
