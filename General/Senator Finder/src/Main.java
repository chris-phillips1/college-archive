import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

public class Main {

	public static void main(String[] args) throws TwitterException, IOException {
		
		Twitter twitter = new TwitterFactory().getInstance();
		
		twitter.setOAuthConsumer( "", "");
		
		twitter.setOAuthAccessToken(new AccessToken("",
				""));
		
		Scanner scan = new Scanner(new File("Senators.txt"));
		
		PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
		ArrayList<String> screenNames = new ArrayList<String>();
		
		String[] names = new String[100];
		ResponseList<User> searchResults;
		String screenName;
		
		for(int i = 0; i < 100; i++)
			names[i] = scan.next() + " " + scan.next();
		
		for(int j = 0; j < 100; j++)
		{
			searchResults = twitter.searchUsers(names[j], 1);
			
			if((searchResults.get(0).getScreenName().contains("Sen")))
				screenName = searchResults.get(0).getScreenName();
			else if(searchResults.get(1).getScreenName().contains("Sen"))
				screenName = searchResults.get(1).getScreenName();
			else if(searchResults.get(2).getScreenName().contains("Sen"))
				screenName = searchResults.get(2).getScreenName();
			else
				screenName = searchResults.get(0).getScreenName();
			
			if((j + 1)% 25 == 0)
				System.out.println((j + 1) + "% Complete");
			screenNames.add(screenName);
		}
		
		for(int k = 0; k < 100; k++)
		{
			out.println(names[k] + " " + screenNames.get(k));
		}
		
		out.close();
	}
}
