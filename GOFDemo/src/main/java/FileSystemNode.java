import java.util.ArrayList;
import java.util.List;

public class FileSystemNode {
    private String path;
    private boolean isFile;
    private List<FileSystemNode> subNodes = new ArrayList<>();

    public FileSystemNode(String path, boolean isFile) {
        this.path = path;
        this.isFile = isFile;
    }

//    private boolean isFile(String path) {
//        String suffix = path.substring(path.length()-3, path.length()-1);
//        if (suffix.equals(".txt")) return true;
//        return false;
//    }

    public int countNumOfFiles() {
        if (isFile) {
            return 1;
        }
        int count = 0;
        for (FileSystemNode node : subNodes) {
            count += node.countNumOfFiles();
        }
        return count;




//        // TODO:...
//        int count = 0;
//        if (! node.isFile) {
//            for (FileSystemNode n : subNodes) {
//                count++;
//                countNumOfFiles(n);
//            }
//        } else {
//            count++;
//        }
//
//        return count;
    }

//    public long countSizeOfFiles() {
//
//        // TODO:...
//
//
//
//
//    }

    public String getPath() {
        return path;
    }

    public void addSubNode(FileSystemNode fileOrDir) {
        subNodes.add(fileOrDir);
    }

    public void removeSubNode(FileSystemNode fileOrDir) {
    }

    public static void main(String[] args) {
        FileSystemNode f1 = new FileSystemNode("/folder1", false);
        FileSystemNode f2 = new FileSystemNode("/folder2", false);
        FileSystemNode f3 = new FileSystemNode("/folder3", false);
        FileSystemNode f4 = new FileSystemNode("/file1", true);
        FileSystemNode f5 = new FileSystemNode("/file2", true);
//        FileSystemNode f6 = new FileSystemNode("/file3", true);
        f1.addSubNode(f2);
        f1.addSubNode(f3);
        f1.addSubNode(f4);
//        f1.addSubNode(f5);
//        f1.addSubNode(f6);
        System.out.println(f1.countNumOfFiles());


    }


}