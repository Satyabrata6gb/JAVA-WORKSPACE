package Practice;

import javax.swing.*;
import java.awt.FlowLayout;

public class MyWindow {
    public static void main(String[] args) {
        
        // window : object JFrame

        JFrame frame = new JFrame("My Window");

        //Create button and add jframe

        frame.setSize(400, 400);
        frame.setLayout(new FlowLayout());
        JButton button = new JButton("Click Me");

        frame.add(button);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
