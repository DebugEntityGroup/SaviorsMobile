/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import Services.PublicationService;
import com.codename1.capture.Capture;
import com.codename1.components.Accordion;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.openGallery;
import com.codename1.ui.CN1Constants;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;
import models.CommentairePub;
import models.Publication;

/**
 *
 * @author lenovo
 */
public class ListPubsForm extends Form {

    Form current;

    public ListPubsForm(Form previous, Resources theme) {

        current = this;
        setTitle("Liste publication");

        ArrayList<Publication> listepubs = new ArrayList<>();
        PublicationService ps = new PublicationService();
        listepubs = ps.getAllPubs();

        for (Publication i : listepubs) {
            //   System.out.println(i);
            current.add(AddItem(i, theme));
        }

        /* SpanLabel sp = new SpanLabel();
        sp.setText(ServiceProduit.getInstance().getAllProduits().toString());
        add(sp);*/
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public Container AddItem(Publication c, Resources theme) {

        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        EncodedImage enco = EncodedImage.createFromImage(theme.getImage("load.png"), false);
        String url = "http://localhost/symfony/web/uploads/" + c.getBrochure_filename();
        Image img = URLImage.createToStorage(enco, c.getBrochure_filename(), url);
        ImageViewer imag = new ImageViewer(img);
        c1.add(imag);
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        SpanLabel titre = new SpanLabel("le titre : " + c.getTitre());

        SpanLabel description = new SpanLabel("La description : " + c.getDescription());
        SpanLabel video = new SpanLabel("Le video est :" + c.getVideo());
        Button supprimer = new Button("supprimer");
        Button modifier = new Button("modifier");
        // c2.add(l);
        c2.add(titre);
        c2.add(description);
        c2.add(video);
        c2.add(supprimer);
        c2.add(modifier);

        c1.add(c2);
        c1.getStyle().setBorder(Border.createLineBorder(2));
        c1.getStyle().setMargin(1, 1, 1, 1);
        c1.getStyle().setPadding(0, 0, 0, 0);
        c1.getStyle().setBgColor(0xffabab);

        supprimer.addActionListener(evt -> {
            //creation dune instance de la classe publicztion servicre
            PublicationService es = new PublicationService();
            if (Dialog.show("confirmer", "voulez vous le supprimer ?", "ok", "Cancel")) {
                es.deletePubs(c.getId());
                HomeForm_1 h = new HomeForm_1(theme, current);
                h.getCurrent().show();
            }
        });

        modifier.addPointerPressedListener(new ActionListener() {
            String fileName;
            Image img2;

            @Override
            public void actionPerformed(ActionEvent evt) {

                Form modif = new Form();
                modif.setTitle("Modifier publication");

                modif.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> current.showBack());

                modif.setLayout(BoxLayout.y());
                PublicationService ser = new PublicationService();
                TextField Titre = new TextField(c.getTitre());
                TextField Description = new TextField(c.getDescription());
                // TextField Image = new TextField(c.getBrochure_filename());

                final String[] jobPic = new String[1];
                Label jobIcon = new Label();
                Button image = new Button(FontImage.MATERIAL_ADD_A_PHOTO);
                final String[] image_name = {""};
                final String[] filePath = {""};
                fileName = "";
                ImageViewer imvAdd = new ImageViewer();
                image.addActionListener((ActionEvent actionEvent) -> {
                    Display.getInstance().openGallery(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ev) {

                            filePath[0] = (String) ev.getSource();
                            // System.out.println(ev.getSource());
                            int fileNameIndex = filePath[0].lastIndexOf("/") + 1;
                            fileName = filePath[0].substring(fileNameIndex);
                            try {
                                img2 = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                                jobIcon.setIcon(img2);
                                imvAdd.setImage(img2);
                            } catch (Exception e) {
                            }
                        }
                    }, Display.GALLERY_IMAGE);
                });

                //  TextField Video = new TextField(c.getVideo());
                Button btnVideo = new Button(FontImage.MATERIAL_VIDEOCAM);
                SpanLabel vidroute = new SpanLabel();

                btnVideo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (Dialog.show("Caméra ou Galerie", "Souhaitez-vous utiliser l'appareil photo ou la galerie pour la vidéo?", "Caméra", "Galerie")) {
                            String video = Capture.captureVideo();
                            int extpos = video.lastIndexOf(".");
                            String extension = video.substring(extpos);
                            System.out.println(extension);
                            if (video != null) {
                                OutputStream out = null;
                                try {
                                    Random rand = new Random(); //instance of random class
                                    int upperbound = 30;
                                    //generate random values from 0-24
                                    int int_random = rand.nextInt(upperbound);
                                    FileSystemStorage fs = FileSystemStorage.getInstance();
                                    InputStream stream = fs.openInputStream(video);
                                    out = Storage.getInstance().createOutputStream("file://C:/wamp64/www/symfony/web/uploads/" + int_random + extension);
                                    Util.copy(stream, out);
                                    Util.cleanup(stream);
                                    Util.cleanup(out);
                                    String videoPath = "file://C:/wamp64/www/symfony/web/uploads/" + int_random + extension;
                                    vidroute.setText(videoPath);
                                } catch (IOException ex) {
                                    ToastBar.showErrorMessage("Une erreur s'est produite lors de l'enregistrement de la vidéo:" + ex.getMessage());
                                }
                            }
                        } else {
                            openGallery(ee -> {
                                if (ee.getSource() != null) {
                                    OutputStream out = null;
                                    try {
                                        String video = ee.getSource().toString();
                                        System.out.println(video);
                                        int extpos = video.lastIndexOf(".");
                                        String extension = video.substring(extpos);
                                        Random rand = new Random(); //instance of random class
                                        int upperbound = 30;
                                        //generate random values from 0-24
                                        int int_random = rand.nextInt(upperbound);
                                        FileSystemStorage fs = FileSystemStorage.getInstance();
                                        InputStream stream = fs.openInputStream(video);
                                        out = fs.openOutputStream("file://C:/wamp64/www/symfony/web/uploads/" + int_random + extension);
                                        Util.copy(stream, out);
                                        Util.cleanup(stream);
                                        Util.cleanup(out);
                                        String videoPath = "file://C:/wamp64/www/symfony/web/uploads/" + int_random + extension;
                                        vidroute.setText(videoPath);
                                    } catch (IOException ex) {
                                        ToastBar.showErrorMessage("Une erreur s'est produite lors de l'enregistrement de la vidéo:" + ex.getMessage());
                                    } finally {
                                        try {
                                            out.close();
                                        } catch (IOException ex) {
                                            ToastBar.showErrorMessage("Une erreur s'est produite lors de l'enregistrement de la vidéo:" + ex.getMessage());
                                        }
                                    }
                                }
                            }, CN1Constants.GALLERY_VIDEO);
                        }
                    }
                });

                Button Valider = new Button("Valider");
                Button Annuler = new Button("Annuler");
                Annuler.addPointerPressedListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        ListPubsForm h = new ListPubsForm(current, theme);
                        h.current.show();
                    }
                });

                modif.add(Titre);
                modif.add(Description);
                modif.add(image);
                modif.add(vidroute);
                modif.add(btnVideo);
                modif.add(Valider);
                modif.add(Annuler);
                modif.add(imvAdd);
                modif.show();
                Valider.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        ser.ModifierPubs(c.getId(), Titre.getText(), fileName, Description.getText(), vidroute.getText());
                        try {
                            String pathToBeStored = "file://C:/wamp64/www/symfony/web/uploads/" + fileName;
                            OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                            ImageIO.getImageIO().save(img2, os, ImageIO.FORMAT_PNG, 0.9f);
                            os.close();
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                        ListPubsForm h = new ListPubsForm(current, theme);
                        h.current.show();

                    }
                });

            }
        }
        );
        imag.addPointerReleasedListener(ev
                -> {
            Form detail = new Form();
            detail.setTitle("Detail publication");

            detail.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> current.showBack());

            detail.setLayout(BoxLayout.yCenter());

            EncodedImage enco1 = EncodedImage.createFromImage(theme.getImage("load.png"), false);
            String url1 = "http://localhost/symfony/web/uploads/" + c.getBrochure_filename();
            Image img1 = URLImage.createToStorage(enco, c.getBrochure_filename(), url1);
            ImageViewer imag1 = new ImageViewer(img1);

            SpanLabel titre1 = new SpanLabel("le titre : " + c.getTitre());

            SpanLabel description1 = new SpanLabel("La description : " + c.getDescription());
            SpanLabel video1 = new SpanLabel("L video est :" + c.getVideo());

            Button btnComment = new Button("Commenter");
            TextArea commentaire = new TextArea();
            commentaire.setHint("Commentaire");

            Button clickbtn = new Button("liste commentaire");

            clickbtn.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    PublicationService se = new PublicationService();
                    Accordion accr = new Accordion();
                    String contenu = "";
                    Label ContentL = new Label();
                    for (int i = 0; i < se.getAllCommentsCols().size(); i++) {
                        if (se.getAllCommentsCols().get(i).getPublication_id().equals(c.getTitre())) {
                            contenu = se.getAllCommentsCols().get(i).getDescription();
                            ContentL.setText(contenu);
                            accr.addContent("comments", new SpanLabel(ContentL.getText()));
                        }
                    }
                    detail.add(accr);

                    detail.show();
                }
            });
            btnComment.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                      if (commentaire.getText().length()==0)
                    Dialog.show("Alert", "s'il vous plait remplissez tout les champs", new Command("OK"));
                else
                {
                    try {
                     
                        CommentairePub p = new CommentairePub();
                        p = new CommentairePub(c.getTitre(), commentaire.getText());
                    
                        if (PublicationService.getInstance().addComment(p)) {
                            Dialog.show("Success", "Commentaire ajouter", new Command("OK"));
                            ListPubsForm h = new ListPubsForm(current, theme);
                            h.current.show();
                        } else {
                            Dialog.show("Success", "Commentaire ajouter", new Command("OK"));
                            ListPubsForm h = new ListPubsForm(current, theme);
                            h.current.show();
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }}
            });

            detail.add(imag1);

            detail.add(String.valueOf("                             "));
            Container e = FlowLayout.encloseCenter(titre1);
            Container e1 = FlowLayout.encloseCenter(description1);
            Container e2 = FlowLayout.encloseCenter(video1);

            detail.addAll(e, e1, e2);
            detail.add(commentaire);
            detail.add(btnComment);

            detail.add(clickbtn);
            detail.show();

            PublicationService ser = new PublicationService();
            ser.DetailPubs(c.getId());

        });

        return c1;
    }
}
