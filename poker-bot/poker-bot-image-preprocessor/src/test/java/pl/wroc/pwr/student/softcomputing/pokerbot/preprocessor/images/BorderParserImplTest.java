package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import org.junit.Before;
import org.junit.Test;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.BorderParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ChipsParser;

import java.awt.image.BufferedImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by RaV on 14.01.14.
 */
public class BorderParserImplTest {

    private BorderParser testObject;

    @Before
    public void setUp()
    {
        testObject = new BorderParserImpl();
    }
    @Test
    public void testParseBorder() throws Exception {
        TableParserImpl parser = new TableParserImpl();
        ImageProcessorImpl processor = new ImageProcessorImpl();

        System.out.println("***Borders from table***");
        System.out.println("Test on 1.png");
        String[] values= {"noLabel","yellow","orange","lime","green"};
        for (int i = 1; i <= 5; i++)
        {
            parser.loadTable("src/test/resources/Borders/1.png");
            BufferedImage img = parser.parseOpponentBorder(i);
            String border=testObject.parseBorder(img);
            System.out.println("Opponent "+i+": "+border);
            assertEquals(border,values[i-1]);
        }
        System.out.println("Test on 2.png");
        values = new String[]{"lime","orange","cyan","yellow","noLabel"};
        for (int i = 1; i <= 5; i++)
        {
            parser.loadTable("src/test/resources/Borders/2.png");
            BufferedImage img = parser.parseOpponentBorder(i);
            String border=testObject.parseBorder(img);
            System.out.println("Opponent "+i+": "+border);
            assertEquals(border,values[i-1]);
        }
        System.out.println("Test on 3.png");
        values = new String[]{"green","darkBlue","noLabel","black","white"};
        for (int i = 1; i <= 5; i++)
        {
            parser.loadTable("src/test/resources/Borders/3.png");
            BufferedImage img = parser.parseOpponentBorder(i);
            String border=testObject.parseBorder(img);
            System.out.println("Opponent "+i+": "+border);
            assertEquals(border,values[i-1]);
        }
        System.out.println("Test on 4.png");
        values = new String[]{"black","white","noLabel","darkBlue","green"};
        for (int i = 1; i <= 5; i++)
        {
            parser.loadTable("src/test/resources/Borders/4.png");
            BufferedImage img = parser.parseOpponentBorder(i);
            String border=testObject.parseBorder(img);
            System.out.println("Opponent "+i+": "+border);
            assertEquals(border,values[i-1]);
        }
        System.out.println("Test on 5.png");
        values = new String[]{"blue","red","noLabel","gray","purple"};
        for (int i = 1; i <= 5; i++)
        {
            parser.loadTable("src/test/resources/Borders/5.png");
            BufferedImage img = parser.parseOpponentBorder(i);
            String border=testObject.parseBorder(img);
            System.out.println("Opponent "+i+": "+border);
            assertEquals(border,values[i-1]);
        }
        System.out.println("Test on 6.png");
        values = new String[]{"purple","gray","noLabel","red","blue"};
        for (int i = 1; i <= 5; i++)
        {
            parser.loadTable("src/test/resources/Borders/6.png");
            BufferedImage img = parser.parseOpponentBorder(i);
            String border=testObject.parseBorder(img);
            System.out.println("Opponent "+i+": "+border);
            assertEquals(border,values[i-1]);
        }
        System.out.println("Test on 7.png");
        values = new String[]{"pink","lightOrange","noLabel","purple","gray"};
        for (int i = 1; i <= 5; i++)
        {
            parser.loadTable("src/test/resources/Borders/7.png");
            BufferedImage img = parser.parseOpponentBorder(i);
            String border=testObject.parseBorder(img);
            System.out.println("Opponent "+i+": "+border);
            assertEquals(border,values[i-1]);
        }
        System.out.println("Test on 8.png");
        values = new String[]{"yellow","white","noLabel","pink","lightOrange"};
        for (int i = 1; i <= 5; i++)
        {
            parser.loadTable("src/test/resources/Borders/8.png");
            BufferedImage img = parser.parseOpponentBorder(i);
            String border=testObject.parseBorder(img);
            System.out.println("Opponent "+i+": "+border);
            assertEquals(border,values[i-1]);
        }
        System.out.println("Test on 9.png");
        values = new String[]{"red","black","noLabel","blue","orange"};
        for (int i = 1; i <= 5; i++)
        {
            parser.loadTable("src/test/resources/Borders/9.png");
            BufferedImage img = parser.parseOpponentBorder(i);
            String border=testObject.parseBorder(img);
            System.out.println("Opponent "+i+": "+border);
            assertEquals(border,values[i-1]);
        }
        System.out.println("Test on 10.png");
        values = new String[]{"green","yellow","noLabel","lime","cyan"};
        for (int i = 1; i <= 5; i++)
        {
            parser.loadTable("src/test/resources/Borders/10.png");
            BufferedImage img = parser.parseOpponentBorder(i);
            String border=testObject.parseBorder(img);
            System.out.println("Opponent "+i+": "+border);
            assertEquals(border,values[i-1]);
        }
        System.out.println("Test on 11.png");
        values = new String[]{"white","lime","noLabel","lightOrange","noLabel"};
        for (int i = 1; i <= 5; i++)
        {
            parser.loadTable("src/test/resources/Borders/11.png");
            BufferedImage img = parser.parseOpponentBorder(i);
            String border=testObject.parseBorder(img);
            System.out.println("Opponent "+i+": "+border);
            assertEquals(border,values[i-1]);
        }
        System.out.println("Test on 12.png");
        values = new String[]{"noLabel","gray","noLabel","gray","noLabel"};
        for (int i = 1; i <= 5; i++)
        {
            parser.loadTable("src/test/resources/Borders/12.png");
            BufferedImage img = parser.parseOpponentBorder(i);
            String border=testObject.parseBorder(img);
            System.out.println("Opponent "+i+": "+border);
            assertEquals(border,values[i-1]);
        }
        System.out.println("Test on 13.png");
        values = new String[]{"noLabel","noLabel","noLabel","noLabel","noLabel"};
        for (int i = 1; i <= 5; i++)
        {
            parser.loadTable("src/test/resources/Borders/13.png");
            BufferedImage img = parser.parseOpponentBorder(i);
            String border=testObject.parseBorder(img);
            System.out.println("Opponent "+i+": "+border);
            assertEquals(border,values[i-1]);
        }
        System.out.println("Test on 14.png");
        values = new String[]{"noLabel","noLabel","lime","noLabel","noLabel"};
        for (int i = 1; i <= 5; i++)
        {
            parser.loadTable("src/test/resources/Borders/14.png");
            BufferedImage img = parser.parseOpponentBorder(i);
            String border=testObject.parseBorder(img);
            System.out.println("Opponent "+i+": "+border);
            assertEquals(border,values[i-1]);
        }
        System.out.println("Test on 15.png");
        values = new String[]{"lime","lime","noLabel","noLabel","black"};
        for (int i = 1; i <= 5; i++)
        {
            parser.loadTable("src/test/resources/Borders/15.png");
            BufferedImage img = parser.parseOpponentBorder(i);
            String border=testObject.parseBorder(img);
            System.out.println("Opponent "+i+": "+border);
            assertEquals(border,values[i-1]);
        }
    }
}
