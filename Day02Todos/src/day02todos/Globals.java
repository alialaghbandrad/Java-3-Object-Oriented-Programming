
package day02todos;

import java.text.SimpleDateFormat;

public class Globals {
    // TODO: use toLocalizedPattern()
    static final SimpleDateFormat dateFormatScreen = new SimpleDateFormat("yyyy/MM/dd");
    
    static { // static initializer
        dateFormatScreen.setLenient(false);
    }
}
