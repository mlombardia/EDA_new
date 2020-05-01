import info.debatty.java.stringsimilarity.QGram;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.io.*;
import java.util.*;

public class CommerceBack {

    private List<String> catalogue;

    public CommerceBack() throws IOException {
        this.catalogue = new ArrayList<>();
        try {
            addItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void generateSimilarity(String s) throws EncoderException {
        s = applyRules(s);
        Map<String, Double> most_coincidence = new HashMap<>();
        Iterator it = catalogue.iterator();
        while(it.hasNext()){
            String other_s = (String)it.next();
            most_coincidence.put(other_s, calculateMatch(s,other_s));
        }
        System.out.println(most_coincidence);

    }

    public void printCatalogue(){
        Iterator it = catalogue.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    private Double calculateMatch(String s1, String s2) throws EncoderException {
        return Math.max(Math.max(calcSoundex(s1,s2),calcLevenshtein(s1,s2)),Math.max(calcMetaphoneSimil(s1,s2),calcTrigram(s1,s2)));
    }

    private String applyRules(String previous){
        String final_s="";

        String s[] = previous.toLowerCase().split(" ");
        for(int i = 0; i < s.length;i++){
            if(!s[i].equals("")){
                final_s = final_s.concat(s[i]).concat(" ");
            }
        }
        final_s = final_s.trim();

        return final_s;
    }

    private void addItems() throws IOException {
        File file = new File(CommerceImpl.class.getClassLoader().getResource("products.txt").getFile());
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null){
            catalogue.add(st);
        }
    }

    private double calcSoundex(String s1, String s2) throws EncoderException {
        int difference = new Soundex().difference(s1,s2);
        return (double)difference*0.25;
    }

    private double calcLevenshtein(String s1, String s2){
        Integer difference = new LevenshteinDistance().apply(s1,s2);
        return 1-((double)difference/Math.max(s1.length(),s2.length()));
    }

    private double calcMetaphoneSimil(String s1, String s2){
        String encoding1 = new Metaphone().encode(s1);
        String encoding2 = new Metaphone().encode(s2);

        return calcLevenshtein(encoding1, encoding2);
    }

    private double calcTrigram(String s1, String s2){
        QGram difference = new QGram(3);
        String aux1 = "##".concat(s1).concat("##");
        String aux2 = "##".concat(s2).concat("##");

        double TG_1 = calculateTG(difference.getProfile(aux1));
        double TG_2 = calculateTG(difference.getProfile(aux2));
        double TG_notShared = difference.distance(aux1,aux2);

        return ((TG_1 + TG_2 - TG_notShared)/(TG_1+TG_2));
    }

    private double calculateTG(Map<String, Integer> profile){
        double result = 0;
        Set keys = profile.keySet();
        Iterator it = keys.iterator();
        while(it.hasNext()){
            result += profile.get(it.next());
        }
        return result;
    }
}
