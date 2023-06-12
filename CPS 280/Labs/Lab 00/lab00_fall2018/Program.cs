/* 
 * Oliver Strong
 * Lab 00
 * 08/30/2018
 * A program to demonstrate style and convention while calculating average
 * of digits.
 */
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
// ^^^^ remove the uneeded imports 

namespace lab00_fall2018
{
    class Program
    {
        /// <summary>
        /// All methods need a brief discription here.
        /// </summary>
        /// <param name="args">Command line args, in case we want to use them</param>
        static void Main(string[] args)
        {
            // declare/initialize at the top of the method scope
            bool hasNumber = false;
            double average;
            int myNumber = 0;

            // get an integer, loop until it happens
            while (!hasNumber) // does NOT use redundant hasNumber != true
            {
                try
                {
                    Console.Write("Input an integer: "); // NO newline here
                    myNumber = int.Parse(Console.ReadLine()); // camel case naming convention
                    hasNumber = true;
                }
                catch (Exception e) // Unused exception
                {
                    Console.WriteLine("That was not a valid number!");
                }
            }

            // use math to calculate the average of the digits, by stripping one digit at a time
            int remainder, sum = 0, tmp = myNumber;
            while (tmp != 0)
            {
                remainder = tmp % 10;
                tmp = tmp / 10;
                sum = sum + remainder;
            }
            average = (double)sum / myNumber.ToString().Length;

            // print the average
            Console.WriteLine("The average of the digits in {0} is {1}.", myNumber, average);

            // Holds the console window open.
            Console.Write("Press Enter/Return to end."); // Prompt, NO newline
            Console.Read();
        }
    }
}
