import java.util.Scanner;

class TicTacToe {

    public enum Cell {E,X,O}; //Empty, X or O

    private Cell[][] gameBoard;

    public TicTacToe(){
        gameBoard = new Cell[3][3]; //Create the board
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < gameBoard[i].length; j++){
                gameBoard[i][j] = Cell.E;

            }
        }
    }

    public String toString(){ //To visually see the board state
        String result = "";
        for(Cell[] row:gameBoard){
            for(Cell col: row){
                result += col;
            }
            result += "\n"; //New row
        }
        return result; //Returned as a string to be printed
    }

    public void setCell(int row, int col, Cell value){ //To change the board
        gameBoard[row][col] = value; 
    }
    public boolean checkCell(int myRow, int myCol, Cell value){ //To change the board
        if(gameBoard[myRow][myCol] == Cell.E){
            setCell(myRow, myCol, value);
            return true; 
        }
        else return false;
    }

    public boolean checkWin(Cell playerTurn){

        if (gameBoard[0][0] == playerTurn && gameBoard[0][1] == playerTurn && gameBoard[0][2] == playerTurn) return true; //Top row
        else if(gameBoard[1][0] == playerTurn && gameBoard[1][1] == playerTurn && gameBoard[1][2] == playerTurn) return true; //Middle Row
        else if (gameBoard[2][0] == playerTurn && gameBoard[2][1] == playerTurn && gameBoard[2][2] == playerTurn) return true;  //Bottom Row
        else if (gameBoard[0][0] == playerTurn && gameBoard[1][0] == playerTurn && gameBoard[2][0] == playerTurn) return true; //Left Column
        else if (gameBoard[0][1] == playerTurn && gameBoard[1][1] == playerTurn && gameBoard[2][1] == playerTurn) return true; //Middle Column
        else if (gameBoard[0][2] == playerTurn && gameBoard[1][2] == playerTurn && gameBoard[2][2] == playerTurn) return true; //Right Column
        else if (gameBoard[0][0] == playerTurn && gameBoard[1][1] == playerTurn && gameBoard[2][2] == playerTurn) return true; //Top left to bottom right diagonal
        else if (gameBoard[2][0] == playerTurn && gameBoard[1][1] == playerTurn && gameBoard[0][2] == playerTurn) return true; //Bottom left to top right diagonal
        
        return false;
    }

    public static void main(String[] args){
        TicTacToe gameInstance = new TicTacToe(); //Creates a new game

        Scanner myScanner = new Scanner(System.in); //To get user inputs
        boolean game = true;
        int playerTurn = 1; //Player 1 starts the game
        Cell playerTurnCell = Cell.X; //Player 1 starts with X


        while(game){
            for(int i=0;i<9;i++){
                System.out.println("\n" + gameInstance); //To show the board state
                System.out.printf("Player %d's Turn \n", playerTurn);
                System.out.println("Row Please:");
                int myRow = Integer.parseInt(myScanner.nextLine());
                System.out.println("Column Please:");
                int myCol = Integer.parseInt(myScanner.nextLine());
                
                //NEED TO CHECK FOR VALID INPUTS

                System.out.println("\n \n THIS IS i:" + i + "\n \n ");


                //Check if taken, if not change the board
                if(!gameInstance.checkCell(myRow-1, myCol-1, playerTurnCell)){
                    System.out.println("Player %d Please enter a valid move"); 
                    i--; //Effectively keeping i the same when the loop restarts
                    continue; //Restart the loop
                }

                //Cant win till 3rd place anyway
                if(i >= 4){
                    if(gameInstance.checkWin(playerTurnCell)) break; 
                }

                //Switch player turns
                if(playerTurn == 1){playerTurn = 2; playerTurnCell = Cell.O;}
                else{playerTurn = 1; playerTurnCell = Cell.X;}

            }
            System.out.println("\n" + gameInstance + "\n"); //To show the final board state
            System.out.printf("Player %d won the game! \n", playerTurn);
            System.out.println("If you wish to rematch type in Y otherwise just press enter to quit");
            String contiue = myScanner.nextLine();
            
            if(!contiue.equalsIgnoreCase("y")){ 
                game = false; //Ends the game 
            }else{
                //Swap player turns so the other player starts first
                if(playerTurn == 1) playerTurn = 2;
                else playerTurn = 1;
                gameInstance = new TicTacToe(); //Reset the game board
            }
        }
        myScanner.close(); //To silence a warning
    }


}
