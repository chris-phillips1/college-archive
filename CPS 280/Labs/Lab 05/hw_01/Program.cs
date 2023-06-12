using System;


namespace hw_01
{
    class Program
    {
        static void Main(string[] args)
        {
            // Some test data
            int[,] arr1 = { { 1, 5, 6 }, { 7, 9, 7 }, { 8, 10, 3 } };
            int[,] arr2 = { { 1, 5, 6 }, { 7, 9, 7 }, { 8, 10, 3 } };
            int[] vector = { 1, 2, 3 };

            // To hold the answers
            int[,] ans = new int [3,3];
            int[] outvector = new int[3];

            // Prove everything works
            Console.WriteLine("\n--- Vector ---\n");
            Print(vector);

            Console.WriteLine("\n--- Left Hand Matrix ---\n");
            Print(arr1);

            Console.WriteLine("\n--- Right Hand Matrix ---\n");
            Print(arr1);

            Console.WriteLine("\n--- Vector Multiplication ---\n");
            MultiplyVector(arr1, vector, ref outvector);
            Print(outvector);

            Console.WriteLine("\n-- Matrix Mutiplication ---\n");
            Multiply(arr1, arr2, ref ans);
            Print(ans);

            Console.WriteLine("\n--- Matrix Addition ---\n");
            Add(arr1, arr2, ref ans);
            Print(ans);

            Console.WriteLine("\n--- Matrix Subtraction ---\n");
            Subtract(arr1, arr2, ref ans);
            Print(ans);


            Console.Read(); // Don't close!!!
        }

        /// <summary>
        /// Add NxN matrix by NxN matrix
        /// </summary>
        /// <param name="arr1">Left hand matrix</param>
        /// <param name="arr2">Right hand matrix</param>
        /// <param name="ans">A NxN matrix to hold answer</param>
        private static void Add (int [,] arr1, int [,] arr2, ref int [,] ans)
        {
            for (int col = 0; col < arr1.GetLength(0); col++)
                for (int row = 0; row < arr1.GetLength(1); row++)
                    ans [col,row] = arr1[col, row] + arr2[col,row];
        }

        /// <summary>
        /// Subtract NxN by NxN matrix
        /// </summary>
        /// <param name="arr1">Left hand matrix</param>
        /// <param name="arr2">Right hand matrix</param>
        /// <param name="ans">A NxN matrix to hold answer</param>
        private static void Subtract(int[,] arr1, int[,] arr2, ref int[,] ans)
        {
            for (int col = 0; col < arr1.GetLength(0); col++)
                for (int row = 0; row < arr1.GetLength(1); row++)
                    ans[col, row] = arr1[col, row] - arr2[col, row];
        }

        /// <summary>
        /// Multiply NxN by NxN matrix
        /// </summary>
        /// <param name="arr1">Left hand matrix</param>
        /// <param name="arr2">Right hand matrix</param>
        /// <param name="ans">A NxN matrix to hold answer</param>
        private static void Multiply(int[,] arr1, int[,] arr2, ref int[,] ans)
        {
            for (int row = 0; row < arr1.GetLength(1); row++)
                for (int col = 0; col < arr1.GetLength(0); col++)
                    for (int k = 0; k < arr1.GetLength(0); k++)
                        ans[row, col] = ans[row, col] + arr1[row, k] * arr2[k, col];

        }

        /// <summary>
        /// Multiple a 1xN vector by a NxN matrix
        /// </summary>
        /// <param name="arr1">Matrix to be multiplied</param>
        /// <param name="vector">Vector to be multiplied</param>
        /// <param name="Outvector">Vector to hold results</param>
        private static void MultiplyVector (int [,] arr1, int [] vector, ref int [] Outvector)
        {
            for (int row = 0; row < arr1.GetLength(1); row++)
                    for (int k = 0; k < arr1.GetLength(0); k++)
                        Outvector[row] = Outvector[row] + arr1[row, k] * vector[k];
        }

       /// <summary>
       /// Print a matrix attactively
       /// </summary>
       /// <param name="arr">The matrix to be printed</param>
       private static void Print (int [,] arr)
        {
            for (int col = 0; col < arr.GetLength(0); col++)
            {
                Console.WriteLine();
                for (int row = 0; row < arr.GetLength(1); row++)
                {
                    if (row != 0) Console.Write(", ");
                    Console.Write("{0,3}",arr[col, row]);
                }
            }
        }

        /// <summary>
        /// Some overloading of the print method for a vector.
        /// </summary>
        /// <param name="vector">Vector to be printed</param>
        private static void Print(int [] vector)
        {
            foreach (int i in vector)
            {
                Console.WriteLine(i);
            }
        }
    }
}
