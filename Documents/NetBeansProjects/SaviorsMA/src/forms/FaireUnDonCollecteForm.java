package forms;

import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class FaireUnDonCollecteForm extends Form {
    
    public FaireUnDonCollecteForm(Resources theme) {
        super("Collecte", BoxLayout.y());
        Label donateCol = new Label("Faire un Don");
        donateCol.setAlignment(CENTER);
        donateCol.getStyle().setFgColor(123456);
        this.add(donateCol);
    }
    
}
