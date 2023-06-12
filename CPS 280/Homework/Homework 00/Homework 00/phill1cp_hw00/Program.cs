// Name: Chris Phillips
// Course: CPS 280A
// Date: 9/13/18
// Assignment: Homework OO
using System;
using System.Diagnostics;

namespace phill1cp_HW00
{
    class Program
    {
        /// <summary>
        /// The main method is where all function calls take place, none of the actual logic can be found in this method.
        /// </summary>
        /// <param name="args"></param>
        static void Main(string[] args)
        {
            int[] arr = new int[100000];

            printArray(arr);
            generateArray(arr);
            arr = sortArray(arr);
            swapValues(arr);

            Console.ReadLine();
        }

        /// <summary>
        /// The printArray methond will loop through an array and print it to the screen. 
        /// To make it look nicer, it prints 10 numbers then goes to the next line.
        /// </summary>
        /// <param name="myArr"> This is the array that will be printed to the screen. </param>
        static void printArray(int[] myArr)
        {
            Console.WriteLine("Begin Printing Array");
            for (int i = 0; i < myArr.Length; i++)
            {
                if (i % 10 == 0)
                {
                    Console.WriteLine(myArr[i] + "\n");
                }
                else
                {
                    Console.Write(myArr[i] + " ");
                }
            }
        }

        /// <summary>
        /// The generateArray method will loop through an array and insert a random number 
        /// between 1 and 100 into each of its spaces.
        /// </summary>
        /// <param name="myArr"> This is the array that will have the numbers inserted into it. </param>
        static void generateArray(int[] myArr)
        {
            Random rand = new Random();
            for (int i = 0; i < myArr.Length; i++)
            {
                int randNum = rand.Next(1, 100);
                myArr[i] = randNum;
            }
        }

        /// <summary>
        /// The sortArray method uses an insertion sort to sort the provided array. 
        /// This method also keeps track of the time that it takes to sort and will display this number upon completion.
        /// </summary>
        /// <param name="myArr"> This is the array to be sorted. </param>
        /// <returns> The sort is not done "in-place" so the sorted array is returned. </returns>
        static int[] sortArray(int[] myArr)
        {
            Stopwatch stopWatch = new Stopwatch();
            stopWatch.Start();

            //insertion sort
            for (int i = 0; i < myArr.Length - 1; i++)
            {
                for (int j = i + 1; j > 0; j--)
                {
                    if (myArr[j - 1] > myArr[j])
                    {
                        int temp = myArr[j - 1];
                        myArr[j - 1] = myArr[j];
                        myArr[j] = temp;
                    }
                }
            }

            stopWatch.Stop();
            TimeSpan ts = stopWatch.Elapsed;

            //Determining the time it took to sort
            string elapsedTime = "";
            if (ts.Minutes > 0)
            {
                elapsedTime = String.Format("{0:00}:{1:00}.{2:00} minutes",
                    ts.Minutes, ts.Seconds, ts.Milliseconds / 10);
            }
            else
            {
                elapsedTime = String.Format("{0:00}.{1:00} seconds",
                    ts.Seconds, ts.Milliseconds / 10);
            }

            Console.WriteLine("Total Time to Sort: " + elapsedTime);
            return myArr;
        }


        /// <summary>
        /// This method will prompt the user to select the indicies of the elements
        /// that they would like to swap and will swap those elementst.
        /// </summary>
        /// <param name="myArr"> This is the array the user will be selecting indicies for. </param>
        static void swapValues(int[] myArr)
        {
            int index1, index2, temp;

            Console.Write("\nEnter the index of the first item to swap: ");
            index1 = int.Parse(Console.ReadLine());

            Console.Write("Enter the index of the second item to swap: ");
            index2 = int.Parse(Console.ReadLine());


            Console.WriteLine("myArr[{0}] now equals {1}", index1, myArr[index2]);
            temp = myArr[index1];
            myArr[index1] = myArr[index2];
            myArr[index2] = temp;
            Console.WriteLine("myArr[{0}] now equals {1}", index2, temp);
        }
    }
}
