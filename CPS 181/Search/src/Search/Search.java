package Search;

import java.util.ArrayList;

public class Search {

	static int count = 0;
	
	public Search()
	{
		
	}
	
	public int search(ArrayList<Integer> arr, int start, int end, int key)
	{			
		if(start < end)
		{
			int mid = start + (end - start) / 2;
			if(key < arr.get(mid))
			{
				count++;
				return search(arr, start, mid, key);
			}
			else if(key > arr.get(mid))
			{
				count++;
				return search(arr, mid+1, end, key);
			}
			else
			{
				System.out.println(arr.get(mid) + " is found at index " + mid);
				System.out.println("Number of Comparisons Made: " + count);
				return mid;
			}
			
		}
		System.out.println("The number " + key + " was not found in the list.");
		return -1;
	}
	
}
