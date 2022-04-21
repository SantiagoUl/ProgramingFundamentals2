package phishingscanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PhishingScanner {
    public static int[] phishingWordsCount;
    public static String[] phishingWords;
    public static int[] phishingPoints;

    public void initialize(){
        ArrayList<String> textFileWords = new ArrayList<String>();
        BufferedReader training;
        String str = new String();
        try {
            training = new BufferedReader(new FileReader("TrainingData.txt"));
            String st;
            while ((st = training.readLine()) != null) {
                str += st + " ";
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        phishingWords = new String[str.length()];
        phishingPoints = new int[str.length()];
        phishingWordsCount = new int[str.length()];
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int count = -1;
        for (int i = 0; i < str.length(); i++) {
            if ((!Character.isLetter(str.charAt(i))) || (i + 1 == str.length())) {
                if (i - count > 1) {
                    if (Character.isLetter(str.charAt(i))) {
                        i++;
                    }
                    String word = str.substring(count + 1, i);

                    if (map.containsKey(word)) {
                        map.put(word, map.get(word) + 1);
                    } else {
                        map.put(word, 1);
                    }
                    textFileWords.add(word);
                }
                count = i;
            }
        }

        String[] testWords = new String[textFileWords.size()];
        phishingWords = textFileWords.toArray(testWords);
        str = str.replaceAll("\\D+","");
        String regx = "089";
            char[] ca = regx.toCharArray();
            for (char c : ca) {
                str = str.replace(""+c, "");
            }
        for(int i = 0; i < str.length(); i++){
            char character = str.charAt(i);
            int value = Character.getNumericValue(character);
            if(value == 1){
                phishingPoints[i] = 1;
            }
            if(value > 1 && value <= 3){
                phishingPoints[i] = 2;
            }
            if(value > 3){
                phishingPoints[i] = 3;
            }
        }
    }

    public void run(){
        initialize();
        readFile();
    }

    public static void wordPrint(String[] testWords) {
        int total = 0;

        for (int j = 0; j < testWords.length; j++) {
            for (int i = 0; i < phishingWords.length; i++) {
                if (testWords[j].equals(phishingWords[i])) {
                    ++phishingWordsCount[i];

                    total += phishingPoints[i];
                }
            }
        }

        System.out.printf("%-15s%-10s%s\n", "Word", "Count", "Points\n");

        for (int k = 0; k < phishingWords.length; k++) {
            if(phishingWordsCount[k]!=0){
                System.out.printf("%-15s%-10s%s\n", phishingWords[k], phishingWordsCount[k], phishingPoints[k]);
            }
        }

        System.out.println("Total points: " + total);
        if(total <= 40){
            System.out.println("This is hardly a scam but be careful");
        }
        if(total > 40 && total <= 60){
            System.out.println("This might be a scam be careful");
        }
        if(total > 60){
            System.out.println("This is really risky be careful seems like scam");
        }
    }

    private static void readFile() {
        ArrayList<String> textFileWords = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("Check.txt"));            
            String str = "";
            String st;
            while ((st = br.readLine()) != null) {
                str += st + " ";
            }
            HashMap<String, Integer> map = new HashMap<String, Integer>();

            str = str.toLowerCase();

            int count = -1;
            for (int i = 0; i < str.length(); i++) {
                if ((!Character.isLetter(str.charAt(i))) || (i + 1 == str.length())) {
                    if (i - count > 1) {
                        if (Character.isLetter(str.charAt(i))) {
                            i++;
                        }
                        String word = str.substring(count + 1, i);

                        if (map.containsKey(word)) {
                            map.put(word, map.get(word) + 1);
                        } else {
                            map.put(word, 1);
                        }
                        textFileWords.add(word);
                    }
                    count = i;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        String[] testWords = new String[textFileWords.size()];
        testWords = textFileWords.toArray(testWords);

        wordPrint(testWords);
    }
}
