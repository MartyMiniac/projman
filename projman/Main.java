package projman;

import projman.commands.Init;
import projman.commands.Save;
import projman.commands.Start;
import projman.commands.Close;
import projman.commands.Command;

public class Main {
    public static void main(String args[]) {
        commandSelector(args[0]);
    }

    private static void commandSelector(String s) {
        Command cmd = new Command();
        switch (s.toLowerCase()) {
            case "init":
                cmd = new Init();
                break;
            case "start":
                cmd = new Start();
                break;
            case "save":
                cmd = new Save();
                break;
            case "close":
                cmd = new Close();
                break;
        }

        if (cmd.main()) {
            System.out.println("Success");
        } else {
            System.out.println("Failure");
        }
    }
}