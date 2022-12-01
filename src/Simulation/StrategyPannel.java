package Simulation;


import Utilities.ControlSystem;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

/**
 * This class represents the panel containing the buttons to choose the selling strategy
 */
public class StrategyPannel extends JPanel {

    private static final long serialVersionUID = 1L;
    String strategy;
    public StrategyPannel() {

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton strategy1 = new JRadioButton("Strategy 1");
        JRadioButton strategy2 = new JRadioButton("Strategy 2");

        JButton confirmButton = new JButton("Confirm");

        //Lambda example
        confirmButton.addActionListener((ActionEvent e) -> {
            String selectedButtonText =getSelectedButtonText(buttonGroup);
            this.strategy=selectedButtonText;
            ControlSystem.getInstance().setStrategy(this.strategy);
            SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
        });

        JButton cancelButton = new JButton("Cancel");

        //Lambda example
        cancelButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
        });

        buttonGroup.add(strategy1);
        buttonGroup.add(strategy2);
        add(strategy1);
        add(strategy2);
        add(confirmButton);
        add(cancelButton);

    }

    /**
     * Return the selected button from a button group
     * @param buttonGroup
     * @return
     */
    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton bouton = buttons.nextElement();
            if (bouton.isSelected()) {
                return bouton.getText();
            }
        }
        return null;
    }

}