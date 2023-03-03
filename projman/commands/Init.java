package projman.commands;

import projman.Project;

public class Init {
    public static boolean main() {
        if (!Project.createListFile() || !Project.createProjectFolder() || !Project.createStructureFile()) {
            System.out.println("Error: Project Already Exists");
            return false;
        }
        System.out.println("Log: Project Initialized Successfully");
        return true;
    }
}