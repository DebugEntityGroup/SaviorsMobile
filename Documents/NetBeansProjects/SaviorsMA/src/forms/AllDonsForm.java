package forms;

import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class AllDonsForm extends Form {
    
    public AllDonsForm(Resources theme) {
        super("Dons contribués", BoxLayout.y());
        Label donsCollecte = new Label("Dons contribués à cette Collecte");
        donsCollecte.setAlignment(CENTER);
        donsCollecte.getStyle().setFgColor(123456);
        this.add(donsCollecte);
    }
    
}
