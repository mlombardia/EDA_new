import info.debatty.java.stringsimilarity.QGram;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class NewTriGram {
    public static double calculate(String s1, String s2){
        QGram difference = new QGram(3);
        String aux1 = "##".concat(s1).concat("##");
        String aux2 = "##".concat(s2).concat("##");

        double TG_1 = calculateTG(difference.getProfile(aux1));
        double TG_2 = calculateTG(difference.getProfile(aux2));
        double TG_notShared = difference.distance(aux1,aux2);

        return ((TG_1 + TG_2 - TG_notShared)/(TG_1+TG_2));
    }

    private static double calculateTG(Map<String, Integer> profile){
        double result = 0;
        Set keys = profile.keySet();
        Iterator it = keys.iterator();
        while(it.hasNext()){
            result += profile.get(it.next());
        }
        return result;
    }
}
