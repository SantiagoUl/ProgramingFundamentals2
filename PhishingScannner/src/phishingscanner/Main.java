package phishingscanner;
import java.util.Scanner;

//Class that creates a new Exception
class DifferentFromAnswersException  extends Exception  
{  
    public DifferentFromAnswersException (String str)  
    {
        super(str);
    }  
}  
//The main class
public class Main{
    //the method that throws the exception that we created and gives the condition for the connection
    public static void check(int x) throws DifferentFromAnswersException{
        if(x < 1 || x > 2){
            throw new DifferentFromAnswersException("Input must be 1 or 2. Try Again...");
        }
    }

    //The main method
    public static void main(String[] args) {
        PhishingScanner phcheck = new PhishingScanner();
        TrainClass train = new TrainClass();
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome");
        System.out.println("Do you want to train the system or check if scam ?: \n 1.Train than Check \n 2.Check");
        int input = scan.nextInt();
        boolean loop = true;
        //This is a loop in case the user inputs the wrong answer
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
                train.start();
                phcheck.run();
                break;
            case 2:
                phcheck.run();
                break;
        }
        scan.close();
    }
}