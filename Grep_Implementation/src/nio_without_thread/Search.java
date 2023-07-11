package nio_without_thread;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Search {
    private final Pattern pattern;
    private final Path basePath;

    // constructor
    public Search(String pattern, String path) {
        this.pattern = Pattern.compile(pattern);
        this.basePath = Paths.get(path);
    }

    // to check whether a path exists or not
    boolean isValidPath(){
        return Files.exists(basePath);
    }

    // to print lines of a particular file
    private void printLines(String fileName, ArrayList<LineContainer> LinesData){
        System.out.println("\n" + fileName);
        for (LineContainer container : LinesData){
            System.out.println(container);
        }
    }

    // to find lines in a particular file
    ArrayList<LineContainer> findLines(Path path){
        ArrayList<LineContainer> linesData = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(path);
            int lineNumber = 0;
            for (String line : allLines) {
                LineContainer container = new LineContainer();
                lineNumber++;
                boolean flag = true;
                Matcher m = pattern.matcher(line);
                while (m.find()){
                    if(flag) {
                        container.lineNumber = lineNumber;
                        container.line = line;
                        flag=false;
                    }
                    container.starts.add(m.start());
                }
                if(container.lineNumber!=0) linesData.add(container);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linesData;
    }

    private void makeOutput(Path path) {
        // If provided path is a directory
        if(Files.isDirectory(path)){
            try (Stream<Path> stream = Files.list(path)) {
                stream.forEach(this::makeOutput);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        // If provided path is a file
        else{
            String fileName = path.getFileName().toString();
            // Searching only for .txt files
            if(!fileName.endsWith(".txt"))  return;

            ArrayList<LineContainer> linesData = findLines(path);

            if(!linesData.isEmpty())
                printLines(fileName, linesData);
        }
    }
    public void getOutput(){
        makeOutput(basePath);
    }

}
