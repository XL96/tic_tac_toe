
public class State {
    String[] board = new String[9];
    String player;//the player that is moving , leading to next move
    
    public void printboard(){
    	for(int i=0;i<9;i+=3){
			   System.err.println(board[i]+board[i+1]+board[i+2]);
			}
    }
    
    public void change(int i){
    	board[i] = player;
    }
    
    public State(String[] s, String player){
    	board = s;
    	this.player = player;
    }
    
    public boolean checkDraw(){
    	if(checkWin()){
    		return false;
    	}else{
    		boolean draw = true;
    		for(int i=0;i<board.length;i++){
    			if(this.board[i].equals("_")){
    				draw = false;
    				return draw;
    			}
    		}
    		return draw;
    	}
    }
	
	public boolean checkWin(){
		return(checkH()||checkV()||checkD());
	}
	
	public boolean checkSame(String a,String b,String c){
		if(!a.equals("_")){
			return(a.equals(b)&&b.equals(c)&&c.equals(a));
		}
		    return false;
	}
	
	public boolean checktwoSame(String a,String b,String c){
		if(a.equals("_")||b.equals("_")||c.equals("_")){
			if(a.equals(b)&&!(a.equals("_"))){
				return true;
			}else if(a.equals(c)&&!(b.equals("_"))){
				return true;
			}else if(c.equals(b)&&!(b.equals("_"))){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public int checkH2(){
		int n = 0;
		for(int i = 0;i < board.length;i=i+3){
			if(checktwoSame(board[i],board[i+1],board[i+2])){
				n++;
			}
		}
		return n;
	}
	
	public int checkV2(){
		int n = 0;
		for(int i = 0;i < 3;i++){
			if(checktwoSame(board[i],board[i+3],board[i+6])){
				n++;
			}
		}
		return n;
	}
	
	public int checkD2(){
		int n = 0;
		if(checktwoSame(board[0],board[4],board[8])){
			n++;
		}if(checktwoSame(board[2],board[4],board[6])){
		    n++;
		}
	    return n;
	}
	
public boolean checkH(){
		for(int i = 0;i < board.length;i=i+3){
			if(checkSame(board[i],board[i+1],board[i+2])){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkV(){
		for(int i = 0;i < 3;i++){
			if(checkSame(board[i],board[i+3],board[i+6])){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkD(){
		if(checkSame(board[0],board[4],board[8])||checkSame(board[2],board[4],board[6])){
			return true;
		}else{
			return false;
		}
	}
	
	public int utility(){
    	if(checkDraw()){
    		return 0;
    	}else if(player.equals("X")){
    		return -10;
    	}else{
    		return 10;
    	}
    }

}
