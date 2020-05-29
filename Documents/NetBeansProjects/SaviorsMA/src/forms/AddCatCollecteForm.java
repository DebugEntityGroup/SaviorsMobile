package forms;

import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class AddCatCollecteForm extends Form {
    
    public AddCatCollecteForm(Resources theme) {
        super("Ajouter une Catégorie", BoxLayout.y());
        Label addCat = new Label("Ajouter une Nouvelle Catégorie");
        addCat.setAlignment(CENTER);
        addCat.getStyle().setFgColor(123456);
        this.add(addCat);
    }
    
}
