using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class GridSpace : MonoBehaviour {

	public Button button;
	public Text buttonText;
	private GameController gameController;
	private static int difficulty = 0;

	// Sets the difficulty to be sent to the AI
	public void setDifficulty(int dif)
	{
		difficulty = dif;
	}

	// Displays the current difficulty setting in the calling text box
	public void displayDifficulty(Text difficultyDisplay)
	{
		difficultyDisplay.text = "Level " + difficulty;
	}

	//Executes the gameplay, allowing the human to go first, then AI taking a turn
	public void SetSpace ()
	{
		if(gameController.GetPlayerSide() == "X")
		{
			buttonText.text = gameController.GetPlayerSide();
			button.interactable = false;
			gameController.EndTurn();
			gameController.AITurnPicker(difficulty);
		}
	}

	// Setting up references
	public void SetGameControllerReference(GameController controller)
	{
		gameController = controller;
	}

}
