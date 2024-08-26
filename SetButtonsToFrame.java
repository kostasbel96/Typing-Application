import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class SetButtonsToFrame {
    private LinkedHashMap<String, JButton> buttons;
    private final boolean[] rowsOfKeyboard = {false, false, false, false};
    private final JPanel keyboardPanel;
    public SetButtonsToFrame(LinkedHashMap<String, JButton> buttons, JPanel keyboardPanel){
        this.buttons = buttons;
        this.keyboardPanel = keyboardPanel;
    }

    public void setButtons(){
        JPanel[] panel = {new JPanel(new FlowLayout()), new JPanel(new FlowLayout()),
                new JPanel(new FlowLayout()), new JPanel(new FlowLayout()), new JPanel(new FlowLayout())};
        for(Map.Entry<String, JButton> button: buttons.entrySet()){
            changeStateOfLine(button.getKey());
            if(!rowsOfKeyboard[0]){
                setStyleToButton(button.getValue());
                if(button.getKey().equals("Backspace")){
                    button.getValue().setPreferredSize(new Dimension(100,30));
                }else{
                    button.getValue().setPreferredSize(new Dimension(50,30));
                }
                panel[0].add(button.getValue());
            }
            else if(!rowsOfKeyboard[1]){
                setStyleToButton(button.getValue());
                if(button.getKey().equals("Tab")){
                    button.getValue().setPreferredSize(new Dimension(100,30));

                }else{
                    button.getValue().setPreferredSize(new Dimension(50,30));
                }
                panel[1].add(button.getValue());
            }
            else if(!rowsOfKeyboard[2]){
                setStyleToButton(button.getValue());
                if(button.getKey().equals("Caps") || button.getKey().equals("Enter")){
                    button.getValue().setPreferredSize(new Dimension(103,30));

                }else{
                    button.getValue().setPreferredSize(new Dimension(50,30));
                }
                panel[2].add(button.getValue());
            }
            else if(!rowsOfKeyboard[3]){
                setStyleToButton(button.getValue());

                if(button.getKey().equals("Shift")){
                    button.getValue().setPreferredSize(new Dimension(100,30));
                    panel[3].add(button.getValue());
                }
                else if(button.getKey().equals("↑")){
                    button.getValue().setPreferredSize(new Dimension(100,30));
                    Box b = Box.createHorizontalBox();
                    b.add(Box.createHorizontalStrut(60));
                    b.add(button.getValue());
                    panel[3].add(b);
                }else{
                    button.getValue().setPreferredSize(new Dimension(50,30));
                    panel[3].add(button.getValue());
                }
            }
            else {
                setStyleToButton(button.getValue());
                if(button.getKey().equals("Space")){
                    button.getValue().setPreferredSize(new Dimension(400, 30));
                    Box b = Box.createHorizontalBox();
                    b.add(Box.createHorizontalStrut(200));
                    b.add(button.getValue());
                    panel[4].add(b);
                    panel[4].add(button.getValue());
                }
                else if(button.getKey().equals("←")){
                    button.getValue().setPreferredSize(new Dimension(58,30));
                    Box b = Box.createHorizontalBox();
                    b.add(Box.createHorizontalStrut(47));
                    b.add(button.getValue());
                    panel[4].add(b);
                }
                else{
                    button.getValue().setPreferredSize(new Dimension(50, 30));
                    Box b = Box.createHorizontalBox();
                    b.add(Box.createHorizontalStrut(0));
                    b.add(button.getValue());
                    panel[4].add(b);
                }

            }
        }
        keyboardPanel.add(panel[0]);
        keyboardPanel.add(panel[1]);
        keyboardPanel.add(panel[2]);
        keyboardPanel.add(panel[3]);
        keyboardPanel.add(panel[4]);
    }

    private void setStyleToButton(JButton button){
        button.setFocusable(false);
        button.setEnabled(false);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("Arial",Font.BOLD,12));
    }

    private void changeStateOfLine(String button){
        switch (button){
            case "Tab":
                rowsOfKeyboard[0] = true;
                break;
            case "Caps":
                rowsOfKeyboard[1] = true;
                break;
            case "Shift":
                rowsOfKeyboard[2] = true;
                break;
            case "Space":
                rowsOfKeyboard[3] = true;
                break;
        }
    }



}
