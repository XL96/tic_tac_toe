import java.util.Scanner;

public class ANodes {
	Astate astate;
	ANodes parent;
	ANodes[] children;
	AActions actions;
	Scanner in = new Scanner(System.in);
	int Depth = 0;
	
	public void printBoard(){
		System.err.println();
		for(int i =0;i<3;i++){
			for(int j=0;j<3;j++){
				System.err.print(astate.getAstate()[i].board[j]);
			}
			System.err.print("|");
		}
		System.err.println();
		for(int i =0;i<3;i++){
			for(int j=3;j<6;j++){
				System.err.print(astate.getAstate()[i].board[j]);
			}
			System.err.print("|");
		}
		System.err.println();
		for(int i =0;i<3;i++){
			for(int j=6;j<9;j++){
				System.err.print(astate.getAstate()[i].board[j]);
			}
			System.err.print("|");
		}
		System.err.println();
		System.err.println("************");
		for(int i =3;i<6;i++){
			for(int j=0;j<3;j++){
				System.err.print(astate.getAstate()[i].board[j]);
			}
			System.err.print("|");
		}
		System.err.println();
		for(int i =3;i<6;i++){
			for(int j=3;j<6;j++){
				System.err.print(astate.getAstate()[i].board[j]);
			}
			System.err.print("|");
		}
		System.err.println();
		for(int i =3;i<6;i++){
			for(int j=6;j<9;j++){
				System.err.print(astate.getAstate()[i].board[j]);
			}
			System.err.print("|");
		}
		System.err.println();
		System.err.println("************");
		for(int i =6;i<9;i++){
			for(int j=0;j<3;j++){
				System.err.print(astate.getAstate()[i].board[j]);
			}
			System.err.print("|");
		}
		System.err.println();
		for(int i =6;i<9;i++){
			for(int j=3;j<6;j++){
				System.err.print(astate.getAstate()[i].board[j]);
			}
			System.err.print("|");
		}
		System.err.println();
		for(int i =6;i<9;i++){
			for(int j=6;j<9;j++){
				System.err.print(astate.getAstate()[i].board[j]);
			}
			System.err.print("|");
		}
		System.err.println();
	}
	
	public void setAstate(Astate a){
		this.astate = a;
	}
	
	public ANodes(){
		
	}
	
	public ANodes(Astate s, int i,int j){
	    this.astate = s;
	    this.actions = new AActions(s, i);
	    this.Depth = j;
	}
	
	public void findNext(){
		if(actions.actionpos>=0){
			children = new ANodes[actions.actions.size()];
			for(int m =0;m<children.length;m++){
				children[m] = new ANodes();
				Astate next = transit(actions.actionpos, actions.actions.get(m));
				children[m].setAstate(next);
				children[m].actions = new AActions(children[m].astate, actions.actions.get(m));
				children[m].parent = this;
			}
		}
	}
	
	public Astate transit(int a, int b){
		State[] shasha = new State[9];
		for(int i = 0;i<9;i++){
			String[] bb = new String[9];
			for(int j =0;j<9;j++){
				bb[j] = this.astate.Boards[i].board[j];
			}
			shasha[i] = new State(bb,this.astate.getPlayer());
		}
		Astate next = new Astate(shasha, a);
		next.mark(b);
		next.setpos(b);
		if(astate.getPlayer().equals("X")){
			next.setPlayer("O");
		}else{
			next.setPlayer("X");
		}
		return next;
	}
	
	public int Max(int a, int b){
		if(Depth == 5){
			return astate.Hueristic();
		}
    	if(astate.checkDraw()||astate.checkWin()){
    		return astate.Utility();
    	}else{
    		Depth += 1;
    		int v = -1000;
    		for(int i=0;i<actions.actions.size();i++){
    			ANodes x = new ANodes(transit(actions.actionpos,actions.actions.get(i)),actions.actions.get(i),Depth);
    			v = Math.max(v, x.Min(a, b));
    			if(v>=b){
    				return v;
    			}
    			a = Math.max(a, v);
    		}
    		return v;
    	}
    }
    
    public int Min(int a, int b){
    	if(Depth == 5){
			return astate.Hueristic();
		}
    	if(astate.checkDraw()||astate.checkWin()){
    		return astate.Utility();
    	}else{
    		Depth += 1;
    		int v = 1000;
    		for(int i=0;i<actions.actions.size();i++){
    			ANodes x = new ANodes(transit(actions.actionpos,actions.actions.get(i)),actions.actions.get(i),Depth);
    			v = Math.min(v, x.Max(a, b));
    			if(v<=a){
    				return v;
    			}
    			b = Math.min(b, v);
    		}
    		return v;
    	}
    }
    
    public int AlphaBeta(){
    	int move = 0;
    	if(astate.getPlayer().equals("X")){
    		int i = -1000;
    		for(int a : actions.actions){
    			int temp = new ANodes(transit(actions.actionpos, a),
    					a,Depth).Min(-1000,1000);
    			System.err.println("Move on "+ (a+1) +": "+temp);
    			if(i<temp){
    				i = temp;
    				move = actions.actions.indexOf(a);
    			}    		
    		}
    	}else{
    		int i = 1000;
    		for(int a : actions.actions){
    			int temp = new ANodes(transit(actions.actionpos, a),
    					a,Depth).Max(-1000,1000);
    			System.err.println("Move on "+ (a+1) +": "+temp);
    			if(i>temp){
    				i=temp;
    				move = actions.actions.indexOf(a);
    			}
    		}
    	}
    	return move;
    }


}
