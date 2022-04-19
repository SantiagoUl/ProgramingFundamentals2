package phishingscanner;
import java.io.BufferedReader;
import java.io.FileReader;
public class TrainClass{
    public void test(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("Check.txt"));            
            String str = "";
            String st;
            while ((st = br.readLine()) != null) {
                str += st + " ";
            }
            str = str.toLowerCase();
            br.close();
            int wordCount = 1;
            for (int i = 0; i < str.length(); i++) 
            {
                if (str.charAt(i) == ' ') 
                {
                    if(str.charAt(i) != '.' || str.charAt(i) != ','){
                        wordCount++;
                    }
                } 
            }
            System.out.println("Word count is = " + wordCount);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}