import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.LinkedHashMap;
import area.MyTextArea;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import buttons.MapOfButtons;

public class App{
    public static JFrame frame;
    static LinkedHashMap<String, JButton> buttons = new MapOfButtons().getMapOfButtons();
    public static void main(String[] args) {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Typing Application");

        frame.setSize(new Dimension(1100, 500));
        frame.setMinimumSize(new Dimension(1100, 600));

        frame.setLayout(new BorderLayout());
        JPanel myPanelText = new JPanel(new FlowLayout());
        myPanelText.setLayout(new FlowLayout());
        MyTextArea area = new MyTextArea();
        myPanelText.add(area.getTextArea());

        PressKey k = new PressKey(area, frame);


        JPanel myPanelKeyboardOne = new JPanel();

        myPanelKeyboardOne.setLayout(new FlowLayout(FlowLayout.CENTER,0,-5));
        myPanelKeyboardOne.setBorder(new EmptyBorder(0,200,0,200));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        SetButtonsToFrame setButtonsToFrame = new SetButtonsToFrame(buttons, myPanelKeyboardOne);
        setButtonsToFrame.setButtons();
        mainPanel.add(myPanelText, BorderLayout.NORTH);
        mainPanel.add(myPanelKeyboardOne, BorderLayout.CENTER);
        mainPanel.setMaximumSize(new Dimension(200,200));

        JButton bt = new JButton("Save to file");
        bt.setFocusable(false);
        SaveButton saveButton = new SaveButton(bt, frame, area);

        mainPanel.add(bt, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setContentPane(mainPanel);
        frame.setFocusable(true);
        frame.setFocusTraversalKeysEnabled(false);

        Thread t1 = new Thread(k);
        Thread thread = new Thread(saveButton);
        thread.start();

        JScrollPane scrollBar = new JScrollPane(area.getTextArea());
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        myPanelText.add(scrollBar);
        frame.setVisible(true);
        t1.start();


    }

}
