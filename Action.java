import java.util.ArrayList;

public class Action {
	ArrayList<Integer> actions;
	String mark;
	
	//find and record all the movable position 
	public Action(State s){
		if(s.checkDraw()||s.checkWin()){
			actions = null;
		}else{
			if(s.player.equals("X")){
				this.mark = "X";
			}else{
				this.mark = "O";
			}
			actions = new ArrayList<Integer>();
			for(int i = 0;i<s.board.length;i++){
				if(s.board[i].equals("_")){
					actions.add(i);
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
