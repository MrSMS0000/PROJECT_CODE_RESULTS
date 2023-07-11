package buffered_with_threads;

import java.util.ArrayList;

public class LineContainer {
    public int lineNumber;
    public String line;
    public ArrayList<Integer> starts;

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
