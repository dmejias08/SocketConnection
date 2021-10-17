package com.img;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {

    }


//    public static String infixToPosfix (String exp){
//
//        System.out.println("Estoy en infixtoPosfix");
//        String result = "";
//        // empty stack for operators
//        Stack<Character> operators = new Stack<>();
//
//        for (int i = 0; i < exp.length(); i++){
//            System.out.println(operators);
//            char ch = exp.charAt(i);
//
//            if (Character.isDigit(ch)){
//                result += ch;
//                System.out.println("Soy digit " + ch);
//
//            }else if (ch == '('){
//                operators.push(ch);
//                System.out.println("Soy (");
//
//                // pop until find "("
//            }else if (ch == ')'){
//                while (!operators.isEmpty() &&
//                        operators.peek() != '('){
//                    result += operators.pop();
//                    System.out.println("Soy "+result);
////                    operators.pop();
//
//                }
//            }else { // found an operator
//                while (!operators.isEmpty() &&
//                        Prec(ch) <= Prec(operators.peek())) {
//                    System.out.println("soy operator " + ch);
//                    result += operators.pop();
//                }
//                operators.push(ch);
//            }
//        }
//        //pop all
//        while (!operators.isEmpty()){
//            System.out.println("last while");
//            if(operators.peek() == '(') {
//                break;
//            }
//            else if(operators.peek()!= '(') {
//                result += operators.pop();
//            }
//        }
//        return result;
//    }
//    static int Prec (char  operator){
//        if (operator == '+' || operator == '-'){
//            return 1;
//        }
//        else if (operator == '*' || operator == '/'){
//            return 2;
//        }
//        return -1;
//    }
}
