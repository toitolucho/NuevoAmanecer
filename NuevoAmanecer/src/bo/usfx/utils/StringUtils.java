package bo.usfx.utils;

import java.util.*;

/**
 * La classe contiene numerosas funciones 
 * de manipulacion de cadenas
 * 
 */
public class StringUtils {

    public StringUtils() {
    }

    public static final String fill(String someString, int number, String fillString) {
        if (someString.length() < number) {
            StringBuffer sb = new StringBuffer();
            sb.append(someString);
            int totalLength = someString.length();
            while (totalLength < number) {
                sb.append(fillString);
                totalLength += fillString.length();
            }
            return sb.toString();
        }
        return someString;
    }

    /**
     * Replaces all instances of searchFor in originalText with replaceWith. The check is done without paying attention
     * to upper or lowercase.
     * <p/>
     * Example StringUtils.replaceCaseInSensitive("the Lazy fox jumped over the lazy dog", "lazy","red") returns
     * "the red fox jumped over the red dog"
     *
     * @param originalText the String to search to perform replacements on
     * @param searchFor    the String that should be replaced by newString
     * @param replaceWith  the String that will replace all instances of oldString
     * @return a String will all instances of oldString replaced by newString
     */
    public static final String replaceCaseInSensitive(String originalText, String searchFor, String replaceWith) {
        if (originalText == null) {
            return null;
        }
        int i = 0;
        String compareLine = originalText.toLowerCase();
        searchFor = searchFor.toLowerCase();
        if ((i = compareLine.indexOf(searchFor, i)) >= 0) {
            char[] originalChars = originalText.toCharArray();
            char[] replaceWithChars = replaceWith.toCharArray();
            int searchForLength = searchFor.length();
            StringBuffer buf = new StringBuffer(originalChars.length);
            buf.append(originalChars, 0, i).append(replaceWithChars);
            i += searchForLength;
            int j = i;
            while ((i = compareLine.indexOf(searchFor, i)) > 0) {
                buf.append(originalChars, j, i - j).append(replaceWithChars);
                i += searchForLength;
                j = i;
            }
            buf.append(originalChars, j, originalChars.length - j);
            return buf.toString();
        }
        return originalText;
    }

    /**
     * Replaces all instances of searchFor in originalText with replaceWith.
     * <p/>
     * Example StringUtils.replace("the Lazy fox jumped over the lazy dog", "lazy", "red") returns
     * "the Lazy fox jumped over the red dog"
     *
     * @param originalText the String to search to perform replacements on
     * @param searchFor    the String that should be replaced by newString
     * @param replaceWith  the String that will replace all instances of oldString
     * @return a String will all instances of oldString replaced by newString
     * @publish yes
     */
    public static final String replace(String originalText, String searchFor, String replaceWith) {
        if (originalText == null) {
            return null;
        }
        int i = 0;
        if ((i = originalText.indexOf(searchFor, i)) >= 0) {
            char[] originalText2 = originalText.toCharArray();
            char[] newString2 = replaceWith.toCharArray();
            int oLength = searchFor.length();
            StringBuffer buf = new StringBuffer(originalText2.length);
            buf.append(originalText2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = originalText.indexOf(searchFor, i)) > 0) {
                buf.append(originalText2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(originalText2, j, originalText2.length - j);
            return buf.toString();
        }
        return originalText;
    }

    /**
     * This method will return the text that is between beginString and endString.
     *
     * @param searchIn
     * @param beginString
     * @param endString
     * @return
     */
	public static final String findBetween(String searchIn, String beginString, String endString) {
		int beginpos = searchIn.indexOf(beginString);
		int endpos = -1;
		
		if (beginpos != -1) {
		    // we found the beginstring, now search from this position for the endstring
		    endpos = searchIn.substring(beginpos + beginString.length()).indexOf(endString);
		    if (endpos != -1) {
			return searchIn.substring(beginpos + beginString.length(), beginpos + beginString.length() + endpos);
		    }
		}
		return null;
	}

	public static int getIntParameter(Map parameters, String theKey, int defaultValue) {
		if (parameters.containsKey(theKey)) {
		    try {
			return Integer.parseInt((String) parameters.get(theKey));
		    } catch (NumberFormatException e) {
			// couldnt parse, return the defaultValue
		    }
		}
		return defaultValue;
	}
	
   	public static final String getObjectName(Object ob){
		String nameObject = (String)ob.getClass().getName();
		return (nameObject.trim().substring(nameObject.lastIndexOf(".")+1));
	}
	
	public static final String numberToStringWithSerie(int numDigitos,int serieActual,int numActual){
		String[] digitos = prepareLengthNumber(numDigitos).split(",");
		if (numActual > Integer.parseInt(digitos[1])){
			numActual = 1;
			serieActual ++;
		}
		char serie[ ] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		String codigo = String.valueOf(numActual);
		codigo = digitos[0].substring(0,digitos[0].length()-codigo.length())+codigo.trim();
		codigo = codigo+serie[serieActual];
		return codigo.trim();
	}
	
	public static final String numberToStringWithOutSerie(int numDigitos,int siguienteId){
		String[] digitos = prepareLengthNumber(numDigitos).split(",");
		String codigo = String.valueOf(siguienteId);
		codigo = digitos[0].substring(0,digitos[0].length()-codigo.length())+codigo.trim();
		return codigo.trim();
	}
	
  	public static final String prepareLengthNumber(int numDigitos){
		String digitos = new String("0");
		String valorMax = new String("9");
		for (int i = 1;i < numDigitos; i++){
			digitos +="0";
			valorMax +="9"; 
		}
		return (digitos+","+valorMax);
	} 
	
  	public static final String changeCharInString(char[] charSpecial, char[] changeChar,String stringToReplace){
		for (int i = 0; i < charSpecial.length; i++){
			stringToReplace = stringToReplace.replace(charSpecial[i], changeChar[i]);
		}
		return stringToReplace;
	} 
}