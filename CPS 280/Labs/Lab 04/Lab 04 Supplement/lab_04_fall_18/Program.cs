using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;

namespace lab_04_fall_18
{
    class Program
    {
        static void Main(string[] args)
        {
            List<String> aiw = new List<string>();
            try
            {
                // Read in all the words from the file
                StreamReader cin = new StreamReader("words_alpha.txt");
                String s = cin.ReadToEnd();
                String[] sa = s.Split('\n');
                for (int i = 0; i < sa.Length; i++)
                    sa[i] = sa[i].Trim(); // stupid unix carriage return new lines need cleaned up

                // Run the method
                List < String > rslt = new List<string>();
                Stopwatch sw = new Stopwatch();
                sw.Start();
                    //
                    // do whatever you need to do here
                    //
                sw.Stop();

                // Time and word count
                Console.WriteLine(rslt.Count + " elements were found in " + sw.ElapsedMilliseconds + " milliseconds.");
                Console.Read();
            }
            catch (Exception e)
            {
                Console.WriteLine("File not found!");
                Console.Read();
            }


        }

        /// <summary>
        /// A method that uses brute force (looks through all possibilities to find words in the dict.
        /// </summary>
        /// <param name="rslt">A list that contains found words.</param>
        /// <param name="dict">The valid words to be found. </param>
        static void findWords (ref List<string> rslt, String [] dict)
        {
            //
            // your code goes here
            //
        }
    }
}
