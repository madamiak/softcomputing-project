package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ChipsParser;

public class ChipsParserImpl implements ChipsParser {
	private static final double SIMILARITY = 0.7;
	private final String _0_KEY_VALUE = "6A6224A82";
	private final String _1_KEY_VALUE = "224CC11";
	private final String _2_KEY_VALUE = "25557974";
	private final String _3_KEY_VALUE = "4344AB5";
	private final String _4_KEY_VALUE = "34444CC2";
	private final String _5_KEY_VALUE = "8745875";
	private final String _6_KEY_VALUE = "6A853487";
	private final String _7_KEY_VALUE = "25755442";
	private final String _8_KEY_VALUE = "28B547B7";
	private final String _9_KEY_VALUE = "477445A93";
	private final String A_KEY_VALUE = "13466578653";

	private final String _0_TABLE_KEY_VALUE = "69333595";
	private final String _1_TABLE_KEY_VALUE = "22AB11";
	private final String _2_TABLE_KEY_VALUE = "3466776";
	private final String _3_TABLE_KEY_VALUE = "1345797";
	private final String _4_TABLE_KEY_VALUE = "24334B21";
	private final String _5_TABLE_KEY_VALUE = "754586";
	private final String _6_TABLE_KEY_VALUE = "69554873";
	private final String _7_TABLE_KEY_VALUE = "3574543";
	private final String _8_TABLE_KEY_VALUE = "39756972";
	private final String _9_TABLE_KEY_VALUE = "47545895";

	@Override
	public int parseChips(BufferedImage image) {
		if(image.getType()!=BufferedImage.TYPE_BYTE_BINARY)throw new ImageNotBlackAndWhiteException();
		if(image.getWidth()!=94 || image.getHeight()!=18)throw new WrongImageSizeException();
		int chipCount=0;
		String key = calculateKey(0, image.getWidth(), image);
		ArrayList<String> templateList = generateNumberTemplateArray();
		int numberIndex=1;
		int noiseCount=0;
		
		if(checkIfAllIn(key))return Integer.MAX_VALUE;
		for(String subKey : key.split("_")){
			boolean numberFound=false;
			for(int j=0; j<templateList.size();j++){
				if(areSimilar(subKey, templateList.get(j))){
                    chipCount= chipCount+(j*(int)Math.pow(10,key.split("_").length-numberIndex));
					numberIndex++;
					numberFound=true;
					break;
				}
			}
			if(!numberFound)noiseCount++;
		}
		chipCount=(int) (chipCount/Math.pow(10, noiseCount));
        return chipCount;
	}
	
	@Override
	public int parseTableChips(BufferedImage image) {
		if(image.getType()!=BufferedImage.TYPE_BYTE_BINARY)throw new ImageNotBlackAndWhiteException();
		if(image.getWidth()!=150 || image.getHeight()!=14)throw new WrongImageSizeException();
		long chipCount=0;
		String key = calculateKey(0, image.getWidth(), image);
		ArrayList<String> templateList = generateTableNumberTemplateArray();
		int numberIndex=1;
		int noiseCount=0;
        for(String subKey : key.split("_")){
			boolean numberFound=false;
			for(int j=0; j<templateList.size();j++){
				if(areSimilar(subKey, templateList.get(j))){
                    chipCount= chipCount+(j*(long)Math.pow(10,key.split("_").length-numberIndex));
                    numberIndex++;
					numberFound=true;
					break;
				}
			}
			if(!numberFound)noiseCount++;
		}
		chipCount=(long) (chipCount/Math.pow(10, noiseCount));
		return (int)chipCount;
	}
	
	private boolean areSimilar(String a, String b){
		if(a.length()!=b.length()){
		boolean result = false;
			if(a.length()==b.length()+1){
				result = areSimilar(a, b+"0");
				result = result || areSimilar(a, "0"+b);
			}
			if(a.length()+1==b.length()){
				result = areSimilar(a+"0", b);
				result = result || areSimilar("0"+a, b);
			}
		return result;
		}
		double similar=0;
		for(int i=a.length()-1;i>=0;i--){
			if(a.charAt(i)==b.charAt(i))similar++;
		}
		similar=similar/a.length();
        //System.out.println("Similarity "+a+" "+b+" :"+similar);
        if(similar>SIMILARITY) return true;
		else return false;
	}
	
	private ArrayList<String> generateNumberTemplateArray(){
		ArrayList<String> template = new ArrayList<String>();
		template.add(_0_KEY_VALUE);
		template.add(_1_KEY_VALUE);
		template.add(_2_KEY_VALUE);
		template.add(_3_KEY_VALUE);
		template.add(_4_KEY_VALUE);
		template.add(_5_KEY_VALUE);
		template.add(_6_KEY_VALUE);
		template.add(_7_KEY_VALUE);
		template.add(_8_KEY_VALUE);
		template.add(_9_KEY_VALUE);
		return template;
	}
	
	private ArrayList<String> generateTableNumberTemplateArray(){
		ArrayList<String> template = new ArrayList<String>();
		template.add(_0_TABLE_KEY_VALUE);
		template.add(_1_TABLE_KEY_VALUE);
		template.add(_2_TABLE_KEY_VALUE);
		template.add(_3_TABLE_KEY_VALUE);
		template.add(_4_TABLE_KEY_VALUE);
		template.add(_5_TABLE_KEY_VALUE);
		template.add(_6_TABLE_KEY_VALUE);
		template.add(_7_TABLE_KEY_VALUE);
		template.add(_8_TABLE_KEY_VALUE);
		template.add(_9_TABLE_KEY_VALUE);
		return template;
	}
	private boolean checkIfAllIn(String key) {
		if(key.contains(A_KEY_VALUE))return true;
		else return false;
	}
	
	private String calculateKey(int x, int width, BufferedImage image){
		StringBuilder key = new StringBuilder();
		for(int i=0; i<width;i++){
			int intKey=getWhitePixelsCount(i+x, image);
			if(intKey==0)key.append("-");
			else{
				if(intKey>9) key.append((char)(intKey+55));
				else key.append((char)(intKey+48));
			}
		}
		String outKey= key.toString();
		outKey=outKey.replaceAll("-+", "_");
		if(outKey.equals("_"))return _0_KEY_VALUE;
		return outKey.substring(1, outKey.length()-1);
	}

	
	private int getWhitePixelsCount(int x, BufferedImage image){
		int count=0;

		for(int i=0;i<image.getHeight();i++){
			if(image.getRGB(x, i)==-1)count++;
		}
		return count;
	}
	
	
	private class ImageNotBlackAndWhiteException extends RuntimeException {
		private static final long serialVersionUID = 7244483847097074149L;
	}
	
	private class WrongImageSizeException extends RuntimeException {
		private static final long serialVersionUID = 7244483847097074149L;
	}
}
