package projman;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import projman.stuctures.ProjectEntity;

public class Project {
    private static final String PROJXML = "projects.xml";
    private static final String PROJDIR = "./currLoadedProject";
    private static final String PROJSTUC = PROJDIR + "/.structure.xml";
    private static final String PROJGITIGNORE = PROJDIR + "/.gitignore";

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

    public static boolean createStructureFile(String projName, String projDes) {
        ProjectEntity p = new ProjectEntity(projName, projDes);

        try {
            File f = new File(PROJSTUC);
            if (f.createNewFile()) {
                FileWriter fileWriter = new FileWriter(f);
                fileWriter.write(
                        "<Structure>\n    <Project name=\"" + p.getProjectName() + "\" description=\""
                                + p.getProjectDescription()
                                + "\" hash=\"" + p.getProjectHash()
                                + "\"/>\n    <Files>\n        <File name=\"\" description=\"\" type=\"\"/>\n    </Files>\n</Structure>");
                fileWriter.close();

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

    public static boolean createProjectGitignoreFile() {
        try {
            File f = new File(PROJGITIGNORE);
            if (f.createNewFile()) {
                FileWriter fileWriter = new FileWriter(f);
                fileWriter.write(".structure.xml");
                fileWriter.close();

                System.out.println("Log: successfully created " + PROJGITIGNORE);
                return true;
            } else {
                System.out.println("Error: Failed creating " + PROJGITIGNORE);
                System.out.println("LOG: \"" + PROJGITIGNORE + "\" Already exists");
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
