import java.util.Random;
import java.util.Scanner;

public class AdvancedTTT {
    private ANodes root;
    private ANodes current;
    private boolean turn;
    Scanner in = new Scanner(System.in);
    Random rdm = new Random();
    
    public int chooseBoard(){
    	System.err.println("Choose Board 1-9: ");
    	int m = in.nextInt();
    	if(m>=1&&m<=9){
    		return m-1;
    	}else{
    		return chooseBoard();
    	}
    }
    public int makeMove(){
    	System.err.println("Make a move: ");
    	int m = in.nextInt();
    	if(m>=1&&m<=9){
    		return m-1;
    	}else{
    		return makeMove();
    	}
    }
    
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
    	State[] Boards = new State[9];
    	for(int i = 0; i<9; i++){
    		Boards[i] = new State(s,"X");
    	}
    	if(!turn){
    		Astate start = new Astate(Boards,1);
    		root = new ANodes();
    		root.astate = start;
    		root.parent = null;
    		int firstboard = chooseBoard();
    		root.astate = new Astate(Boards,firstboard);
    		root.actions = new AActions(root.astate,firstboard);
    		current = root;
    		current.findNext();
    		int n = makeMove();
    		current = current.children[n];
    		current.printBoard();
    		turn = true;
    		System.err.println("Your Move: "+(firstboard+1)+" "+(n+1));
    	}else{
    		Astate start = new Astate(Boards,1);
    		root = new ANodes();
    		root.astate = start;
    		int firstboard = rdm.nextInt(9);
    		root.astate = new Astate(Boards,firstboard);
    		root.actions = new AActions(root.astate,firstboard);
    		current = root;
    		current.findNext();
    		int n = current.AlphaBeta();
    		current = current.children[n];
    		current.printBoard();
    		System.err.println("AI Move: "+(firstboard+1)+" "+(n+1));
    		turn = false;
    	}
    }
    
    public void play(){
    	start();
    	while(current.actions.actionpos>=0){
    		current.findNext();
    		if(turn){
    			AI();
    		}else{
    			human();
    		}
    	}
    	if(current.astate.checkDraw()){
    		System.err.println("Draw");
    	}else if(current.astate.checkWin()&&!turn){
    		System.err.println("You Lose, AI Wins");
    	}else if(current.astate.checkWin()&&turn){
    		System.err.println("You Win, AI Loses");
    	}
    	play();
    }
    
    public void human(){
    	int a = chooseBoard();
    	if(a!=current.astate.getPos()){
    		System.err.println("Invalid Move!");
    		human();
    	}
    	int b = makeMove();
    	if(current.actions.actions.contains(b)){
    		int index = current.actions.actions.indexOf(b);
    		current = current.children[index];
    		turn = true;
    		current.printBoard();
    		System.err.println("Your Move: "+(a+1)+" "+(b+1));
    	}else{
    		System.err.println("Invalid Move!");
    		human();
    	}
    }
    
    public void AI(){
    	int pos = current.astate.getPos();
    	int move = current.AlphaBeta();
    	int a = current.actions.actions.get(move);
    	current = current.children[move];
    	current.printBoard();
    	System.out.println(pos+1+" "+(a+1));
    	System.err.println("AI Move: "+(pos+1)+"  "+(a+1));
    	turn = false;
    }
	
    public static void main(String[] args) {
		AdvancedTTT a = new AdvancedTTT();
		a.play();
	}
}
