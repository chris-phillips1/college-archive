// Name: Chris Phillips
// Course: CPS 480
// Date: 9/26/18
// Project: TicTacToe AI that gets progressively better, finally using the minimax algorithm for unbeatable AI

using UnityEngine;
using UnityEngine.UI;
using System.Collections;
using System.Collections.Generic;

// Used to have two separate player objects that can have modified text
[System.Serializable]
public class Player 
{
	public Image panel;
	public Text text;
}

// Used for changing the color for whose turn it is
[System.Serializable]
public class PlayerColor 
{
	public Color panelColor;
	public Color textColor;
}

// Used for Minimax algorithm, mainly traversing the tree
public class Move 
{
    public int index;
    public int score;

}

public class GameController : MonoBehaviour {

    //This contains a list of all of the buttons and their text properties
    public Text[] buttonList;

    //These are various objects for the end-game screen
    public GameObject gameOverPanel;
    public Text gameOverText;
    public GameObject restartButton;

    // These are used for switching the active player colors and displaying whose turn it is
	public Player playerX;
	public Player playerO;
	public PlayerColor activePlayerColor;
	public PlayerColor inactivePlayerColor;

    // These references are used for some of the logic in the later functions
    private string playerSide;
    private int[] currentBoard = new int[9];
    private int moveCount;
    private int numOccupiedSpaces = 0;

    // This fuction is called when the game initially runs. It sets up all the basics of the board.
    void Awake ()
    {
		SetPlayerColors(playerX, playerO);
        SetGameControllerReferenceOnButtons();
        playerSide = "X";
        gameOverPanel.SetActive(false);
        moveCount = 0;
        restartButton.SetActive(false);
    }

    // This function will get all of the "GridSpaces" in the canvas and will allow them to be referenced, and thus, changed
    void SetGameControllerReferenceOnButtons ()
    {
        for (int i = 0; i < buttonList.Length; i++)
        {
            buttonList[i].GetComponentInParent<GridSpace>().SetGameControllerReference(this);
        }
    }

    // Returns the current player, in string format
    public string GetPlayerSide()
    {
        return playerSide;
    }


    // Determines whether the current player has placed a winning move, by checking the board at the three given indicies
    public bool Equals(int first, int second, int third)
    {
        return (buttonList[first].text == playerSide && buttonList[second].text == playerSide && buttonList[third].text == playerSide);
    }

    // This method is used when there is a 1-step lookahead. The method will check to see if the player has 
    // at least 2 of the spots occupied, meaning there is a winning move to play
    public bool Equals(int first, int second, int third, int player)
    {
        int count = 0;

        if(currentBoard[first] == player)
            count++;
        if(currentBoard[second] == player)
            count++;
        if(currentBoard[third] == player)
            count++;

        return (count >= 2);
    }

    // This method will determine which level of AI to play with, based on the passed parameter
    public void AITurnPicker(int level)
    {
        switch(level)
        {
            case 0: 
                AILevelZero();
                break;
            case 1: 
                AILevelOne();
                break;
            case 2:
                AILevelTwo();
                break;
            case 3:
                AILevelThree();
                break;
        }
    }

    // This method will loop through the entire grid and create a numerical representation of the board
    // AI = O = -1
    // Human = X = 1
    // Blank = 0
    // This method also increments the numOccupiedSpaces variable to get a better sense of the current board
    public void CreateCurrentBoard()
    {
        // currentBoard = new int[9];
        for(int i = 0; i < buttonList.Length; i++)
        {
            if(buttonList[i].text == "X")
            {
                currentBoard[i] = 1;
                numOccupiedSpaces++;
            }
            else if(buttonList[i].text == "O")
            {
                currentBoard[i] = -1;
                numOccupiedSpaces++;
            }
            else
            {
                currentBoard[i] = 0;
                numOccupiedSpaces++;
            }
        }
    }

