package DealershipGUI;
import javax.swing.*;
import java.awt.*;

public class GridBagPanelExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GridBagLayout with Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        // Create a main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        
        // Create another panel to be placed inside the GridBagLayout
        JPanel innerPanel = new JPanel();
        innerPanel.setBackground(Color.CYAN); // Just for visibility
        
        // Create GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Column
        gbc.gridy = 0; // Row
        gbc.gridwidth = 1; // This panel will occupy one column
        gbc.gridheight = 1; // This panel will occupy one row
        gbc.fill = GridBagConstraints.BOTH; // Make the panel expand to fill space
        
        // Add the inner panel to the main panel with GridBagLayout
        mainPanel.add(innerPanel, gbc);
        
        // Set the frame's content pane to the main panel
        frame.setContentPane(mainPanel);
        
        // Make the frame visible
        frame.setVisible(true);
    }
}
