package laboflieven;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class FileSystemScanner
{
    public static List<FileStat> files = new ArrayList<>();

    public static List<FileStat> scan(File startPoint) throws IOException {
        files.clear();
        Files.walk(Paths.get(startPoint.toURI()))
                .filter(Files::isRegularFile)
                .forEach(FileSystemScanner::addFile);
        return files;
    }

    public static void addFile(Path file)
    {
        FileStat stat = new FileStat();
        stat.file = file.toFile();
        stat.size = stat.file.length();
        files.add(stat);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(FileSystemScanner.scan(new File("c:\\test")));
    }
}
