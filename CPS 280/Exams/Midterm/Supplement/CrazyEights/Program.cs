using System;
using System.Collections.Generic;
using System.Linq;


namespace CrazyEights
{
    public enum Suit { HEARTS, DIAMONDS, SPADES, CLUBS };
    public enum Face { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE };

    class Program
    {
        private static Stack<Card> deck = new Stack<Card>();
        private static Stack<Card> played = new Stack<Card>();
        private static Suit namedSuit;


        /// <summary>
        /// THIS METHOD MAY BE BUGGY USE AT OWN RISK
        /// </summary>
        /// <param name="args">Not used.</param>
        static void Main(string[] args)
        {
            List<Player> players = new List<Player>();
            int playTo = 100;

            players.Add(new Stron1om("Oliver"));
            players.Add(new Stron1om("Bob"));

            // Play to given score
            bool playToReached = false;
            while (!playToReached)
            {
                // Empty remaining deck and regen
                deck.Clear();
                foreach (Suit s in (Suit[])Enum.GetValues(typeof(Suit)))
                {
                    foreach (Face f in (Face[])Enum.GetValues(typeof(Face)))
                    {
                        deck.Push(new Card(s, f));
                    }
                }
                Shuffle(ref deck);

                // Deal 7 cards to each player
                foreach (Player p in players)
                {
                    p.TakeHand(Deal());
                }

                // Draw first card for played pile
                played.Push(deck.Pop());


                bool winner = false;
                int passcnt = 0;
                // No one has one and everyone hasn't passed.
                while (!winner && passcnt < players.Count)
                {
                    // Everyone makes a play or passes, passing is represented by playing null
                    foreach (Player p in players)
                    {
                        Card c = p.Play();

                        if (c == null)
                        {
                            Console.WriteLine("{0} passed", p);
                            passcnt++;
                        } else {
                            passcnt = 0;
                            Card tmp = played.Peek();
                            played.Push(c);
                            if (played.Peek().F == Face.EIGHT)
                            {
                                namedSuit = p.NameSuit();
                            }

                            Console.WriteLine(p + " played " + played.Peek() + " on " + tmp + " and has " + p.CardsRemaining() + " cards remaining.");
                            Console.Read();


                            if (p.CardsRemaining() == 0)
                            {
                                Console.WriteLine("{0} is the winner", p);
                                winner = true;
                                break;
                            }
                        }


                    }

                }
                // Did everyone pass? Game is considered draw.
                // No points awarded.
                if (passcnt == players.Count)
                {
                    Console.WriteLine("Draw!!");
                } else
                {
                    int score = 0;
                    foreach (Player p in players)
                    {
                        score += calcPoints(p.ShowCards());

                    }

                    foreach (Player p in players)
                    {
                        if (p.CardsRemaining() == 0)
                        {
                            p.AssignScore(score);
                            if (p.GetScore() >= playTo)
                                playToReached = true;
                            break;
                        }
                    }
                }


            }
          

            Console.Read();

        }

        /// <summary>
        /// Basic point calculation per scoring rules.
        /// </summary>
        /// <param name="cards">A list of remaining cards in a player's hand.</param>
        /// <returns>Point value of given cards.</returns>
        public static int calcPoints(List<Card> cards)
        {
            int points = 0;
            foreach (Card c in cards)
            {
                if (c.F == Face.EIGHT)
                {
                    points += 50;
                }
                else if (c.F == Face.JACK || c.F == Face.QUEEN || c.F == Face.KING || c.F == Face.ACE)
                {
                    points += 10;
                } else if (c.F == Face.TWO)
                {
                    points += 2;
                }
                else if (c.F == Face.THREE)
                {
                    points += 2;
                }
                else if (c.F == Face.FOUR)
                {
                    points += 2;
                }
                else if (c.F == Face.FIVE)
                {
                    points += 2;
                }
                else if (c.F == Face.SIX)
                {
                    points += 2;
                }
                else if (c.F == Face.SEVEN)
                {
                    points += 2;
                }
                else if (c.F == Face.EIGHT)
                {
                    points += 2;
                }
                else if (c.F == Face.NINE)
                {
                    points += 2;
                }
                else if (c.F == Face.TEN)
                {
                    points += 2;
                }

            }

            return points;
        }

        /// <summary>
        /// Removes 7 cards from deck and returns them.
        /// </summary>
        /// <returns>7 cards from the deck.</returns>
        public static List<Card> Deal ()
        {
            List<Card> c = new List<Card>();
            for (int i = 0; i < 7; i++)
                c.Add(deck.Pop());
            return c;
        }

