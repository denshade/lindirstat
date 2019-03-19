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
    public static int cnt = 0;

    public static List<FileStat> scan(File startPoint) throws IOException {
        files.clear();
        /*Files.walk(Paths.get(startPoint.toURI()))
                .filter(Files::isRegularFile) => This peace of shit can't handle access denied exceptions. There is no way of cathing it without killing it.
                .forEach(FileSystemScanner::addFile);*/
        walk(startPoint.getAbsolutePath());
        return files;
    }

    public static void walk( String path ) {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
            }
            else {
                addFile(f.getAbsoluteFile());
            }
        }
    }


    public static void addFile(File file)
    {
        cnt++;
        if (cnt > 1000)
        {
            System.out.println("file:"  + file);
            cnt = 0;
        }
        FileStat stat = new FileStat();
        stat.file = file;
        stat.size = stat.file.length();
        files.add(stat);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(FileSystemScanner.scan(new File("c:\\tmp")));
    }
}
