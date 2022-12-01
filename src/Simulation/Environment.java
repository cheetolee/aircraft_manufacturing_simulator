package Simulation;


import javax.swing.SwingWorker;


/**
 * This class is designed to enable a long running task run in a background thread and provide updates to the UI.
 */
public class Environment extends SwingWorker<Object, String> {
    private boolean active = true;
    private static final int DELAI = 1;


    @Override
    protected Object doInBackground() throws Exception {

        while (active) {
            Thread.sleep((long) DELAI);
            firePropertyChange("TEST", null, "");
        }
        return null;
    }
}