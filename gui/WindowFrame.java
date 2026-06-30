package gui;

import javax.swing.*;

public class WindowFrame extends JFrame
{
    public WindowFrame(String title)
    {
        setTitle(title);
        setSize(960,540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void reset()
    {
        revalidate();
        repaint();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void goTo(JPanel  panel){
        getContentPane().removeAll();
        add(panel);
        reset();
    }
}