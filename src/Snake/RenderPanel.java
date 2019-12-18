package Snake;

import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
public class RenderPanel extends JPanel {

    public static Color green = new Color(1666073);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(green);
        g.fillRect(0, 0, 800, 700);
        Snake snake = Snake.snake;
        g.setColor(Color.RED);
        for (Point point : snake.snakeParts) {

            g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
            }
        g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
    }
}

