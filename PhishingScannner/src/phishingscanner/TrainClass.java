package phishingscanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class TrainClass{
    List<String> words = new ArrayList<>();
    public static <T> List<T> ArrayToListConversion(T array[])   
    {   
        //creating the constructor of the List class  
        List<T> list = new ArrayList<>();   
        //using for-each loop to iterate over the array  
        for (T t : array)   
        {   
            //adding each element to the List  
            list.add(t);   
        }   
        //returns the list converted into Array  
    return list;   
    }   
    public void addWords(String word){
        String listToReplace[] = {"a", "an", "why", "where", "when", "hello", "dear", "mr", "ms", "mrs",
        "i", "you", "are", "not", "now", "member", "members", "how", "us", " ", "in"};
        List<String> wordsToReplace = new ArrayList<>();
        wordsToReplace =  ArrayToListConversion(listToReplace);
        if(wordsToReplace.contains(word)){
            words.add("");
        }
        else words.add(word);
    }
    public void start(){
        
        read();

        write();
    }
    public void write(){
        String text = new String();
        for(int i = 0 ; i < words.size(); i++){
            text = text + words.get(i) + "\n";
        }
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