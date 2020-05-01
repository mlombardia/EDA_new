public abstract class Soundex {
    public static char mapping[] = {'0','1','2','3','0','1','2','0','0','2','2','4','5','5','0','1','2','6','2','3','0','1','0','2','0','2'};
    //                               A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P   Q   R   S   T   U   V   W   X   Y   Z

    private static char in[];
    private static char out[]= {'0','0','0','0'};;

    public static String encode(String word){
        in = word.toCharArray();
        int start = 0;
        while((in[start] < 'A' || in[start] > 'Z') && (in[start] < 'a' || in[start] > 'z')){
            start++;
        }
        if((in[start] >= 'a' && in[start] <= 'z')){
            in[start] -= 32;
        }
        out[0] = in[start];
        int count = 1;
        char current, last = getMapping(in[start]);
        for(int i = start; count < 4 && i < in.length; i++, last = current){
            char aux = in[i];
            current = getMapping(aux);
            if(current != last && current != '0'){
                out[count++] = current;
            }
        }
        return String.valueOf(out);
    }

    static char getMapping(char candidate) {

        if (candidate < 'A' || candidate > 'Z') {
            if (candidate < 'a' || candidate > 'z') {
                return 0;
            }
            candidate -= 32;
        }
        return mapping[candidate-'A'];
    }

    public static double checkSimilarity(String one, String other){
        int count = 0;
        char aux_one[] = one.toCharArray();
        char aux_other[] = other.toCharArray();
        for(int i = 0; i < aux_one.length; i++){
            if(aux_one[i] == aux_other[i])
                count++;
        }
        return (0.25*count);
    }

}
