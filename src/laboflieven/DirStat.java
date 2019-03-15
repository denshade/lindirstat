package laboflieven;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirStat
{
    public static Map<String, Long> getDirectoryStats(List<FileStat> files)
    {
        Map<String, Long> paths = new HashMap<>();
        for (FileStat file : files)
        {
            File parent = file.file.getParentFile();
            while(parent != null)
            {
                String parentS = parent.toString();
                if (paths.containsKey(parentS))
                {
                    paths.put(parentS, paths.get(parentS) + file.size);
                } else {
                    paths.put(parentS, file.size);
                }
                parent = parent.getParentFile();
            }
        }
        return paths;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(DirStat.getDirectoryStats(FileSystemScanner.scan(new File("c:\\tmp"))));
    }
}
