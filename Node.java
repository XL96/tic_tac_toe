import java.util.Scanner;

public class Node {
	State state;
	Action actions;
	Node[] children;
	Node parent;
	Scanner in = new Scanner(System.in);
	
	public void printboard(){
		for(int i=0;i<9;i+=3){
			   System.err.println(state.board[i]+state.board[i+1]+state.board[i+2]);
			}
	}
	
	public Node(State input, Node parent){
		this.state = input;
		this.actions = new Action(input);
		this.parent = parent;
	}
	
	public void findnext(){
		//if there are movable positions 
		if(actions.actions!=null){
			children = new Node[actions.actions.size()];
			for(int i =0;i < actions.actions.size();i++){
				children[i] = new Node(transit(actions.actions.get(i), actions.mark),this);
				children[i].parent = this;
			}
		}
	}
	
	public int MinimaxDecision(){
		int act = 0;
		if(state.player.equals("X")){
			int i = -1000;
			for(int a : actions.actions){ 
				int temp = new Node(transit(a,actions.mark),this).MM(-1000,1000);
				System.err.println("Move on "+a+": "+temp);
				if(i<temp){
					i = temp;
					act = actions.actions.indexOf(a);
				}
			}
		}else{
			int i = 1000;
			for(int a : actions.actions){
				int temp = new Node(transit(a,actions.mark),this).MX(-1000,1000);
				System.err.println("Move on "+ (a+1) +": "+temp);
				if(i>temp){
					i = temp;
					act = actions.actions.indexOf(a);
				}

			}
		}

		return act;
		
	}
	
    /*public int Max(){
    	if(state.checkDraw()||state.checkWin()){
    		return state.utility();
    	}else{
    		int v = -1000;
    		for(int i=0;i<actions.actions.size();i++){
    			Node a = new Node(transit(actions.actions.get(i),actions.mark),this);
    			v = Math.max(v, a.Min());
    		}
    		return v;
    	}
    }
    
    public int Min(){
    	if(state.checkDraw()||state.checkWin()){
    		return state.utility();
    	}else{
    		int v = 1000;
    		for(int i=0;i<actions.actions.size();i++){
    			Node a = new Node(transit(actions.actions.get(i),actions.mark),this);
    			v = Math.min(v, a.Max());
    		}
    		return v;
    	}
    }
    */
    public int MM(int a, int b){
    	if(state.checkDraw()||state.checkWin()){
    		return state.utility();
    	}else{
    		int v = 1000;
    		for(int i=0;i<actions.actions.size();i++){
    			Node x = new Node(transit(actions.actions.get(i),actions.mark),this);
    			v = Math.min(v, x.MX(a, b));
    			if(v<=a){
    				return v;
    			}
    			b = Math.min(b, v);
    		}
    		return v;
    	}
    }
    
    public int MX(int a, int b){
    	if(state.checkDraw()||state.checkWin()){
    		return state.utility();
    	}else{
    		int v = -1000;
    		for(int i=0;i<actions.actions.size();i++){
    			Node x = new Node(transit(actions.actions.get(i),actions.mark),this);
    			v = Math.max(v, x.MM(a, b));
    			if(v>=b){
    				return v;
    			}
    			a = Math.max(a, v);
    		}
    		return v;
    	}
    }
	
    public State transit(int i, String mark){
    	String[] s= new String[9];
    	for(int a =0;a<9;a++){
    		s[a] = state.board[a];
    	}
    	String m;
    	if(mark.equals("X")){
    		m = "O";
    	}else{
    		m = "X";
    	}
    	State newstate = new State(s,m);
    	newstate.board[i] = mark;
    	return newstate;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
