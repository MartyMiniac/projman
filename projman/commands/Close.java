package projman.commands;

import projman.controllers.Project;

public class Close extends Command implements ICommand {
    @Override
    public boolean main() {
        Project.deletecurrLoadedProject();
        return true;
    }
}
