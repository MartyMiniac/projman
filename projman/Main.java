package projman;

import projman.commands.Init;
import projman.commands.Start;

public class Main {
    public static void main(String args[]) {
        System.out.println("Init successful : " + Init.main());
        System.out.println("Start successful : " + Start.main("devTest", "sample test description"));
    }
}