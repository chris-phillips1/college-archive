using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab_08
{
    class Program
    {
        /// <summary>
        /// Compares users against previous logged in user. 
        /// Somewhat arbitrarily.
        /// </summary>
        /// <param name="args">Not used.</param>
        static void Main(string[] args)
        {
            String newUser = null, oldUser = null;

            // Greets our user
            newUser = String.Format("Hello, {0}!", Environment.UserName);
            Console.WriteLine(newUser);

           // Compares the two users info before authentication, displays appropriate message.
            if (oldUser.CompareTo(newUser) == 0)
                Console.WriteLine("They are the same person!");
            else
                Console.WriteLine("They are not the same person!");

            // Uses magic information from method to set authenticating user
            oldUser = GetUserCredentials(newUser);

            // Writes to console percent difference after authentication.
            Console.WriteLine("The percent difference is {0}.", Math.Abs(newUser.Length / newUser.CompareTo(oldUser)));

            Console.Read();
        }

        /// <summary>
        /// Magic method that does things.
        /// DO NOT ALTER!
        /// </summary>
        /// <param name="user"></param>
        /// <returns></returns>
        private static string GetUserCredentials (string user)
        {
            Random rnd = new Random();

            if (rnd.Next() % 2 == 0)
                return "Hello, stron1om!";
            else
                return user;
        }
    }
}
