package Simulation;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

/**
 * This class is the main JFrame that provides a window on the screen, on which other components rely.
 */
public class MainFrame extends JFrame implements PropertyChangeListener {

    private static final long serialVersionUID = 1L;
    private static final String TITRE_FENETRE = "Final Project";
    private static final Dimension DIMENSION = new Dimension(700, 700);

    public MainFrame() {
        MainPanel mainPanel = new MainPanel();
        MenuBar menuBar = new MenuBar();
        add(mainPanel);
        add(menuBar, BorderLayout.NORTH);
        // Enable exit upon clicking on the X button
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(TITRE_FENETRE);
        setSize(DIMENSION);
        // Make the mainFrame visible
        setVisible(true);
        // Make the mainFrame in the center of the screen
        setLocationRelativeTo(null);
        // Forbid the resize of the mainFrame
        setResizable(false);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("TEST")) {
            repaint();
            System.out.println(evt.getNewValue());
        }
    }
}