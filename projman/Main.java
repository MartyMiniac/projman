package projman;

import projman.commands.Init;
import projman.commands.Save;
import projman.commands.Start;
import projman.commands.Command;

public class Main {
    public static void main(String args[]) {
        Command cmd = new Init();
        System.out.println("Init successful : " + cmd.main());
        cmd = new Start();
        System.out.println("Start successful : " + cmd.main());
        cmd = new Save();
        System.out.println("Save successful : " + cmd.main());
    }
}