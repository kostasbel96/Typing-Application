import area.MyTextArea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveButton implements Runnable{
    private final JButton button;
    private final JFrame frame;
    private final MyTextArea area;
    public SaveButton(JButton button, JFrame frame, MyTextArea area){
        this.button = button;
        this.frame = frame;
        this.area = area;
    }
    public void run(){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File f = new File("test.txt");
                Thread th1 = new Thread(){
                    public void run(){
                        try {
                            Thread.sleep(1000);
                            JOptionPane.showMessageDialog(frame, "The text saved successfully!");
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                };

                Thread th2 = new Thread(){
                    public void run(){
                        try {
                            Thread.sleep(1000);
                            JOptionPane.showMessageDialog(frame, "The text doesn't saved!");
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                };

                try(BufferedWriter bf = new BufferedWriter(new FileWriter(f))){
                    bf.write(area.getTextArea().getText());
                    th1.start();

                } catch (IOException e1) {
                    th2.start();
                    e1.printStackTrace();
                }
            }
        });
    }

    public JButton getButton(){
        return button;
    }
}
