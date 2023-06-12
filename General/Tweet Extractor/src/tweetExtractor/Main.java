package tweetExtractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

/* This program will fetch tweets from all the senators and
 * if they contain a given term, they will be added to a .csv file
 * to be added to a database in python.
 * 
 * @author Chris Phillips
 * @version 1.0
 * @date 10 November 2017
 */
public class Main {

	public static void main(String[] args) throws TwitterException, IOException {
		
		//Setting up all the required stuff for the twitter api
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer( "", "");
		twitter.setOAuthAccessToken(new AccessToken("",
				""));
		
		
		PrintWriter out = new PrintWriter(new File("output.csv"));
		StringBuilder sb = new StringBuilder();
		
		//File that contains senator screen names
		BufferedReader br = new BufferedReader(new FileReader(
				"/Users/Chris/Desktop/Sophomore Year - Fall 2017/Political Website/Senator Data.csv"));
		
		
		//variables to store the extracted data 
		String screenName, splitData[];
		ArrayList<String> screenNames = new ArrayList<String>();
		
		//Loop that stores all 100 screen names
		for(int i = 0; i < 100; i++)
		{
			splitData = br.readLine().split(",");
			screenName = splitData[2];
			screenNames.add(screenName);
		}
		
		//search the senators' tweets for the term "tax" and store the
		//tweets and the corresponding senator in two arraylists
		TweetInfo ti = termSearch(screenNames, "tax");
		ArrayList<Status> taxTweets = ti.getTermResults();
		ArrayList<User> users = ti.getNameResults();
		
		//Clean the tweets in taxTweets and add them to the tweets arraylist
		ArrayList<String> tweets = addCleanedTweetsToList(taxTweets);
		
		//Print the first row of the .csv with the labels
		sb.append("Senator Screen Name");
		sb.append(",");
		sb.append("Tweet Text");
		sb.append(",");
		sb.append("Tweet ID");
		sb.append("\n");
		
		//For all the tweets, put screenName in first column, the
		//tweet text in second column, and tweet id in third column.
		for(int k = 0; k < taxTweets.size(); k++)
		{
			sb.append(users.get(k).getScreenName());
			sb.append(",");
			sb.append(tweets.get(k));
			sb.append(",");
			sb.append(taxTweets.get(k).getId());
			sb.append("\n");
		}
		
		//Add the string to the .csv file, then close it
		out.write(sb.toString());
		out.close();
		
	}
	
	/* The addCleanedTweetsToList function will clean a list of tweets that it's 
	 * given and will then return the cleaned tweets in a separate arraylist
	 * @return ArrayList<String> cleanedTweets: This is the arraylist of the cleaned tweets
	 * @params ArrayList<Status> uncleanTweets: This is the arraylist of the tweets that need to be cleaned
	 */
	public static ArrayList<String> addCleanedTweetsToList(ArrayList<Status> uncleanTweets)
	{
		ArrayList<String> cleanedTweets = new ArrayList<String>();
		String status;
		
		for(int i = 0; i < uncleanTweets.size(); i++)
		{
			status = uncleanTweets.get(i).getText();
			status = cleanTweet(status);
			cleanedTweets.add(status);
		}
		
		return cleanedTweets;
	}
	
	/* The cleanTweet function will clean a given tweet by replacing any new lines and commas
	 * @return String cleanedTweet: This is the tweet after any new lines or commas have been replaced
	 * @params String tweetToClean: This is the tweet that needs to be cleaned
	 */
	public static String cleanTweet(String tweetToClean)
	{
		String cleanedTweet = "";
		
		cleanedTweet = tweetToClean.replaceAll(",", "");
		cleanedTweet = cleanedTweet.replaceAll("\n", " ");
		
		return cleanedTweet;
	}
	
	/* The taxSearch function will go through all the senators and add any tweets that include
	 * the provided term to an arraylist and add the current senator to another arraylist
	 * @return TweetInfo ti: This an object that contains both the senator arraylist and the tweet arraylist
	 * @params ArrayList<String> senators:This is the list of senator screenNames used to search tweets
	 * @params String term: This is what each senator's page will be searched by
	 */
	public static TweetInfo termSearch(ArrayList<String> senators, String term) throws TwitterException
	{
		//Setting up all the required stuff for the twitter api
		Twitter twit = new TwitterFactory().getInstance();
		twit.setOAuthConsumer( "", "");
		twit.setOAuthAccessToken(new AccessToken("",
				""));
		
		//Create local variables for the tweets and senators
		ArrayList<Status> taxResults = new ArrayList<Status>();
		ArrayList<User> names = new ArrayList<User>();
		ResponseList<Status> tweets;
			
		//Add all relevant tweets along with their author
		for(int i = 0; i < senators.size(); i++)
		{
			tweets = twit.getUserTimeline(senators.get(i));
			for(int j = 0; j < tweets.size(); j++)
			{
				if( (tweets.get(j).getText().toLowerCase().contains(term.toLowerCase())) )
				{
					taxResults.add(tweets.get(j));
					names.add(tweets.get(j).getUser());
				}
			}
		}
		
		TweetInfo ti = new TweetInfo(taxResults, names);
		
		return ti;
	}
}
