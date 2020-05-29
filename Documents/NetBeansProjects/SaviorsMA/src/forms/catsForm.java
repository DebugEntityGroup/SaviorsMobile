package forms;

import com.codename1.components.SpanLabel;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import models.categorieCollecte;
import services.LoginService;

public class catsForm extends Form {

    

    public catsForm() {
        super("Categories", BoxLayout.y());
        
        this.add(new SpanLabel(new LoginService().getCategories().toString()));
        ComboBox<String> c = new ComboBox();
        for (int i=0; i<new LoginService().getCategories().size();i++) {
            
            c.addItem(new LoginService().getCategories().get(i).toString());
        }
        this.add(c);
            
        }

    private ArrayList createListEntry(categorieCollecte name) {
        ArrayList A = new ArrayList<>();
        A.add(name);
        return A;
    }

}
