package sort;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		int[] arr = {5,6,3,8,1,9,11,2};
		int[] arr2;
		
		for(int i : arr)
			System.out.print(i + " ");
		
		Arrays.sort(arr);
		System.out.println();
		
		for(int i : arr)
			System.out.print(i + " ");
		
		System.out.println();
		System.out.println(Arrays.toString(arr));
		
		arr2 = Arrays.copyOf(arr, 3);
		
		for(int i : arr2)
			System.out.print(i + " ");
		
		System.out.println();
		System.out.println(Arrays.binarySearch(arr2, 1));
		
	}

}
