package forms;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class DetailsCollecteForm extends Form {

    public DetailsCollecteForm(Resources theme) {
        super("Details", BoxLayout.y());
        Label detCollecte = new Label("Détails de la Collecte");
        detCollecte.setAlignment(CENTER);
        detCollecte.getStyle().setFgColor(123456);
        this.add(detCollecte);
    }
}
