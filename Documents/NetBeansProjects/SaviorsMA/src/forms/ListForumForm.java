package forms;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.util.ArrayList;
import models.Forum;
import services.CategorieForumService;
import services.ForumService;

/**
 *
 * @author Yasmine
 */
public class ListForumForm extends Form {
   
    Form current;

    public ListForumForm(Form previous) {
        
    
        current = this;
        current.setLayout(BoxLayout.y());
Toolbar.setGlobalToolbar(true);
Style s = UIManager.getInstance().getComponentStyle("Title");


TextField searchField = new TextField("", "Search"); // <1>
searchField.getHintLabel().setUIID("Title");
searchField.setUIID("Title");
searchField.getAllStyles().setAlignment(Component.LEFT);
current.getToolbar().setTitleComponent(searchField);
FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
searchField.addDataChangeListener((i1, i2) -> { // <2>
    String t = searchField.getText();
    if(t.length() < 1) {
        for(Component cmp : current.getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
    } else {
        t = t.toLowerCase();
        for(Component cmp : current.getContentPane()) {
            String val = null;
            if(cmp instanceof Label) {
                val = ((Label)cmp).getText();
            } else {
                if(cmp instanceof TextArea) {
                    val = ((TextArea)cmp).getText();
                } else {
                    val = (String)cmp.getPropertyValue("text");
                }
            }
            boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
            cmp.setHidden(!show); // <3>
            cmp.setVisible(show);
        }
    }
    current.getContentPane().animateLayout(250);
});
current.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
    searchField.startEditingAsync(); // <4>
});
        ArrayList<Forum> listeForums = new ArrayList<>();
        ForumService ps = new ForumService();
        listeForums = ps.getAllForums();
int i;
        for (i=0;i<ps.getAllForums().size();i++) {
            Forum c=new Forum();
            Button modif = new Button("modifier");
            Button supp=new Button("supprimer");
            Label space=new Label("    ");
            SpanLabel sujet = new SpanLabel("le sujet : " + ps.getAllForums().get(i).getSujet());
        SpanLabel description = new SpanLabel("La description : " + ps.getAllForums().get(i).getDescription());
        SpanLabel categorie = new SpanLabel("La categorie : " +ps.getAllForums().get(i).getCategorie());
        current.addAll(sujet,description,categorie);
        current.add(modif);
        current.add(supp);
        current.add(space);
          int id =  ps.getAllForums().get(i).getId();
          String sujett=ps.getAllForums().get(i).getSujet();
          String desc=ps.getAllForums().get(i).getDescription();
          modif.addPointerPressedListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Form fod = new Form();
                fod.setTitle("Modifier Forum");
               
                fod.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> current.showBack());

                fod.setLayout(BoxLayout.y());
                services.ForumService ser = new ForumService();
                TextField tfsujet = new TextField(sujett);
                TextField tfDescription = new TextField(desc);
               ComboBox<String> cx = new ComboBox();
        for (int i = 0; i < new CategorieForumService().getAllCats().size(); i++) {

            cx.addItem(new CategorieForumService().getAllCats().get(i).toString());
        }
                Button bo = new Button("Valider");
                Button btnAnnuler = new Button("Annuler");
                btnAnnuler.addPointerPressedListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        ListForumForm h = new ListForumForm(current);
                            h.current.show();
                    }
                });

                fod.add(tfsujet);
                fod.add(tfDescription);
                fod.add(cx);
                fod.add(bo);
                fod.add(btnAnnuler);
                fod.show();
                bo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        //for(int i =0;i<new ForumService().getAllForums().size();i++){
                         //if(new ForumService().getAllForums().get(i).getSujet().equals(c.getSujet())){
                        //int id = new ForumService().getAllForums().get(i).getId();
                         ArrayList<Forum> listeForums = new ArrayList<>();
                        ForumService ps = new ForumService();
                           listeForums = ps.getAllForums();

    
                      // for (Forum i : listeForums) {
                           
                        ser.ModifierForum(id, tfsujet.getText(),tfDescription.getText(), cx.getSelectedItem());
                            
                      //  }
                        ListForumForm h = new ListForumForm(current);
                            h.current.show();

                    }
                });

            }

        }
        );
        
        
        
        
        supp.addActionListener(evt -> {
            services.ForumService es = new ForumService();
            
            if (Dialog.show("confirmer", "tu veut supprimer ?", "ok", "Cancel")) {
                es.delete(id);
                 HomeForumForm h = new HomeForumForm(current);
                        h.getCurrent().show();
            } 
        });
    
    
        
         space.getStyle().setBorder(Border.createLineBorder(2));
        space.getStyle().setMargin(1, 1, 1, 1);
        space.getStyle().setPadding(0, 0, 0, 0);
        space.getStyle().setBgColor(0xffabab);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    
    
    }


}
