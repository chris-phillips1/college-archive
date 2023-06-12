using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Abot.Crawler;
using Abot.Poco;
using System.Net;



namespace Lab_09_Bell3k
{
	public partial class Form1 : Form
	{
		static CrawlConfiguration crawlConfig = new CrawlConfiguration();
		PoliteWebCrawler crawler = new PoliteWebCrawler(crawlConfig, null, null, null,
		null, null, null, null, null);

		public Form1()
		{
			//Crawl limits 
			crawlConfig.CrawlTimeoutSeconds = 100;
			crawlConfig.MaxConcurrentThreads = 10;
			crawlConfig.MaxPagesToCrawl = 500;
			crawlConfig.UserAgentString = "abot v1.0 http://code.google.com/p/abot";

			crawler.PageCrawlStartingAsync += crawler_ProcessPageCrawlStarting;
			crawler.PageCrawlCompletedAsync += crawler_ProcessPageCrawlCompleted;
			crawler.PageCrawlDisallowedAsync += crawler_PageCrawlDisallowed;
			crawler.PageLinksCrawlDisallowedAsync += crawler_PageLinksCrawlDisallowed;

			InitializeComponent();
		}

		/// <summary>
		/// Takes in the website to craw and sets the pageToCrawl for it
		/// </summary>
		/// <param name="sender"></param>
		/// <param name="e">The website to crawl</param>
		void crawler_ProcessPageCrawlStarting(object sender, PageCrawlStartingArgs e)
		{
			PageToCrawl pageToCrawl = e.PageToCrawl;
			//Console.WriteLine(String.Format("About to crawl link {0} which was found on page {1}", pageToCrawl.Uri.AbsoluteUri, pageToCrawl.ParentUri.AbsoluteUri));
			String search = sTB.Text;
			
		}

		/// <summary>
		/// Lets the user know if the crawl was sucessful and if it was blank
		/// </summary>
		/// <param name="sender"></param>
		/// <param name="e"></param>
		void crawler_ProcessPageCrawlCompleted(object sender, PageCrawlCompletedArgs e)
		{
			CrawledPage crawledPage = e.CrawledPage;

			//Snippet Size, Search term, Page to search, and the Snippet
			int snipSize = 25;
			String search = sTB.Text, 
			page = crawledPage.Content.Text.ToString(), 
			snip;

			//Check if the you couldn't crawl the page
			if (crawledPage.WebException != null || crawledPage.HttpWebResponse.StatusCode != HttpStatusCode.OK)
			{

			}
			else
			{
				//if the page contains the search, print it and the snippet
				if (page.Contains(search))
				{
					Console.WriteLine("Page ({0}) contains {1}", crawledPage.Uri.AbsoluteUri, search);
					snip = page.Substring(page.IndexOf(search) - snipSize/2, snipSize);
					Console.WriteLine("Snip, \"{0}\"", snip);
				}
			}

			var htmlAgilityPackDocument = crawledPage.HtmlDocument; //Html Agility Pack parser
			var angleSharpHtmlDocument = crawledPage.AngleSharpHtmlDocument; //AngleSharp parser
			
	    }

		/// <summary>
		/// Lets you know if the craw was disallowed,  and tells you why.
		/// </summary>
		/// <param name="sender"></param>
		/// <param name="e"></param>
		void crawler_PageLinksCrawlDisallowed(object sender, PageLinksCrawlDisallowedArgs e)
		{
			CrawledPage crawledPage = e.CrawledPage;
			Console.WriteLine("Did not crawl the links on page {0} due to {1}",
			crawledPage.Uri.AbsoluteUri, e.DisallowedReason);
		}

		/// <summary>
		/// Lets you know if the craw was disallowed, 
		/// </summary>
		/// <param name="sender"></param>
		/// <param name="e"></param>
		void crawler_PageCrawlDisallowed(object sender, PageCrawlDisallowedArgs e)
		{
			PageToCrawl pageToCrawl = e.PageToCrawl;
			Console.WriteLine("Did not crawl page {0} due to {1}",
		   pageToCrawl.Uri.AbsoluteUri, e.DisallowedReason);
		}


		/*private void Form1_Load(object sender, EventArgs e)
		{

		}*/

		/// <summary>
		/// Starts the crawler
		/// </summary>
		/// <param name="sender"></param>
		/// <param name="e"></param>
		private void cButton_Click(object sender, EventArgs e)
		{
			CrawlResult result = crawler.Crawl(new Uri("https://www.reddit.com")); //This is synchronous, it will not go to the next line until the crawl has completed

			if (result.ErrorOccurred)
				Console.WriteLine("Crawl of {0} completed with error: {1}", result.RootUri.AbsoluteUri, result.ErrorException.Message);
			else
				Console.WriteLine("Crawl of {0} completed without error.", result.RootUri.AbsoluteUri);
		}
	}
}
