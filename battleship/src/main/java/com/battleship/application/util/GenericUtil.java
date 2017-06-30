package com.battleship.application.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This is generic utility class provides application level common functions.
 * 
 * @author pmalsh
 *
 */
@Component
public class GenericUtil {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
		
	/**
	 * Method that return <code>List<code> from comma separated values in string.
	 * 
	 * @param <code>String<code> commaSeparatedValues
	 * @return <code>List<String><code>
	 */
	public List<String> convertToList(String commaSeparatedValues) {

		List<String> listOfValues = new ArrayList<>();
		String[] arrayValues = commaSeparatedValues.split(",");

		for (String value : arrayValues) {
			listOfValues.add(value.trim());
		}
		return listOfValues;
	}

	/**
	 * Method that return <code>List<code> from comma separated values in string.
	 * 
	 * @param <code>String<code> commaSeparatedValues
	 * @return <code>List<String><code>
	 */
	public List<int[][]> convertToListOfArrayIntegers(String commaSeparatedValues) {

		logger.info("Inside GenericUtil.convertToListOfArrayIntegers");
		List<int[][]> listOfValues = new ArrayList<>();
		String[] arrayValues = commaSeparatedValues.split(",");

		for (String value : arrayValues) {
			listOfValues.add(new int[][]{{Integer.parseInt(String.valueOf(value.charAt(0)))},{ Integer.parseInt(String.valueOf(value.charAt(1)))}});
		}
		return listOfValues;
	}
	
	/**
	 * Method that return <code>List<code> from comma separated values in string.
	 * 
	 * @param <code>String<code> commaSeparatedValues
	 * @return <code>List<String><code>
	 */
	public int[][] getCoordinateArray(String value) {
		return new int[][]{{Integer.parseInt(String.valueOf(value.charAt(0)))},{ Integer.parseInt(String.valueOf(value.charAt(1)))}};
	}
	
	
	/**
	 * @param coordinateArray
	 */
	public StringBuffer retrieveSingleDimensionCoordinates(List<int[][]> coordinateArray) {
		StringBuffer sbf = new StringBuffer();
		for (int[][] hitCoord : coordinateArray) {
			
			if (sbf.length() > 1)
				sbf.append(",");
				
			String str1 = Arrays.toString(hitCoord[0]);
			str1 = str1.substring(1, str1.length() - 1).replaceAll(",", "");
			sbf.append(str1);

			String str2 = Arrays.toString(hitCoord[1]);
			str2 = str2.substring(1, str2.length() - 1).replaceAll(",", "");
			sbf.append(str2);
		}
		return sbf;
	}
	
}
