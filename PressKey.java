
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedHashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.text.BadLocationException;
import area.MyTextArea;
import buttons.MapOfButtons;

public class PressKey implements KeyListener, Runnable{

    private final MyTextArea area;
    public LinkedHashMap<String, JButton> buttons = new MapOfButtons().getMapOfButtons();
    
    public PressKey(MyTextArea area, JFrame frame){
        frame.addKeyListener(this);
        this.area = area;
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if(buttons.containsKey(String.valueOf(e.getKeyChar()).toUpperCase())){
            buttons.get(String.valueOf(e.getKeyChar()).toUpperCase()).setBackground(Color.RED);
        }
        else{
            switch(e.getExtendedKeyCode()){
                case KeyEvent.VK_ENTER:
                    buttons.get("Enter").setBackground(Color.RED);
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    buttons.get("Backspace").setBackground(Color.RED);
                    break;
                case KeyEvent.VK_SPACE:
                    buttons.get("Space").setBackground(Color.RED);
                    break;    
                case KeyEvent.VK_TAB:
                    buttons.get("Tab").setBackground(Color.RED);
                    break;    
                case KeyEvent.VK_CAPS_LOCK:
                    buttons.get("Caps").setBackground(Color.RED);
                    break;    
                default:
                    break;    

            }

            
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(buttons.containsKey(String.valueOf(e.getKeyChar()).toUpperCase())){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    buttons.get(String.valueOf(e.getKeyChar()).toUpperCase()).setBackground(Color.RED);

                }
            }).start();
            area.appendText(String.valueOf(e.getKeyChar()));
        }
        else{
            switch(e.getExtendedKeyCode()){
                case KeyEvent.VK_ENTER:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            buttons.get("Enter").setBackground(Color.RED);

                        }
                    }).start();

                    area.appendText("\n");
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            buttons.get("Backspace").setBackground(Color.RED);

                        }
                    }).start();

                    if(area.getTextArea().getText().length() > 0){
                        int offset = area.getTextArea().getCaretPosition();
                        if(offset > 0){
                            area.getTextArea().replaceRange("", offset - 1 , offset);
                        }
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            buttons.get("Space").setBackground(Color.RED);

                        }
                    }).start();

                    try {
                        area.getTextArea().getDocument().insertString(area.getTextArea().getCaretPosition(), " ", null);
                    } catch (BadLocationException e1) {
                        throw new RuntimeException(e1);
                    }
                    break;    
                case KeyEvent.VK_TAB:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            buttons.get("Tab").setBackground(Color.RED);


                        }
                    }).start();
                    area.getTextArea().setText(area.getTextArea().getText() + "\t");
                    break;    
                case KeyEvent.VK_CAPS_LOCK:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            buttons.get("Caps").setBackground(Color.RED);



                        }
                    }).start();
                    break;
                case KeyEvent.VK_BACK_QUOTE:
                    try{
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                buttons.get("Tilt").setBackground(Color.RED);
                            }
                        }).start();
                    }
                    catch(Exception e2){
                        e2.printStackTrace();
                    }
                    break;
                case KeyEvent.VK_UP:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            buttons.get("↑").setBackground(Color.RED);
                        }
                    }).start();
                    int caretPosition = area.getTextArea().getCaretPosition();
                    int column = 0;
                    int line = 0;
                    try {
                        line = area.getTextArea().getLineOfOffset(caretPosition);
                        column = caretPosition - area.getTextArea().getLineStartOffset(line);

                        if (line > 0) {
                            int prevLineStart = area.getTextArea().getLineStartOffset(line - 1);
                            int prevLineEnd = area.getTextArea().getLineEndOffset(line - 1);
                            int newCaretPosition = prevLineStart + column;

                            if (newCaretPosition > prevLineEnd) {
                                newCaretPosition = prevLineEnd;
                            }
                            area.getTextArea().setCaretPosition(newCaretPosition);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    buttons.get("←").setBackground(Color.RED);
                    int caretPosition2 = area.getTextArea().getCaretPosition();
                    if (caretPosition2 > 0) {
                        area.getTextArea().setCaretPosition(caretPosition2 - 1);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    buttons.get("→").setBackground(Color.RED);
                    int caretPosition3 = area.getTextArea().getCaretPosition();
                    if (caretPosition3 < area.getTextArea().getText().length() ) {
                        area.getTextArea().setCaretPosition(caretPosition3 + 1);
                    }
                    else if(caretPosition3 == area.getTextArea().getText().length()){
                        area.getTextArea().append(" ");
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    buttons.get("↓").setBackground(Color.RED);
                    try {
                        int caretPosition4 = area.getTextArea().getCaretPosition();
                        int currentLine = area.getTextArea().getLineOfOffset(caretPosition4);
                        int totalLines = area.getTextArea().getLineCount();

                        if (currentLine == totalLines - 1) {
                            area.getTextArea().append("\n");
                        }

                        int currentLineStartOffset = area.getTextArea().getLineStartOffset(currentLine);
                        int column4 = caretPosition4 - currentLineStartOffset;

                        int nextLineStartOffset = area.getTextArea().getLineStartOffset(currentLine + 1);
                        int nextLineEndOffset = area.getTextArea().getLineEndOffset(currentLine + 1);

                        int nextCaretPosition = Math.min(nextLineStartOffset + column4, nextLineEndOffset - 1);
                        area.getTextArea().setCaretPosition(nextCaretPosition);

                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                    break;
                default:
                    break;    

            }

            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(buttons.containsKey(String.valueOf(e.getKeyChar()).toUpperCase())){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(500);
                        buttons.get(String.valueOf(e.getKeyChar()).toUpperCase()).setBackground(Color.WHITE);

                    }
                    catch (InterruptedException e1){
                        e1.printStackTrace();
                    }
                }
            }).start();
        }
        else{
            switch(e.getExtendedKeyCode()){
                    case KeyEvent.VK_ENTER:
                        sleepOneSecond();
                        buttons.get("Enter").setBackground(Color.WHITE);
                        break;
                    case KeyEvent.VK_BACK_SPACE:
                        sleepOneSecond();
                        buttons.get("Backspace").setBackground(Color.WHITE);
                        break;
                    case KeyEvent.VK_SPACE:
                        sleepOneSecond();
                        buttons.get("Space").setBackground(Color.WHITE);
                        break;
                    case KeyEvent.VK_TAB:
                        sleepOneSecond();
                        buttons.get("Tab").setBackground(Color.WHITE);

                        break;    
                    case KeyEvent.VK_CAPS_LOCK:
                        sleepOneSecond();
                        buttons.get("Caps").setBackground(Color.WHITE);
                        break;    

                    case KeyEvent.VK_BACK_QUOTE:
                        try{
                            buttons.get("Tilt").setBackground(Color.RED);
                        }
                        catch(Exception e2){

                        }
                        break;
                    case KeyEvent.VK_UP:
                        sleepOneSecond();
                        buttons.get("↑").setBackground(Color.WHITE);
                        break;
                    case KeyEvent.VK_LEFT:
                        sleepOneSecond();
                        buttons.get("←").setBackground(Color.WHITE);
                        break;
                    case KeyEvent.VK_RIGHT:
                        sleepOneSecond();
                        buttons.get("→").setBackground(Color.WHITE);
                        break;
                    case KeyEvent.VK_DOWN:
                        sleepOneSecond();
                        buttons.get("↓").setBackground(Color.WHITE);
                        break;
                    default:
                        break;
                }
        }
     
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sleepOneSecond(){
        try{
            Thread.sleep(200);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
}
