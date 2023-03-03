package projman.commands;

import projman.Project;

public class Start extends Command implements ICommand {
    @Override
    public boolean main() {
        String projName = new String();
        String projDes = new String();

        if (!Project.createProjectFolder() || !Project.createStructureFile(projName, projDes)
                || !Project.createProjectGitignoreFile()) {
            System.out.println(
                    "Error: An Active project already exists. Close the active project using the \"projeman close\" command before using \"projman start\"");
            return false;
        }
        System.out.println("Log: New Project Directory Creation Successfully");
        return true;
    }
    public void takeInput(String projName, String projDes) {
        projName="devTest";
        projDes="sample test description";
    }
}
