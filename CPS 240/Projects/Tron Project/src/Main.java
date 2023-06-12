import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/* This program runs our version of the Tron Game, by implementing
 * a GridPane and painting rectangles and adding them to the pane.
 * The program includes border detection, crash detection, and
 * general movement functionality.
 * 
 * @author Becky House, Josh Goosen, Katie Wenban, and Chris Phillips
 * @version 1.0
 * @date April 24, 2017
 */
public class Main extends Application {
	
	//Creating the GridPane, starting positions of the players, and starting a timer
	static GridPane pane = new GridPane();
	static int p1x = 75;
	static int p1y = 30;
	static int p2x = 25;
	static int p2y = 30;
	static Timer timer = new Timer();
	
	//Booleans for which direction the players last chose (up, down, left, right)
	static boolean p1U = false;
	static boolean p1D = false;
	static boolean p1L = false;
	static boolean p1R = false;
	static boolean p2U = false;
	static boolean p2D = false;
	static boolean p2L = false;
	static boolean p2R = false;
	
	//Booleans for if either player has lost
	static boolean gameoverP1 = false;
	static boolean gameoverP2 = false;
	
	//Create a 2D array configured to the size of the board, for border and crash detection
	static int[][] board = new int[100][60];
	
	/* This function ticks every second to move each player
	 * in the direction they last pressed
	 * @return void
	 */
	public static void myTimer(){
		
		TimerTask task = new TimerTask(){
			
			public void run(){
				
				/* Sign the Executive Order for increased Border Patrol
				 * Runs border detection and if neither of the players have lost, it runs crash detection
				 */
				borderPatrol();
				
				if(gameoverP1 == false && gameoverP2 == false)
					crashDetection();

				/* If either of the players failed the borderPatrol or crashDetection
				 * methods, create an alert box to run later (ending the game)
				 */
				if(gameoverP1 == true || gameoverP2 == true){
					
					Platform.runLater(() ->{
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("GAME OVER!");
						
						//The player on the left is "player 1" in this situation but player2 in the program
						if(gameoverP1 && gameoverP2){
							alert.setContentText("It's a Tie!");
						}
						else if(gameoverP1){
							alert.setContentText("Player 1 Wins!");
						}
						else if(gameoverP2){
							alert.setContentText("Player 2 Wins!");
						}
						
						alert.showAndWait();
						
					});	
					timer.cancel();
				}
				else{
					
					//Based on the last key player 1 pressed, move their "train" in that direction, once per tick
					if(p1U){ 
						p1y--;
					}else if(p1D){
						p1y++;
					}else if(p1L){
						p1x--;
					}else if(p1R){
						p1x++;
					}
					
					//Based on the last key player 2 pressed, move their "train" in that direction, once per tick
					if(p2U){
						p2y--;
					}else if(p2D){
						p2y++;
					}else if(p2L){
						p2x--;
					}else if(p2R){
						p2x++;
					}
					
					/* In the GridPane create a new rectangle in each player's
					 * current pane, and color it appropriately. Then add that 
					 * location to the 2D array, for use in the crashDetection
					 * method.
					 */
					Platform.runLater(() ->{
						
						pane.add(new Rectangle(10,10, Color.GREEN), p1x, p1y);
						board[p1x][p1y] = 1;
						
						pane.add(new Rectangle(10,10, Color.RED), p2x, p2y);
						board[p2x][p2y] = 2;
						
					});	
				}
			}
		}; 
		
		timer.schedule(task, 0, 100); //Tick every 100 milliseconds
	}
	
