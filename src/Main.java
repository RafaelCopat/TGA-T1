import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by I852780 on 23/09/2016.
 */
public class Main {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        String flag = getCodeOption(scanner);
        File file = getFile(scanner);
        CodeMethod codeMethod = chooseCodeMethod(scanner);
        Codec codec = new Codec();
        try {
            codec.setFile(file);
            if(flag.compareTo("1") == 0) {
                codec.codeFile(codeMethod);
                System.out.println("File coded with success.");
            }
            else {
                codec.decodeFile(codeMethod);
                System.out.println("File decoded with success.");
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error in reading/writing File");
            e.printStackTrace();
        }
    }
    private static String getCodeOption(Scanner scanner) {
        System.out.println("Are you trying to code or decode a file?:" + '\n' + "1 - Code" + '\n' + "2 - Decode");
        String flag = "";
        do {
            flag = scanner.next();
            if (flag.compareTo("1") == 0)
                flag = "1";
            else if (flag.compareTo("2") == 0)
                flag = "2";
            else {
                System.out.println("Please choose a valid option");
            }
        } while(flag.compareTo("1") != 0 && flag.compareTo("2") != 0);
        return flag;
    }

    private static File getFile(Scanner scanner) {
        System.out.println("Set the File's Path:");
        String filepath = scanner.next();
        return new File(filepath);
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
