package laboflieven;

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
    public void showAsText()
    {
        drawBarChart(80, 300);
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
            int newSize = recalc(entry.getValue(), totalSize, maxUISize);

            for (int a = 0; a < newSize; a++)
            {
                System.out.print("."+index+".");
                sizeRow += ("."+index+".").length();
                if (sizeRow > maxRowSize)
                {
                    System.out.println();
                    sizeRow = 0;
                }
            }
            legend.append(index);
            legend.append(":");
            legend.append(entry.getKey());
            legend.append("\n");
            index++;
        }
        System.out.println();
        System.out.println(legend);
    }

    public static int recalc(long currentWidth, long totalSize, int maxUISize)
    {
        return (int)((currentWidth * maxUISize) / totalSize);
    }


    public static void main(String[] args) throws IOException {
        Map<String, Long> elements = new HashMap<String, Long>();
        elements.put("/tmp", 50L);
        elements.put("/opt", 50L);
        DirStatTextRenderer r = new DirStatTextRenderer(elements);
        r.drawBarChart(100, 300);

        elements = new HashMap<String, Long>();
        elements.put("/tmp", 0L);
        elements.put("/opt", 50L);
        r = new DirStatTextRenderer(elements);
        r.drawBarChart(100, 100/3);
    }
}
