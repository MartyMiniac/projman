package projman.commands;

import projman.controllers.Project;

public class Start extends Command implements ICommand {
    @Override
    public boolean main() {
        String projName = this.takeInput()[0];
        String projDes = this.takeInput()[1];

        if (!Project.createProjectFolder() || !Project.createStructureFile(projName, projDes)
                || !Project.createProjectGitignoreFile()) {
            System.out.println(
                    "Error: An Active project already exists. Close the active project using the \"projeman close\" command before using \"projman start\"");
            return false;
        }
        System.out.println("Log: New Project Directory Creation Successfully");
        return true;
    }

    public String[] takeInput() {
        String rt[] = new String[2];
        rt[0] = "devTest";
        rt[1] = "sample test description";
        return rt;
    }
}
