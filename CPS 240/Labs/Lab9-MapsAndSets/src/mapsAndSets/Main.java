package mapsAndSets;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		
		Set<String> hset = new HashSet<>();
		Set<String> lhset = new LinkedHashSet<>();
		Set<String> tset = new TreeSet<>();
		
		hset.add("Wendy");
		hset.add("Peter Pan");
		hset.add("Captain Hook");
		hset.add("London");

		lhset.add("Wendy");
		lhset.add("Peter Pan");
		lhset.add("Captain Hook");
		lhset.add("London");
		
		tset.add("Wendy");
		tset.add("Peter Pan");
		tset.add("Captain Hook");
		tset.add("London");
		
		hset.forEach(e -> System.out.print(e + ", "));
		System.out.println();
		lhset.forEach(e -> System.out.print(e + ", "));
		System.out.println();
		tset.forEach(e -> System.out.print(e + ", "));

	}

}
