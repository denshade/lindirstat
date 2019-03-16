package laboflieven;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DirStatRenderer extends JPanel
{


    private final Map<String, Long> elements;

    public DirStatRenderer(Map<String, Long> elements)
    {
        super();
        this.elements = elements;
    }
    public void paint(Graphics g)
    {
        drawBarChart(g);
    }


    private void drawBarChart(Graphics g) {
        long totalSize = 0;
        for (Long l : elements.values())
            totalSize += l;
        Random r = new Random();
        int currentX = 10;
        for (Map.Entry<String, Long> entry : elements.entrySet())
        {
            Color c = new Color(r.nextInt(255*255*255));
            int newSize = recalc(entry.getValue(), totalSize, 600);
            g.setColor(c);
            g.fillRect(currentX,12,currentX + newSize,150);
            g.drawString(entry.getKey(), currentX, 170);
            currentX += newSize;
        }
    }

    public static int recalc(long currentWidth, long totalSize, int maxUISize)
    {
        return (int)((currentWidth * maxUISize) / totalSize);
    }


    public static void main(String[] args) throws IOException {
        Map<String, Long> elements = DirStat.getDirectoryStats(FileSystemScanner.scan(new File("c:\\tmp")), "c:\\tmp");
        DirStatRenderer r = new DirStatRenderer(elements);
        r.setVisible(true);
        JFrame jframe = new JFrame();
        jframe.getContentPane().add(r);
        jframe.setMinimumSize(new Dimension(600,600));
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
