// Names: Chris Phillips, Nick Clark, Kevin Bell, Mitchell Tecklenburg
// Course: CPS 280
// Assignment: Lab 08
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace group5_lab_08
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
            try
            {
                if (oldUser.CompareTo(newUser) == 0)
                    Console.WriteLine("They are the same person!");
                else
                    Console.WriteLine("They are not the same person!");
            }
            catch (NullReferenceException e)
            {
                // If there is no oldUser, print out that the variable is null
                Console.WriteLine("No authentication occurs. One of the user variables is null.");
            }

            // Uses magic information from method to set authenticating user
            oldUser = GetUserCredentials(newUser);

            // Writes to console percent difference after authentication.
            try
            {
                Console.WriteLine("The percent difference is {0}.", Math.Abs(newUser.Length / newUser.CompareTo(oldUser)));
            }
            catch(DivideByZeroException e)
            {
                // If there is no difference, print out this fact
                Console.WriteLine("They are similar. When comparing newUser to oldUser, they were similar.");
            }
            catch(NullReferenceException e)
            {
                // If there is no newUser, print out that the variable is null
                Console.WriteLine("No difference has been calculated. One of the users is null.");
            }

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
                return "Hello, phill1cp!";
            else
                return user;
        }
    }
}
