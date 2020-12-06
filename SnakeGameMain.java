import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGameMain extends JPanel implements ActionListener {

    public static JFrame jFrame;
    public static final int SCALE=32;
    public static final int WIDTH=20;
    public static final int HEIGHT=20;
    public static int speed=10;
    public boolean isAlive=true;
    public int score;

    Snake s=new Snake(1,1,1,1);
    Mouse mouse=new Mouse((int)Math.random()*SnakeGameMain.WIDTH,(int)Math.random()*SnakeGameMain.HEIGHT);

    Timer timer=new Timer(1000/speed,this);



    public SnakeGameMain(){

        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);

    }

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,SCALE*WIDTH,SCALE*HEIGHT);

        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(mouse.posX*SCALE+1,mouse.posY*SCALE+1,SCALE-1,SCALE-1);


        for (int l=0;l<s.length;l++){
            g.setColor(Color.MAGENTA);
            g.fillRect(s.sX[l]*SCALE,s.sY[l]*SCALE,SCALE,SCALE);
        }
        for (int l=1;l<s.length;l++){
            g.setColor(Color.YELLOW);
            g.fillRect(s.sX[l]*SCALE,s.sY[l]*SCALE,SCALE,SCALE);
        }

        for(int x=0;x<SCALE*HEIGHT;x+=SCALE){
            g.setColor(Color.GREEN);
            g.drawLine(x,0,x,HEIGHT*SCALE);
        }

        for(int y=0;y<SCALE*WIDTH;y+=SCALE){
            g.setColor(Color.GREEN);
            g.drawLine(0,y,WIDTH*SCALE,y);
        }
    }

    public static void main(String[] args) {

        jFrame=new JFrame("Title");
        jFrame.setSize(WIDTH*SCALE+6,HEIGHT*SCALE+28);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(new SnakeGameMain());

        jFrame.setVisible(true);

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(isAlive)s.move();
        for(int i=1;i<s.length;i++) {
            if ((s.sX[0]==s.sX[i])&&(s.sY[0]==s.sY[i])) {
                isAlive=false;
                JTextField j=new JTextField("Конец игры,ваш результат: "+score);
                j.setVisible(true);
                jFrame.add(j);
                s.length=1;
                score=0;

            }
        }

        if((s.sX[0]==mouse.posX)&&(s.sY[0]==mouse.posY)){
            mouse.changePosition();
            s.length++;
            score++;

        }
        repaint();
    }

    public class KeyBoard extends KeyAdapter{
        public void keyPressed(KeyEvent event){
            int key=event.getKeyCode();
            if(key==KeyEvent.VK_UP) s.direction=0;
            if(key==KeyEvent.VK_DOWN) s.direction=2;
            if(key==KeyEvent.VK_LEFT) s.direction=3;
            if(key==KeyEvent.VK_RIGHT) s.direction=1;
            if(key==KeyEvent.VK_E) isAlive=true;
            if(key==KeyEvent.VK_Q) isAlive=false;

        }
    }
}
