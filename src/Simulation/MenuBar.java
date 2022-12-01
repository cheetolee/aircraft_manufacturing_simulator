package Simulation;


import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 * This class represents the menu bar containing the file and simulation buttons.
 */
public class MenuBar extends JMenuBar {

    private static final long serialVersionUID = 1L;
    private static final String FILE_MENU_TITLE = "File";
    private static final String FILE_MENU_LOAD = "Load";
    private static final String FILE_MENU_EXIT = "Exit";
    private static final String SIMULATION_MENU_TITLE = "Simulation";
    private static final String SIMULATION_MENU_SELECT = "Select";

    public MenuBar() {
        addFileMenu();
        addSimulationMenu();
    }

    /**
     * Create the file menu
     */
    private void addFileMenu() {
        JMenu fileMenu = new JMenu(FILE_MENU_TITLE);
        JMenuItem loadMenu = new JMenuItem(FILE_MENU_LOAD);
        JMenuItem exitMenu = new JMenuItem(FILE_MENU_EXIT);

        loadMenu.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Select a configuration file");
            fileChooser.setAcceptAllFileFilterUsed(false);
            // Create a filter
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".xml", "xml");
            fileChooser.addChoosableFileFilter(filter);

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
            }
        });

        //Lambda example
        exitMenu.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        fileMenu.add(loadMenu);
        fileMenu.add(exitMenu);

        add(fileMenu);

    }

    /**
     * Create the simulation menu
     */
    private void addSimulationMenu() {
        JMenu menuSimulation = new JMenu(SIMULATION_MENU_TITLE);
        JMenuItem menuChoisir = new JMenuItem(SIMULATION_MENU_SELECT);
        menuSimulation.add(menuChoisir);

        //Lambda example
        menuChoisir.addActionListener((ActionEvent e) -> {
            new StrategyFrame();
        });
        add(menuSimulation);
    }


}