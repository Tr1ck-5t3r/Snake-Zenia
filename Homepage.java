import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Homepage extends JFrame {

    int speed = 1;
    String backgroundColor = "Black";
    String snakeColor = "Green";
    String appleColor = "Red";

    public Homepage() {
    }

    public void buildpage() {
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Disable resize option
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Using GridBagLayout for centering

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        JLabel titleLabel = new JLabel("Snake Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.TOP);
        panel.add(titleLabel, gbc);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setSize(200, 120);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SnakeGame(snakeColor, appleColor, backgroundColor, speed);
                dispose();
            }
        });
        panel.add(startButton, gbc);

        JButton helpButton = new JButton("Help");
        helpButton.setFont(new Font("Arial", Font.BOLD, 16));
        helpButton.setSize(200, 120);
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action to display game instructions
                JOptionPane.showMessageDialog(Homepage.this,
                        "<html>Use arrow keys to move the snake.<br>Collect food to increase score.<br>Avoid colliding with the walls or yourself.</html>");
            }
        });
        panel.add(helpButton, gbc);

        JButton settingsButton = new JButton("Settings");
        settingsButton.setFont(new Font("Arial", Font.BOLD, 16));
        settingsButton.setSize(200, 120);
        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSettings();
            }
        });
        panel.add(settingsButton, gbc);

        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.setFont(new Font("Arial", Font.BOLD, 16));
        highScoresButton.setSize(200, 120);
        highScoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action to display high scores
                String[] columnNames = { "Rank", "Player", "Score" };
                Object[][] data = {
                        { "1", "Player 1", "1000" },
                        { "2", "Player 2", "900" },
                        { "3", "Player 3", "800" },
                        { "4", "Player 4", "700" },
                        { "5", "Player 5", "600" }
                };
                JTable table = new JTable(data, columnNames);
                JOptionPane.showMessageDialog(Homepage.this, new JScrollPane(table), "High Scores",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
        panel.add(highScoresButton, gbc);

        add(panel);

        setVisible(true);
    }

    public void startGame() {
        new Homepage(snakeColor, appleColor, backgroundColor, speed);
        dispose();
    }

    public void openSettings() {
        setTitle("Snake Game Settings");
        getContentPane().removeAll();
        revalidate();
        repaint();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 rows, 2 columns, with gaps

        // Speed slider
        JLabel speedLabel = new JLabel("Snake Speed:");
        JSlider speedSlider = new JSlider(1, 5, 1);
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                speed = speedSlider.getValue();
            }
        });
        panel.add(speedLabel);
        panel.add(speedSlider);

        // Color selection for background
        JLabel backgroundLabel = new JLabel("Background Color:");
        String[] bgcolors = { "WHITE", "BLACK" };
        JComboBox<String> backgroundComboBox = new JComboBox<>(bgcolors);
        backgroundComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backgroundColor = (String) backgroundComboBox.getSelectedItem();
            }
        });
        panel.add(backgroundLabel);
        panel.add(backgroundComboBox);

        // Color selection for snake
        JLabel snakeLabel = new JLabel("Snake Color:");
        String[] colors = { "GREEN", "YELLOW", "ORANGE", "RED", "PINK", "MAGENTA", "BLUE", "CYAN" };
        JComboBox<String> snakeComboBox = new JComboBox<>(colors);
        snakeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                snakeColor = (String) snakeComboBox.getSelectedItem();
            }
        });
        panel.add(snakeLabel);
        panel.add(snakeComboBox);

        // Color selection for apple
        JLabel appleLabel = new JLabel("Apple Color:");
        JComboBox<String> appleComboBox = new JComboBox<>(colors);
        appleComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                appleColor = (String) appleComboBox.getSelectedItem();
            }
        });
        panel.add(appleLabel);
        panel.add(appleComboBox);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setTitle("Snake Game");
                getContentPane().removeAll();
                revalidate();
                repaint();
                buildpage();
            }
        });
        panel.add(submitButton);

        add(panel);

        setVisible(true);
    }

    public Homepage(String snakeColor, String appleColor, String backgroundColor, int speed) {
        // Game initialization with specified settings
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Homepage homepage = new Homepage();
                homepage.buildpage();
            }
        });
    }
}
