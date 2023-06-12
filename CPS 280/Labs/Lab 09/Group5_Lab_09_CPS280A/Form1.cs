/*  Lab 9_Group 5
 *  Nicholas Clark, Mitchell Tecklenburg, Kevin Bell, Christopher Phillips 
 * 
 */

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


namespace Group5_Lab_09_CPS280A
{
    public partial class Form1 : Form
    {
        static CrawlConfiguration crawlConfig = new CrawlConfiguration();
        PoliteWebCrawler crawler = new PoliteWebCrawler(crawlConfig, null, null, null, null, null, null, null, null);

        /// <summary>
        /// Initialize configuration for crawler, add crawler event handlers
        /// </summary>
        public Form1()
        {
            InitializeComponent();
            crawlConfig.CrawlTimeoutSeconds = 100;
            crawlConfig.MaxConcurrentThreads = 10;
            crawlConfig.MaxPagesToCrawl = 10;
            crawlConfig.UserAgentString = "abot v1.0 http://code.google.com/p/abot";

            //ADD EVENT HANDLERS
            crawler.PageCrawlStartingAsync += crawler_ProcessPageCrawlStarting;
            crawler.PageCrawlCompletedAsync += crawler_ProcessPageCrawlCompleted;
            crawler.PageCrawlDisallowedAsync += crawler_PageCrawlDisallowed;
            crawler.PageLinksCrawlDisallowedAsync += crawler_PageLinksCrawlDisallowed;

        }

        /// <summary>
        /// Begin the crawl
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        void crawler_ProcessPageCrawlStarting(object sender, PageCrawlStartingArgs e)
        {
            PageToCrawl pageToCrawl = e.PageToCrawl;
            Console.WriteLine(String.Format("About to crawl link {0} which was found on page {1}", pageToCrawl.Uri.AbsoluteUri, pageToCrawl.ParentUri.AbsoluteUri));
        }

        /// <summary>
        /// When crawl has finished, tells user the results of the crawl
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        void crawler_ProcessPageCrawlCompleted(object sender, PageCrawlCompletedArgs e)
        {
            CrawledPage crawledPage = e.CrawledPage;

            if (crawledPage.WebException != null || crawledPage.HttpWebResponse.StatusCode != HttpStatusCode.OK)
                Console.WriteLine("Crawl of page failed {0}", crawledPage.Uri.AbsoluteUri);
            else Console.WriteLine("Crawl of page succeeded {0}", crawledPage.Uri.AbsoluteUri);

            if (string.IsNullOrEmpty(crawledPage.Content.Text))
                Console.WriteLine("Page had no content {0}", crawledPage.Uri.AbsoluteUri);

            var htmlAgilityPackDocument = crawledPage.HtmlDocument; //Html Agility Pack parser             
            var angleSharpHtmlDocument = crawledPage.AngleSharpHtmlDocument; //AngleSharp parser         
        } 

        /// <summary>
        /// Tells the user if the crawl was disallowed due to page links disallowed
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        void crawler_PageLinksCrawlDisallowed(object sender, PageLinksCrawlDisallowedArgs e)
        {
            CrawledPage crawledPage = e.CrawledPage;
            Console.WriteLine("Did not crawl the links on page {0} due to {1}", crawledPage.Uri.AbsoluteUri, e.DisallowedReason);
        }

        /// <summary>
        /// Tells the user if the crawl was disallowed for other reasons
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        void crawler_PageCrawlDisallowed(object sender, PageCrawlDisallowedArgs e)
        {
            PageToCrawl pageToCrawl = e.PageToCrawl;
            Console.WriteLine("Did not crawl page {0} due to {1}", pageToCrawl.Uri.AbsoluteUri, e.DisallowedReason);
        }

        /// <summary>
        /// When button is clicked, begins the crawl.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button1_Click(object sender, EventArgs e)
        {
            CrawlResult result = crawler.Crawl(new Uri("http://www.harvard.edu")); //This is synchronous, it will not go to the next line until the crawl has completed 

            if (result.ErrorOccurred)
            {
                Console.WriteLine("Crawl of {0} completed with error: {1}", result.RootUri.AbsoluteUri, result.ErrorException.Message);
            }
            else Console.WriteLine("Crawl of {0} completed without error.", result.RootUri.AbsoluteUri);

        }

        /// <summary>
        /// Form1_Load is necessary
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Form1_Load(object sender, EventArgs e)
        {

        }
    }
}
