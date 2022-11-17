package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SW = 800;
    static final int SH = 800;
    static final int UNIT_SIZE = 50;
    static final int GAME_UNITS = (SW*SW)/(UNIT_SIZE*UNIT_SIZE);
    static final int DELAY = 100;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    Color bgColor = new Color(0,22,59);
    Color snakeHColor = new Color(1,176,64);
    Color snakeBColor = new Color(30, 70, 58);
    Color appleColor = new Color(150, 1, 1);
    Color textColor = new Color(180, 170, 58);



    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SH, SW));
        this.setBackground(bgColor);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();

    }
    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {
        if (running) {

            // Drawing the apple
            g.setColor(appleColor);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            //Drawing the snake
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(snakeHColor);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(snakeBColor);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.white);
            g.setFont(new Font("Bahnschrift Condensed", Font.BOLD, 50));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SW - metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
        }
        else {
            gameOver(g);
        }
    }
    public void newApple() {
        appleX = random.nextInt((int)(SW/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SH/UNIT_SIZE))*UNIT_SIZE;
    }
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }
    public void checkApple() {
        if((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(textColor);
        g.setFont(new Font("Bahnschrift Condensed", Font.BOLD, 50));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SH - metrics1.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());

        g.setColor(textColor);
        g.setFont(new Font("Baskerville Old Face", Font.BOLD, 50));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("YOU dead my boi try again", (SW - metrics2.stringWidth("YOU dead my boi try again"))/2, SH/2);

    }

    public void checkCollisions() {
        // This checks if head collides with body
        for(int i = bodyParts; i>0 ;i--) {
            if((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        // This checks if head touches left border
        if(x[0] < 0) {
            running = false;
        }
        // This checks if head touches right border
        if(x[0] > SH) {
            running = false;
        }
        // This checks if head touches top border
        if(y[0] < 0) {
            running = false;
        }
        // This checks if head touches bottom border
        if(y[0] > SW) {
            running = false;
        }

        if(!running) {
            timer.stop();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();

    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }

        }
    }
}
