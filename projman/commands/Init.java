package projman.commands;

import projman.controllers.Project;

public class Init extends Command implements ICommand {
    @Override
    public boolean main() {
        if (!Project.createListFile()) {
            System.out.println("Error: Project Already Exists");
            return false;
        }
        System.out.println("Log: Project Initialized Successfully");
        return true;
    }
}
