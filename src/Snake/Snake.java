package Snake;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Snake implements ActionListener, KeyListener {

    public JFrame jframe;
    public RenderPanel renderPanel;
    public Timer timer = new Timer(20,this);


    public static Snake snake;

    public ArrayList<Point> snakeParts = new ArrayList<>();
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
    public  int ticks = 0, direction = DOWN, score , tailLength = 10, time;
    public Point head, cherry;
    public Random random;
    public Dimension dim;
    public boolean over = false, paused;



    public Snake(){
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("Snake");
        jframe.setVisible(true);
        jframe.setLocation(dim.width / 2 - jframe.getWidth()/2,
                            dim.height / 2 - jframe.getHeight()/2);
        jframe.add(renderPanel = new RenderPanel());
        jframe.setSize(dim.width / 2 ,dim.height / 2);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(this);
        startGame();
    }
    public void startGame()
    {
        over=false;
        paused=false;
        time=0;
        score=0;
        tailLength=5;
        direction=DOWN;
        head = new Point(0,0);
        random = new Random();
        snakeParts.clear();
        cherry = new Point(random.nextInt(46)+1,random.nextInt(62)+1);

        for (int i = 0; i < tailLength ; i++) {
            snakeParts.add(new Point(head.x, head.y));
        }
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        //System.out.println(head.x+ ", "+ head.y);
        renderPanel.repaint();
        ticks++;
        if (ticks % 4 == 0 && head != null && !over  && !paused){
            time++;
            snakeParts.add(new Point(head.x, head.y));
            if (direction == UP) {
                if (head.y - 1 >= 0 && noTailAt(head.x, head.y+1) )
                    head = new Point(head.x,head.y-1);
                else over = true;}
            if (direction == DOWN)
                if (head.y + 1 < 47 && noTailAt(head.x, head.y-1) )
                head = new Point(head.x,head.y + 1);
                else over = true;
            if (direction == LEFT)
                if (head.x - 1 >= 0 && noTailAt(head.x+1, head.y))
                    head = new Point(head.x-1,head.y);
                else over = true;
            if (direction == RIGHT)
                if (head.x + 1 < 62 && noTailAt(head.x-1, head.y))
                    head = new Point(head.x+1,head.y);
                else
                    over = true;
            if (snakeParts.size() > tailLength)
                snakeParts.remove(0);

            if (cherry != null){
                if (head.equals(cherry)){
                    score += 10;
                    tailLength++;
                    cherry.setLocation(random.nextInt(46),random.nextInt(52));

                }
            }
        }
    }

    private boolean noTailAt(int i, int y) {
        for (Point point : snakeParts)
        {
            if (point.equals(head))
                return true;
        }
        return false;
    }


    public static void main(String[] args) {
        snake = new Snake();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if(i == KeyEvent.VK_A && direction != RIGHT)
            direction = LEFT;
        if(i == KeyEvent.VK_D && direction != LEFT)
            direction = RIGHT;
        if(i == KeyEvent.VK_W && direction != DOWN)
            direction = UP;
        if(i == KeyEvent.VK_S && direction != UP)
            direction = DOWN;
        if (i == KeyEvent.VK_SPACE)
            if (over)
                startGame();
            else paused = !paused;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
