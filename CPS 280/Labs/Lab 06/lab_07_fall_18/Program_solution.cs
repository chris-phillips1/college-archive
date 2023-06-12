using System;
using System.Collections.Generic;


namespace lab_07_fall_18_solution
{
    class Program
    {
        /// <summary>
        /// Implement all your code in the MyClass class, don't mess with this method. 
        /// </summary>
        /// <param name="args"></param>
        static void Main(string[] args)
        {
            // Creating the List to get things started
            List<int> arr = new List<int>();
            Random r = new Random();
            for (int i = 0; i < 10; i++)
                arr.Add(r.Next(1000));

            MyClass mc = new MyClass(ref arr); // default initialization 
            mc.Print(); // display elements space seperated on one line
            mc.Sort();  // sort ascending
            mc.Print(); // display elements space seperated on one line
            Console.WriteLine(mc.Max()); // Greatest element
            Console.WriteLine(mc.Min()); // Least element

            // proving that you didn't mess with the original
            foreach (int i in arr)
                Console.Write("{0} ", i);
            Console.WriteLine();
            mc.Print();

            Console.Read(); // keep the stupid console from closing too quickly
        }
    }

    /// <summary>
    /// Class the extends some attributes of the List type
    /// </summary>
    class MyClass
    {
        List<int> myarr = new List<int>();

        /// <summary>
        /// Default initializer
        /// </summary>
        /// <param name="arr">List to prime this thing</param>
        public MyClass(ref List<int> arr)
        {
            foreach (int i in arr)
                myarr.Add(i);
        }

        /// <summary>
        /// Cheap and dirty sort. It already exist I am not rewriting it.
        /// </summary>
        public void Sort ()
        {
            myarr.Sort();
        }

        /// <summary>
        /// Return the largest value in the list.
        /// </summary>
        /// <returns>Largest value</returns>
        public int Max ()
        {
            int max = myarr[0];
            foreach(int i in myarr)
            {
                if (i > max)
                    max = i;
            }

            return max;
        }

        /// <summary>
        /// Finds the smallest value in the list.
        /// </summary>
        /// <returns>Smallest value.</returns>
        public int Min()
        {
            int max = myarr[0];
            foreach (int i in myarr)
            {
                if (i < max)
                    max = i;
            }

            return max;
        }

        /// <summary>
        /// Display the list as a single line of space seperated values.
        /// </summary>
        public void Print ()
        {
            foreach(int i in myarr)
                Console.Write("{0} ", i);
            Console.WriteLine();
        }


    }
}
