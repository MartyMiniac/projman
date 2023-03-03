package projman.commands;

import projman.controllers.Project;
import projman.stuctures.FileEntity;
import projman.stuctures.ProjectEntity;
import projman.stuctures.StructureEntity;

public class Save extends Command implements ICommand {
    @Override
    public boolean main() {
        //scan for all the files in the project directory
        ProjectEntity pe = Project.getProjectInfo();
        System.out.println(pe);
        StructureEntity se = new StructureEntity(pe);
        for(FileEntity fe : Project.scanFiles()) {
            System.out.println(fe);
            se.addFileEntity(fe);
        }
        Project.updateStructureXML(se);
        return false;
    }
}
