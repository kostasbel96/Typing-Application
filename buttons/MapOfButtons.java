package buttons;

import java.util.LinkedHashMap;
import javax.swing.JButton;

public class MapOfButtons extends Buttons{
    private final LinkedHashMap<String, JButton> buttons = new LinkedHashMap<>();
    
    public MapOfButtons(){
        //init map
        for(JButton button : keyboard){
            buttons.put(button.getText(), button);
        }
    }

    public LinkedHashMap<String, JButton> getMapOfButtons(){
        return buttons;
    }

    public static Buttons getButton(){
        return new Buttons();
    }
    

}
