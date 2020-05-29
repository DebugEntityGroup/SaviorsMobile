/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
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
import models.Publication;
import services.PublicationService;

/**
 *
 * @author lenovo
 */
public class AddPublicationForm extends Form{
        String fileName;
      Image img2;
    public AddPublicationForm(Form previous) {
        setTitle("Ajouter Un Publication");
        setLayout(BoxLayout.y());
        
        TextField tfTitre = new TextField("","titre");
        TextField tfDescription= new TextField("", "description");
        //TextField tfImage= new TextField("", "image");
        TextField tfVideo= new TextField("", "video");
        
         final String[] jobPic = new String[1];
        Label jobIcon = new Label();
        Button image = new Button("Ajouter une image ");
        final String[] image_name = {""};
        final String[] filePath = {""};
        fileName = "";
        image.addActionListener((ActionEvent actionEvent) -> {
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {

                    filePath[0] = (String) ev.getSource();
                    System.out.println(ev.getSource());
                    int fileNameIndex = filePath[0].lastIndexOf("/") + 1;
                    fileName = filePath[0].substring(fileNameIndex);
                    try {
                        img2 = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                        jobIcon.setIcon(img2);

                    } catch (Exception e) {
                    }
                }
            }, Display.GALLERY_IMAGE);
        });
       
        Button btnValider = new Button("Ajouter");
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener((evt) -> {
           previous.showBack();
        });
        btnValider.addActionListener(new ActionListener() {
            @Override         
            public void actionPerformed(ActionEvent evt) {
                    if (fileName.equals("")) {
                    ToastBar.showErrorMessage("Veuillez importer une image valide !");
                }
                if ((tfTitre.getText().length()==0)||(tfDescription.getText().length()==0)||(tfVideo.getText().length()==0))
                    Dialog.show("Alert", "s'il vous plait remplissez tout les champs", new Command("OK"));
                else
                {
                    try {
                        Publication p = new Publication(tfTitre.getText(),tfDescription.getText(),fileName,tfVideo.getText());
                        if(PublicationService.getInstance().addPublication(p))
 try {
                                Dialog.show("Success", "Publication Ajouter avec succes", new Command("OK"));
                                String pathToBeStored = "file://C:/wamp64/www/symfony/web/uploads/" + fileName;
                                OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                                ImageIO.getImageIO().save(img2, os, ImageIO.FORMAT_PNG, 0.9f);
                                os.close();
                            } catch (Exception ex) {
                                System.out.println(ex);
                            }                        else
                            Dialog.show("ERROR", "Serer error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }

      
        });
        
        addAll(tfTitre,tfDescription,image,tfVideo,btnValider,btnAnnuler);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }


    
}
