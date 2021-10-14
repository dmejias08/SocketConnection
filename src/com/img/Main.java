package com.img;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        String test = "(5+4)";
        System.out.println(infixToPosfix(test));
    }


    public static String infixToPosfix (String exp){

        System.out.println("Estoy en infixtoPosfix");
        String result = new String("");

        // empty stack for operators
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < exp.length(); i++){
            char ch = exp.charAt(i);

            if (Character.isDigit(ch)){
                result += ch;
                System.out.println("Soy digit " + ch);

            }else if (String.valueOf(ch) == "("){
                operators.push(ch);
                System.out.println("Soy (");

                // pop until find "("
            }else if (String.valueOf(ch) == ")"){
                while (!operators.isEmpty() &&
                        String.valueOf(operators.peek()) != "("){
                    result += operators.pop();
                    System.out.println("Soy )");
                    operators.pop();

                }
            }else { // found an operator

                while (!operators.isEmpty() &&
                        Prec(String.valueOf(ch)) <= Prec(String.valueOf(operators.peek())))
                {
                    System.out.println("soy operator " + ch);
                    result += String.valueOf(operators.pop());
                }
            }
            operators.push(ch);
        }
        //pop all
        while (operators.isEmpty()==true){
            System.out.println("last while");
            if(String.valueOf(operators.peek()) == "("){
                return "Invalid";
            }
        }
        System.out.println("Soy resutado "+result);
        return result;
    }
    static int Prec (String operator){
        if (operator == "+" || operator == "-"){
            return 1;
        }
        else if (operator == "*" || operator == "/"){
            return 2;
        }
        return -1;
    }
}