        /// <summary>
        /// Return a card from the top of the deck.
        /// Throws generic exception if there isn't a card to draw.
        /// </summary>
        /// <returns>Card or exception if no cards.</returns>
        public static Card Draw()
        {
            if (deck.Count() == 0)
                throw new Exception();
            return deck.Pop();
        }

        /// <summary>
        /// Pretty simple play mechanics.
        /// Suit or face must match top of discard pile, or 8 must be played.
        /// </summary>
        /// <param name="c">The card a player is trying to play.</param>
        /// <returns>true if valid, false otherwise</returns>
        public static bool isValidPlay(Card c)
        {
            if ((played.Peek().F == Face.EIGHT && namedSuit == c.S) || (c.F == played.Peek().F || c.S == played.Peek().S))
                return true;

            return false;
        }

        /// <summary>
        /// What is the top card on the discard pile?
        /// </summary>
        /// <returns>Top card of discard pile.</returns>
        public static Card getTopCard ()
        {
            return played.Peek();
        }

        /// <summary>
        /// Shuffle the deck
        /// </summary>
        /// <param name="deck">The deck of cards to shuffle.</param>
        public static void Shuffle(ref Stack<Card> deck)
        {
            Random rnd = new Random();
            Card [] deckArr = deck.ToArray();
            deck.Clear();
            foreach (Card c in deckArr.OrderBy(x => rnd.Next()))
                deck.Push(c);
        }
    }

    /// <summary>
    /// Representation of traditional playing card.
    /// </summary>
    public class Card
    {
        private Suit s;
        private Face f;

        /// <summary>
        /// Constructor, note there is no default.
        /// </summary>
        /// <param name="s">Suit of card.</param>
        /// <param name="f">Face value of card.</param>
        public Card(Suit s, Face f)
        {
            this.S = s;
            this.F = f;
        }

        // Simple way to allow for getting and setting of suit/face.
        // you can do something like c.s == Suit.SPADES;
        public Suit S { get => s; private set => s = value; }
        public Face F { get => f; private set => f = value; }

        /// <summary>
        /// Changes the way cards are represented in when printed.
        /// </summary>
        /// <returns>String representation of card.</returns>
        public override string ToString()
        {
            return this.F.ToString() + " of " + this.S.ToString();
        }
    }

    public abstract class Player
    {
        public abstract Card Play();
        public abstract Suit NameSuit();
        public abstract int CardsRemaining();
        public abstract void AssignScore(int points);
        public abstract int GetScore();
        public abstract List<Card> ShowCards();
        public abstract void TakeHand(List<Card> cards);
    }

    /// <summary>
    /// My play class.
    /// </summary>
    class Stron1om : Player
    {
        /// <summary>
        /// Constructor that accepts a hand of cards.
        /// </summary>
        /// <param name="h">A hand of cards.</param>
        public Stron1om (List<Card> h)
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Accept dealt hand.
        /// </summary>
        /// <param name="cards">The dealt hand.</param>
        public override void TakeHand (List<Card> cards)
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Constructor that requires just a name.
        /// </summary>
        /// <param name="name">The player's name.</param>
        public Stron1om(string name)
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Representation of the player by their name.
        /// </summary>
        /// <returns>Player's name.</returns>
        public override string ToString()
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// The player will show the hand. Used at the end of a round to calculate final score.
        /// </summary>
        /// <returns></returns>
        public override List<Card> ShowCards ()
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// The number of current points the player has.
        /// </summary>
        /// <returns>Player's current score.</returns>
        public override int GetScore()
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Adds points to player's self maintained total.
        /// </summary>
        /// <param name="points">Number of points to add to total.</param>
        public override void AssignScore (int points)
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Constructor with new hand and name. Used at beginning of game.
        /// </summary>
        /// <param name="h">Cards dealt to player.</param>
        /// <param name="name">The chosen name of player.</param>
        public Stron1om(List<Card> h, string name)
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Makes decision of 
        /// </summary>
        /// <returns>The name of suit when the player is allowed to chose.</returns>
        public override Suit NameSuit()
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Gets the amount of cards in the player's hand.
        /// </summary>
        /// <returns>Count of cards in hand.</returns>
        public override int CardsRemaining ()
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Get a card from the draw pile.
        /// </summary>
        /// <returns>Card.</returns>
        public Card Draw ()
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Makes decision of what to do on Player's turn
        /// </summary>
        /// <returns>Card or null</returns>
        public override Card Play ()
        {
            throw new NotImplementedException();

        }

      
}
