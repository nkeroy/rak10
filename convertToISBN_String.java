import java.util.Scanner;
import java.lang.Integer;

public class convertToISBN_String {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String userInput = sc.next();
		System.out.println(convertToISBN(userInput));
		//System.out.println(calculateWeightedSum(reduceDigits(userInput)));
	}

	// overall function
	public static String convertToISBN(String productID) {
			return appendFinalDigit(reduceDigits(productID));
	}
	
	// convert 12-digit productID to 9-digit number by removing first 3 digits of 12-digit productID
	public static String reduceDigits(String productID) {
		return productID.substring(3);
	}
	
	// find the weighted sum given the reduced 9-digit productID
	public static int calculateWeightedSum(String reducedProductID){
		int weightedSum = 0;
		for (int i=0; i<reducedProductID.length(); i++){
			weightedSum += Integer.parseInt(reducedProductID.substring(i,i+1)) * (10 - i); // (10-i) is the weight to multiply 
		}
		return weightedSum;
	}
	
	// append error control digit at the back of reduced 9-digit productID utilising '0 modulo 11'
	public static String appendFinalDigit(String reducedProductID){
		int weightedSum = calculateWeightedSum(reducedProductID);
		int differenceInValue = weightedSum % 11; // the difference to add to the weightedSum, should the weightedSum be not exactly divisible by 11
		
		// special exceptions where adding 10 to weightedSum is exactly divisible by 11 (two conditions below)
		if (differenceInValue == 10 && (weightedSum + differenceInValue) % 11 == 0) return reducedProductID + "x";
		if (differenceInValue == 1 && (weightedSum + (11 - differenceInValue)) % 11 == 0) return reducedProductID + "x";
		
		if ((weightedSum + differenceInValue) % 11 == 0) return reducedProductID + differenceInValue; 
		else return reducedProductID + (11 - differenceInValue); // (11 - differenceInValue) because sometimes you minus differenceInValue from weightedSum to get number divisble by 11..
	}
}