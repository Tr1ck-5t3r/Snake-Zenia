import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JFrame implements KeyListener {

    private final int width = 30; // Width of the game area in cells
    private final int height = 30; // Height of the game area in cells
    private final int cellSize = 20; // Size of each cell in pixels
    private ArrayList<Point> snake; // List to hold the snake's body
    private Point food; // Point to hold the food's position
    String SnakeColor = "Green";
    String FoodColor = "Red";
    String BackgroundColor = "Black";
    int speed = 1;
    private int direction; // Current direction of the snake (0: up, 1: right, 2: down, 3: left)

    public SnakeGame(String SnakeColor, String FoodColor, String BackgroundColor, int speed) {
        this.SnakeColor = SnakeColor;
        this.FoodColor = FoodColor;
        this.BackgroundColor = BackgroundColor;
        this.speed = speed;
        setTitle("Snake Game");
        setSize(width * cellSize, height * cellSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
            }
        };

        panel.setFocusable(true);
        panel.addKeyListener(this);
        add(panel);

        initializeGame();

        setVisible(true);
    }

    private void initializeGame() {
        snake = new ArrayList<>();
        snake.add(new Point(width / 2, height / 2));
        direction = 1; // Start moving right initially
        generateFood();
        // Start the game loop
        Timer timer = new Timer((((speed - 1) * 20) + 100), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
                checkCollision();
                repaint();
            }
        });
        timer.start();
    }

    private void draw(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());

        // Draw background
        if (BackgroundColor.equalsIgnoreCase("Black")) {
            g.setColor(Color.BLACK);
        } else if (BackgroundColor.equalsIgnoreCase("White")) {
            g.setColor(Color.WHITE);
        }

        g.fillRect(0, 0, height * cellSize, width * cellSize);

        if (SnakeColor.equalsIgnoreCase("Green")) {
            g.setColor(Color.GREEN);
        } else if (SnakeColor.equalsIgnoreCase("Yellow")) {
            g.setColor(Color.YELLOW);
        } else if (SnakeColor.equalsIgnoreCase("Orange")) {
            g.setColor(Color.ORANGE);
        } else if (SnakeColor.equalsIgnoreCase("Red")) {
            g.setColor(Color.RED);
        } else if (SnakeColor.equalsIgnoreCase("Pink")) {
            g.setColor(Color.PINK);
        } else if (SnakeColor.equalsIgnoreCase("Magenta")) {
            g.setColor(Color.MAGENTA);
        } else if (SnakeColor.equalsIgnoreCase("Blue")) {
            g.setColor(Color.BLUE);
        } else if (SnakeColor.equalsIgnoreCase("Cyan")) {
            g.setColor(Color.CYAN);
        } else {
            g.setColor(Color.GREEN);
        }

        for (Point p : snake) {
            g.fillRect(p.x * cellSize, p.y * cellSize, cellSize, cellSize);
        }

        // Draw food
        if (FoodColor.equalsIgnoreCase("Green")) {
            g.setColor(Color.GREEN);
        } else if (FoodColor.equalsIgnoreCase("Yellow")) {
            g.setColor(Color.YELLOW);
        } else if (FoodColor.equalsIgnoreCase("Orange")) {
            g.setColor(Color.ORANGE);
        } else if (FoodColor.equalsIgnoreCase("Red")) {
            g.setColor(Color.RED);
        } else if (FoodColor.equalsIgnoreCase("Pink")) {
            g.setColor(Color.PINK);
        } else if (FoodColor.equalsIgnoreCase("Magenta")) {
            g.setColor(Color.MAGENTA);
        } else if (FoodColor.equalsIgnoreCase("BLue")) {
            g.setColor(Color.BLUE);
        } else if (FoodColor.equalsIgnoreCase("Cyan")) {
            g.setColor(Color.CYAN);
        } else {
            g.setColor(Color.RED);
        }

        g.fillRect(food.x * cellSize, food.y * cellSize, cellSize, cellSize);
    }

    private void move() {
        // Move snake
        Point head = snake.get(0);
        Point newHead = new Point(head.x, head.y);
        switch (direction) {
            case 0: // Up
                newHead.y--;
                break;
            case 1: // Right
                newHead.x++;
                break;
            case 2: // Down
                newHead.y++;
                break;
            case 3: // Left
                newHead.x--;
                break;
        }
        snake.add(0, newHead);

        // Check if food is eaten
        if (newHead.equals(food)) {
            generateFood();
        } else {
            snake.remove(snake.size() - 1); // Remove tail
        }
    }

    private void generateFood() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width - 2) + 1;
            y = rand.nextInt(height - 2) + 1;
        } while (snake.contains(new Point(x, y)));
        food = new Point(x, y);
    }

    private void checkCollision() {
        Point head = snake.get(0);
        // Check if snake collides with itself or with the walls
        if (head.x < 0 || head.x > width - 1 || head.y < 0 || head.y > height - 1
                || snake.subList(1, snake.size()).contains(head)) {
            JOptionPane.showMessageDialog(this, "Game Over! Your Score: " + (snake.size() - 1), "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && direction != 1) {
            direction = 3; // Left
        } else if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && direction != 3) {
            direction = 1; // Right
        } else if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && direction != 2) {
            direction = 0; // Up
        } else if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && direction != 0) {
            direction = 2; // Down
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
