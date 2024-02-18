package basics.service;

import java.util.Arrays;

public class Blocks {
    static {
        System.out.println("acc loans");
        Blocks.main(new Integer[]{23,24});
        Blocks.main(new String[]{"hello","world"});
    }
    public static void main(String[] args) {
        System.out.println(" Cli ");
        System.out.println(Arrays.toString(args));
        Blocks.main(new Integer[]{12,13,14});
        System.out.println(args);
    }

    public static void main(Integer[] args) {
        System.out.println("Banking ");
        System.out.println(args.length);
        System.out.println(args);
    }
}
