package buffered_with_threads;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindWithThread extends Thread{
    private final Pattern pattern;
    private final Path path;
    private final ArrayList<LineContainer> linesData;

    Search obj;

    public FindWithThread(Pattern pattern,Path path,Search obj) {
        this.obj = obj;
        this.pattern=pattern;
        this.path = path;
        linesData = new ArrayList<>();
    }

    public void run(){
        findLines();
        if(!linesData.isEmpty())
            obj.printLines(linesData,path);
    }

    // to find lines in a particular file
    private void findLines(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path.toString()));
            // reading first line
            String line = reader.readLine();
            int lineNumber = 0;

            while (line != null) {
                lineNumber++;
                // making a lineContainer
                LineContainer container = new LineContainer();
                boolean flag = true;
                // finding occurrences
                Matcher m = pattern.matcher(line);
                while (m.find()){
                    if(flag) {
                        container.lineNumber = lineNumber;
                        container.line = line;
                        flag=false;
                    }
                    container.starts.add(m.start());
                }
                // If any occurrence is found
                if(container.lineNumber!=0) linesData.add(container);

                // reading next line
                line = reader.readLine();
            }

            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


}
