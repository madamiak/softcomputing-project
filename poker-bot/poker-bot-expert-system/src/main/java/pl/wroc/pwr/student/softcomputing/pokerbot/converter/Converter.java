package pl.wroc.pwr.student.softcomputing.pokerbot.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RaV on 15.01.14.
 */
public class Converter {
    private boolean isFoldButton;
    private int dealerPosition;
    private int numberOfPlayers;
    private String firstCardFigure;
    private String firstCardSuit;
    private String secondCardFigure;
    private String secondCardSuit;
    private List<Integer> totalChips;
    private List<Integer> chipsAtTable;
    private List<Boolean> presentAtTable;
    private List<String> border;

    public Converter(boolean isFoldButton, int dealerPosition, int numberOfPlayers, String firstCardFigure, String firstCardSuit, String secondCardFigure, String secondCardSuit, List<Integer> totalChips, List<Integer> chipsAtTable, List<Boolean> presentAtTable, List<String> border) {
        this.isFoldButton = isFoldButton;
        this.dealerPosition = dealerPosition;
        this.numberOfPlayers = numberOfPlayers;
        this.firstCardFigure = firstCardFigure;
        this.firstCardSuit = firstCardSuit;
        this.secondCardFigure = secondCardFigure;
        this.secondCardSuit = secondCardSuit;
        this.totalChips = totalChips;
        this.chipsAtTable = chipsAtTable;
        this.presentAtTable = presentAtTable;
        this.border = border;

    }

    public ConvertedData convertData(){
        ConvertedData data = new ConvertedData();
        data.setEffectiveStack(calculateEffectiveStack());
        data.setPlayerStackinBb(calculatePlayerStackinBb());
        data.setCardsSuited(areCardsSuited());
        data.setHigherFigure(getHigerCardFigure());
        data.setLowerFigure(getLowerCardFigure());
        data.setNumberOfPlayers(numberOfPlayers);
        data.setNumberOfLimpers(getNumberOfLimpers());
        data.setNumberOfRaisers(getNumberOfRaisers());
        data.setPosition(getPlayerPosition());
        return data;
    }

    private int getBigBlindPosition(){
        int position = dealerPosition;
        for(int i=0;i<2;i++){
            position++;
            if(position==7)position=1;
            if(presentAtTable.get(position-1)==false)i--;
        }
        return position;
    }

    private int calculateBigBlind(){
        return chipsAtTable.get(getBigBlindPosition()-1);
    }

    private int calculateEffectiveStack(){
        int playerChips = totalChips.get(0);
        int maxOpponentChips = 0;
        for(int i=1; i<6; i++){
            if(totalChips.get(i)>maxOpponentChips)
                maxOpponentChips=totalChips.get(i);
        }
        return playerChips<maxOpponentChips ? playerChips : maxOpponentChips;
    }

    private int calculatePlayerStackinBb(){
        int stack = totalChips.get(1);
        stack = stack-(stack%calculateBigBlind());
        return stack/calculateBigBlind();
    }

    private boolean areCardsSuited(){
        return firstCardSuit.equals(secondCardSuit);
    }

    private String getHigerCardFigure(){
        int first=0;
        int second=0;
        try{
            first = Integer.parseInt(firstCardFigure);
        }catch (NumberFormatException e){
            first=10;
        }
        try{
            second = Integer.parseInt(secondCardFigure);
        }catch (NumberFormatException e){
            second=10;
        }
        if(first>second)return firstCardFigure;
        if(first<second)return secondCardFigure;

        if(firstCardFigure.equals("A"))return firstCardFigure;
        if(secondCardFigure.equals("A"))return secondCardFigure;
        if(firstCardFigure.equals("K"))return firstCardFigure;
        if(secondCardFigure.equals("K"))return secondCardFigure;
        if(firstCardFigure.equals("Q"))return firstCardFigure;
        if(secondCardFigure.equals("Q"))return secondCardFigure;
        if(firstCardFigure.equals("J"))return firstCardFigure;
        if(secondCardFigure.equals("J"))return secondCardFigure;
        if(firstCardFigure.equals("T"))return firstCardFigure;
        return secondCardFigure;
    }

    private String getLowerCardFigure(){
        if(firstCardFigure.equals(getHigerCardFigure()))
            return secondCardFigure;
        else
            return firstCardFigure;
    }

    private int getNumberOfLimpers(){
        int count=-1;
        int bb=calculateBigBlind();
        for(Integer tableChip : chipsAtTable){
            if(tableChip==bb)count++;
        }
        return count;
    }

    private int getNumberOfRaisers(){
        int count=0;
        int bb=calculateBigBlind();
        for(Integer tableChip : chipsAtTable){
            if(tableChip>bb)count++;
        }
        return count;
    }

    private int getPlayerPosition(){
        int position = 7-numberOfPlayers;
        int index=getBigBlindPosition();
        if(index==1)return 6;
        while(true){
            index++;
            if(index==7)return position;
            if(presentAtTable.get(index-1))position++;
        }
    }

    private Map<String, Border> getBorderMap(){
        Map<String, Border> map = new HashMap<String, Border>();
        map.put("noLabel",Border.Random);
        map.put("yellow",Border.Random);
        map.put("orange",Border.Regular);
        map.put("lime",Border.Regular);
        map.put("green",Border.Regular);
        map.put("cyan",Border.Random);
        map.put("darkBlue",Border.Regular);
        map.put("black",Border.Random);
        map.put("white",Border.Regular);
        map.put("blue",Border.Random);
        map.put("red",Border.Random);
        map.put("purple",Border.GoodLimper);
        map.put("pink",Border.Random);
        map.put("lightOrange",Border.Random);
        map.put("gray",Border.WeakLimper);
        return map;
    }

}
