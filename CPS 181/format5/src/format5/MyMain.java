package format5;

import java.text.DecimalFormat;

public class MyMain {

	public static void main(String[] args) {
		
		DecimalFormat form1 = new DecimalFormat("$#0.00");
		DecimalFormat form2 = new DecimalFormat("$#0.0000");
		double num = 3.141596;
		System.out.println(form1.format(num));
		System.out.println(form2.format(num));
		
		
	}

}
