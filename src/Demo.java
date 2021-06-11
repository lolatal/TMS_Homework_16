import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        IParser parser = new Parser();
        System.out.println("Type in the path to the folder: ");
        Scanner scanner = new Scanner(System.in);
        String pathToFolder = scanner.nextLine();
        System.out.println("Type in the number of documents to process");
        int count = scanner.nextInt();
        parser.parse(pathToFolder, count);
        scanner.close();
    }
}
