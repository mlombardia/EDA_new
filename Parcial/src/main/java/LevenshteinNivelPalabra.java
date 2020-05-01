public class LevenshteinNivelPalabra {

    public static int getLevenshteinPalabra (String frase1, String frase2){
        String[] strings = frase1.split("\\s+");
        String[] strings1 = frase2.split("\\s+");

        int distance[][] = new int[strings.length + 1][strings1.length + 1];

        for(int i=0; i<=strings.length; i++){
            distance[i][0]=i;
        }

        for(int i=0; i<=strings1.length;i++){
            distance[0][i] = i;
        }

        for(int i = 1; i <= strings1.length; i++){
            for(int j = 1; j <= strings.length; j++){
                distance[i][j] = Math.min(distance[i][j-1]+1,Math.min(distance[i-1][j]+1,distance[i-1][j-1] + (strings1[i-1].equals(strings[j-1])? 0 : 1)));
            }
        }
        return distance[strings.length][strings1.length];
    }

}