    // FillSpace will create a random number and attempt to add it to the board.
    // If it is successfully added to the board, the method quits, else it calls FillSpace again.
    public void FillSpace()
    {
        int currentSpace = Random.Range(0,9);
        if(currentBoard[currentSpace] == 0)
        {
            buttonList[currentSpace].text = "O";
            buttonList[currentSpace].GetComponentInParent<Button>().interactable = false;
        }
        else
        {
            FillSpace();
        }
    }

    //This method takes a board and the player to calculate for. The method returns true if the player has won the game.
    public bool EvaluateBoard(int[] board, int player)
    {
        bool hasWin;

        if(board[0] == player && board[1] == player && board[2] == player)
        {
            Debug.Log("Win top row");
            hasWin = true;
        }     
        else if(board[3] == player && board[4] == player && board[5] == player)
        {
            Debug.Log("Win middle row");
            hasWin = true;
        }
        else if(board[6] == player && board[7] == player && board[8] == player)
        {
            Debug.Log("Win bottom row");
            hasWin = true;
        }
        else if(board[0] == player && board[3] == player && board[6] == player)
        {
            Debug.Log("Win left column");
            hasWin = true;
        }
        else if(board[1] == player && board[4] == player && board[7] == player)
        {
            Debug.Log("Win middle column");
            hasWin = true;
        }
        else if(board[2] == player && board[5] == player && board[8] == player)
        {
            Debug.Log("Win right column");
            hasWin = true;
        }
        else if(board[0] == player && board[4] == player && board[8] == player)
        {
            Debug.Log("Win left diagonal");
            hasWin = true;
        }
        else if(board[2] == player && board[4] == player && board[6] == player)
        {
            Debug.Log("Win right diagonal");
            hasWin = true;
        }
        else
        {
            hasWin = false;
        }

        return hasWin;

    }

    // Given an index on the board and a player, this method determines if the passed player would win the game if they played at index i.
    public bool checkWinCondition(int i, int player)
    {
        bool hasWin = false;
        int[] tempBoard = new int[9];

        CreateCurrentBoard();
        tempBoard = currentBoard;

        if(tempBoard[i] == 0)
        {
            tempBoard[i] = player;
            hasWin = EvaluateBoard(tempBoard, player);
        }

        return hasWin;
    }

    // This method will determine if the AI has any winning moves that could be played this turn, then plays them.
    // This method will also return whether it played any winning moves or not, in the form of a boolean.
    public bool AIWinningMoves()
    {
        CreateCurrentBoard();
        int[] winningMoves = new int[9];
        int index = 0;

        // Loops through every tile on the board
        for(int i = 0; i < 9; i++)
        {
            // Checks to see if the AI (-1) has a win condition at the current index (i).
            if(checkWinCondition(i, -1))
            {
                // Add winning index to an array
                Debug.Log("AI Check Win Condition passed");
                winningMoves[index] = i;
                index++;
            }
        }

        // If there was at least one winning move, take the first element from the array and make the play at that index
        if(index > 0)
        {
            int firstWin = winningMoves[0];
            buttonList[firstWin].text = "O";
            buttonList[firstWin].GetComponentInParent<Button>().interactable = false; 
            Debug.Log("Winning Move at index" + firstWin);
            EndTurn();
            return true;
        }
        else
        {
            return false;
        }
    }

    // This method will act as if the AI has two turns in a row and will determine if, in those two turns, the AI can win.
    // If so, the AI will log the index, and play there.
    public void doubleLookAhead(int player)
    {
        bool hasWin;
        List<int> winning = new List<int>();
        int[] current = currentBoard;

        for(int i = 0; i < 9; i++)
        {
            if(current[i] == 0)
            {
                current[i] = player;
                hasWin = EvaluateBoard(current, player);
                if(hasWin)
                {
                    winning.Add(i);
                }
            }

            int[] currentX = current;
            for(int j = 0; j < 9; j++)
            {
                if(currentX[j] == 0)
                {
                    currentX[j] = player;
                    hasWin = EvaluateBoard(currentX, player);
                    if(hasWin)
                    {
                        winning.Add(j);
                    }
                }
            }
        }

        //If, somewhere in the grid, the AI can win, play at the first win found.
        if(winning.Count > 0)
        {
            int firstWin = winning[0];
            Debug.Log("AI has a winning move in 2 turns, will play at " + firstWin);
            buttonList[firstWin].text = "O";
            buttonList[firstWin].GetComponentInParent<Button>().interactable = false; 
        }

    }

