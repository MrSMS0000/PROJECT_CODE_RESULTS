package buffered_without_thread;

import java.util.ArrayList;

public class LineContainer {
    int lineNumber;
    String line;
    ArrayList<Integer> starts;

    public LineContainer() {
        this.starts = new ArrayList<Integer>();
    }

    @Override
    public String toString() {
        return
                "lineNumber=" + lineNumber +
                        ", line='" + line + '\'' +
                        ", starts=" + starts;
    }
}
