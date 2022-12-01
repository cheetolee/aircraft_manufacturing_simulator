package Simulation;


import Utilities.ControlSystem;
import java.awt.Graphics;
import javax.swing.*;


/**
 * This class represents the main panel that contains the grid with all its components
 */
public class MainPanel extends JPanel  {
    private static final long serialVersionUID = 1L;
    private ControlSystem creator = ControlSystem.getInstance();
    @Override
    public void paint(Graphics g) {
        /**
         * The drawing is subcontracted to the ControlSystem
         */
        creator.draw(g);
    }

}