    // When selecting the difficult, this is the method that is called for AI Level 0
    public void AILevelZero()
    {
        Debug.Log("AI LEVEL 0");
        // Get current board, randomly fill
        CreateCurrentBoard();
        FillSpace();
        EndTurn();
    }

    // When selecting the difficult, this is the method that is called for AI Level 1
    public void AILevelOne()
    {
        Debug.Log("AI LEVEL 1");
        // If AI doesn't have winning moves, play random
        if(!AIWinningMoves())
            AILevelZero();
    }

    // When selecting the difficult, this is the method that is called for AI Level 2
    public void AILevelTwo()
    {
        Debug.Log("AI LEVEL 2");

        // If AI doesn't have winning moves
        if(!AIWinningMoves())
        {
            // Check if human has winning moves
            CreateCurrentBoard();
            int[] winningMoves = new int[9];
            int index = 0;

            for(int i = 0; i < 9; i++)
            {
                if(checkWinCondition(i, 1))
                {
                    Debug.Log("Check Win Condition passed");
                    winningMoves[index] = i;
                    index++;
                }
            }

            // If human has winning move, block it
            if(index > 0)
            {
                int firstWin = winningMoves[0];
                Debug.Log("Human has a winning move at " + firstWin);
                buttonList[firstWin].text = "O";
                buttonList[firstWin].GetComponentInParent<Button>().interactable = false; 
                EndTurn();
            }
            // If human doesn't have winning move, lookahead two levels for AI player
            else
            {
                doubleLookAhead(-1);
                EndTurn();
            }
        }
    }

    // When selecting the difficult, this is the method that is called for AI Level 3 
    public void AILevelThree()
    {
        // Get current game board and execute minimax algorithm
        Debug.Log("AT LEVEL 3");
        CreateCurrentBoard();
        Move m = minimax(currentBoard, -1);
        Debug.Log("Index: " + m.index + " Score: " + m.score);
        EndTurn();
    }

    // The minimax method will take in a board and a player and will continuously evaluate until there is a clear board state (win, lose, draw).
    // Once at a clear board state, assign a certain value to the score variable for the current "Move". Using a list of all of the possible moves,
    // evaluate them based on their score (minimize and maximize). Save whatever is the best possible move and return it to the caller in the AILevelThree method.
    // Credit to freeCodeCamp on Medium for parts of the algorithm
    public Move minimax(int[] board, int player)
    {
        Move move;
        Move retMove = new Move();
        int[] availableSpots = emptyIndicies(board);

        if(EvaluateBoard(board, -1))
        {
            retMove.score = -10;
            return retMove;
        }
        else if(EvaluateBoard(board, 1))
        {
            retMove.score = 10;
            return retMove;
        }
        else if(availableSpots.Length == 0)
        {
            retMove.score = 0;
            return retMove;
        }

        List<Move> moves = new List<Move>();

        for(int i = 0; i < availableSpots.Length; i++)
        {
            move = new Move();
            move.index = board[availableSpots[i]];

            board[availableSpots[i]] = player;

            if(player == -1)
            {
                Move result = minimax(board, 1);
                move.score = result.score;
            }
            else
            {
                Move result = minimax(board, -1);
                move.score = result.score;
            }

            board[availableSpots[i]] = move.index;

            moves.Add(move);
        }

        int bestMove = 0;
        if(player == -1)
        {
            int bestScore = 10000;
            for(int i = 0; i < moves.Count; i++)
            {
                if(moves[i].score < bestScore)
                {
                    bestScore = moves[i].score;
                    bestMove = i;
                }
            }
        }
        else
        {
            int bestScore = -10000;
            for(int i = 0; i < moves.Count; i++)
            {
                if(moves[i].score > bestScore)
                {
                    bestScore = moves[i].score;
                    bestMove = i;
                }
            }
        }

        return moves[bestMove];
    }

