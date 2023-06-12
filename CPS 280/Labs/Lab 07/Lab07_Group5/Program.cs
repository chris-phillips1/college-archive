using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab07_Group5
{
	/// <summary>
	/// The base class for a Animal
	/// </summary>
	public abstract class Animal
	{
		private double weight;
		public double Weight { get => weight; set => weight = value; }
		public abstract String MakeSound();
		public abstract String petSound();
		public abstract String petFood();
	}

	/// <summary>
	/// This is a class for farm pigs 
	/// </summary>
	public class Pig : Animal
	{
		public override String MakeSound()
		{
			return "Oink";
		}
		public override string ToString()
		{
			return "Pig";
		}
		public override string petSound()
		{
			throw new NotImplementedException();
		}
		public override string petFood()
		{
			return "Pig Active";
		}
		public string timeRollingInMud()
		{
			return "2hrs";
		}
		public string timeOutOfPen()
		{
			return "6hrs";
		}
		public static bool operator <(Pig a1, Animal a2)
		{
			return a1.Weight < a2.Weight;
		}
		public static bool operator >(Pig a1, Animal a2)
		{
			return a1.Weight > a2.Weight;
		}
	}

	/// <summary>
	/// This is a class for a house cat
	/// </summary>
	public class Cat : Animal
	{
		public override string MakeSound()
		{
			return "Meow";
		}
		public override string ToString()
		{
			return "Cat";
		}
		public override string petSound()
		{
			return "*Purr*";
		}
		public override string petFood()
		{
			return "Fancy Fest";
		}
		public string jumpHeight()
		{
			return "1.2 ft";
		}
		public Boolean isSneaky()
		{
			return true;
		}
		public static bool operator <(Cat a1, Animal a2)
		{
			return a1.Weight < a2.Weight;
		}
		public static bool operator >(Cat a1, Animal a2)
		{
			return a1.Weight > a2.Weight;
		}
	}

	/// <summary>
	/// This is a house dog
	/// </summary>
	public class Dog : Animal
	{
		public override string MakeSound()
		{
			return "Ruff";
		}
		public override string ToString()
		{
			return "Dog";
		}
		public override string petSound()
		{
			return "*Pant* *Pant*";
		}
		public override string petFood()
		{
			return "Blue Buffalo";
		}
		public string timeTillWalk()
		{
			return "8hrs";
		}
		public Boolean isAGoodBoy()
		{
			return true;
		}
		public static bool operator <(Dog a1, Animal a2)
		{
			return a1.Weight < a2.Weight;
		}
		public static bool operator >(Dog a1, Animal a2)
		{
			return a1.Weight > a2.Weight;
		}
	}

	/// <summary>
	/// Uses the different animals 
	/// </summary>
	class Program
	{
		static void Main(string[] args)
		{
			Pig p = new Pig();
			p.Weight = 12;
			Console.WriteLine("{0}: says {1} and eats {2}, rolls in the mud for {3}, and out of the pen for {4}", p.ToString(), p.MakeSound(), p.petFood(), p.timeRollingInMud(), p.timeOutOfPen());

			Cat c = new Cat();
			c.Weight = 4;
			Console.WriteLine("{0}: says {1} and when pet says {2}, eats {3}, jumps to {4}, and is the cat sneaky? {5}", c.ToString(), c.MakeSound(), c.petSound(), c.petFood(), c.jumpHeight(), c.isSneaky() ? "Yes" : "No");

			Dog d = new Dog();
			d.Weight = 10;
			Console.WriteLine("{0}: says {1} and when pet says {2}, eats {3}, time to walk in {4}, and is he a good boy? {5}", d.ToString(), d.MakeSound(), d.petSound(), d.petFood(), d.timeTillWalk(), d.isAGoodBoy() ? "Yes he is!" : "No he isnt :(");

			Console.WriteLine("{0}", p < c ? "This is one big cat" : "This pig is bigger than the cat");

			Console.Read();
		}
	}
}
