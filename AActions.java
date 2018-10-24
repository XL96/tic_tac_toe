import java.util.ArrayList;

public class AActions {
	int actionpos;
	ArrayList<Integer> actions;
	String mark;
	
	public AActions(Astate s ,int p){
		if(s.checkDraw()||s.checkWin()){
			actionpos = -1;
			actions = null;
		}else{
			actionpos = p;
			actions = new ArrayList<Integer>();
			for(int j = 0;j<s.getAstate()[actionpos].board.length;j++){
				if(s.getAstate()[actionpos].board[j].equals("_")){
					actions.add(j);
				}
			}
		}
	}
    
    	
	public static void main(String[] args) {
		

	}

}
