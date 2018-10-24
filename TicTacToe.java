import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    Node root;
    Node current;
    Scanner in = new Scanner(System.in);
    Boolean turn;
    
    public void start(){
    	System.err.println("Choose X or O: ");
    	Scanner into = new Scanner(System.in);
        String choice = into.nextLine();
    	if(choice.equals("X")||choice.equals("x")){
    		turn = false;
    	}else if(choice.equals("O")||choice.equals("o")){
    		turn = true;
    	}else{
    		System.err.println("Invalid input!");
    		start();
    	}
    	String[] s = new String[9];
    	for(int i = 0; i<9; i++){
    		s[i]="_";
    	}
    	State emptyboard = new State(s,"X");
    	root = new Node(emptyboard, null);
    	current = root;
     	current.printboard();
    	current.findnext();
    }
    
    public void human(){
		System.err.println("Please make move: ");{
			int i = in.nextInt();
			if(current.actions.actions.contains(i-1)){
				int m = current.actions.actions.indexOf(i-1);
				current = current.children[m];
				turn = true;
				current.printboard();
			}else{
				System.err.println("Invalid move!");
				human();
			}
		}
	}
    

    
    public void AI(){
    	int i;
    	i = current.MinimaxDecision();
    	System.out.println(current.actions.actions.get(i)+1);
    	current = current.children[i];
		turn = false;
		current.printboard();
    }
    
    public void play(){
    	start();
    	while(!(current.state.checkDraw()||current.state.checkWin())){
    	    current.findnext();
    		if(turn){
    			System.err.println("AI's turn");
    			AI();
    		}else{
    			human();
    		}
    	}
    	if(current.state.checkDraw()){
    		System.err.println("Draw");
    	}else if(current.state.checkWin()&&!turn){
    		System.err.println("You Lose, AI Wins");
    	}else if(current.state.checkWin()&&turn){
    		System.err.println("You Win, AI loses");
    	}
    	current = null;
    	play();
    }
    
    public static void main(String[] args) {
		TicTacToe a = new TicTacToe();
		a.play();
        
	}

}
