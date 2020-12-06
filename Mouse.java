public class Mouse {
    public int posX;
    public int posY;
    public Mouse(int x,int y){
       posX=x;
       posY=y;
    }

    public void changePosition(){
        posX=Math.abs((int)(Math.random()*SnakeGameMain.WIDTH-1));
        posY=Math.abs((int)(Math.random()*SnakeGameMain.HEIGHT-1));
    }
}
