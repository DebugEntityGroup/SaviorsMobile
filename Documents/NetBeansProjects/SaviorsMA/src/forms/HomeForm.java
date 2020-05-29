package forms;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

public class HomeForm extends Form {

    public HomeForm(Resources theme) {

        super("Accueil", BoxLayout.y());
        
        /*this.getToolbar().addCommandToRightBar("", theme.getImage("disconnect.png"), (evt) -> {
            if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                new loginForm(theme).show();
            }
        });*/
    }

}
