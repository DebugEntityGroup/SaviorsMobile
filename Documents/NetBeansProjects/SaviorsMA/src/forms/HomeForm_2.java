
package forms;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


public class HomeForm_2 extends Form{
  //  Resources theme;
Form current;
    public HomeForm_2(Resources theme, Form previous) {
        current=this;
        setTitle("Produits");
        setLayout(BoxLayout.y());
        
        //current.getToolbar()
        
       
        Button btnAddProduit = new Button(FontImage.MATERIAL_ADD_SHOPPING_CART);
        Button btnListProduits = new Button(FontImage.MATERIAL_LIST_ALT);
        Button btnAddCategorie = new Button(FontImage.MATERIAL_ADD_CHART);
        btnAddProduit.addActionListener(e-> new AddProduitForm(current).show());
        btnListProduits.addActionListener(e-> new ListProduitsForm(current,theme).show());
        btnAddCategorie.addActionListener(e-> new AddCategorieForm(current).show());
        add(new Label("Gestion Produits"));
        add(btnListProduits);
        add(btnAddProduit);
        add(new Label("Ajouter Categorie"));
        add(btnAddCategorie);
        
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }

    public Form getCurrent() {
        return current;
    }

    public void setCurrent(Form current) {
        this.current = current;
    }

    public boolean isFocusScrolling() {
        return focusScrolling;
    }

    public void setFocusScrolling(boolean focusScrolling) {
        this.focusScrolling = focusScrolling;
    }

 

}
