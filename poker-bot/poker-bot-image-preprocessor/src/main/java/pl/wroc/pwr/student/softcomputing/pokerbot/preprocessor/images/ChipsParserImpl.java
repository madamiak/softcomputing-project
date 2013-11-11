package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ChipsParser;

public class ChipsParserImpl implements ChipsParser {
	private static final double SIMILARITY = 0.7;
	private final String DOLAR_KEY_VALUE = "47614572";
	private final String PLAYER_DOLAR_KEY_VALUE = "47614671";
	private final String COMA_KEY_VALUE = "33";
	private final String _0_KEY_VALUE = "61062241082";
	private final String _1_KEY_VALUE = "224121211";
	private final String _2_KEY_VALUE = "25557974";
	private final String _3_KEY_VALUE = "434410115";
	private final String _4_KEY_VALUE = "3444412122";
	private final String _5_KEY_VALUE = "8745875";
	private final String _6_KEY_VALUE = "610853487";
	private final String _7_KEY_VALUE = "25755442";
	private final String _8_KEY_VALUE = "2811547117";
	private final String _9_KEY_VALUE = "4774451093";
	private final String A_KEY_VALUE = "13466578653";

	@Override
	public int parseChips(BufferedImage image) {
		if(image.getType()!=BufferedImage.TYPE_BYTE_BINARY)throw new ImageNotBlackAndWhiteException();
		if(image.getWidth()!=94 || image.getHeight()!=18)throw new WrongImageSizeException();
		int chipCount=0;
		String key = calculateKey(0, image.getWidth(), image);
		ArrayList<String> templateList = generateNumberTemplateArray();
		int numberIndex=1;
		
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
			if(!numberFound)throw new ChipsParsingException();
		}
		System.out.println(key);
		return chipCount;
	}
	
	private boolean areSimilar(String a, String b){
		if(a.length()!=b.length()) return false;
		double similar=0;
		for(int i=a.length()-1;i>=0;i--){
			if(a.charAt(i)==b.charAt(i))similar++;
		}
		similar=similar/a.length();
		System.out.println("Similarity \""+a+"\" and \""+b+"\" is: "+similar);
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
	private boolean checkIfAllIn(String key) {
		if(key.contains(A_KEY_VALUE))return true;
		else return false;
	}
	
	private String calculateKey(int x, int width, BufferedImage image){
		StringBuilder key = new StringBuilder();
		for(int i=0; i<width;i++){
			int intKey=getWhitePixelsCount(i+x, image);
			if(intKey==0)key.append("X");
			else key.append(intKey);
		}
		String outKey= key.toString();
		outKey=outKey.replaceAll(DOLAR_KEY_VALUE, "");
		outKey=outKey.replaceAll(PLAYER_DOLAR_KEY_VALUE, "");
		outKey=outKey.replaceAll(COMA_KEY_VALUE, "");
		outKey=outKey.replaceAll("X+", "_");
		return outKey.substring(1, outKey.length()-1);
	}

	@Override
	public int parseTableChips(BufferedImage image) {
		return 0;
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
	
	private class ChipsParsingException extends RuntimeException {
		private static final long serialVersionUID = 4064907318587150066L;
	}

}
