package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.BorderParser;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by RaV on 14.01.14.
 */
public class BorderParserImpl implements BorderParser {
    private static final int[] NOLABEL = {102,102,102};
    private static final int[] YELLOW = {245,211,51};
    private static final int[] ORANGE = {195,101,6};
    private static final int[] LIME = {147,245,50};
    private static final int[] GREEN = {5,107,5};
    private static final int[] CYAN = {6,197,197};
    private static final int[] DARKBLUE = {6,102,190};
    private static final int[] BLACK = {5,5,5};
    private static final int[] WHITE = {245,245,245};
    private static final int[] BLUE = {6,6,245};
    private static final int[] RED = {245,64,51};
    private static final int[] PURPLE = {124,5,245};
    private static final int[] PINK = {245,72,245};
    private static final int[] LIGHTORANGE = {245,158,116};
    private static final int[] GRAY = {125,125,125};

    private static final int MAX_DELTA = 10;
    @Override
    public String parseBorder(BufferedImage image) {
        int x = image.getWidth();
        int y = image.getHeight();

        String colorName = "";
        for(int j=0;j<y;j++){
            int red = 0;
            int green = 0;
            int blue = 0;
            for(int i=0; i<x; i++){
                Color c = new Color(image.getRGB(i,j));
                red += c.getRed();
                green += c.getGreen();
                blue += c.getBlue();
            }
            red = red/x;
            green = green/x;
            blue = blue/x;

            colorName = getColorName(red, green, blue);
            if(!colorName.equals("noLabel"))return colorName;
        }
        return colorName;
    }

    private String getColorName(int r, int g, int b){
        if(areRGBsEqual(r , g, b,NOLABEL))return "noLabel";
        if(areRGBsEqual(r, g, b,YELLOW))return "yellow";
        if(areRGBsEqual(r, g, b,ORANGE))return "orange";
        if(areRGBsEqual(r, g, b,LIME))return "lime";
        if(areRGBsEqual(r, g, b,GREEN))return "green";
        if(areRGBsEqual(r, g, b,CYAN))return "cyan";
        if(areRGBsEqual(r, g, b,DARKBLUE))return "darkBlue";
        if(areRGBsEqual(r, g, b,BLACK))return "black";
        if(areRGBsEqual(r, g, b,WHITE))return "white";
        if(areRGBsEqual(r, g, b,BLUE))return "blue";
        if(areRGBsEqual(r, g, b,RED))return "red";
        if(areRGBsEqual(r, g, b,PURPLE))return "purple";
        if(areRGBsEqual(r, g, b,PINK))return "pink";
        if(areRGBsEqual(r, g, b,LIGHTORANGE))return "lightOrange";
        if(areRGBsEqual(r, g, b,GRAY))return "gray";
        return "noLabel";
    }

    private boolean areRGBsEqual(int r, int g, int b, int [] rgb){
        boolean isRed = false;
        boolean isGreen = false;
        boolean isBlue = false;

        if(r+MAX_DELTA>rgb[0]&&r-MAX_DELTA<rgb[0]) isRed=true;
        if(g+MAX_DELTA>rgb[1]&&g-MAX_DELTA<rgb[1]) isGreen=true;
        if(b+MAX_DELTA>rgb[2]&&b-MAX_DELTA<rgb[2]) isBlue=true;

        return isRed&&isGreen&&isBlue;
    }
}
