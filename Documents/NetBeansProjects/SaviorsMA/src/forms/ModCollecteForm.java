package forms;

import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class ModCollecteForm extends Form {
    public ModCollecteForm(Resources theme, Form retour) {
        super("Modification", BoxLayout.y());
        Label addCollecte = new Label("Modifier la Collecte");
        addCollecte.setAlignment(CENTER);
        addCollecte.getStyle().setFgColor(123456);
        this.add(addCollecte);
    }
}
