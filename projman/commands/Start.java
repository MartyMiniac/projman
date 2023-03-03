package projman.commands;

import projman.Project;

public class Start {
    public static boolean main(String projName, String projDes) {
        if (!Project.createProjectFolder() || !Project.createStructureFile(projName, projDes)
                || !Project.createProjectGitignoreFile()) {
            System.out.println(
                    "Error: An Active project already exists. Close the active project using the \"projeman close\" command before using \"projman start\"");
            return false;
        }
        System.out.println("Log: New Project Directory Creation Successfully");
        return true;
    }
}
