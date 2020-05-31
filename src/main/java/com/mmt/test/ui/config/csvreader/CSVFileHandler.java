/**
 * 
 */
package com.mmt.test.ui.config.csvreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Tanmoy May 31, 2020
 */
public class CSVFileHandler {

	private static final Logger logger = LogManager.getLogger(CSVFileHandler.class);

	/**
	 * @param inputFilePath
	 * @return csv first data record and converting into list
	 */
	public static Map<String, String> readCSVInputFile(String inputFilePath) {

		try {
			File file = new File(inputFilePath);
			// to Store csv file header as a key
			List<String> csvKeyList = new ArrayList<String>();
			// to store csv data - test data
			List<String> csvDataList = new ArrayList<String>();
			Scanner scanner = new Scanner(file);
			// read first header line
			String headerLine = scanner.nextLine();
			csvKeyList.addAll(convertStringToList(headerLine));
			if (scanner.hasNext()) {
				// read the first data
				String dataLine = scanner.nextLine();
				csvDataList.addAll(convertStringToList(dataLine));
			}
			scanner.close();
			return buildMapFromArrayKeyValueList(csvKeyList, csvDataList);
		} catch (FileNotFoundException e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * 
	 * @param csvLineData
	 * @return
	 */
	private static List<String> convertStringToList(String csvLineData) {
		if (null == csvLineData) {
			return null;
		} else {
			String[] csvData = csvLineData.split(",");
			return Arrays.asList(csvData);
		}
	}

	/**
	 * 
	 * @param keyList
	 * @param valueList
	 * @return
	 */
	private static Map<String, String> buildMapFromArrayKeyValueList(List<String> keyList, List<String> valueList) {

		Map<String, String> testData = new HashMap<>();

		/**
		 * keylist for key and valuelist for value
		 */

		if (keyList != null && keyList.size() > 0 && valueList != null) {
			for (int index = 0; index < keyList.size(); index++) {
				String key = keyList.get(index);
				String value = new String();
				try {
					value = valueList.get(index);
				} catch (IndexOutOfBoundsException e) {
					value = null;
					logger.error(e);
				} catch (NullPointerException ex) {
					value = null;
					logger.error(ex);
				}
				testData.put(key, value);
			}
			return testData;
		}
		return null;
	}

	public static List<String> readCSVInputFileMultipleData(String inputFilePath) {
		try {
			File file = new File(inputFilePath);
			List<String> csvData = new ArrayList<>();
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				csvData.add(scanner.nextLine());
			}
			scanner.close();
			return csvData;
		} catch (FileNotFoundException e) {
			logger.error(e);
		}
		return null;
	}
}