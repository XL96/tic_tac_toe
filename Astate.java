public class Astate {
    State[] Boards = new State[9];
    
    private int pos;
    private String player;
    
    
    public Astate(State[] b, int i){
    	Boards = b;
    	pos = i;
    	player = b[i].player;
    }
    
    
    public void setpos(int i){
    	this.pos = i;
    }
    
    public void mark(int a){
    //	System.err.println("I'm marking "+pos+" "+a);
    	Boards[pos].board[a]=player;
    }
    
    public State[] getAstate(){
    	return Boards;
    }
    
    public int getPos(){
    	return pos;
    }
    
    public String getPlayer(){
    	return player;
    }
    
    public void setPlayer(String p){
    	this.player = p;
    }
    
    public boolean checkWin(){
    	for(int i = 0;i<9;i++){
    		if(Boards[i].checkWin()){
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean checkDraw(){
    	for(int i = 0;i<9;i++){
    		if(!Boards[i].checkDraw()){
    			return false;
    		}
    	}
    	return true;
    }
    
    public int Utility(){
    	if(checkDraw()){
    		return 0;
    	}else if(player.equals("X")){
    		return -25;
    	}else{
    		return 25;
    	}
    }

    public int Hueristic(){
    	int m = 0;
    	for(int i = 0;i<9;i++){
    		m+=Boards[i].checkD2();
    		m+=Boards[i].checkH2();
    		m+=Boards[i].checkV2();
    	}
    	if(player.equals("X")){
    		return m;
    	}else{
    		return -m;
    	}
    }


}
