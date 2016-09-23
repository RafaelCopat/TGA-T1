import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by I852780 on 23/09/2016.
 */
public class Main {
    public static void main(String args[]){
        System.out.println("Set the File's Path:");
        Scanner scanner = new Scanner(System.in);
        String filepath = scanner.next();
        File file = new File(filepath);
        CodeMethod codeMethod = chooseCodeMethod(scanner);
        Codec codec = new Codec();
        try {
            codec.setFile(file);
            file = new File(codec.codeFile(codeMethod));
            codec.setFile(file);
            codec.decodeFile(codeMethod);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error in reading/writing File");
            e.printStackTrace();
        }

    }

    private static CodeMethod chooseCodeMethod(Scanner scanner) {
        CodeMethod codeMethod = null;
        System.out.println("Pick the compressing method:" + '\n' + "1 - Elias Gamma" + '\n' + "2 - Golomb");
        String method = "";
        do {
            method = scanner.next();
            if (method.compareTo("1") == 0)
                codeMethod = new EliasGamma();
            else if (method.compareTo("2") == 0)
                codeMethod = new Golomb();
            else {
                System.out.println("Please choose a valid option");
            }
        } while(method.compareTo("1") != 0 && method.compareTo("2") != 0);
        return codeMethod;
    }
}
