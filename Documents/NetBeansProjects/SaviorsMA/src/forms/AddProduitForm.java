package forms;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import models.CategorieProduit;
import models.Produit;
import models.UserLogin;
import services.ServiceCategorie;
import services.ServiceProduit;

public class AddProduitForm extends Form {

    public AddProduitForm(Form previous) {
        
        Button listProduits = new Button ("Liste des Produits");
        Label spaceTop = new Label ("");
        Label spaces = new Label ("   ");
        this.addAll(spaceTop, listProduits, spaces);
        
        listProduits.addActionListener((listProd) -> {
            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
            previous.show();
        });
        
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
