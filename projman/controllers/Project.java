package projman.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import projman.stuctures.FileEntity;
import projman.stuctures.ProjectEntity;
import projman.stuctures.StructureEntity;

public class Project {
    private static final String PROJXML = "projects.xml";
    private static final String PROJDIR = "./currLoadedProject";
    private static final String PROJSTUC = PROJDIR + "/.structure.xml";
    private static final String PROJGITIGNORE = PROJDIR + "/.gitignore";

    public static boolean createListFile() {
        try {
            File f = new File(PROJXML);
            if (f.createNewFile()) {
                System.out.println("Log: successfully created " + PROJXML);
                return true;
            } else {
                System.out.println("Error: Failed creating " + PROJXML);
                System.out.println("LOG: \"" + PROJXML + "\" Already exists");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createStructureFile(String projName, String projDes) {
        ProjectEntity p = new ProjectEntity(projName, projDes);

        try {
            File f = new File(PROJSTUC);
            if (f.createNewFile()) {
                FileWriter fileWriter = new FileWriter(f);
                fileWriter.write("<Structure>\n    <Project>\n        <name>" + p.getProjectName()
                        + "</name>\n        <description>" + p.getProjectDescription()
                        + "</description>\n        <hash>" + p.getProjectHash()
                        + "</hash>\n    </Project>\n    <Files>\n    </Files>\n</Structure>");
                fileWriter.close();

                System.out.println("Log: successfully created " + PROJSTUC);
                return true;
            } else {
                System.out.println("Error: Failed creating " + PROJSTUC);
                System.out.println("LOG: \"" + PROJSTUC + "\" Already exists");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createProjectGitignoreFile() {
        try {
            File f = new File(PROJGITIGNORE);
            if (f.createNewFile()) {
                FileWriter fileWriter = new FileWriter(f);
                fileWriter.write(".structure.xml");
                fileWriter.close();

                System.out.println("Log: successfully created " + PROJGITIGNORE);
                return true;
            } else {
                System.out.println("Error: Failed creating " + PROJGITIGNORE);
                System.out.println("LOG: \"" + PROJGITIGNORE + "\" Already exists");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createProjectFolder() {
        File f = new File(PROJDIR);
        if (f.mkdir()) {
            System.out.println("Log: successfully created " + PROJDIR);
            return true;
        } else {
            System.out.println("Error: Failed creating " + PROJDIR);
            System.out.println("LOG: \"" + PROJDIR + "\" Already exists");
            return false;
        }
    }

    public static LinkedList<FileEntity> scanFiles() {
        // perform a DFS on the dir to get all the files
        LinkedList<FileEntity> paths = new LinkedList<FileEntity>();
        Stack<File> unVisited = new Stack<File>();
        HashSet<File> visited = new HashSet<File>();

        unVisited.push(new File(PROJDIR));

        while (!unVisited.empty()) {
            File uvf = unVisited.pop();
            if (uvf.isDirectory()) {
                Project.extendList(unVisited, uvf.listFiles(), visited);
            } else {
                paths.add(Project.fileToFileEntity(uvf));
            }
            visited.add(uvf);
        }

        return paths;
    }

    public static ProjectEntity getProjectInfo() {
        try {
            DocumentBuilderFactory objFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder objBuilder = objFactory.newDocumentBuilder();
            File objFile = new File(PROJSTUC);
            Document objDocument = objBuilder.parse(objFile);
            objDocument.getDocumentElement().normalize();
            NodeList node = objDocument.getElementsByTagName("Project");
            Element proj = (Element) node.item(0);
            String projName = proj.getElementsByTagName("name").item(0).getTextContent();
            String projDescription = proj.getElementsByTagName("description").item(0).getTextContent();
            ProjectEntity pe = new ProjectEntity(projName, projDescription);
            return pe;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void updateStructureXML(StructureEntity se) {
        try {
            DocumentBuilderFactory objFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder objBuilder = objFactory.newDocumentBuilder();
            File objFile = new File(PROJSTUC);
            Document objDocument = objBuilder.parse(objFile);
            objDocument.getDocumentElement().normalize();

            NodeList node = objDocument.getElementsByTagName("Files");
            Element fileList = (Element) node.item(0);
            fileList.getParentNode().removeChild(fileList);
            fileList = objDocument.createElement("Files");
            for (FileEntity fe : se.getFileEntity()) {
                Element fileName = objDocument.createElement("name");
                fileName.setTextContent(fe.getFileName());
                Element fileDescription = objDocument.createElement("description");
                fileDescription.setTextContent(fe.getFileDescription());
                Element fileType = objDocument.createElement("type");
                fileType.setTextContent(fe.getFileType());
                Element file = objDocument.createElement("File");
                file.appendChild(fileName);
                file.appendChild(fileDescription);
                file.appendChild(fileType);
                fileList.appendChild(file);
            }
            objDocument.getElementsByTagName("Structure").item(0).appendChild(fileList);

            FileOutputStream fos = new FileOutputStream(objFile);
            writeXML(objDocument, fos);
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void writeXML(Document doc,
            OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }

    private static FileEntity fileToFileEntity(File f) {
        String fname = "." + (f.toPath() + "").substring(19);
        String fext = f.getName();
        fext = fext.split("\\.")[fext.split("\\.").length - 1];
        FileEntity fe = new FileEntity(fname, fext);
        return fe;
    }

    private static void extendList(Stack<File> st, File[] fileList, HashSet<File> hs) {
        for (File f : fileList) {
            // System.out.println(f);
            if (!hs.contains(f)) {
                st.push(f);
            }
        }
    }
}
