package raf_with_file_threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Search {
    private final Pattern pattern;
    private final Path basePath;
    ArrayList<FindWithThread> threads;

    // constructor
    public Search(String pattern, String path) {
        this.pattern = Pattern.compile(pattern);
        this.basePath = Paths.get(path);
        threads = new ArrayList<>();
    }

    // to check whether a path exists or not
    boolean isValidPath(){
        return Files.exists(basePath);
    }
    // to print lines of a particular file
    synchronized void printLines(ArrayList<LineContainer> linesData, Path path){
        System.out.println("\n" + path.getFileName().toString());
        for (LineContainer container : linesData){
            System.out.println(container);
        }
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
            FindWithThread thisFile = new FindWithThread(pattern,path,this);
            thisFile.start();
            threads.add(thisFile);
        }
    }
    public void getOutput(){
        makeOutput(basePath);
        for(FindWithThread t:threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