	/* This function checks for if either of the
	 * players has run into any of the borders
	 * and ends the game if they have.
	 * @return void
	 */
	public static void borderPatrol(){
		
		//Testing the borders for first player
		if(p1U){
			if(p1y - 1 < 0){
				gameoverP1 = true;
			}
		}else if(p1D){
			if(p1y + 1 > 59){
				gameoverP1 = true;
			}
		}else if(p1L){
			if(p1x - 1 < 0){
				gameoverP1 = true;
			}
		}else if(p1R){
			if(p1x + 1 > 99){
				gameoverP1 = true;
			}
		}
		
		//Testing the borders for second player
		if(p2U){
			if(p2y - 1 < 0){
				gameoverP2 = true;
			}
		}else if(p2D){
			if(p2y + 1 > 59){
				gameoverP2 = true;
			}
		}else if(p2L){
			if(p2x - 1 < 0){
				gameoverP2 = true;
			}
		}else if(p2R){
			if(p2x + 1 > 99){
				gameoverP2 = true;
			}
		}
	}
	
	/* This function checks for if either of the
	 * players has run into any of the borders
	 * and ends the game if they have.
	 * @return void
	 */
	public static void crashDetection(){
		
		/* Uses the 2D array to check if the next
		 * tile is already occupied, if so, end the game
		 */
		if(p1U){
			if(board[p1x][p1y - 1] != 0){
				gameoverP1 = true;
			}
		}else if(p1D){
			if(board[p1x][p1y + 1] != 0){
				gameoverP1 = true;
			}
		}else if(p1L){
			if(board[p1x - 1][p1y] != 0){
				gameoverP1 = true;
			}
		}else if(p1R){
			if(board[p1x + 1][p1y] != 0){
				gameoverP1 = true;
			}
		}
		
		if(p2U){
			if(board[p2x][p2y - 1] != 0){
				gameoverP2 = true;
			}
		}else if(p2D){
			if(board[p2x][p2y + 1] != 0){
				gameoverP2 = true;
			}
		}else if(p2L){
			if(board[p2x - 1][p2y] != 0){
				gameoverP2 = true;
			}
		}else if(p2R){
			if(board[p2x + 1][p2y] != 0){
				gameoverP2 = true;
			}
		}
	}
	
	public void start(Stage primaryStage){
		
		//Create the playing field for the players
		Rectangle square;
		for(int x = 0; x < 100; x++){
			for(int y = 0; y < 60; y++){
				square = new Rectangle(10,10);
				pane.add(square, x, y);
			}
		}
		
		//Create and add player 1 and player 2 to the grid
		Rectangle player1 = new Rectangle(10,10, Color.GREEN);
		Rectangle player2 = new Rectangle(10,10, Color.RED);
		
		pane.add(player1, p1x, p1y);
		pane.add(player2, p2x, p2y);

		Scene scene = new Scene(pane, 1000, 600);
	
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//Check to see if the movement keys are pressed, if so, update the booleans
		scene.setOnKeyPressed( e -> {
			
			//Player 1 (UP, DOWN, LEFT, RIGHT)
			if(e.getCode() == KeyCode.UP){
				p1D = false;
				p1L = false;
				p1R = false;
				p1U = true;
			}
			else if(e.getCode() == KeyCode.DOWN){
				p1U = false;
				p1L = false;
				p1R = false;
				p1D = true;
			}
			else if(e.getCode() == KeyCode.LEFT){
				p1D = false;
				p1U = false;
				p1R = false;
				p1L = true;
			}
			else if(e.getCode() == KeyCode.RIGHT){
				p1U = false;
				p1L = false;
				p1D = false;
				p1R = true;
			}
			
			//Player 2 (W, A, S, D)
			if(e.getCode() == KeyCode.W){
				p2D = false;
				p2L = false;
				p2R = false;
				p2U = true;
			}
			else if(e.getCode() == KeyCode.S){
				p2U = false;
				p2L = false;
				p2R = false;
				p2D = true;
			}
			else if(e.getCode() == KeyCode.A){
				p2D = false;
				p2U = false;
				p2R = false;
				p2L = true;
			}
			else if(e.getCode() == KeyCode.D){
				p2D = false;
				p2L = false;
				p2U = false;
				p2R = true;
			}
		});
	}
	
	public static void main(String[] args) {
		
		//Mark the starting locations and start the timer
		board[p1x][p1y] = 1;
		board[p2x][p2y] = 2;
		myTimer();
		launch(args);	
	}
}
