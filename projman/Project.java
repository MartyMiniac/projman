package projman;

import java.io.File;
import java.io.IOException;

public class Project {
    private static final String PROJXML = "projects.xml";
    private static final String PROJDIR = "./currLoadedProject";
    private static final String PROJSTUC = PROJDIR + "/.structure.xml";

    public static boolean createListFile() {
        try {
            File f = new File(PROJXML);
            if (f.createNewFile()) {
                System.out.println("Log: successfully created " + PROJXML);
                return true;
            } else {
                System.out.println("Error: Failed creating " + PROJXML);
                System.out.println("LOG: \"" + PROJXML + "\" Already exists");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createStructureFile() {
        try {
            File f = new File(PROJSTUC);
            if (f.createNewFile()) {
                System.out.println("Log: successfully created " + PROJSTUC);
                return true;
            } else {
                System.out.println("Error: Failed creating " + PROJSTUC);
                System.out.println("LOG: \"" + PROJSTUC + "\" Already exists");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createProjectFolder() {
        File f = new File(PROJDIR);
        if (f.mkdir()) {
            System.out.println("Log: successfully created " + PROJDIR);
            return true;
        } else {
            System.out.println("Error: Failed creating " + PROJDIR);
            System.out.println("LOG: \"" + PROJDIR + "\" Already exists");
            return false;
        }
    }
}
