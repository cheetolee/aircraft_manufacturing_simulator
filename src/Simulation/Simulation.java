package Simulation;

public class Simulation {

    /**
     * This class represents the starting point of the application
     */
    public static void main(String[] args) {
        Environment environment = new Environment();
        MainFrame fenetre = new MainFrame();

        environment.addPropertyChangeListener(fenetre);
        environment.execute();

    }

}

