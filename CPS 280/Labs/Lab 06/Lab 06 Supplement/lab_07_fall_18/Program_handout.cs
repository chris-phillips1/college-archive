using System;
using System.Collections.Generic;


namespace lab_07_fall_18
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

    class MyClass
    {

        // Code goes here

    }
}
