package laboflieven;

import java.io.File;

public class FileStat
{
    public File file;
    public long size;

    public String toString()
    {
        return size+":"+file;
    }
}
