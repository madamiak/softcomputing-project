package pl.wroc.pwr.student.softcomputing.pokerbot.converter;

import java.util.ArrayList;
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

    public Converter(boolean isFoldButton, int dealerPosition, String firstCard, String secondCard, List<Integer> totalChips, List<Integer> chipsAtTable, List<String> border) {
        this.isFoldButton = isFoldButton;
        this.dealerPosition = dealerPosition;
        this.firstCardFigure = String.valueOf(firstCard.split(" ")[0].charAt(0)).toUpperCase();
        this.firstCardSuit = String.valueOf(firstCard.split(" ")[2].charAt(0)).toLowerCase();
        this.secondCardFigure = String.valueOf(secondCard.split(" ")[0].charAt(0)).toUpperCase();
        this.secondCardSuit = String.valueOf(secondCard.split(" ")[2].charAt(0)).toLowerCase();
        this.totalChips = totalChips;
        this.chipsAtTable = chipsAtTable;
        this.border = border;
        if(this.firstCardFigure.equals("1"))this.firstCardFigure="T";
        if(this.secondCardFigure.equals("1"))this.secondCardFigure="T";
        presentAtTable = calculatePresentAtTable();
        numberOfPlayers = countNumberOfPlayers();
    }

    private List<Boolean> calculatePresentAtTable() {
        List<Boolean> present = new ArrayList<Boolean>();
        for(int i=0;i<6;i++){
            present.add(totalChips.get(i)>0);
        }
        return present;
    }

    private int countNumberOfPlayers() {
        int count=0;
        for(int i=0;i<6;i++){
            if(totalChips.get(i)>0)
                count++;
        }
        return count;
    }

    public ConvertedData convertData(){
        ConvertedData data = new ConvertedData();
        data.setEffectiveStackInBb(calculateEffectiveStackInBb());
        data.setPlayerStackInBb(calculatePlayerStackInBb());
        data.setCardsSuited(areCardsSuited());
        data.setHigherFigure(getHigerCardFigure());
        data.setLowerFigure(getLowerCardFigure());
        data.setNumberOfPlayers(numberOfPlayers);
        data.setNumberOfLimpers(getNumberOfLimpers());
        data.setNumberOfRaisers(getNumberOfRaisers());
        data.setPosition(getPlayerPosition());
        data.setFirstRaisePosition(getFirstRaisePosition());
        data.setBorder(getSpecyficBorder());
        return data;
    }

    private Border getSpecyficBorder() {
        if(getNumberOfLimpers()+getNumberOfRaisers()==0)
            return getBorderMap().get(border.get(getBigBlindPosition()-1));
        else
        {
            int index = getBigBlindPosition();
            int bigBlind = calculateBigBlind();
            while(true){
                if(index==6)index = 0;
                if(chipsAtTable.get(index)>=bigBlind){
                    return getBorderMap().get(border.get(index));
                }
                index++;
            }
        }
    }

    private int getBigBlindPosition(){
        int position = dealerPosition;
        for(int i=0;i<2;i++){
            position++;
            if(position==7)position=1;
            if(!presentAtTable.get(position-1))i--;
        }
        return position;
    }

    private int calculateBigBlind(){
        return chipsAtTable.get(getBigBlindPosition()-1);
    }

    private int calculateEffectiveStackInBb(){
        int playerChips = totalChips.get(0);
        int maxOpponentChips = 0;
        int bbIndex = getBigBlindPosition()-1;
        for(int i=1; i<6; i++){
            if(totalChips.get(i)>maxOpponentChips&&i<=bbIndex)
                maxOpponentChips=totalChips.get(i);
            if(totalChips.get(i)>maxOpponentChips&&chipsAtTable.get(i)>0)
                maxOpponentChips=totalChips.get(i);
        }
        System.out.println("player: "+playerChips);
        System.out.println("maxOpponentChips: "+maxOpponentChips);
        int effectiveStack = playerChips<maxOpponentChips ? playerChips : maxOpponentChips;
        System.out.println("Effective stack: "+effectiveStack);
        int effectiveStackInBB = effectiveStack/calculateBigBlind();
        if(effectiveStack%calculateBigBlind()>(calculateBigBlind()/2))
            effectiveStackInBB++;
        return effectiveStackInBB;
    }

    private int calculatePlayerStackInBb(){
        int stack = totalChips.get(0);
        int stackInBB = stack/calculateBigBlind();
        if(stack%calculateBigBlind()>(calculateBigBlind()/2))
            stackInBB++;
        return stackInBB;
    }

    private boolean areCardsSuited(){
        return firstCardSuit.equals(secondCardSuit);
    }

    private String getHigerCardFigure(){
        int first;
        int second;
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

        if(firstCardFigure.equals("A")){
            return firstCardFigure;
        }
        if(secondCardFigure.equals("A")){
            return secondCardFigure;
        }
        if(firstCardFigure.equals("K")){
            return firstCardFigure;
        }
        if(secondCardFigure.equals("K")){
            return secondCardFigure;
        }
        if(firstCardFigure.equals("Q")){
            return firstCardFigure;
        }
        if(secondCardFigure.equals("Q")){
            return secondCardFigure;
        }
        if(firstCardFigure.equals("J")){
            return firstCardFigure;
        }
        if(secondCardFigure.equals("J")){
            return secondCardFigure;
        }
        if(firstCardFigure.equals("T")){
            return firstCardFigure;
        }
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

    private int getFirstRaisePosition(){
        int position = 7-numberOfPlayers;
        int bigBlind = calculateBigBlind();
        int bigBlindPosition=getBigBlindPosition();
        int index=bigBlindPosition;
        while(true){
            index++;
            if(index==7)index=1;
            if(index==bigBlindPosition)return 0;
            if(chipsAtTable.get(index-1)>bigBlind)return position;
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
