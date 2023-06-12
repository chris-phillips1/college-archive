package tweetExtractor;

import java.util.ArrayList;

import twitter4j.Status;
import twitter4j.User;

public class TweetInfo {
	
	private ArrayList<Status> termResults = new ArrayList<Status>();
	private ArrayList<User> nameResults = new ArrayList<User>();
	
	public TweetInfo()
	{
		
	}
	
	public TweetInfo(ArrayList<Status> termResults, ArrayList<User> nameResults)
	{
		this.termResults = termResults;
		this.nameResults = nameResults;
	}
	
	public void setTermResults(ArrayList<Status> tResults)
	{
		this.termResults = tResults;
	}
	
	public void setNameResults(ArrayList<User> nResults)
	{
		this.nameResults = nResults;
	}
	
	public ArrayList<Status> getTermResults()
	{
		return this.termResults;
	}
	
	public ArrayList<User> getNameResults()
	{
		return this.nameResults;
	}
	
}
