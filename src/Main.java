import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        // this kicks up the gui leveraging swing
        SwingUtilities.invokeLater(() -> {
            
            UniversityGUI gui = new UniversityGUI();

            gui.setVisible(true);
        });
    }
}