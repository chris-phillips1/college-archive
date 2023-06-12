package concatenate;

import java.util.InputMismatchException;

public class CalcConcat extends Calculator {

	public CalcConcat() 
	{

	}
	
	/*
	 * This function allows the user to add, subtract, multiply, divide or concatenate.
	 * @param input1 This is the first user input, either a String or double
	 * @param input2 This is the second user input, either a String or double
	 * @param input3 This is the third user input, something arithmetic
	 * @return String This returns the output of the calculations performed
	 */
	
	public String addOrConcat(String input1, String input2, String input3)
			throws InputMismatchException, IllegalArgumentException, InvalidCalcException {
		//false = double, true = string
		boolean input1Boolean = false;
		boolean input2Boolean = false;
		String result = "";
		double in1 = 0;
		double in2 = 0;

		try 
		{
			in1 = Double.parseDouble(input1);
		} 
		catch (IllegalArgumentException ex) 
		{
			input1Boolean = true;
		}

		try 
		{
			in2 = Double.parseDouble(input2);
		} 
		catch (IllegalArgumentException ex) 
		{
			input2Boolean = true;
		}
		
		//Tests to see if the input is an arithmetic operator
		if (input3.equals("+") == true || input3.equals("-") == true || input3.equals("*") || input3.equals("/")) {
			//Tests to see if one of the inputs is a String and the operator is a plus sign: this is for concatenation
			if ((input1Boolean == true || input2Boolean == true) && input3.equals("+") == true) {
				result = input1 + input2;
			}
			//If both of those aren't true, then test if both of them are doubles
			else if (input1Boolean == false && input2Boolean == false) {
				//If they are both doubles, either add, subtract, multiply, or divide
				if (input3.equals("+") == true) {
					result = Double.toString(add(in1, in2));
				} 
				else if(input3.equals("-") == true) {
					result = Double.toString(subtract(in1, in2));
				}
				else if(input3.equals("*") == true) {
					result = Double.toString(multiply(in1, in2));
				}
				else {
					if(in2 == 0) //In case the user tries to divide by zero
						throw new IllegalArgumentException("Cannot Divide by Zero.");
					else
						result = Double.toString(divide(in1, in2));
				}
			} 
			else {
				//if the correct sign for the operation isn't used throw these errors, one for if it's a string, one for if its doubles
				if(input1Boolean || input2Boolean)
					throw new InvalidCalcException("For appending strings, sign cannot be " + input3);
				else
					throw new InvalidCalcException("For calculation with doubles, sign cannot be " + input3);
			}
		} 
		else {
			//if the the sign does not correspond with any of the valid signs, throw these errors
			if(input1Boolean || input2Boolean)
				throw new InvalidCalcException("For appending strings, sign cannot be " + input3);
			else
				throw new InvalidCalcException("For calculation with doubles, sign cannot be " + input3);
		}

		return result;
	}
}
