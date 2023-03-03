package projman.stuctures;

public class FileEntity {
    private String fileName;
    private String fileDescription;
    private String fileType;

    public FileEntity(String fileName, String fileDescription, String fileType) {
        this.fileName = fileName;
        this.fileDescription = fileDescription;
        this.fileType = fileType;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getFileDescription() {
        return this.fileDescription;
    }

    public String getFileType() {
        return this.fileType;
    }

}