package forms;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.List;
import models.CategorieProduit;
import services.ServiceCategorie;

/**
 *
 * @author Ahmed
 */
public class AddCategorieForm extends Form {

    String fileNameProdCat;
    Image img2ProdCat;
    List<CategorieProduit> listeProdCat = new ArrayList<>();

    public AddCategorieForm(Form previous) {
        
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
