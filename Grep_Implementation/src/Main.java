import java.util.HashSet;

public class Main {
    String name;
    String lastName;
    static int count  = 0;
    public Main(String name, String     lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Main main = (Main) o;
        boolean b = name.equals(main.name) && lastName.equals(main.lastName);
        System.out.println(b);
        return b;
    }

    public static void main(String[] args) {
        HashSet<Main> set = new HashSet<>();
        String s1 = "Vijay";
        String s2 = "Singh";
        Main p1 = new Main(s1,s2);
        Main p2 = new Main(s1,s2);
        Main p3 = p1;
        set.add(p1);
        System.out.println("size:" + set.size());
        set.add(p2);
        System.out.println("size:" + set.size());
        set.add(p3);
        System.out.println("size:" + set.size());
    }
}