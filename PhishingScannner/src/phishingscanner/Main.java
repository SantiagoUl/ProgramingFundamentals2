package phishingscanner;
import java.util.*;

class DifferentFromAnswersException  extends Exception  
{  
    public DifferentFromAnswersException (String str)  
    {
        super(str);  
    }  
}  

public class Main{
    public static void check(int x) throws DifferentFromAnswersException{
        if(x < 1 || x > 2){
            throw new DifferentFromAnswersException("Input must be 1 or 2. Try Again...");
        }
    }
    public static void main(String[] args) {
        PhishingScanner phcheck = new PhishingScanner();
        TrainClass train = new TrainClass();
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome");
        System.out.println("Do you want to train the system or check if scam ?: \n 1.Train \n 2.Check");
        int input = scan.nextInt();
        boolean loop = true;
        while(loop){
            try {
                check(input);
                loop = false;
            } catch (DifferentFromAnswersException ex) {
                System.out.println(ex);
                input = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Must be 1 or 2. Try Again...");
                input = scan.nextInt();
            }
        }
        switch (input) {
            case 1:
                train.test();
                break;
            case 2:
                phcheck.run();
                break;
        }
        scan.close();
    }
}