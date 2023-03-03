package projman.stuctures;

import projman.helpers.HashGen;

public class ProjectEntity {
    private String projectName;
    private String projectDescription;
    private String projectHash;

    public ProjectEntity(String projectName, String projectDescription) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectHash = HashGen.generate();
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getProjectHash() {
        return projectHash;
    }

    @Override
    public String toString() {
        return "ProjectEntity [projectName=" + projectName + ", projectDescription=" + projectDescription
                + ", projectHash=" + projectHash + "]";
    }
}
