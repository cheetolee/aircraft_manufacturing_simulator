package Simulation;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * This class is the JFrame containing the JPanel where you choose the strategy.
 */
public class StrategyFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String FRAME_TITLE = "Select your selling strategy";
    private static final Dimension DIMENSION = new Dimension(250, 100);
    private StrategyPannel strategyPannel = new StrategyPannel();

    public StrategyFrame() {
        add(strategyPannel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(FRAME_TITLE);
        setSize(DIMENSION);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}