package forms;

import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class ListeCommentsColsForm extends Form {
    
    public ListeCommentsColsForm(Resources theme) {
        super("Liste des Commentaires", BoxLayout.y());
        Label listeComsCols = new Label("Liste des Commentaires");
        listeComsCols.setAlignment(CENTER);
        listeComsCols.getStyle().setFgColor(123456);
        this.add(listeComsCols);
    }
    
}
