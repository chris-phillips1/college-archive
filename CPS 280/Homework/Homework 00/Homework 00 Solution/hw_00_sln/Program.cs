using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace hw_00_sln
{
    class Program
    {
        // Allows for easy tweaking of array size
        private static int MAXSIZE = 10000;
        private static int MAXRAND = 100000;

        static void Main(string[] args)
        {
            int[] arr = new int [MAXSIZE];
            FillRandom(ref arr);

            Stopwatch sw = new Stopwatch();
            sw.Start();
            Sort(ref arr);
            sw.Stop();
            Console.WriteLine(sw.Elapsed);

            //Print(arr); // Use this to check sort

            Console.Read();
        }

        /// <summary>
        /// Simple array insertion sort
        /// </summary>
        /// <param name="arr">Array of integers to be sorted.</param>
        static void Sort (ref int [] arr)
        {
            for (int i = 0; i < arr.Length - 1; i++)
            {
                for (int j = i + 1; j > 0; j--)
                {
                    if (arr[j - 1] > arr[j])
                    {
                        Swap(ref arr, j - 1, j);
                    }
                }
            }
        }

        /// <summary>
        /// Swaps to elements in an array
        /// </summary>
        /// <param name="arr">Array to have elements swapped</param>
        /// <param name="i">Index of first element</param>
        /// <param name="j">Index of second element</param>
        static void Swap (ref int [] arr, int i, int j)
        {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        /// <summary>
        /// Prints an array to console
        /// </summary>
        /// <param name="arr">An integer array</param>
        static void Print (int [] arr)
        {
            foreach (int i in arr)
                Console.WriteLine(i);
        }

        /// <summary>
        /// Fills an integer array with Random values
        /// </summary>
        /// <param name="arr">The array to be filled</param>
        static void FillRandom (ref int [] arr)
        {
            Random r = new Random();

            for (int i = 0; i < arr.Length; ++i)
                arr[i] = (int)(r.NextDouble()*MAXRAND);
        }
    }
}
