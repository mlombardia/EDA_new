import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Evaluator {
    private Stack<Double>stack;
    private Stack<String>sign_stack;

    private static Map<String, Integer> mapping = new HashMap<String, Integer>() {
        { put("+", 0);put("-", 1);put("*", 2);put("/", 3);put("^", 4);put("(", 5);put(")", 6); }
    };

    private static Map<String, Double> vbles = new HashMap<String, Double>()
    { {put("nro1",0.2);put("x",-2.0);put("y",2.0);} };

    private static boolean [][] precedenceMatrix = {
            //+     -     *    /     ^     (     )
     /* + */{true,true,false,false,false,false,true},
     /* - */{true,true,false,false,false,false,true},
     /* * */{true,true,true,true,false,false,true},
     /* / */{true,true,true,true,false,false,true},
     /* ^ */{true,true,true,true,false,false,true},
     /* ( */{false,false,false,false,false,false}
    };

    private boolean getPrecendence(String tope, String current){
        Integer topeIndex;
        Integer currentIndex;
        if((topeIndex = mapping.get(tope)) == null){
            throw new RuntimeException(String.format("tope operator %s not found", tope));
        }
        if((currentIndex = mapping.get(current)) == null){
            throw new RuntimeException(String.format("current operator %s not found", current));
        }
        return precedenceMatrix[topeIndex][currentIndex];
    }

    public Evaluator(){
        stack = new Stack<>();
        sign_stack = new Stack<>();
    }

    public void addElem(String elem){
        if(!(elem.equals("+") || elem.equals("-") || elem.equals("*") || elem.equals("/") || elem.equals("^"))){
            stack.add(Double.valueOf(elem));
        }else{
            makeOperation(elem);
        }
    }

    public Double showResult(){
        return stack.peek();
    }

    private void makeOperation(String elem){
        Double op_right = stack.pop();
        Double op_left = stack.pop();
        switch (elem){
            case "+":
                stack.push(op_left+op_right);
                break;
            case "-":
                stack.push(op_left-op_right);
                break;
            case "*":
                stack.push(op_left*op_right);
                break;
            case "/":
                stack.push(op_left/op_right);
                break;
            case "^":
                stack.push(Math.pow(op_left,op_right));
                break;
        }
    }

    public void evaluate(){
        System.out.print("Introduzca la notacion infija: ");
        Scanner postfijaScanner = new Scanner(infijaToPostfija());

        //postfijaScanner.hasNextLine();

        //String line = postfijaScanner.nextLine();


        Scanner lineScanner = postfijaScanner.useDelimiter("\\s+");
        while(lineScanner.hasNext()) {
            String token = lineScanner.next();
            //System.out.println(token);
            if(token.matches("(([0-9]+\\.?[0-9]*)+|\\+|-|\\*|/|\\^)+")) {
                //System.out.println("OK");
                addElem(token);
            }
            else
                System.out.println("invalid " + token);
        }
        System.out.println(showResult());
    }

    private String infijaToPostfija(){
        String result="";

        Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
        String s = scanner.nextLine();

        Scanner lineScanner = new Scanner(s).useDelimiter("\\s+");
        while(lineScanner.hasNext()) {
            String token = lineScanner.next();
            if (!(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^") || token.equals("(") || token.equals(")"))) {
                if (vbles.containsKey(token)){
                    token = String.valueOf(vbles.get(token));
                }
                result = result.concat(token).concat(" ");
            } else {
                if (sign_stack.empty() || !getPrecendence(sign_stack.peek(),token)) {
                    sign_stack.push(token);
                } else {
                    if (token.equals(")")) {
                        //sign_stack.pop();
                        while (!(sign_stack.empty()) && !(sign_stack.peek().equals("("))) {
                            result = result.concat(sign_stack.pop()).concat(" ");
                        }
                        if(sign_stack.empty()){
                            throw new RuntimeException("Error Falta (");
                        }
                        sign_stack.pop();
                    } else {
                        if (sign_stack.peek().equals("^")) {
                            while (!(sign_stack.empty()) && sign_stack.peek().equals("^")) {
                                result = result.concat(sign_stack.pop()).concat(" ");
                            }
                            sign_stack.push(token);
                        } else {
                            result = result.concat(sign_stack.pop()).concat(" ");
                            sign_stack.push(token);
                        }
                    }
                }
            }
        }
        while(!(sign_stack.empty())){
            if(sign_stack.peek().equals("(")){
                throw new RuntimeException("Error Falta )");
            }
            result = result.concat(sign_stack.pop()).concat(" ");
        }

        System.out.println(result);
        return result;
    }
}


//3 * ( ( 5 - 10.2 ) / 0.5 ) - 2
//Rta: 3 5 10.2 - 0.5 / * 2 -
//
//3 * ( ( 5 - 10.2 / 0.5 ) -
//Rta: Error falta )
//
//3 * (   5 - 10.2 ) / 0.5 ) - 2
//Rta: Error falta (


/*
private static Map<String,Integer> vbles = new HashMap<String, Double>()
{ {put("nro1",0.2);put("x",-2.0);put("y",2.0);} };

Invoco igual que antes

Si ingreso ( nro1 + 3 ) * (x - -2 + y) se deberia obtener 6.4

 */