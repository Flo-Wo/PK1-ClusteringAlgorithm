package readFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author florianwolf
 * 
 * This class provides the file reader method, to read vectors from a .csv-style .txt-File
 * - returnVectors
 * - sameDimension
 *
 */

public class FileReader {
	/**
	 * This methods reads a given file and returns an array of arrays, representing vectors
	 * @param filename
	 * @return array of vectors
	 * @throws Exception
	 */
	public static double[][] returnVectors(String filename) throws Exception{
		// create file
		File file = new File(filename);
		if(file.exists()) {
			// list to hold the vectors
			List<double[]> tempVectors = new ArrayList<double[]>();
			// creating scanner and reading the file
			Scanner inputStream = new Scanner(file);
			while(inputStream.hasNext()) {
				// get next vector
				String data = inputStream.nextLine();
				// split the data by comma --> get vector components
				String[] values = data.split(",");
				double[] temp = new double[values.length];
				// transform each string array to a double array
				for(int i = 0; i < values.length ; i++) {
					temp[i] = Double.parseDouble(values[i]);
				}
				// add current vector to the list of vectors
				tempVectors.add(temp);
			}
			inputStream.close();
			// transform list to an array
			double[][] vectors = new double[tempVectors.size()][];
			for(int i = 0; i < tempVectors.size(); i++) {
				vectors[i] = tempVectors.get(i);
			}
			boolean testDimension = sameDimension(vectors);
			if(testDimension) {
				// return all vectors
				return vectors;
			}
			else {
				throw new IllegalArgumentException("Vectors have not the same dimension.");
			}
		}
		// File not found
		else {
			throw new FileNotFoundException("File not found.");
		}
	}
	/**
	 * This methods checks, whether the input vectors have the same dimension
	 * @param vectors
	 * @return sameDimension
	 */
	public static boolean sameDimension(double[][] vectors) {
		// check for same length
		boolean testDimension = true;
		// get first dimension
		int dimension = vectors[0].length;
		// check whether all vectors have the same dimension
		for(int i = 1; i < vectors.length; i++) {
			if(vectors[i].length != dimension) {
				testDimension = false;
				break;
			}
		}
		return testDimension;
	}

}
