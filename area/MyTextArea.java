package area;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class MyTextArea {

    private JTextArea textArea = new JTextArea(10,60);
    
    public MyTextArea(){
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Arial", 1, 16));
        textArea.getCaret().setVisible(true);
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.BLACK);
        
    }

    public JTextArea getTextArea(){
        return textArea;
    }

    public void appendText(String text){
        //textArea.setText(textArea.getText() + text);
//        textArea.setCaretPosition(textArea.getDocument().getLength());
        try {
            textArea.getDocument().insertString(textArea.getCaretPosition(), text, null);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
//        textArea.append(text);
    }
//    public void appendText(String text, int line){
//        textArea.setCaretPosition(line);
//        textArea.setText(textArea.getText() + text);
//
//    }
}
