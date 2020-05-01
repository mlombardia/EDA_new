import java.util.Scanner;

public class ScannerTest {
    public static void main(String args[]){

        Evaluator eval = new Evaluator();

        eval.evaluate();

    /*Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresión en notación postfija: ");
        inputScanner.hasNextLine();

    String line = inputScanner.nextLine();


    Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");
        while(lineScanner.hasNext()) {
            String token = lineScanner.next();
            System.out.println(token);
            if(token.matches("(([0-9]+\\.?[0-9]*)+|\\+|-|\\*|/|\\^)+")) {
                System.out.println("OK");
                eval.addElem(token);
            }
            else
                System.out.println("invalid " + token);
        }
        System.out.println(eval.showResult());*/

}
}

//implementar evaluator, evalua expresion que venga en posfija seguro; hacer stack de double o de string y hacer double
//