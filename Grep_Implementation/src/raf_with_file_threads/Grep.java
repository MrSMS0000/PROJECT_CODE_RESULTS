package raf_with_file_threads;

import java.time.Duration;
import java.time.Instant;

class Grep {

    public static void main(String[] args) {
        if(args.length<2){
            System.out.println("Terminating: Too few arguments");
            System.out.println("Example: Grep pattern_string path_string");
            return;
        }
        if(args.length>2){
            System.out.println("Warning: Ignoring extra arguments");
        }
        // starting time
        Instant start = Instant.now();

        String pattern = args[0];
        String path = args[1];

        Search ans = new Search(pattern,path);
        // If path is not valid
        if(!ans.isValidPath()){
            System.out.println("Terminating: Path does not exist");
            return;
        }
        // Finding
        ans.getOutput();

        // stopping time
        Instant finish = Instant.now();
        // calculating time difference
        long timeElapsed = Duration.between(start, finish).toMillis();

        // printing time
        System.out.println();
        System.out.println("______________ "+timeElapsed+" ms ______________");
        System.out.println();
    }
}
