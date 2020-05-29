package forms;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.List;
import models.CategorieForum;
import models.Forum;
import services.CategorieForumService;
import services.ForumService;


public class AjouterForm extends Form {
List<CategorieForum> liste = new ArrayList<>();

    public AjouterForm(Form previous) {
        setTitle("Ajouter Un Forum");
        setLayout(BoxLayout.y());

        TextField sujet = new TextField("", "sujet");
        TextField description = new TextField("", "description");

       ComboBox<String> c = new ComboBox();
        for (int i = 0; i < new CategorieForumService().getAllCats().size(); i++) {

            c.addItem(new CategorieForumService().getAllCats().get(i).toString());
        }
        Button btnValider = new Button("Ajouter");
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener((evt) -> {
            previous.showBack();
        });
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                if ((sujet.getText().length() == 0) || (description.getText().length() == 0) ) {
                    Dialog.show("Alert", "s'il vous plait remplissez tout les champs", new Command("OK"));
                } else {
                    try {
                        Forum p = new Forum(sujet.getText(),  description.getText(), c.getSelectedItem().toString());
                        if (ForumService.getInstance().AjouterForum(p)) {
                            try {
                                if (Dialog.show("Ajout", "Confirmer l'ajout ?", "OK", "Cancel")) {
                                    Dialog.show("Success", "Forum Ajouter avec succes", new Command("OK"));

                                }

                            } catch (Exception ex) {
                                System.out.println(ex);
                            }
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }

           
        });

        addAll(sujet, description,c,btnValider, btnAnnuler);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
