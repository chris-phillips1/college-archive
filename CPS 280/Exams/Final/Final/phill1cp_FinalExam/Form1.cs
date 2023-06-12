// Name: Chris Phillips
// Date: 11 December 2018
// Course: CPS 280
// Assignment: Final Exam
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace phill1cp_FinalExam
{
    public partial class Form1 : Form
    {
        List<Button> buttons = new List<Button>(); //All 27 buttons used to play 3D tic-tac-toe
        List<Button> buttonsUsed = new List<Button>(); //All buttons that have currently been played on
        bool gameOver = false;
        Game g = new Game("X");


        public Form1()
        {
            InitializeComponent();
            AddButtons(); //Adds all the tic-tac-toe buttons to the list
            g.Start(label4, label5); //Start the game by updating the "turn" and "status" labels
        }

        /// <summary>
        /// The AddButtons method adds the 27 buttons necessary to play tic-tac-toe to the list, in the correct order.
        /// </summary>
        public void AddButtons()
        {
            buttons.Add(new Button()); //To fix the offset when referencing later
            buttons.Add(button1);
            buttons.Add(button2);
            buttons.Add(button3);
            buttons.Add(button4);
            buttons.Add(button5);
            buttons.Add(button6);
            buttons.Add(button7);
            buttons.Add(button8);
            buttons.Add(button9);
            buttons.Add(button10);
            buttons.Add(button11);
            buttons.Add(button12);
            buttons.Add(button13);
            buttons.Add(button14);
            buttons.Add(button15);
            buttons.Add(button16);
            buttons.Add(button17);
            buttons.Add(button18);
            buttons.Add(button19);
            buttons.Add(button20);
            buttons.Add(button21);
            buttons.Add(button22);
            buttons.Add(button23);
            buttons.Add(button24);
            buttons.Add(button25);
            buttons.Add(button26);
            buttons.Add(button27);
        }

        /// <summary>
        /// The ButtonClick method is used by all the buttons in the form and is the main logic to play the game. The 
        /// method will allow the current player to click any button that is not already taken, will check to see if there's a win,
        /// and will handle the game accordingly.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ButtonClick(object sender, EventArgs e)
        {
            Button currentBtn = (Button)sender;

            if (currentBtn.Text == "" && !gameOver)
            {
                currentBtn.Text = g.GetPlayer();
                buttonsUsed.Add(currentBtn);

                gameOver = g.CheckWin(buttons, buttonsUsed, label5);

                if(!gameOver)
                    g.UpdatePlayer(label4);
            }
        }


        /// <summary>
        /// This method will restart the game by resetting the gameover variable to false, and calling the restart method.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void RestartButton(object sender, EventArgs e)
        {
            gameOver = false;
            g.Restart(buttonsUsed, label4, label5);
        }

        /// <summary>
        /// This method is used for debugging.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void LogButton(object sender, EventArgs e)
        {
            Console.WriteLine("{0} total buttons", buttonsUsed.Count);
            foreach (Button b in buttonsUsed)
            {
                Console.WriteLine("A button with the name: {0} and the value: {1}", b.Name, b.Text);
            }

            foreach (Button b in buttons)
            {
                Console.WriteLine(b.Name);
            }
        }

    }

    public class Game
    {
        private string currentPlayer;

        public Game(string startingPlayer)
        {
            this.currentPlayer = startingPlayer;
        }

        /// <summary>
        /// The Start method will update the turn and status labels.
        /// </summary>
        /// <param name="turnLabel">The label that indicates which players turn it is.</param>
        /// <param name="statusLabel">The label that indicates the current status of the game.</param>
        public void Start(Label turnLabel, Label statusLabel)
        {
            this.UpdateTurn(turnLabel);
            this.UpdateStatus(statusLabel, "In Progress");
        }

        /// <summary>
        /// The Evaluate method will check all possible win combinations and return the winning player
        /// if there is a win, otherwise it will return null
        /// </summary>
        /// <param name="buttons">A list of all the buttons in the game.</param>
        /// <returns>The player that has won the game, otherwise, null</returns>
        public string Evaluate(List<Button> buttons)
        {
            string winner = null;

            //First board

            //Horizontals
            if (buttons[1].Text == currentPlayer && buttons[2].Text == currentPlayer && buttons[3].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[4].Text == currentPlayer && buttons[5].Text == currentPlayer && buttons[6].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[7].Text == currentPlayer && buttons[8].Text == currentPlayer && buttons[9].Text == currentPlayer)
                winner = currentPlayer;

            //Verticals
            if (buttons[1].Text == currentPlayer && buttons[4].Text == currentPlayer && buttons[7].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[2].Text == currentPlayer && buttons[5].Text == currentPlayer && buttons[8].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[3].Text == currentPlayer && buttons[6].Text == currentPlayer && buttons[9].Text == currentPlayer)
                winner = currentPlayer;

            //Diagonals
            if (buttons[1].Text == currentPlayer && buttons[5].Text == currentPlayer && buttons[9].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[3].Text == currentPlayer && buttons[5].Text == currentPlayer && buttons[7].Text == currentPlayer)
                winner = currentPlayer;


            //Second board

            //Horizontals
            if (buttons[10].Text == currentPlayer && buttons[11].Text == currentPlayer && buttons[12].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[13].Text == currentPlayer && buttons[14].Text == currentPlayer && buttons[15].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[16].Text == currentPlayer && buttons[17].Text == currentPlayer && buttons[18].Text == currentPlayer)
                winner = currentPlayer;

            //Verticals
            if (buttons[10].Text == currentPlayer && buttons[13].Text == currentPlayer && buttons[16].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[11].Text == currentPlayer && buttons[14].Text == currentPlayer && buttons[17].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[12].Text == currentPlayer && buttons[15].Text == currentPlayer && buttons[18].Text == currentPlayer)
                winner = currentPlayer;

            //Diagonals
            if (buttons[10].Text == currentPlayer && buttons[14].Text == currentPlayer && buttons[18].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[12].Text == currentPlayer && buttons[14].Text == currentPlayer && buttons[16].Text == currentPlayer)
                winner = currentPlayer;

            //Third board

            //Horizontals
            if (buttons[19].Text == currentPlayer && buttons[20].Text == currentPlayer && buttons[21].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[22].Text == currentPlayer && buttons[23].Text == currentPlayer && buttons[24].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[25].Text == currentPlayer && buttons[26].Text == currentPlayer && buttons[27].Text == currentPlayer)
                winner = currentPlayer;

            //Verticals
            if (buttons[19].Text == currentPlayer && buttons[22].Text == currentPlayer && buttons[25].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[20].Text == currentPlayer && buttons[23].Text == currentPlayer && buttons[26].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[21].Text == currentPlayer && buttons[24].Text == currentPlayer && buttons[27].Text == currentPlayer)
                winner = currentPlayer;

            //Diagonals
            if (buttons[19].Text == currentPlayer && buttons[23].Text == currentPlayer && buttons[27].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[21].Text == currentPlayer && buttons[23].Text == currentPlayer && buttons[25].Text == currentPlayer)
                winner = currentPlayer;

            //All boards

            //Horizontal Depth
            if (buttons[1].Text == currentPlayer && buttons[10].Text == currentPlayer && buttons[19].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[2].Text == currentPlayer && buttons[11].Text == currentPlayer && buttons[20].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[3].Text == currentPlayer && buttons[12].Text == currentPlayer && buttons[21].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[4].Text == currentPlayer && buttons[13].Text == currentPlayer && buttons[22].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[5].Text == currentPlayer && buttons[14].Text == currentPlayer && buttons[23].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[6].Text == currentPlayer && buttons[15].Text == currentPlayer && buttons[24].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[7].Text == currentPlayer && buttons[16].Text == currentPlayer && buttons[25].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[8].Text == currentPlayer && buttons[17].Text == currentPlayer && buttons[26].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[9].Text == currentPlayer && buttons[18].Text == currentPlayer && buttons[27].Text == currentPlayer)
                winner = currentPlayer;

            //Vertical Multi-layer
            if (buttons[1].Text == currentPlayer && buttons[13].Text == currentPlayer && buttons[25].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[7].Text == currentPlayer && buttons[13].Text == currentPlayer && buttons[19].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[2].Text == currentPlayer && buttons[14].Text == currentPlayer && buttons[26].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[8].Text == currentPlayer && buttons[14].Text == currentPlayer && buttons[20].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[3].Text == currentPlayer && buttons[15].Text == currentPlayer && buttons[27].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[9].Text == currentPlayer && buttons[15].Text == currentPlayer && buttons[21].Text == currentPlayer)
                winner = currentPlayer;

            //Horizontal Multi-layer
            if (buttons[1].Text == currentPlayer && buttons[11].Text == currentPlayer && buttons[21].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[3].Text == currentPlayer && buttons[11].Text == currentPlayer && buttons[19].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[4].Text == currentPlayer && buttons[14].Text == currentPlayer && buttons[24].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[6].Text == currentPlayer && buttons[14].Text == currentPlayer && buttons[22].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[7].Text == currentPlayer && buttons[17].Text == currentPlayer && buttons[27].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[9].Text == currentPlayer && buttons[17].Text == currentPlayer && buttons[25].Text == currentPlayer)
                winner = currentPlayer;


            //Diagonal Multi-layer
            if (buttons[1].Text == currentPlayer && buttons[14].Text == currentPlayer && buttons[27].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[3].Text == currentPlayer && buttons[14].Text == currentPlayer && buttons[25].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[7].Text == currentPlayer && buttons[14].Text == currentPlayer && buttons[21].Text == currentPlayer)
                winner = currentPlayer;
            if (buttons[9].Text == currentPlayer && buttons[14].Text == currentPlayer && buttons[19].Text == currentPlayer)
                winner = currentPlayer;

            return winner;
        }

        /// <summary>
        /// The CheckWin method will evaluate the board to see if anyone has won. If so, the status
        /// label will be updated with the winner being shown. Otherwise, the label will declare a draw.
        /// </summary>
        /// <param name="buttons">A list of all the buttons in the game.</param>
        /// <param name="used">A list of all the buttons that are currently used.</param>
        /// <param name="statusLabel">The label that indicates the status of the game.</param>
        /// <returns>True if it's gameover, False otherwise </returns>
        public bool CheckWin(List<Button> buttons, List<Button> used, Label statusLabel)
        {
            bool gameover = false;

            string winner = this.Evaluate(buttons);

            if (winner != null)
            {
                this.UpdateStatus(statusLabel, winner + " wins!");
                gameover = true;
            }
            else if (winner == null && used.Count == 27)
            {
                this.UpdateStatus(statusLabel, "It's a draw!");
                gameover = true;
            }

            return gameover;
        }

        /// <summary>
        /// The Restart method will reset all buttons on the board and restart the game.
        /// </summary>
        /// <param name="used">A list of all the buttons currently being used.</param>
        /// <param name="turnLabel">The label that indicates which players turn it is.</param>
        /// <param name="statusLabel">The label that indicates the status of the game.</param>
        public void Restart(List<Button> used, Label turnLabel, Label statusLabel)
        {
            foreach (Button b in used)
                b.Text = "";

            used.Clear();
            Start(turnLabel, statusLabel);
        }

        /// <summary>
        /// A method to get the currentPlayer variable
        /// </summary>
        /// <returns>The current player</returns>
        public string GetPlayer()
        {
            return currentPlayer;
        }

        /// <summary>
        /// Updates the current player to be the opposite of it's current value. Then
        /// updates the turn label to reflect this update.
        /// </summary>
        /// <param name="turnLabel">The label that indicates which players turn it is.</param>
        public void UpdatePlayer(Label turnLabel)
        {
            currentPlayer = currentPlayer == "X" ? "O" : "X";
            UpdateTurn(turnLabel);
        }

        /// <summary>
        /// Updates the label that displays which players turn it is.
        /// </summary>
        /// <param name="turnLabel">The label that indicates which players turn it is.</param>
        public void UpdateTurn(Label turnLabel)
        {
            turnLabel.Text = "Turn: " + currentPlayer;
        }

        /// <summary>
        /// Updates the label that displays the current status of the game.
        /// </summary>
        /// <param name="statusLabel">The label that indicates the status of the game.</param>
        /// <param name="status">The status that will be set in the label.</param>
        public void UpdateStatus(Label statusLabel, string status)
        {
            statusLabel.Text = "Status: " + status;
        }
    }
}
