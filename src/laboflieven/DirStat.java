package laboflieven;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirStat
{
    public static Map<String, Long> getDirectoryStats(List<FileStat> files, String sourceDirectory)
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
        Map<String, Long> results = new HashMap<String, Long>();
        for (Map.Entry<String, Long> entry : paths.entrySet())
        {
            String s = entry.getKey();
            File f = new File(s);
            if (f.getParent() != null && f.getParent().equals(sourceDirectory))
            {
                results.put(s, entry.getValue());
            }
        }
        return results;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(DirStat.getDirectoryStats(FileSystemScanner.scan(new File("c:\\tmp")), "c:\\"));
    }
}
