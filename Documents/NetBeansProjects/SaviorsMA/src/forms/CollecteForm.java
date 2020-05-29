package forms;

import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

public class CollecteForm extends Form {
    
    public CollecteForm(Resources theme) {
        super("Collecte", BoxLayout.y());
        //Toolbar.setGlobalToolbar(true);
        //super.getToolbar().addCommandToSideMenu("", icon, (e) -> Log.p("Clicked"));
        
        /*this.getToolbar().addCommandToRightBar("", theme.getImage("disconnect.png"), (evt) -> {
            if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                new loginForm(theme).show();
            }
        });*/
    }
    
}
