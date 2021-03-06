package laboflieven;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DirStatTextRenderer
{


    private final Map<String, Long> elements;

    public DirStatTextRenderer(Map<String, Long> elements)
    {
        super();
        this.elements = elements;
    }

    private void drawBarChart(int maxRowSize, int maxUISize) {
        long totalSize = 0;
        for (Long l : elements.values())
            totalSize += l;
        int index = 0;
        StringBuffer legend = new StringBuffer();
        legend.append("Legend:\n");
        int sizeRow = 0;
        for (Map.Entry<String, Long> entry : elements.entrySet())
        {
            int sizeInBlocks = recalc(entry.getValue(), totalSize, maxUISize);

            for (int a = 0; a < sizeInBlocks; a++)
            {
                System.out.print("."+index+".");
                sizeRow += ("."+index+".").length();
                if (sizeRow > maxRowSize)
                {
                    System.out.println();
                    sizeRow = 0;
                }
            }
            if (sizeInBlocks > 0)
            {
                legend.append(index);
                legend.append(":");
                legend.append(entry.getKey());
                legend.append("\n");
                index++;
            }
        }
        System.out.println();
        System.out.println(legend);
    }

    public static int recalc(long currentWidth, long totalSize, int maxUISize)
    {
        return (int)((currentWidth * maxUISize) / totalSize);
    }


    public static void main(String[] args) throws IOException {
        if (args.length != 2)
        {
            System.err.println("Usage: This <subdirectory> <barsize>");
            System.exit(1);
        }
        String subDir = args[0];
        Integer barsize = Integer.parseInt(args[1]);
        if (subDir.endsWith("\\") || subDir.endsWith("/"))
        {
            subDir = subDir.substring(0, subDir.length() - 1);
        }
        Map<String, Long>  elements = DirStat.getDirectoryStats(FileSystemScanner.scan(new File(subDir)), subDir);
        DirStatTextRenderer r = new DirStatTextRenderer(elements);
        r.drawBarChart(100, barsize);

    }
}
