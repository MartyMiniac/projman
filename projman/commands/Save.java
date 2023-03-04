package projman.commands;

import java.io.File;

import projman.controllers.Project;
import projman.helpers.FileOperations;
import projman.stuctures.FileEntity;
import projman.stuctures.ProjectEntity;
import projman.stuctures.StructureEntity;

public class Save extends Command implements ICommand {
    @Override
    public boolean main() {
        // scan for all the files in the project directory
        ProjectEntity pe = Project.getProjectInfo();
        StructureEntity se = new StructureEntity(pe);
        for (FileEntity fe : Project.scanFiles()) {
            se.addFileEntity(fe);
        }
        Project.updateStructureXML(se);

        System.out.println("LOG: Deleting old files");
        Project.deleteOldProject(se);

        System.out.println("LOG: Copying Files to Destination Directory");
        for (FileEntity fe : se.getFileEntity()) {
            File src = new File("./currLoadedProject/" + fe.getFileName());
            File dest = new File("./" + se.getProject().getProjectName() + "/" + fe.getFileName());
            FileOperations.copyFile(src, dest);
        }
        System.out.println("LOG: Project Saved Successfully");

        return true;
    }
}
