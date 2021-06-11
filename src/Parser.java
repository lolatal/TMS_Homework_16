import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Parser implements IParser {

    private Map<String, Document> report = new HashMap<>();


    @Override
    public void parse(String pathToFolder, int count) {
        File folder = new File(pathToFolder);

        if (folder.isDirectory() && folder.exists()) {
            File[] filesList = folder.listFiles((dir, name) -> name.endsWith(".txt"));

            if (filesList.length == 0) {
                System.out.println("No files that match the requirements found");
                return;
            }
            int numberOfFilesToProcess = Math.min(count, filesList.length);
            for (File file : Arrays.stream(filesList).limit(numberOfFilesToProcess).collect(Collectors.toList())) {
                readFile(file);
            }
            for (Map.Entry<String, Document> item : report.entrySet()) {
                System.out.println(item.getKey() + ": " + item.getValue());
            }
        } else {
            System.out.println("The path does not indicate a directory or doesn't exist");
        }
    }


    private void readFile(File file) {
        Pattern docPattern = Pattern.compile("\\d{4}-[a-zA-Z]{3}-\\d{4}-[a-zA-Z]{3}-\\d{4}", Pattern.CASE_INSENSITIVE);
        Pattern emailPattern = Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}", Pattern.CASE_INSENSITIVE);
        Pattern phoneNumberPattern = Pattern.compile("(\\+*)[(]\\d{2}[)]\\d{7}([\\W\\n\\t]|$)");


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String oneLine;
            Document document = new Document();
            List<String> docNumbersList = new ArrayList<>();
            String documentName = file.getName().replaceAll(".txt", "");
            while ((oneLine = br.readLine()) != null) {
                Matcher docMatcher = docPattern.matcher(oneLine);
                Matcher emailMatcher = emailPattern.matcher(oneLine);
                Matcher phoneNumberMatcher = phoneNumberPattern.matcher(oneLine);

                if (docMatcher.find()) {
                    docNumbersList.add(oneLine.substring(docMatcher.start(), docMatcher.end()));
                }
                if (emailMatcher.find()) {
                    document.setEmail(oneLine.substring(emailMatcher.start(), emailMatcher.end()));
                }
                if (phoneNumberMatcher.find()) {
                    document.setPhoneNumber(oneLine.substring(phoneNumberMatcher.start(), phoneNumberMatcher.end()));
                }
            }
            document.setDocNumber(docNumbersList.toString());
            report.put(documentName, document);

        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
