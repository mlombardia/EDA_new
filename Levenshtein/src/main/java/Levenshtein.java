public class Levenshtein {
    public static void main(String args[]){
        String str1 = "exkusa";
        String str2 = "ex-amigo";

        char aux1[];
        aux1 = str1.toUpperCase().toCharArray();
        char aux2[];
        aux2 = str2.toUpperCase().toCharArray();

        int distance[][] = new int[aux1.length + 1][aux2.length + 1];

        for(int i=0; i<=aux1.length; i++){
            distance[i][0]=i;
        }

        for(int i=0; i<=aux2.length;i++){
            distance[0][i] = i;
        }

        for(int i = 1; i <= aux1.length; i++){
            for(int j = 1; j <= aux2.length; j++){
                distance[i][j] = Math.min(distance[i][j-1]+1,Math.min(distance[i-1][j]+1,distance[i-1][j-1] + (aux1[i-1] == aux2[j-1] && i == j? 0:1)));
                System.out.print(distance[i][j]);
            }
            System.out.println();
        }
        System.out.println(distance[aux1.length][aux2.length]);
        double normalize = 1.0 - ((double)distance[aux1.length][aux2.length]/(Math.max(aux1.length,aux2.length)));
        System.out.println(normalize);
    }
}