package forms;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class AddCollecteForm extends Form {
    
    public AddCollecteForm(Resources theme) {
        super("Ajouter une Collecte", BoxLayout.y());
        Label addCollecte = new Label("Ajouter une Nouvelle Collecte");
        addCollecte.setAlignment(CENTER);
        addCollecte.getStyle().setFgColor(123456);
        this.add(addCollecte);
    }
    
}
