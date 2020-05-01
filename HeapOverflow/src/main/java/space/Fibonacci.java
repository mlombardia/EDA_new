package space;

public class Fibonacci {
    public static void main(String args[]){
        int lower = 0;
        int higher = 1;
        int aux;
        while(true){
            aux = higher;
            higher += lower;
            lower  = aux;
            System.out.println(higher);
        }
    }
}