    // This method will determine every index in the current board that is currently empty
    public int[] emptyIndicies(int[] board)
    {
        int[] emptyArray;
        List<int> emptyList = new List<int>();

        for(int i = 0; i < 9; i++)
        {
            if(board[i] == 0)
            {
                emptyList.Add(i);
            }
        }
        emptyArray = new int[emptyList.Count];

        return emptyArray;
    }

    // This method will check to see if either side has a win on the board. If so, end the game with the approriate text.
    public void checkWins()
    {
        if (Equals(0,1,2))
        {
            GameOver(playerSide);
        }
        else if (Equals(3,4,5))
        {
            GameOver(playerSide);
        }
        else if (Equals(6,7,8))
        {
            GameOver(playerSide);
        }
        else if (Equals(0,3,6))
        {
            GameOver(playerSide);
        }
        else if (Equals(1,4,7))
        {
            GameOver(playerSide);
        }
        else if (Equals(2,5,8))
        {
            GameOver(playerSide);
        }
        else if (Equals(0,4,8))
        {
            GameOver(playerSide);
        }
        else if (Equals(2,4,6))
        {
            GameOver(playerSide);
        }
        else
        {
            return ;
        }
    }

    // Increase the number of moves made, check if there are wins or a draw, change sides if not
    public void EndTurn()
    {
        moveCount++;
        checkWins();
        
        if (moveCount > 9)
        {
            GameOver("draw");
        }
		else
		{
        	ChangeSides();
		}
    }

    // Changes colors and the current active player in the playerSide variable
    void ChangeSides()
    {
        playerSide = (playerSide == "X") ? "O" : "X";
		if (playerSide == "X")
		{
			SetPlayerColors(playerX, playerO);
		}
		else
		{
			SetPlayerColors(playerO, playerX);
		}
    }

    // Given the string of the winning player, print out the end game message
    void GameOver (string winningPlayer)
    {
		if(winningPlayer == "draw")
		{
			SetGameOverText("It's a Draw!");
		}
		else
		{
			SetGameOverText(winningPlayer + " Wins!");
		}

        SetBoardInteractable(false);
        restartButton.SetActive(true);
    }

    //Restart the game to allow for multiple playthroughs 
    public void RestartGame ()
    {
		SetPlayerColors(playerX, playerO);
        playerSide = "X";
        moveCount = 0;
        gameOverPanel.SetActive(false);
        restartButton.SetActive(false);
        SetBoardInteractable(true);

        for (int i = 0; i < buttonList.Length; i++)
        {
            buttonList[i].text = "";
        }
    }

    // Sets the text on the game over panel
    void SetGameOverText (string value)
    {
        gameOverPanel.SetActive(true);
        gameOverText.text = value;
    }

    // Sets whether the board is interactable or not
    void SetBoardInteractable (bool toggle)
    {
        for (int i = 0; i < buttonList.Length; i++)
        {
            buttonList[i].GetComponentInParent<Button>().interactable = toggle;
        }
    }

    // Changes player colors, based on current and previous active player
	void SetPlayerColors (Player newPlayer, Player oldPlayer)
	{
		newPlayer.panel.color = activePlayerColor.panelColor;
		newPlayer.text.color = activePlayerColor.textColor;
		oldPlayer.panel.color = inactivePlayerColor.panelColor;
		oldPlayer.text.color = inactivePlayerColor.textColor;
	}
}
