package laboflieven;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
        drawBarChart();
    }


    private void drawBarChart() {
        long totalSize = 0;
        for (Long l : elements.values())
            totalSize += l;
        int index = 0;
        StringBuffer legend = new StringBuffer();
        legend.append("Legend:");
        for (Map.Entry<String, Long> entry : elements.entrySet())
        {
            int newSize = recalc(entry.getValue(), totalSize, 200);
            for (int a = 0; a < newSize; a++)
            {
                System.out.print("["+index+"]");
            }
            legend.append(index);
            legend.append(":");
            legend.append(entry.getKey());
            legend.append("\n");
            index++;
        }
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
        r.drawBarChart();
    }
}
