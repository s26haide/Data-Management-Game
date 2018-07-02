import java.awt.*;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import java.io.*;
public class GameGraphic
extends JPanel
{
    static int x1 = 100;
    static int y1 = 600;
    static int x2 = 550;
    static int y2 = 600;
    static Graphics2D g2;
    static Random rand;
    static String piece;
    static String direction;
    static int moveNum;
    static String input;
    static boolean goal;
    static Scanner fileScan;
    public static void main (String []args)
    {
        String input = JOptionPane.showInputDialog("If you do not know the rules, type 'rules'. To start the game, press enter.");
        if(input.equalsIgnoreCase("rules"))
        {
            try
            {
                fileScan = new Scanner(new File("GameRules.txt"));
            }
            catch(FileNotFoundException FNFE)
            {
                JOptionPane.showMessageDialog(null, "The GameRules file was not found. If this occurs, tell Sakin or Timur.");
            }
            fileScan.useDelimiter("_");
            while(fileScan.hasNext())
            {
                JOptionPane.showMessageDialog(null, fileScan.next());
            }
        }
        
        JFrame frame = new JFrame();
        frame.setSize(950,750);
        frame.getContentPane().add(new GameGraphic());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        while (!goal)
        {
            options();
            move(piece,direction,moveNum);
            frame.validate();
            frame.repaint();
        }
        JOptionPane.showMessageDialog(null, "Congratulations! Whoever moved the piece wins!");
    }

    public static void options()
    {
        rand = new Random();
        int roll = rand.nextInt(6) + 1;
        if(roll <= 3)
        {
            piece = "A";
        }
        else
        {
            piece = "B";
        }
        JOptionPane.showMessageDialog(null, "You rolled a " + roll + ". This means you can move piece " + piece);
        if(piece.equalsIgnoreCase("A"))
        {
            direction = JOptionPane.showInputDialog("Move up or right?");
            if(direction.equalsIgnoreCase("up"))
            {
                input = JOptionPane.showInputDialog("How many spaces? (1,2)");
                moveNum = Integer.parseInt(input);
                if(moveNum < 0)
                {
                    moveNum = moveNum*-1;
                }

                if(moveNum > 2)
                {
                    moveNum = 2;
                }
            }
            else if(direction.equalsIgnoreCase("right"))
            {
                input = JOptionPane.showInputDialog("How many spaces? (1,2)");
                moveNum = Integer.parseInt(input);
                if(moveNum < 0)
                {
                    moveNum = moveNum*-1;
                }

                if(moveNum > 2)
                {
                    moveNum = 2;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Input not valid. Please try again");
            }
        }
        else if(piece.equalsIgnoreCase("B"))
        {
            direction = JOptionPane.showInputDialog("Move up or left?");
            if(direction.equalsIgnoreCase("up"))
            {
                input = JOptionPane.showInputDialog("How many spaces? (1,2)");
                moveNum = Integer.parseInt(input);
                if(moveNum < 0)
                {
                    moveNum = moveNum*-1;
                }

                if(moveNum > 2)
                {
                    moveNum = 2;
                }
            }
            else if(direction.equalsIgnoreCase("left"))
            {
                input = JOptionPane.showInputDialog("How many spaces? (1,2)");
                moveNum = Integer.parseInt(input);
                if(moveNum < 0)
                {
                    moveNum = moveNum*-1;
                }

                if(moveNum > 2)
                {
                    moveNum = 2;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Input not valid. Please try again.");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Input not valid. Please try again.");
        }
    }

    public static void erasePieceA()
    {
        if(x1%100 == 0 && y1%100 == 0)
        {
            g2.clearRect(x1, y1-50, 50,50);
        }
        else
        {
            g2.setColor(Color.BLACK);
            g2.fillRect(x1, y1-50, 50,50);
            g2.setColor(Color.RED);
        }
    }

    public static void erasePieceB()
    {
        if(x2%100 == 0 && y2%100 == 0)
        {
            g2.clearRect(x2, y2-50, 50,50);
        }
        else
        {
            g2.setColor(Color.BLACK);
            g2.fillRect(x2, y2-50, 50,50);
            g2.setColor(Color.RED);
        }
    }

    public static void move(String piece, String direction, int num)
    {
        if(piece.equalsIgnoreCase("A"))
        {
            if(direction.equalsIgnoreCase("up"))
            {
                erasePieceA();
                y1 -= (num*50);
                if(y1 < 150)
                {
                    y1 = 150;
                }
                g2.drawString("A", x1, y1);
            }
            else if(direction.equalsIgnoreCase("right"))
            {
                erasePieceA();
                x1 += (num*50);
                if(x1 > 550)
                {
                    x1 = 550;
                }
                g2.drawString("A", x1, y1);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Something went wrong with the input. Please try again");
            }

            if(x1 == x2 && y1 == y2)
            {
                JOptionPane.showMessageDialog(null,"There has been a confrontation!");
                rand = new Random();
                int randNum = rand.nextInt(6) + 1;
                if(randNum <= 4)
                {
                    x2 = 550;
                    y2 = 600;
                    g2.drawString("B", x2, y2);
                }
                else
                {
                    x1 = 100;
                    y1 = 600;
                    g2.drawString("A", x1, y1);
                }
            }

            if(x1 == 550 && y1 == 150)
            {
                goal = true;
            }
        }
        else if(piece.equalsIgnoreCase("B"))
        {
            if(direction.equalsIgnoreCase("Up"))
            {
                erasePieceB();
                y2 -= (num*50);
                if(y2 < 150)
                {
                    y2 = 150;
                }
                g2.drawString("B", x2, y2);
            }
            else if(direction.equalsIgnoreCase("left"))
            {
                erasePieceB();
                x2 -= (num*50);
                if(x2 < 100)
                {
                    x2 = 100;
                }
                g2.drawString("B", x2, y2);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Something went wrong with the input. Please try again");
            }

            if(x1 == x2 && y1 == y2)
            {
                JOptionPane.showMessageDialog(null,"There has been a confrontation!");
                rand = new Random();
                int randNum = rand.nextInt(6) + 1;
                if(randNum <= 4)
                {
                    x1 = 100;
                    y1 = 600;
                    g2.drawString("A", x1, y1);
                }
                else
                {
                    x2 = 550;
                    y2 = 600;
                    g2.drawString("B", x2, y2);
                }
            }

            if(x2 == 100 && y2 == 150)
            {
                goal = true;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"That move is not valid. Please try again");
        }
    }

    public void paint (Graphics g)
    {
        g.fillRect(100,100,500,500);
        for(int i = 100; i <=500; i+=100)
        {
            for(int j = 100; j <= 500; j+=100)
            {
                g.clearRect(i,j,50,50);
            }
        }
        for(int i = 150; i <= 550; i+=100)
        {
            for(int j = 150; j <= 550; j+=100)
            {
                g.clearRect(i,j,50,50);
            }
        }

        g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        g2.drawString("A", x1, y1);
        g2.drawString("B", x2, y2);
    }
}