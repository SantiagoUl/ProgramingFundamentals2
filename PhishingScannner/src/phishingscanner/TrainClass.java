package phishingscanner;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class TrainClass{
    List<String> words = new ArrayList<>();
    public static <T> List<T> ArrayToListConversion(T array[]){
        List<T> list = new ArrayList<>();
        for (T t : array){ 
            list.add(t);
        }
    return list;
    }
    public void addWords(String word){
        String listToReplace[] = {"a ", "an ", "why ", "where ", "when ", "hello ", "dear ", "mr ", "ms ", "mrs ",
        "i ", "you ", "are ", "not ", "now ", "member ", "members ", "how ", "us ", " ", "in ", "this ", "is ",
        "our ", "me ", "we ", "want ", "to ", "all ", "of ", "and ", "your ", "has ", "been ", "also ", "at ",
        "for ", "the ", "or ", "if ", "after ", "each ", "there ", "ready ", "go ", "back ", "have ", "from ",
        "what ", "my ", "do ", "know ", "even ", "he ", "just ", "that ", "one ", "who ", "will ", "can ", "so ",
        "aren't "};
        List<String> wordsToReplace = new ArrayList<>();
        wordsToReplace =  ArrayToListConversion(listToReplace);
        if(!wordsToReplace.contains(word)){
            words.add(word);  
        }
        for(int i = 0; i < words.size(); i++){
            for(int j = 0; j < wordsToReplace.size(); j++){
                if(words.get(i) == wordsToReplace.get(j)){
                    words.remove(i);
                }
            }
        }
    }
    public void start(){
        
        read();

        write();
        System.out.println("Done Training now Checking.");
    }
    public String format(List<String> list){
        String text = new String();
        HashMap<String, Integer> ScamWords = new HashMap<>();
        for(int i = 0; i < list.size(); i++){
            int counter = 1;
            for(int j = list.size()-1; j >= 0; j--){
                if(list.get(i).equals(list.get(j)) && i!=j){
                    counter++;
                }
            }
            ScamWords.put(list.get(i), counter);
        }
        for (String i : ScamWords.keySet()) {
            text = text + i + "" + ScamWords.get(i) + "\n";
          }
        return text;
    }

    public void write(){
        String text = format(words);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("TrainingData.txt"));
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void read(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("PhishingTraining.txt"));
            String str = "";
            String st;
            while ((st = br.readLine()) != null) {
                str += st + " ";
            }
            str = str.toLowerCase();
            String regx = ",.-?_@:";
            char[] ca = regx.toCharArray();
            for (char c : ca) {
                str = str.replace(""+c, "");
            }
            br.close();
            String word = new String();
            for (int i = 0; i < str.length(); i++) 
            {
                word = word+str.charAt(i);
                if (str.charAt(i) == ' ') {
                    addWords(word);
                    word = new String();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}