package projman.stuctures;

import java.util.LinkedList;

public class StructureEntity {
    private ProjectEntity project;
    private LinkedList<FileEntity> files;

    public StructureEntity(ProjectEntity project) {
        this.project = project;
        this.files = new LinkedList<FileEntity>();
    }

    public void addFileEntity(FileEntity fe) {
        this.files.add(fe);
    }
    public LinkedList<FileEntity> getFileEntity() {
        return this.files;
    }
}
