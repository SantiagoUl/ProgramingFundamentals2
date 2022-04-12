package phishingscanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class PhishingScanner {

    private static final int phishingWordsCount[] = new int[30];

    private static final String[] phishingWords = {
        "fraud", "official", "bank", "security", "urgent", "alert",
        "important", "information", "ebay", "password", "credit", "immediately",
        "confirm", "account", "bill", "verification", "address", "telephone",
        "ssn", "charity", "check", "secure", "personal", "confidential",
        "atm", "warning", "amazon", "citibank", "irs", "paypal"};

    private static final int phishingPoints[] = {100, 100, 100, 100, 100, 80, 80, 80, 80, 80, 60, 60, 60, 60, 60, 40, 40, 40, 40, 40, 20, 20, 20, 20, 20, 5, 5, 5, 5, 5};

    public static void main(String[] args) {
        readFile();
    }

    public static void wordTest(String[] testWords) {
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
            System.out.printf("%-15s%-10s%s\n", phishingWords[k], phishingWordsCount[k], phishingPoints[k]);
        }

        System.out.println("Total points: " + total);
    }

    private static void readFile() {
        ArrayList<String> textFileWords = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("phishingletter.txt"));            
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

                        //^^ Reads each word and puts it into the textFileWords Array List
                    }
                    count = i;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        String[] testWords = new String[textFileWords.size()];
        testWords = textFileWords.toArray(testWords);

        wordTest(testWords);
    }
}
