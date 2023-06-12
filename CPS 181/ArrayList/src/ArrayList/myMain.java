package ArrayList;

import java.util.ArrayList;

public class myMain {

	public static void main(String[] args) {
		
		ArrayList arr = new ArrayList();
		arr.add("Hello");
		arr.add("World");
		arr.add("This");
		arr.add("is");
		arr.add("Cool");
		System.out.println(arr.size());
		
		for(int i = 0; i < arr.size(); i++){
			
			System.out.println(arr.get(i));
			
		}
		

	}

}
