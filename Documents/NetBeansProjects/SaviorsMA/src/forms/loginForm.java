package forms;

import com.codename1.components.ImageViewer;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import models.Collecte;
import models.UserLogin;
import models.UserSignup;
import services.CollecteService;
import services.LoginService;
import java.io.OutputStream;
import services.SignupService;
import com.codename1.capture.Capture;
import com.codename1.components.Accordion;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.*;
import com.codename1.io.File;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.util.Callback;
import static forms.AddComment.id;
import static forms.EventDetails.e;
import static forms.ListComments.e;
import java.io.IOException;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.util.Random;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import models.CategorieProduit;
import models.CommentEvent;
import models.DonCollecte;
import models.Event;
import models.Produit;
import models.ProduitComment;
import models.categorieCollecte;
import models.commentaireCollecte;
import services.EventService;
import services.ServiceCategorie;
import services.ServiceProduit;
import services.WebService;

public class loginForm extends Form {

    private TextField username;
    private TextField password;
    private Button login;
    private ImageViewer img;
    private int user_id;
    private String fileName;
    private String fileNamee;
    private ImageViewer imv;
    private String url;
    private String nomCollecte;
    private int fontAtteintCF;
    private Label nomCollecteL;
    private Label infoProvidedL;
    private Label deadCollectL;
    private Label fontAtteintCFL;
    private Label l;
    private Image im;
    private Label l2;
    private Image img2;
    private Image img3;
    private Button modifier;
    private Button modifierCollecte;
    private Button supprimerCollecte;
    private Button detailsCollecteBtn;
    private Button faireUnDonBtn;
    Form current;
    String fileNameProd;
    Image img2Prod;
    java.util.List<CategorieProduit> listeProd = new ArrayList<>();
    String fileNameProdCat;
    Image img2ProdCat;
    java.util.List<CategorieProduit> listeProdCat = new ArrayList<>();

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public TextField getPassword() {
        return password;
    }

    public void setPassword(TextField password) {
        this.password = password;
    }

    public Button getLogin() {
        return login;
    }

    public void setLogin(Button login) {
        this.login = login;
    }

    public ImageViewer getImg() {
        return img;
    }

    public void setImg(ImageViewer img) {
        this.img = img;
    }

    public loginForm(Resources theme) {

        super("Connexion", BoxLayout.y());
        Toolbar.setGlobalToolbar(true);
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        //FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_FACE, s);
        FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_HOME, s);
        FontImage icon3 = FontImage.createMaterial(FontImage.MATERIAL_EMOJI_EVENTS, s);
        FontImage icon4 = FontImage.createMaterial(FontImage.MATERIAL_LOCAL_ATTRACTION, s);
        FontImage icon5 = FontImage.createMaterial(FontImage.MATERIAL_LOCAL_PHONE, s);
        //super.getToolbar().addCommandToSideMenu("", icon, (e) -> Log.p("Clicked"));
        super.getToolbar().addCommandToSideMenu("", theme.getImage("SaviorsLogo.png"), e -> {
        });
        super.getToolbar().addCommandToSideMenu("Accueil", icon2, e -> {
        });
        super.getToolbar().addCommandToSideMenu("Actualités", icon3, e -> {
        });
        super.getToolbar().addCommandToSideMenu("A Propos", icon4, e -> {
        });
        super.getToolbar().addCommandToSideMenu("Contact", icon5, e -> {
        });

        /*tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
        });
        tb.addMaterialCommandToSideMenu("Website", FontImage.MATERIAL_WEB, e -> {
        });
        tb.addMaterialCommandToSideMenu("Settings", FontImage.MATERIAL_SETTINGS, e -> {
        });
        tb.addMaterialCommandToSideMenu("About", FontImage.MATERIAL_INFO, e -> {
        });*/
        img = new ImageViewer();
        img.setImage(theme.getImage("SaviorsLogo.png"));
        username = new TextField(null, "Nom d'utilisateur");
        password = new TextField(null, "Mot de Passe", 0, TextField.PASSWORD);
        login = new Button("Se Connecter");
        /*this.getToolbar().addCommandToLeftBar("Saviors", null, (evt) -> {
            //retour.showBack();
        });*/
        this.getToolbar().addCommandToRightBar("Inscription", null, (evt) -> {
            Dialog ip = new InfiniteProgress().showInifiniteBlocking();
            new signupForm(this, theme).show();
        });

        login.addActionListener((evt) -> {
            if ((username.getText().length() == 0) || (password.getText().length() == 0)) {
                Dialog.show("Alerte", "Champs Requis", "OK", null);
            } else {
                try {
                    UserLogin ul = new UserLogin(username.getText(), password.getText());
                    if (new LoginService().connectUser(ul)) {
                        Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                        //Dialog.show("SUCCES", "Connecté avec succés !", "OK", null);
                        UserSignup us = new UserSignup(username.getText(), password.getText());
                        System.out.println(new LoginService().getUser(us));
                        if (new LoginService().getUser(us).toString().contains("Association")) {
                            System.out.println("Connecté en tant qu'association !");
                        } else if (new LoginService().getUser(us).toString().contains("Fournisseur")) {
                            System.out.println("Connecté en tant que fournisseur !");
                        } else {
                            System.out.println("Connecté en tant que membre !");
                        }
                        HomeForm hf = new HomeForm(theme);

                        hf.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                        });
                        hf.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                            if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                Dialog ip2 = new InfiniteProgress().showInifiniteBlocking();
                                new loginForm(theme).show();
                            }
                        });
                        FontImage icon6 = FontImage.createMaterial(FontImage.MATERIAL_FORUM, s);
                        FontImage icon7 = FontImage.createMaterial(FontImage.MATERIAL_EVENT, s);
                        FontImage icon8 = FontImage.createMaterial(FontImage.MATERIAL_PUBLISH, s);
                        FontImage icon9 = FontImage.createMaterial(FontImage.MATERIAL_NOT_INTERESTED, s);
                        FontImage icon10 = FontImage.createMaterial(FontImage.MATERIAL_COLLECTIONS, s);
                        FontImage icon11 = FontImage.createMaterial(FontImage.MATERIAL_SHOP, s);
                        //super.getToolbar().addCommandToSideMenu("", icon, (e) -> Log.p("Clicked"));
                        hf.getToolbar().addCommandToSideMenu("", theme.getImage("SaviorsLogo.png"), e -> {
                        });
                        hf.getToolbar().addCommandToSideMenu("Accueil", icon2, e -> {
                        });
                        hf.getToolbar().addCommandToSideMenu("Actualités", icon3, e -> {
                        });
                        hf.getToolbar().addCommandToSideMenu("A Propos", icon4, e -> {
                        });
                        hf.getToolbar().addCommandToSideMenu("Contact", icon5, e -> {
                        });
                        hf.getToolbar().addCommandToSideMenu("Evenement", icon7, e -> {
                            int countEvent = 0;
                            for (int prod = 0; prod < new WebService().getAllEventsP().size(); prod++) {
                                if (new WebService().getAllEventsP().get(prod).getUser().equals(username.getText())) {
                                    countEvent++;
                                }

                                if (countEvent == 1) {
                                    Dialog.show("Information", "Vous avez " + countEvent + " evenement en attente de réponse par l'administrateur.", new Command("OK"));
                                }
                                if (countEvent > 0 && countEvent != 1) {
                                    Dialog.show("Information", "Vous avez " + countEvent + " evenements en attente de réponse par l'administrateur.", new Command("OK"));
                                }
                            }
                            ListEvents le = new ListEvents(theme, hf);
                            le.setName("Evenements");
                            le.setTitle("Evenements");
                            le.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                            //Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
                            FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
                            FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
                            le.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, back -> {
                                hf.showBack();
                            });
                            Button addEventBtn = new Button("Ajouter un Evenement");
                            if (new LoginService().getUser(us).toString().contains("Association")) {
                                le.add(addEventBtn);
                            } else {
                                le.add(String.valueOf("      "));
                            }
                            addEventBtn.addActionListener((addEvent) -> {
                                AddEvent ae = new AddEvent(theme, le);
                                ae.setName("Ajouter un Evenement");
                                ae.setTitle("Ajouter un Evenement");
                                ae.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                                FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
                                /**/
                                Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                Picker datePicker = new Picker();
                                datePicker.setType(Display.PICKER_TYPE_DATE);
                                TextField nom = new TextField();
                                nom.setHint("Nom");
                                TextField description = new TextField();
                                description.setHint("Description");
                                TextField lieu = new TextField();
                                lieu.setHint("Lieu");

                                Label Date = new Label("Date de l'Evenement : ");
                                Button img = new Button("Ajouter une image", icone);

                                Button b = new Button("Ajouter");
                                WebService ws = new WebService();
                                photos.add(nom);
                                photos.add(description);
                                photos.add(lieu);
                                photos.add(Date);
                                photos.add(datePicker);

                                photos.add(img);
                                photos.add(b);
                                ae.add(photos);
                                final String[] jobPic = new String[1];
                                Label jobIcon = new Label();
                                Button image = new Button(FontImage.MATERIAL_ADD_A_PHOTO);
                                final String[] image_name = {""};
                                final String[] filePath = {""};
                                fileNameProd = "";
                                ImageViewer imgPROD = new ImageViewer();
                                img.addActionListener((ActionEvent actionEvent) -> {
                                    Display.getInstance().openGallery(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent ev) {

                                            filePath[0] = (String) ev.getSource();
                                            int fileNameIndex = filePath[0].lastIndexOf("/") + 1;
                                            fileNameProd = filePath[0].substring(fileNameIndex);
                                            try {
                                                img2Prod = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                                                jobIcon.setIcon(img2Prod);
                                                imgPROD.setImage(img2Prod);

                                            } catch (Exception e) {
                                            }
                                        }
                                    }, Display.GALLERY_IMAGE);
                                });
                                b.addActionListener(ef -> {
                                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                    Date dateobj = new Date();
                                    Date da = datePicker.getDate();
                                    String st = df.format(dateobj);
                                    String st1 = df.format(da);
                                    Date dnow = new Date();
                                    Date dc = new Date();
                                    try {
                                        dnow = df.parse(st);
                                        dc = df.parse(st1);
                                    } catch (ParseException ex) {

                                    }
                                    if (nom.getText().equals("") || description.getText().equals("") || lieu.getText().equals("") || fileNameProd.equals("")) {
                                        Dialog.show("ERREUR", "Vérifiez vos informations", "OK", null);

                                    } else {
                                        Event ev = new Event();
                                        ev.setDateevent(st1);
                                        ev.setDescription(description.getText());
                                        ev.setImage(fileNameProd);
                                        ev.setNom(nom.getText());
                                        ev.setLieu(lieu.getText());
                                        ev.setUser(username.getText());
                                        try {
                                            String pathToBeStored = "file://C:/wamp64/www/symfony/web/events/" + fileNameProd;
                                            OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                                            ImageIO.getImageIO().save(img2Prod, os, ImageIO.FORMAT_PNG, 0.9f);
                                            os.close();
                                        } catch (Exception error) {
                                            System.out.println(error);
                                        }
                                        ws.addEvent(ev);

                                    }
                                });
                                ae.show();
                            });
                            WebService ws = new WebService();
                            EventService ds = new EventService();
                            Map x = ws.getResponse("event/listmobile");
                            ArrayList<Event> listevents = ds.getListsEvents(x);
                            for (Event event : listevents) {
                                FontImage fi = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                                EncodedImage enco = EncodedImage.createFromImage(fi.scaled(fi.getWidth() * 15, fi.getHeight() * 10), false);
                                Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                ImageViewer imv = null;
                                Image img;
                                EncodedImage encoded = null;
                                Label aa = new Label(event.getNom());
                                img = URLImage.createToStorage(enco, event.getImage(), "http://localhost/symfony/web/events/" + event.getImage());
                                imv = new ImageViewer(img);
                                photos.add(imv);
                                photos.add(aa);
                                try {
                                    ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));

                                    photos.add(sep);
                                } catch (IOException ex) {
                                }
                                le.add(photos);

                                aa.addPointerPressedListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {

                                        EventDetails.e = event;
                                        EventDetails evd = new EventDetails(theme, le);
                                        WebService ws = new WebService();
                                        evd.setName("Détail de l'évenement");
                                        evd.setTitle("Détail de l'évenement");
                                        evd.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                                        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
                                        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
                                        FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
                                        if (!new LoginService().getUser(us).toString().contains("Association")) {
                                            evd.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_SUBSCRIPTIONS, ev -> {
                                                ws.addParticipate(aa.getText(), username.getText());
                                            });
                                        }

                                        evd.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_LIST, ev -> {
                                            ListComments ecom = new ListComments(theme, le);
                                            ecom.setName("Commentaires");
                                            ecom.setTitle("Commentaires");
                                            ecom.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                                            if (!new LoginService().getUser(us).toString().contains("Association")) {

                                                ecom.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, evcom -> {
                                                    AddComment ac = new AddComment(theme, ecom);
                                                    ac.setName("Ajouter un Commentaire");
                                                    ac.setTitle("Ajouter un Commentaire");
                                                    ac.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

                                                    //WebService ws = new WebService();
                                                    Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                                                    TextArea a = new TextArea();
                                                    a.setHint("Commentaire");
                                                    Button b = new Button("Ajouter");

                                                    photos.add(a);
                                                    photos.add(b);

                                                    ac.add(photos);

                                                    b.addPointerPressedListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent evt) {
                                                            CommentEvent ce = new CommentEvent();
                                                            ce.setInEvent(id);
                                                            ce.setText(a.getText());
                                                            ce.setNomEvent(aa.getText());
                                                            ce.setUsername(username.getText());
                                                            ws.addComment(ce);
                                                            le.show();
                                                        }
                                                    });
                                                    ac.show();
                                                });
                                            }
                                            ecom.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evcom -> {
                                                evd.showBack();
                                            });
                                            EventService ds = new EventService();
                                            Map x = ws.getResponse("event/ListCom/" + event.getId());
                                            ArrayList<CommentEvent> listevents = ds.getListscomments(x);
                                            for (CommentEvent evcom : listevents) {
                                                Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                                ImageViewer imv = null;
                                                Image img;
                                                EncodedImage encoded = null;
                                                Label a = new Label(evcom.getText());
                                                Label b = new Label("Par : " + evcom.getUsername());

                                                photos.add(a);
                                                photos.add(b);
                                                try {
                                                    ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));

                                                    photos.add(sep);
                                                } catch (IOException ex) {
                                                }
                                                ecom.add(photos);

                                                a.addPointerPressedListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent evt) {

                                                    }
                                                });

                                            }
                                            ecom.show();
                                        });

                                        System.out.println(e.toString());
                                        Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                        ImageViewer imv = new ImageViewer();
                                        //Image img;
                                        FontImage fiDet = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                                        EncodedImage encoDet = EncodedImage.createFromImage(fiDet.scaled(fiDet.getWidth() * 15, fiDet.getHeight() * 10), false);
                                        Slider rate = createStarRankSlider();
                                        Container CR = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                        Button bou = new Button("Donner une note");
                                        if (!new LoginService().getUser(us).toString().contains("Association")) {
                                            CR.add(FlowLayout.encloseCenter(rate));
                                            CR.add(FlowLayout.encloseCenter(bou));
                                        }
                                        Label a = new Label("Nom : " + event.getNom());
                                        Label b = new Label("Description : " + event.getDescription());
                                        Label c = new Label("Lieu  : " + event.getLieu());
                                        Label d = new Label("Date : " + event.getDateevent());
                                        Label ef = new Label("Nombre participants : " + event.getNbrInterest());
                                        Label ed = new Label("Note : " + event.getMoyenne());
                                        Label er = new Label(ws.getCountComment("event/countCom/" + event.getId()) + " Commentaires");
                                        Image img = URLImage.createToStorage(encoDet, event.getImage(), "http://localhost/symfony/web/app_dev.php/events/" + event.getImage());
                                        imv = new ImageViewer(img);
                                        photos.add(imv);

                                        try {
                                            ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));

                                            photos.add(sep);
                                        } catch (IOException ex) {
                                        }
                                        photos.add(a);
                                        photos.add(b);
                                        photos.add(c);
                                        photos.add(d);
                                        photos.add(ef);
                                        photos.add(ed);
                                        photos.add(er);
                                        photos.add(CR);
                                        evd.add(photos);

                                        bou.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent evt) {
                                                int avis = rate.getProgress() / 2;
                                                ws.addRate(event.getId(), avis, username.getText());
                                                le.show();
                                            }
                                        });
                                        evd.show();
                                    }
                                });

                            }
                            le.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                            });
                            le.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                    Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                    new loginForm(theme).show();
                                }
                            });
                            le.show();
                        });
                        hf.getToolbar().addCommandToSideMenu("Publication", icon8, e -> {
                            HomeForm_1 hf1 = new HomeForm_1(theme, hf);
                            hf1.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                            });
                            hf1.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                    Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                    new loginForm(theme).show();
                                }
                            });
                            hf1.show();
                        });
                        hf.getToolbar().addCommandToSideMenu("Réclamation", icon9, e -> {
                        });
                        hf.getToolbar().addCommandToSideMenu("Collecte", icon10, e -> {
                            Dialog ip5 = new InfiniteProgress().showInifiniteBlocking();
                            CollecteForm cf = new CollecteForm(theme);
                            cf.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                            });
                            cf.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                    Dialog ip2 = new InfiniteProgress().showInifiniteBlocking();
                                    new loginForm(theme).show();
                                }
                            });
                            cf.getToolbar().addCommandToSideMenu("", theme.getImage("SaviorsLogo.png"), e1 -> {
                                Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                hf.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                });
                                hf.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                    if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                        Dialog ip2 = new InfiniteProgress().showInifiniteBlocking();
                                        new loginForm(theme).show();
                                    }
                                });
                                hf.show();
                            });
                            CollecteService cser = new CollecteService();
                            LoginService lservice = new LoginService();
                            DetailsCollecteForm dc = new DetailsCollecteForm(theme);
                            FaireUnDonCollecteForm donate = new FaireUnDonCollecteForm(theme);

                            Label ltopg = new Label("");
                            Button gérerCollecte = new Button("Gérer mes Collectes");
                            ComboBox<String> cc = new ComboBox();
                            for (int k = 0; k < new CollecteService().getCategories().size(); k++) {
                                int c = k + 1;
                                cc.addItem("Catégorie " + c + ": " + new CollecteService().getCategories().get(k).getTypeCategorie());
                            }
                            cc.setSelectedItem("Catégories des Collectes");
                            if (new LoginService().getUser(us).toString().contains("Association")) {
                                cf.addAll(ltopg, gérerCollecte, cc);
                            } else {
                                cf.addAll(ltopg, cc);
                            }
                            for (int i = 0; i < cser.getAllCollects().size(); i++) {
                                for (int j = 0; j < lservice.getUsers().size(); j++) {
                                    if (cser.getAllCollects().get(i).getUser_id() == lservice.getUsers().get(j).getId()) {
                                        detailsCollecteBtn = new Button("Details");
                                        faireUnDonBtn = new Button("Faire un Don");
                                        //Label idCollecte = new Label(Integer.toString(cser.getAllCollects().get(i).getId()));
                                        Label categorieCollecteDetDCOM = new Label(cser.getAllCollects().get(i).getCatégorie());
                                        //int idCollecteDetCOM = cser.getAllCollects().get(i).getId();
                                        Label nomCollecteDet = new Label("Nom de la Collecte: " + cser.getAllCollects().get(i).getNomCollecte());
                                        Label nomCollecteDetDCOM = new Label(cser.getAllCollects().get(i).getNomCollecte());
                                        Label descCollecteDet = new Label("Description: " + cser.getAllCollects().get(i).getDescriptionCollecte());
                                        Label descCollecteDetCOM = new Label(cser.getAllCollects().get(i).getDescriptionCollecte());
                                        Label budgetCollecteDet = new Label("Budget Demandé: " + cser.getAllCollects().get(i).getBudget());
                                        int budgetCollecteDetCOM = cser.getAllCollects().get(i).getBudget();
                                        Label fondAtteintDet = new Label("Fond Atteint: " + cser.getAllCollects().get(i).getNbreAtteint());
                                        int fondAtteintDetCOM = cser.getAllCollects().get(i).getNbreAtteint();
                                        Label nbreParticipantsDet = new Label("Nombre de Participants: " + cser.getAllCollects().get(i).getNbreParticipantsC());
                                        int nbreParticipantsDetCOM = cser.getAllCollects().get(i).getNbreParticipantsC();
                                        Label imageCollecteDet = new Label(cser.getAllCollects().get(i).getImage());
                                        Label usernameCDet = new Label("Publié par: " + lservice.getUsers().get(j).getUsername());
                                        int userIDCDetCOM = lservice.getUsers().get(j).getId();
                                        String urlDet = url = "http://localhost/symfony/web/uploads/" + imageCollecteDet.getText();
                                        FontImage fiDet = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                                        EncodedImage encoDet = EncodedImage.createFromImage(fiDet.scaled(fiDet.getWidth() * 15, fiDet.getHeight() * 10), false);
                                        Image imDet = URLImage.createToStorage(encoDet, cser.getAllCollects().get(i).getImage(), url);
                                        ImageViewer imvDet = new ImageViewer(imDet);
                                        Collecte colForEdit = new Collecte(nomCollecteDetDCOM.getText(), budgetCollecteDetCOM, fondAtteintDetCOM, descCollecteDetCOM.getText(), nbreParticipantsDetCOM, imageCollecteDet.getText(), categorieCollecteDetDCOM.getText(), userIDCDetCOM);
                                        usernameCDet.setAlignment(LEFT);
                                        nomCollecteDet.setAlignment(LEFT);
                                        descCollecteDet.setAlignment(LEFT);
                                        budgetCollecteDet.setAlignment(LEFT);
                                        fondAtteintDet.setAlignment(LEFT);
                                        nbreParticipantsDet.setAlignment(LEFT);
                                        usernameCDet.getStyle().setFgColor(123456);
                                        nomCollecteDet.getStyle().setFgColor(123456);
                                        descCollecteDet.getStyle().setFgColor(123456);
                                        budgetCollecteDet.getStyle().setFgColor(123456);
                                        fondAtteintDet.getStyle().setFgColor(123456);
                                        nbreParticipantsDet.getStyle().setFgColor(123456);
                                        detailsCollecteBtn.setAlignment(CENTER);
                                        FontImage fi = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                                        EncodedImage enco = EncodedImage.createFromImage(fi.scaled(fi.getWidth() * 15, fi.getHeight() * 10), false);
                                        url = "http://localhost/symfony/web/uploads/" + cser.getAllCollects().get(i).getImage();
                                        nomCollecte = cser.getAllCollects().get(i).getNomCollecte();
                                        fontAtteintCF = cser.getAllCollects().get(i).getNbreAtteint();
                                        nomCollecteL = new Label();
                                        l = new Label("                                               ");
                                        nomCollecteL.setText(nomCollecte);
                                        infoProvidedL = new Label(nomCollecteL.getText() + " - " + fontAtteintCF + " TND contribués");
                                        deadCollectL = new Label("");
                                        if (fontAtteintCF == budgetCollecteDetCOM || budgetCollecteDetCOM - fontAtteintCF < 50) {
                                            deadCollectL = new Label("Collecte Expirée");
                                            faireUnDonBtn.setEnabled(false);
                                            deadCollectL.getStyle().setFgColor(0xFF0000);
                                            deadCollectL.setAlignment(CENTER);
                                            faireUnDonBtn.getStyle().setFgColor(0xFF0000);
                                            faireUnDonBtn.getStyle().setStrikeThru(true);
                                            infoProvidedL.getStyle().setUnderline(true);
                                        }
                                        infoProvidedL.getStyle().setFgColor(123456);
                                        infoProvidedL.setAlignment(CENTER);
                                        /*nomCollecteL.getStyle().setFgColor(123456);
                                        nomCollecteL.setAlignment(CENTER);*/
                                        im = URLImage.createToStorage(enco, cser.getAllCollects().get(i).getImage(), url);
                                        imv = new ImageViewer(im);
                                        cf.addAll(l, imv);
                                        l2 = new Label("");
                                        cf.add(l2);
                                        cf.addAll(deadCollectL, infoProvidedL, detailsCollecteBtn);
                                        Button donsContribuésBtn = new Button("Dons Contribués");
                                        if (!new LoginService().getUser(us).toString().contains("Association")) {
                                            cf.add(faireUnDonBtn);
                                        }
                                        if (new LoginService().getUser(us).toString().contains("Association") && lservice.getUsers().get(j).getUsername().equals(username.getText())) {
                                            cf.add(donsContribuésBtn);
                                        }
                                        donsContribuésBtn.addActionListener((donsC) -> {
                                            Dialog ip8 = new InfiniteProgress().showInifiniteBlocking();
                                            AllDonsForm alldons = new AllDonsForm(theme);
                                            alldons.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                            });
                                            alldons.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                                if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                                    Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                                    new loginForm(theme).show();
                                                }
                                            });
                                            alldons.getToolbar().addCommandToLeftBar("Retour", null, (back) -> {
                                                cf.showBack();
                                                alldons.removeAll();
                                                //lcf.removeAll();
                                            });
                                            for (int k = 0; k < cser.getAllDons().size(); k++) {
                                                if (cser.getAllDons().get(k).getCollectPending().equals(nomCollecteDetDCOM.getText())) {
                                                    int moneyyy = cser.getAllDons().get(k).getMoneyDonated();
                                                    String usernameCom = cser.getAllDons().get(k).getUsername();
                                                    Label moneyyyL = new Label();
                                                    Label usernameComL = new Label();
                                                    Label spaceTop = new Label("   ");
                                                    Label spacesList = new Label("       ");
                                                    moneyyyL.setText("" + moneyyy + " TND");
                                                    usernameComL.setText("Contribué par: " + usernameCom);
                                                    //System.out.println(nomCollecteDet.getText());
                                                    //System.out.println(nomCollecte);
                                                    usernameComL.getStyle().setFgColor(654321);
                                                    moneyyyL.getStyle().setFgColor(654321);
                                                    alldons.addAll(spaceTop, moneyyyL, usernameComL, spacesList);

                                                }
                                            }
                                            alldons.show();
                                        });
                                        Button listeComments = new Button("Liste des Commentaires");
                                        detailsCollecteBtn.addActionListener((det) -> {
                                            Dialog ip2 = new InfiniteProgress().showInifiniteBlocking();
                                            Label spaceDet = new Label("               ");
                                            Label spaceAfterImage = new Label("               ");
                                            TextArea publishComment = new TextArea();
                                            publishComment.setHint("Publier un Commentaire");
                                            Button commentBtn = new Button("Commenter");
                                            Label spaceDown = new Label("          ");

                                            //publishComment.setAlignment(CENTER);
                                            commentBtn.setAlignment(CENTER);
                                            dc.addAll(spaceDet, imvDet, spaceAfterImage, usernameCDet, nomCollecteDet, descCollecteDet, budgetCollecteDet, fondAtteintDet, nbreParticipantsDet);
                                            if (!new LoginService().getUser(us).toString().contains("Association")) {
                                                dc.add(publishComment);
                                                dc.add(commentBtn);
                                                commentBtn.addActionListener((addCom) -> {
                                                    if ((publishComment.getText().length() == 0)) {
                                                        Dialog.show("Alerte", "Champs Requis", "OK", null);
                                                    } else {
                                                        try {
                                                            CollecteService cservice = new CollecteService();
                                                            commentaireCollecte comCol = new commentaireCollecte(publishComment.getText(), username.getText(), nomCollecteDetDCOM.getText());
                                                            if (Dialog.show("Ajout Commentaire", "Ajouter ce Commentaire à cette collecte ?", "OK", "Cancel")) {
                                                                if (new CollecteService().addCommentCollecte(comCol)) {

                                                                    publishComment.setText("");
                                                                    Dialog.show("SUCCES", "Commentaire ajouté avec succés !", "OK", null);
                                                                }
                                                                dc.show();
                                                            }
                                                        } catch (Exception e1) {
                                                            Dialog.show("ERREUR", "Erreur de l'ajout", "OK", null);
                                                        }
                                                    }
                                                });
                                            }

                                            dc.addAll(spaceDown, listeComments);

                                            dc.getToolbar().addCommandToLeftBar("Retour", null, (back) -> {
                                                cf.showBack();
                                                dc.removeAll();
                                                Label detCollecte = new Label("Détails de la Collecte");
                                                detCollecte.setAlignment(CENTER);
                                                detCollecte.getStyle().setFgColor(123456);
                                                dc.add(detCollecte);
                                            });
                                            dc.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                            });
                                            dc.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                                if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                                    Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                                    new loginForm(theme).show();
                                                }
                                            });
                                            dc.show();

                                        });

                                        faireUnDonBtn.addActionListener((don) -> {
                                            Dialog ip2 = new InfiniteProgress().showInifiniteBlocking();
                                            Label spaceDon = new Label("               ");
                                            Label spaceAfterDon = new Label("               ");
                                            TextArea donateF = new TextArea();
                                            donateF.setHint("Contribuer un Don (En dinars)");
                                            Button donateBtn = new Button("Faire un Don");
                                            //Button donsContribuésBtnOtherRoles = new Button("Dons Contribués");
                                            Label spaceLast = new Label("          ");
                                            donate.addAll(spaceDon, imvDet, spaceAfterDon, usernameCDet, nomCollecteDet, descCollecteDet, budgetCollecteDet, fondAtteintDet, nbreParticipantsDet);
                                            donate.add(donateF);
                                            donate.add(donateBtn);
                                            donate.add("   ");
                                            //donate.add(donsContribuésBtnOtherRoles);
                                            if (budgetCollecteDetCOM == fondAtteintDetCOM || budgetCollecteDetCOM - fondAtteintDetCOM < 50) {
                                                Label deadCol = new Label("Cette Collecte est expirée !");
                                                donateF.setEnabled(false);
                                                donateF.remove();
                                                Label spaceDonation = new Label("  ");
                                                donateBtn.setEnabled(false);
                                                donateBtn.setText("");
                                                deadCol.getStyle().setFgColor(0xFF0000);
                                                donateF.setText("");
                                                deadCol.setAlignment(CENTER);
                                                donate.addAll(spaceDonation, deadCol);
                                            }

                                            /*donsContribuésBtnOtherRoles.addActionListener((donsC) -> {
                                                Dialog ip8 = new InfiniteProgress().showInifiniteBlocking();
                                                AllDonsForm alldons = new AllDonsForm(theme);
                                                alldons.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                                });
                                                alldons.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                                    if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                                        Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                                        new loginForm(theme).show();
                                                    }
                                                });
                                                alldons.getToolbar().addCommandToLeftBar("Retour", null, (back) -> {
                                                    donate.showBack();
                                                    alldons.removeAll();
                                                    //lcf.removeAll();
                                                });
                                                for (int k = 0; k < cser.getAllDons().size(); k++) {
                                                    if (cser.getAllDons().get(k).getCollectPending().equals(nomCollecteDetDCOM.getText())) {
                                                        int moneyyy = cser.getAllDons().get(k).getMoneyDonated();
                                                        String usernameCom = cser.getAllDons().get(k).getUsername();
                                                        Label moneyyyL = new Label();
                                                        Label usernameComL = new Label();
                                                        Label spaceTop = new Label("   ");
                                                        Label spacesList = new Label("       ");
                                                        moneyyyL.setText("" + moneyyy);
                                                        usernameComL.setText("Contribué par: " + usernameCom);
                                                        //System.out.println(nomCollecteDet.getText());
                                                        //System.out.println(nomCollecte);
                                                        usernameComL.getStyle().setFgColor(654321);
                                                        moneyyyL.getStyle().setFgColor(654321);
                                                        alldons.addAll(spaceTop, moneyyyL, usernameComL, spacesList);

                                                    }
                                                }
                                                alldons.show();
                                            });*/
                                            donateBtn.addActionListener((donation) -> {

                                                if (Integer.parseInt(donateF.getText()) > budgetCollecteDetCOM) {
                                                    Dialog.show("Alerte", "Vous avez contribué un montant supérieur au Budget !", "OK", null);
                                                } else if ((donateF.getText().length() == 0)) {
                                                    Dialog.show("Alerte", "Champs Requis", "OK", null);
                                                } else if ((Integer.parseInt(donateF.getText()) < 50) && Integer.parseInt(donateF.getText()) > 0) {
                                                    Dialog.show("Alerte", "Le montant minimum de la contribution est 50 TND !", "OK", null);
                                                    donateF.setText("");
                                                } else if (Integer.parseInt(donateF.getText()) == 0) {
                                                    Dialog.show("Alerte", "Vous avez saisi un montant nul !", "OK", null);
                                                } else if (Integer.parseInt(donateF.getText()) < 0) {
                                                    Dialog.show("Alerte", "Vous avez saisi un montant négatif !", "OK", null);
                                                    donateF.setText("");
                                                } else {
                                                    try {
                                                        java.util.Date dt = new java.util.Date();

                                                        java.text.SimpleDateFormat sdf
                                                                = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                                                        String currentTime = sdf.format(dt);
                                                        CollecteService cservice = new CollecteService();
                                                        int moneyN = Integer.parseInt(donateF.getText());
                                                        DonCollecte donationCol = new DonCollecte(username.getText(), moneyN, currentTime, nomCollecteDetDCOM.getText());
                                                        int moneyDonatedTotal = fondAtteintDetCOM + moneyN;
                                                        Label moneyUpdated = new Label("Fond Atteint: " + moneyDonatedTotal);
                                                        moneyUpdated.getStyle().setFgColor(123456);
                                                        colForEdit.setNbreAtteint(moneyN);
                                                        int n = colForEdit.getNbreAtteint();
                                                        if (Dialog.show("Faire un Don", "Contribuer ce Don à cette collecte ?", "OK", "Cancel")) {
                                                            if (new CollecteService().addMoneyCollecte(colForEdit) && new CollecteService().faireUnDon(donationCol)) {
                                                                //System.out.println(colForEdit.toString());
                                                                donateF.setText("");
                                                                Dialog.show("SUCCES", moneyN + " TND ont été contribués avec succés ! Merci 😍", "OK", null);
                                                                //fondAtteintDetCOM = fondAtteintDetCOM + moneyN;
                                                            }
                                                        }

                                                    } catch (NumberFormatException e1) {
                                                        Dialog.show("ERREUR", "Erreur de la contribution", "OK", null);
                                                    }
                                                }
                                            });

                                            donate.add(spaceLast);
                                            donate.getToolbar().addCommandToLeftBar("Retour", null, (back) -> {
                                                cf.showBack();
                                                donate.removeAll();
                                                Label donationCollecte = new Label("Faire un Don");
                                                donationCollecte.setAlignment(CENTER);
                                                donationCollecte.getStyle().setFgColor(123456);
                                                donate.add(donationCollecte);
                                            });
                                            donate.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                            });
                                            donate.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                                if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                                    Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                                    new loginForm(theme).show();
                                                }
                                            });
                                            donate.show();

                                        });

                                        listeComments.addActionListener((listC) -> {
                                            Dialog ip8 = new InfiniteProgress().showInifiniteBlocking();
                                            ListeCommentsColsForm lcf = new ListeCommentsColsForm(theme);
                                            lcf.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                            });
                                            lcf.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                                if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                                    Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                                    new loginForm(theme).show();
                                                }
                                            });
                                            lcf.getToolbar().addCommandToLeftBar("Retour", null, (back) -> {
                                                dc.showBack();
                                                lcf.removeAll();
                                                //lcf.removeAll();
                                            });
                                            for (int k = 0; k < cser.getAllCommentsCols().size(); k++) {

                                                if (cser.getAllCommentsCols().get(k).getCollectP().equals(nomCollecteDetDCOM.getText())) {
                                                    String contenuCom = cser.getAllCommentsCols().get(k).getContenu();
                                                    String usernameCom = cser.getAllCommentsCols().get(k).getUser_id();
                                                    Label contenuComL = new Label();
                                                    Label usernameComL = new Label();
                                                    Label spaceTop = new Label("   ");
                                                    Label spacesList = new Label("       ");
                                                    contenuComL.setText(contenuCom);
                                                    usernameComL.setText("Publié par: " + usernameCom);
                                                    //System.out.println(nomCollecteDet.getText());
                                                    //System.out.println(nomCollecte);
                                                    usernameComL.getStyle().setFgColor(654321);
                                                    contenuComL.getStyle().setFgColor(654321);
                                                    lcf.addAll(spaceTop, usernameComL, contenuComL, spacesList);

                                                }
                                            }

                                            /*System.out.println(nomCollecteDet.getText());
                                                    System.out.println(contenuComL.getText());
                                                    lcf.add(contenuComL.getText());
                                                    lcf.show();*/
                                            lcf.show();
                                        });
                                    }

                                }
                            }
                            gérerCollecte.addActionListener((eb) -> {
                                int countCol = 0;
                                for (int prod = 0; prod < new CollecteService().getAllCollectsP().size(); prod++) {
                                    if (new CollecteService().getAllCollectsP().get(prod).getUsername().equals(username.getText())) {
                                        countCol++;
                                    }
                                }
                                if (countCol == 1) {
                                    Dialog.show("Information", "Vous avez " + countCol + " collecte en attente de réponse par l'administrateur.", new Command("OK"));
                                }
                                if (countCol > 0 && countCol != 1) {
                                    Dialog.show("Information", "Vous avez " + countCol + " collectes en attente de réponse par l'administrateur.", new Command("OK"));
                                }
                                Dialog ip2 = new InfiniteProgress().showInifiniteBlocking();
                                listeCollecteForm lf = new listeCollecteForm(theme);

                                Label ltop = new Label("");
                                Button créerCol = new Button("Créer une nouvelle Collecte");
                                Button créerCat = new Button("Créer une nouvelle Catégorie");
                                CollecteService cs = new CollecteService();
                                LoginService lser = new LoginService();
                                lf.addAll(ltop, créerCol, créerCat);
                                for (int i = 0; i < cs.getAllCollects().size(); i++) {
                                    for (int j = 0; j < lser.getUsers().size(); j++) {
                                        if (lser.getUsers().get(j).getUsername().equals(username.getText())) {
                                            int idU = lser.getUsers().get(j).getId();
                                            if (idU == cs.getAllCollects().get(i).getUser_id()) {
                                                FontImage fi = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                                                EncodedImage enco = EncodedImage.createFromImage(fi.scaled(fi.getWidth() * 15, fi.getHeight() * 10), false);

                                                url = "http://localhost/symfony/web/uploads/" + cs.getAllCollects().get(i).getImage();
                                                nomCollecte = cs.getAllCollects().get(i).getNomCollecte();
                                                int budgetCollecte = cs.getAllCollects().get(i).getBudget();
                                                String descriptionCollecte = cs.getAllCollects().get(i).getDescriptionCollecte();
                                                String categorieCollecte = cs.getAllCollects().get(i).getCatégorie();
                                                int idCollecte = cs.getAllCollectsEdit().get(i).getId();
                                                String nomImage = cs.getAllCollects().get(i).getImage();
                                                int nbreAtteintMod = cs.getAllCollects().get(i).getNbreAtteint();
                                                nomCollecteL = new Label();
                                                //Label budgetL = new Label(budgetCollecte);
                                                Label descriptionL = new Label(descriptionCollecte);
                                                Label allInfos = new Label(nomCollecte);
                                                allInfos.setAlignment(CENTER);
                                                allInfos.getStyle().setFgColor(123456);
                                                nomCollecteL.setText(nomCollecte);
                                                nomCollecteL.getStyle().setFgColor(123456);
                                                nomCollecteL.setAlignment(CENTER);
                                                im = URLImage.createToStorage(enco, cs.getAllCollects().get(i).getImage(), url);
                                                imv = new ImageViewer(im);
                                                modifier = new Button("Modifier");
                                                //modifier.setAlignment(RIGHT);
                                                ModCollecteForm mc = new ModCollecteForm(theme, lf);
                                                modifier.addActionListener((mod) -> {

                                                    Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                                    TextField nomCollecteE = new TextField(null, "Nom de la Collecte");
                                                    TextField budgetE = new TextField(null, "Budget");
                                                    TextArea descriptionE = new TextArea(null);
                                                    descriptionE.setHint("Description");
                                                    descriptionE.setHeight(500);
                                                    ComboBox<String> cE = new ComboBox();
                                                    for (int k = 0; k < new CollecteService().getCategories().size(); k++) {
                                                        cE.addItem(new CollecteService().getCategories().get(k).toString());
                                                    }
                                                    final String[] jobPicc = new String[1];
                                                    Label jobIconn = new Label();
                                                    Button imagee = new Button("Modifier l'image");
                                                    final String[] image_namee = {""};
                                                    final String[] filePathh = {""};

                                                    Label imgName = new Label(nomImage);
                                                    fileNamee = "";
                                                    imagee.addActionListener((ActionEvent actionEvent) -> {
                                                        Display.getInstance().openGallery(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent ev) {

                                                                filePathh[0] = (String) ev.getSource();
                                                                System.out.println(ev.getSource());
                                                                int fileNameIndexx = filePathh[0].lastIndexOf("/") + 1;
                                                                fileNamee = filePathh[0].substring(fileNameIndexx);
                                                                //Image img2 = null;
                                                                try {
                                                                    img3 = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePathh[0]));
                                                                    //image_name[0] = System.currentTimeMillis() + ".png";
                                                                    jobIconn.setIcon(img3);
                                                                    imgName.setText(fileNamee);
                                                                    /*String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + fileName;
                                                            OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                                                            ImageIO.getImageIO().save(img2, os, ImageIO.FORMAT_PNG, 0.9f);
                                                            os.close();*/
                                                                    //System.out.println(pathToBeStored);
                                                                } catch (Exception e) {
                                                                    //e.printStackTrace();
                                                                    //ToastBar.showErrorMessage("Veuillez importer une image valide !");
                                                                }
                                                            }
                                                        }, Display.GALLERY_IMAGE);
                                                    });

                                                    imgName.setAlignment(RIGHT);
                                                    cE.setSelectedItem(categorieCollecte);
                                                    nomCollecteE.setText(allInfos.getText());
                                                    budgetE.setText(Integer.toString(budgetCollecte));
                                                    descriptionE.setText(descriptionCollecte);
                                                    modifierCollecte = new Button("Modifier");
                                                    supprimerCollecte = new Button("Supprimer");
                                                    if (budgetCollecte == nbreAtteintMod || budgetCollecte - nbreAtteintMod < 50) {
                                                        Dialog.show("Alerte", "Collecte non plus modifiable. Elle devra être supprimée !", new Command("OK"));
                                                        modifierCollecte.setEnabled(false);
                                                        modifierCollecte.getStyle().setFgColor(0xFF0000);
                                                        nomCollecteE.setEnabled(false);
                                                        budgetE.setEnabled(false);
                                                        descriptionE.setEnabled(false);
                                                        imagee.setEnabled(false);
                                                        imagee.getStyle().setFgColor(0xFF0000);
                                                        imgName.getStyle().setStrikeThru(true);
                                                    }
                                                    Label spaceDel = new Label("        ");
                                                    mc.addAll(nomCollecteE, budgetE, descriptionE, cE, imagee, imgName, modifierCollecte, spaceDel, supprimerCollecte);
                                                    mc.getToolbar().addCommandToLeftBar("Retour", null, (back) -> {
                                                        lf.showBack();
                                                        mc.removeAll();
                                                        Label modCollecte = new Label("Modifier la Collecte");
                                                        modCollecte.setAlignment(CENTER);
                                                        modCollecte.getStyle().setFgColor(123456);
                                                        mc.add(modCollecte);
                                                    });
                                                    mc.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                                    });
                                                    mc.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                                        if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                                            Dialog ip4 = new InfiniteProgress().showInifiniteBlocking();
                                                            new loginForm(theme).show();
                                                        }
                                                    });
                                                    //mc.showBack();
                                                    mc.show();
                                                    modifierCollecte.addActionListener((edit) -> {
                                                        if (nomCollecteE.getText().length() == 0 || budgetE.getText().length() == 0 || descriptionE.getText().length() == 0) {
                                                            Dialog.show("Alert", "Champs Requis", "OK", null);
                                                        } else {
                                                            try {
                                                                if (fileNamee.equals("")) {
                                                                    fileNamee = imgName.getText();
                                                                }
                                                                if (nomCollecteE.getText().equals(allInfos.getText()) && budgetE.getText().equals(Integer.toString(budgetCollecte)) && descriptionE.getText().equals(descriptionCollecte) && cE.getSelectedItem().equals(categorieCollecte) && imgName.getText().equals(nomImage)) {
                                                                    Dialog.show("", "Vous n'avez rien modifié !", "OK", null);
                                                                } else if (Integer.parseInt(budgetE.getText()) < 0) {
                                                                    Dialog.show("Alert", "Budget négatif !", "OK", null);
                                                                } else if (Integer.parseInt(budgetE.getText()) == 0) {
                                                                    Dialog.show("Alert", "Budget nul !", "OK", null);
                                                                } else if (Integer.parseInt(budgetE.getText()) < 1000) {
                                                                    Dialog.show("Alert", "Budget doit être 1000 au minimum !", "OK", null);
                                                                } else if (Dialog.show("Modifier Collecte", "Confirmer la modification ?", "OK", "Cancel")) {
                                                                    if (new CollecteService().modCollecte(idCollecte, nomCollecteE.getText(), Integer.parseInt(budgetE.getText()), descriptionE.getText(), fileNamee, cE.getSelectedItem())) {
                                                                        Dialog.show("SUCCES", "Collecte modifiée avec succés !", "OK", null);
                                                                        if (!fileNamee.equals(nomImage)) {
                                                                            String pathToBeStoredd = "file://C:/wamp64/www/symfony/web/uploads/" + fileNamee;
                                                                            OutputStream oss = FileSystemStorage.getInstance().openOutputStream(pathToBeStoredd);
                                                                            ImageIO.getImageIO().save(img3, oss, ImageIO.FORMAT_PNG, 0.9f);
                                                                            File f = new File("file://C:/wamp64/www/symfony/web/uploads/" + nomImage);
                                                                            f.delete();
                                                                            oss.close();
                                                                        }
                                                                        //lf.show();
                                                                        //img2 = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                                                                    } else {
                                                                        Dialog.show("ERREUR", "Erreur de la modification", "OK", null);
                                                                    }
                                                                }
                                                            } catch (Exception error) {
                                                                Dialog.show("ERREUR.", "Erreur de la modification", "OK", null);
                                                            }
                                                        }
                                                    });
                                                    supprimerCollecte.addActionListener((del) -> {
                                                        try {
                                                            if (Dialog.show("Supprimer Collecte", "Confirmer la suppression ?", "OK", "Cancel")) {
                                                                if (new CollecteService().delCollecte(idCollecte)) {
                                                                    Dialog.show("SUCCES", "Collecte supprimée avec succés !", "OK", null);
                                                                    File fDel = new File("file://C:/wamp64/www/symfony/web/uploads/" + nomImage);
                                                                    fDel.delete();
                                                                    mc.removeAll();
                                                                } else {
                                                                    Dialog.show("ERREUR", "Erreur de la suppression", "OK", null);
                                                                }
                                                            }
                                                        } catch (Exception delError) {
                                                            Dialog.show("ERREUR", "Erreur de la suppression", "OK", null);
                                                        }
                                                    });
                                                });
                                                Label lll = new Label("                                               ");
                                                lf.addAll(lll, imv);
                                                l2 = new Label("");
                                                lf.add(l2);
                                                lf.addAll(allInfos, modifier);
                                            }
                                            /*if (i == cs.getAllCollects().size() - 1) {
                                                    Label ldown = new Label(" ");
                                                    Label nothing = new Label("Vous n'avez pas encore des collectes");
                                                    nothing.setSize(new Dimension(200, 200));
                                                    nothing.setAlignment(CENTER);
                                                    nothing.getStyle().setFgColor(654321);
                                                    Label ldownE = new Label();
                                                    Image sadEmoji = theme.getImage("sad.png");
                                                    ldownE.setIcon(sadEmoji);
                                                    ldownE.setAlignment(CENTER);
                                                    lf.addAll(ldown, nothing, ldownE);
                                                    //lf.add(sadEmoji);
                                                    Dialog.show("Alerte", "Vous n'avez encore aucune collecte !", "OK", null);
                                                }*/
                                        }

                                    }
                                }
                                créerCol.addActionListener((ecol) -> {
                                    Dialog ip4 = new InfiniteProgress().showInifiniteBlocking();
                                    AddCollecteForm acol = new AddCollecteForm(theme);
                                    acol.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                    });
                                    acol.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                        if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                            new loginForm(theme).show();
                                        }
                                    });
                                    acol.getToolbar().addCommandToSideMenu("", theme.getImage("SaviorsLogo.png"), r -> {
                                        if (Dialog.show("Annuler", "Annuler l'ajout ?", "OK", "Cancel")) {
                                            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                            hf.show();
                                        }
                                    });
                                    acol.getToolbar().addCommandToSideMenu("Accueil", icon2, r -> {
                                        if (Dialog.show("Annuler", "Annuler l'ajout ?", "OK", "Cancel")) {
                                            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                            hf.show();
                                        }
                                    });
                                    acol.getToolbar().addCommandToSideMenu("Actualités", icon3, r -> {
                                    });
                                    acol.getToolbar().addCommandToSideMenu("A Propos", icon4, r -> {
                                    });
                                    acol.getToolbar().addCommandToSideMenu("Contact", icon5, r -> {
                                    });
                                    acol.getToolbar().addCommandToSideMenu("Evenements", icon7, r -> {
                                        int countEvent = 0;
                                        for (int prod = 0; prod < new WebService().getAllEventsP().size(); prod++) {
                                            if (new WebService().getAllEventsP().get(prod).getUser().equals(username.getText())) {
                                                countEvent++;
                                            }

                                            if (countEvent == 1) {
                                                Dialog.show("Information", "Vous avez " + countEvent + " evenement en attente de réponse par l'administrateur.", new Command("OK"));
                                            }
                                            if (countEvent > 0 && countEvent != 1) {
                                                Dialog.show("Information", "Vous avez " + countEvent + " evenements en attente de réponse par l'administrateur.", new Command("OK"));
                                            }
                                        }
                                        ListEvents le = new ListEvents(theme, acol);
                                        le.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                        });
                                        le.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                            if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                                Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                                new loginForm(theme).show();
                                            }
                                        });
                                        le.show();
                                    });
                                    acol.getToolbar().addCommandToSideMenu("Publication", icon8, r -> {
                                        HomeForm_1 hf1 = new HomeForm_1(theme, acol);
                                        hf1.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                        });
                                        hf1.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                            if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                                Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();

                                                new loginForm(theme).show();
                                            }
                                        });
                                        hf1.show();
                                    });
                                    acol.getToolbar().addCommandToSideMenu("Réclamation", icon9, r -> {
                                    });
                                    acol.getToolbar().addCommandToSideMenu("Collecte", icon10, r -> {
                                        if (Dialog.show("Annuler", "Annuler l'ajout ?", "OK", "Cancel")) {
                                            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                            cf.show();
                                        }
                                    });
                                    acol.getToolbar().addCommandToSideMenu("Produit", icon11, r -> {

                                        int countProd = 0;
                                        for (int prod = 0; prod < new ServiceProduit().getAllProduitsP().size(); prod++) {
                                            if (new ServiceProduit().getAllProduitsP().get(prod).getUser_id().equals(username.getText())) {
                                                countProd++;
                                            }

                                            if (countProd == 1) {
                                                Dialog.show("Information", "Vous avez " + countProd + " produit en attente de réponse par l'administrateur.", new Command("OK"));
                                            }
                                            if (countProd > 0 && countProd != 1) {
                                                Dialog.show("Information", "Vous avez " + countProd + " produits en attente de réponse par l'administrateur.", new Command("OK"));
                                            }
                                        }

                                        ListProduitsForm lp = new ListProduitsForm(acol, theme);

                                        Button btnAddProduit = new Button(FontImage.MATERIAL_ADD_SHOPPING_CART);

                                        Button btnAddCategorie = new Button(FontImage.MATERIAL_ADD_CHART);

                                        btnAddCategorie.addActionListener((addCatProd) -> {
                                            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                            AddCategorieForm acatproduit = new AddCategorieForm(lp);
                                            acatproduit.setTitle("Ajouter Une Categorie");
                                            acatproduit.setLayout(BoxLayout.y());

                                            TextField tfNom = new TextField("", "Nom");
                                            TextField tfDescription = new TextField("", "Description");

                                            Button btnValider = new Button(FontImage.MATERIAL_ADD);
                                            Button btnAnnuler = new Button(FontImage.MATERIAL_CANCEL);
                                            btnAnnuler.addActionListener((backtolist) -> {
                                                lp.showBack();
                                            });
                                            btnValider.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent evt) {

                                                    if ((tfNom.getText().length() == 0) || (tfDescription.getText().length() == 0)) {
                                                        Dialog.show("Alerte", "S'il vous plait, remplissez tout les champs", new Command("OK"));
                                                    } else {
                                                        try {
                                                            CategorieProduit p = new CategorieProduit(tfNom.getText(), tfDescription.getText());
                                                            if (ServiceCategorie.getInstance().addCategorie(p)) {

                                                                Dialog.show("Succés", "Categorie ajoutée avec succés", new Command("OK"));
                                                            } else {
                                                                Dialog.show("ERREUR", "Erreur du serveur", new Command("OK"));
                                                            }
                                                        } catch (NumberFormatException e) {
                                                            Dialog.show("ERROR", "Le champs doit etre un entier merci de vérifier", new Command("OK"));
                                                        }

                                                    }

                                                }
                                            });

                                            acatproduit.addAll(tfNom, tfDescription, btnValider, btnAnnuler);
                                            acatproduit.show();
                                        });

                                        btnAddProduit.addActionListener((addProd) -> {
                                            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                            AddProduitForm addprod = new AddProduitForm(lp);
                                            addprod.setTitle("Ajouter Un Produit");
                                            addprod.setLayout(BoxLayout.y());

                                            TextField tfNom = new TextField("", "Nom");
                                            TextField tfDescription = new TextField("", "Description");
                                            //     TextField tfImage= new TextField("", "image");
                                            TextField tfPrix = new TextField("", "Prix");

                                            ComboBox<String> c = new ComboBox();
                                            for (int i = 0; i < new ServiceCategorie().getAllCategories().size(); i++) {

                                                c.addItem(new ServiceCategorie().getAllCategories().get(i).toString());
                                            }
                                            final String[] jobPic = new String[1];
                                            Label jobIcon = new Label();
                                            Button image = new Button(FontImage.MATERIAL_ADD_A_PHOTO);
                                            final String[] image_name = {""};
                                            final String[] filePath = {""};
                                            fileNameProd = "";
                                            ImageViewer imgPROD = new ImageViewer();
                                            image.addActionListener((ActionEvent actionEvent) -> {
                                                Display.getInstance().openGallery(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent ev) {

                                                        filePath[0] = (String) ev.getSource();
                                                        // System.out.println(ev.getSource());
                                                        int fileNameIndex = filePath[0].lastIndexOf("/") + 1;
                                                        fileNameProd = filePath[0].substring(fileNameIndex);
                                                        try {
                                                            img2Prod = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                                                            jobIcon.setIcon(img2Prod);
                                                            imgPROD.setImage(img2Prod);

                                                        } catch (Exception e) {
                                                        }
                                                    }
                                                }, Display.GALLERY_IMAGE);
                                            });
                                            Button btnValider = new Button(FontImage.MATERIAL_ADD);
                                            Button btnAnnuler = new Button(FontImage.MATERIAL_CANCEL);
                                            btnAnnuler.addActionListener((back) -> {
                                                lp.showBack();
                                            });
                                            btnValider.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent evt) {
                                                    if (fileNameProd.equals("")) {
                                                        ToastBar.showErrorMessage("Veuillez importer une image valide !");
                                                    }
                                                    if ((tfNom.getText().length() == 0) || (tfDescription.getText().length() == 0) || (tfPrix.getText().length() == 0) || (fileNameProd.equals(""))) {
                                                        Dialog.show("Alert", "s'il vous plait remplissez tout les champs", new Command("OK"));
                                                    } else {
                                                        try {
                                                            Produit p = new Produit(tfNom.getText(), fileNameProd, Integer.parseInt(tfPrix.getText()), tfDescription.getText(), c.getSelectedItem().toString());
                                                            p.setUser_id(username.getText());
                                                            //System.out.println(username.getText());
                                                            if (ServiceProduit.getInstance().addProduit(p)) {
                                                                try {
                                                                    Dialog.show("SUCCES", "Produit en attente de Confirmation par l'administrateur.", new Command("OK"));
                                                                    String pathToBeStored = "file://C:/wamp64/www/symfony/web/events/" + fileNameProd;
                                                                    OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                                                                    ImageIO.getImageIO().save(img2Prod, os, ImageIO.FORMAT_PNG, 0.9f);
                                                                    os.close();
                                                                } catch (Exception ex) {
                                                                    System.out.println(ex);
                                                                }
                                                            } else {
                                                                Dialog.show("ERREUR", "Erreur du Serveur", new Command("OK"));
                                                            }
                                                        } catch (NumberFormatException e) {
                                                            Dialog.show("ERREUR", "Le prix doit etre un entier merci de vérifier", new Command("OK"));
                                                        }

                                                    }

                                                }
                                            });

                                            addprod.addAll(tfNom, tfDescription, image, tfPrix, c, btnValider, btnAnnuler, imgPROD);
                                            addprod.show();
                                        });

                                        Dialog ipProd = new InfiniteProgress().showInifiniteBlocking();

                                        current = this;

                                        Toolbar.setGlobalToolbar(true);
                                        //Style s = UIManager.getInstance().getComponentStyle("Title");

                                        TextField searchField = new TextField("", "Rechercher"); // <1>
                                        searchField.getHintLabel().setUIID("Title");
                                        searchField.setUIID("Title");
                                        searchField.getAllStyles().setAlignment(Component.LEFT);
                                        lp.getToolbar().setTitleComponent(searchField);

                                        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_EDIT_ATTRIBUTES, s);
                                        Button Recherche = new Button(FontImage.MATERIAL_SEARCH);
                                        Recherche.addActionListener((search) -> {
                                            Produit c = new Produit();
                                            ArrayList<Produit> listepr = new ArrayList<>();

                                            ServiceProduit es = new ServiceProduit();
                                            if (searchField.getText().length() == 0) {
                                                ToastBar.showErrorMessage("Veuillez saisir le Nom du Produit !");
                                            } else {
                                                listepr = es.Recherche(searchField.getText());

                                                if (!listepr.toString().contains("Produit")) {
                                                    ToastBar.showErrorMessage("Produit n'existe pas !", 1);
                                                }

                                                for (Produit i : listepr) {
                                                    if (!searchField.getText().equals(i.getNom())) {
                                                        ToastBar.showErrorMessage("Produit n'existe pas !", 1);
                                                    } else {
                                                        Form det = new Form();
                                                        det.setTitle("Résultat  " + i.getNom());

                                                        det.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, back -> lp.showBack());

                                                        det.setLayout(BoxLayout.yCenter());

                                                        EncodedImage enco1 = EncodedImage.createFromImage(theme.getImage("load.png"), false);
                                                        String url1 = "http://localhost/symfony/web/events/" + i.getImage();
                                                        Image im1 = URLImage.createToStorage(enco1, i.getImage(), url1);
                                                        ImageViewer img = new ImageViewer(im1);

                                                        SpanLabel nom1 = new SpanLabel("Nom : " + i.getNom());
                                                        SpanLabel description1 = new SpanLabel("Description : " + i.getDescription());
                                                        SpanLabel image = new SpanLabel("Image est :" + i.getImage());
                                                        SpanLabel categorie1 = new SpanLabel("Catégorie : " + i.getCategorie_nom());
                                                        SpanLabel prix1 = new SpanLabel("Prix : " + Integer.toString(i.getPrix()) + " TND");
                                                        SpanLabel usernameProduit = new SpanLabel("Publié par: " + i.getUser_id());
                                                        det.add(img);

                                                        det.add(String.valueOf("                             "));
                                                        Container ec = FlowLayout.encloseCenter(nom1);
                                                        Container e1 = FlowLayout.encloseCenter(description1);
                                                        Container e2 = FlowLayout.encloseCenter(categorie1);
                                                        Container e3 = FlowLayout.encloseCenter(prix1);
                                                        Container eu = FlowLayout.encloseCenter(usernameProduit);

                                                        det.addAll(ec, e1, e2, e3, eu);
                                                        det.add(String.valueOf("                             "));
                                                        det.show();

                                                        lp.getContentPane().animateLayout(250);
                                                    }
                                                }
                                            }
                                        });

                                        lp.getToolbar().addCommandToRightBar("", searchIcon, (es) -> {
                                            searchField.startEditingAsync();
                                        });
                                        lp.add(String.valueOf("                                                  "));
                                        lp.add(Recherche);
                                        if (new LoginService().getUser(us).toString().contains("Fournisseur")) {
                                            lp.addAll(btnAddCategorie, btnAddProduit);
                                        }

                                        ArrayList<Produit> listeproduits = new ArrayList<>();
                                        ServiceProduit ps = new ServiceProduit();
                                        listeproduits = ps.getAllProduits();

                                        for (Produit i : listeproduits) {
                                            lp.add(AddItem(i, theme, lp));
                                        }
                                        lp.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, back -> acol.showBack());

                                        lp.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                        });
                                        lp.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                            if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                                Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                                new loginForm(theme).show();
                                            }
                                        });
                                        lp.show();
                                    });
                                    acol.getToolbar().addCommandToSideMenu("Forum", icon6, r -> {
                                        HomeForumForm hf1 = new HomeForumForm(acol);
                                        hf1.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                        });
                                        hf1.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                            if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                                Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                                new loginForm(theme).show();
                                            }
                                        });
                                        hf1.show();
                                    });
                                    TextField nomCollecte = new TextField(null, "Nom de la Collecte");
                                    TextField budget = new TextField(null, "Budget");
                                    TextArea description = new TextArea(null);
                                    description.setHint("Description");
                                    description.setHeight(500);
                                    ComboBox<String> c = new ComboBox();
                                    for (int i = 0; i < new CollecteService().getCategories().size(); i++) {
                                        c.addItem(new CollecteService().getCategories().get(i).getTypeCategorie());
                                    }
                                    //TextField image = new TextField(null, "Image");
                                    final String[] jobPic = new String[1];
                                    Label jobIcon = new Label();
                                    Button image = new Button("Ajouter une image ");
                                    final String[] image_name = {""};
                                    final String[] filePath = {""};
                                    fileName = "";
                                    ImageViewer imvAdd = new ImageViewer();
                                    image.addActionListener((ActionEvent actionEvent) -> {
                                        Display.getInstance().openGallery(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent ev) {

                                                filePath[0] = (String) ev.getSource();
                                                System.out.println(ev.getSource());
                                                int fileNameIndex = filePath[0].lastIndexOf("/") + 1;
                                                fileName = filePath[0].substring(fileNameIndex);
                                                //Image img2 = null;
                                                try {
                                                    img2 = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                                                    //image_name[0] = System.currentTimeMillis() + ".png";
                                                    jobIcon.setIcon(img2);
                                                    imvAdd.setImage(img2);
                                                    /*String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + fileName;
                                                            OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                                                            ImageIO.getImageIO().save(img2, os, ImageIO.FORMAT_PNG, 0.9f);
                                                            os.close();*/
                                                    //System.out.println(pathToBeStored);
                                                } catch (Exception e) {
                                                    //e.printStackTrace();
                                                    //ToastBar.showErrorMessage("Veuillez importer une image valide !");
                                                }
                                            }
                                        }, Display.GALLERY_IMAGE);
                                    });
                                    Button ajouter = new Button("Ajouter");
                                    Label l = new Label(fileName);
                                    acol.addAll(nomCollecte, budget, description, c, image, ajouter, imvAdd);
                                    acol.show();
                                    ajouter.addActionListener((add) -> {
                                        if (fileName.equals("")) {
                                            ToastBar.showErrorMessage("Veuillez importer une image valide !");
                                        }
                                        if (nomCollecte.getText().length() == 0 || budget.getText().length() == 0 || description.getText().length() == 0 || fileName.equals("")) {
                                            Dialog.show("Alert", "Champs Requis", "OK", null);
                                        } else if (Integer.parseInt(budget.getText()) < 0) {
                                            Dialog.show("Alert", "Budget négatif !", "OK", null);
                                        } else if (Integer.parseInt(budget.getText()) == 0) {
                                            Dialog.show("Alert", "Budget nul !", "OK", null);
                                        } else if (Integer.parseInt(budget.getText()) < 1000) {
                                            Dialog.show("Alert", "Budget doit être 1000 au minimum !", "OK", null);
                                        } else {
                                            try {
                                                //int user_id;
                                                LoginService ls = new LoginService();
                                                for (int i = 0; i < ls.getUsers().size(); i++) {
                                                    if (ls.getUsers().get(i).getUsername().equals(username.getText())) {
                                                        user_id = ls.getUsers().get(i).getId();
                                                    }
                                                }
                                                Collecte col = new Collecte(nomCollecte.getText(), Integer.parseInt(budget.getText()), 0, description.getText(), 0, fileName, c.getSelectedItem(), user_id);
                                                if (Dialog.show("Ajout Collecte", "Confirmer l'ajout ?", "OK", "Cancel")) {
                                                    if (new CollecteService().addCollecte(col)) {

                                                        Dialog.show("SUCCES", "Collecte en attente de Confirmation par l'administrateur.", "OK", null);

                                                        String pathToBeStored = "file://C:/wamp64/www/symfony/web/uploads/" + fileName;
                                                        OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                                                        ImageIO.getImageIO().save(img2, os, ImageIO.FORMAT_PNG, 0.9f);
                                                        os.close();
                                                        Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                                        lf.show();
                                                        //img2 = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                                                    } else {
                                                        Dialog.show("ERREUR", "Erreur de l'ajout", "OK", null);
                                                    }
                                                }
                                            } catch (Exception e1) {
                                                Dialog.show("ERREUR", "Erreur de l'ajout", "OK", null);
                                            }
                                        }
                                    });
                                });

                                créerCat.addActionListener((addCol) -> {
                                    Dialog ip4 = new InfiniteProgress().showInifiniteBlocking();
                                    AddCatCollecteForm aCatcol = new AddCatCollecteForm(theme);
                                    aCatcol.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                    });
                                    aCatcol.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                        if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                            new loginForm(theme).show();
                                        }
                                    });
                                    aCatcol.getToolbar().addCommandToSideMenu("", theme.getImage("SaviorsLogo.png"), r -> {
                                        if (Dialog.show("Annuler", "Annuler l'ajout ?", "OK", "Cancel")) {
                                            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                            hf.show();
                                        }
                                    });
                                    aCatcol.getToolbar().addCommandToSideMenu("Accueil", icon2, r -> {
                                        if (Dialog.show("Annuler", "Annuler l'ajout ?", "OK", "Cancel")) {
                                            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                            hf.show();
                                        }
                                    });
                                    aCatcol.getToolbar().addCommandToSideMenu("Actualités", icon3, r -> {
                                    });
                                    aCatcol.getToolbar().addCommandToSideMenu("A Propos", icon4, r -> {
                                    });
                                    aCatcol.getToolbar().addCommandToSideMenu("Contact", icon5, r -> {
                                    });
                                    aCatcol.getToolbar().addCommandToSideMenu("Evenements", icon7, r -> {
                                        int countEvent = 0;
                                        for (int prod = 0; prod < new WebService().getAllEventsP().size(); prod++) {
                                            if (new WebService().getAllEventsP().get(prod).getUser().equals(username.getText())) {
                                                countEvent++;
                                            }

                                        }
                                        if (countEvent > 0) {
                                            Dialog.show("Information", "Vous avez " + countEvent + " evenement(s) en attente de réponse par l'administrateur.", new Command("OK"));
                                        }
                                        ListEvents le = new ListEvents(theme, aCatcol);
                                        le.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                        });
                                        le.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                            if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                                Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                                new loginForm(theme).show();
                                            }
                                        });
                                        le.show();
                                    });
                                    aCatcol.getToolbar().addCommandToSideMenu("Publication", icon8, r -> {
                                        HomeForm_1 hf1 = new HomeForm_1(theme, aCatcol);
                                        hf1.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                        });
                                        hf1.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                            if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                                Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                                new loginForm(theme).show();
                                            }
                                        });
                                        hf1.show();
                                    });
                                    aCatcol.getToolbar().addCommandToSideMenu("Réclamation", icon9, r -> {
                                    });
                                    aCatcol.getToolbar().addCommandToSideMenu("Collecte", icon10, r -> {
                                        if (Dialog.show("Annuler", "Annuler l'ajout ?", "OK", "Cancel")) {
                                            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                            cf.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                            });
                                            cf.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                                if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                                    new loginForm(theme).show();
                                                }
                                            });
                                            cf.show();
                                        }
                                    });
                                    aCatcol.getToolbar().addCommandToSideMenu("Produit", icon11, r -> {
                                        int countProd = 0;
                                        for (int prod = 0; prod < new ServiceProduit().getAllProduitsP().size(); prod++) {
                                            if (new ServiceProduit().getAllProduitsP().get(prod).getUser_id().equals(username.getText())) {
                                                countProd++;
                                            }

                                            if (countProd == 1) {
                                                Dialog.show("Information", "Vous avez " + countProd + " produit en attente de réponse par l'administrateur.", new Command("OK"));
                                            }
                                            if (countProd > 0 && countProd != 1) {
                                                Dialog.show("Information", "Vous avez " + countProd + " produits en attente de réponse par l'administrateur.", new Command("OK"));
                                            }
                                        }
                                        ListProduitsForm lp = new ListProduitsForm(aCatcol, theme);
                                        Button btnAddProduit = new Button(FontImage.MATERIAL_ADD_SHOPPING_CART);
                                        Button btnAddCategorie = new Button(FontImage.MATERIAL_ADD_CHART);

                                        btnAddCategorie.addActionListener((addCatProd) -> {
                                            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                            AddCategorieForm acatproduit = new AddCategorieForm(lp);
                                            acatproduit.setTitle("Ajouter Une Categorie");
                                            acatproduit.setLayout(BoxLayout.y());

                                            TextField tfNom = new TextField("", "Nom");
                                            TextField tfDescription = new TextField("", "Description");

                                            Button btnValider = new Button(FontImage.MATERIAL_ADD);
                                            Button btnAnnuler = new Button(FontImage.MATERIAL_CANCEL);
                                            btnAnnuler.addActionListener((backtolist) -> {
                                                lp.showBack();
                                            });
                                            btnValider.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent evt) {

                                                    if ((tfNom.getText().length() == 0) || (tfDescription.getText().length() == 0)) {
                                                        Dialog.show("Alerte", "S'il vous plait, remplissez tout les champs", new Command("OK"));
                                                    } else {
                                                        try {
                                                            CategorieProduit p = new CategorieProduit(tfNom.getText(), tfDescription.getText());
                                                            if (ServiceCategorie.getInstance().addCategorie(p)) {

                                                                Dialog.show("Succés", "Categorie ajoutée avec succés", new Command("OK"));
                                                            } else {
                                                                Dialog.show("ERREUR", "Erreur du serveur", new Command("OK"));
                                                            }
                                                        } catch (NumberFormatException e) {
                                                            Dialog.show("ERROR", "Le champs doit etre un entier merci de vérifier", new Command("OK"));
                                                        }

                                                    }

                                                }
                                            });

                                            acatproduit.addAll(tfNom, tfDescription, btnValider, btnAnnuler);
                                            acatproduit.show();
                                        });
                                        btnAddProduit.addActionListener((addProd) -> {
                                            AddProduitForm addprod = new AddProduitForm(lp);
                                            addprod.setTitle("Ajouter Un Produit");
                                            addprod.setLayout(BoxLayout.y());

                                            TextField tfNom = new TextField("", "Nom");
                                            TextField tfDescription = new TextField("", "Description");
                                            //     TextField tfImage= new TextField("", "image");
                                            TextField tfPrix = new TextField("", "Prix");

                                            ComboBox<String> c = new ComboBox();
                                            for (int i = 0; i < new ServiceCategorie().getAllCategories().size(); i++) {

                                                c.addItem(new ServiceCategorie().getAllCategories().get(i).toString());
                                            }
                                            final String[] jobPic = new String[1];
                                            Label jobIcon = new Label();
                                            Button image = new Button(FontImage.MATERIAL_ADD_A_PHOTO);
                                            final String[] image_name = {""};
                                            final String[] filePath = {""};
                                            fileNameProd = "";
                                            ImageViewer imgPROD = new ImageViewer();
                                            image.addActionListener((ActionEvent actionEvent) -> {
                                                Display.getInstance().openGallery(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent ev) {

                                                        filePath[0] = (String) ev.getSource();
                                                        // System.out.println(ev.getSource());
                                                        int fileNameIndex = filePath[0].lastIndexOf("/") + 1;
                                                        fileNameProd = filePath[0].substring(fileNameIndex);
                                                        try {
                                                            img2Prod = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                                                            jobIcon.setIcon(img2Prod);
                                                            imgPROD.setImage(img2Prod);

                                                        } catch (Exception e) {
                                                        }
                                                    }
                                                }, Display.GALLERY_IMAGE);
                                            });
                                            Button btnValider = new Button(FontImage.MATERIAL_ADD);
                                            Button btnAnnuler = new Button(FontImage.MATERIAL_CANCEL);
                                            btnAnnuler.addActionListener((back) -> {
                                                lp.showBack();
                                            });
                                            btnValider.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent evt) {
                                                    if (fileNameProd.equals("")) {
                                                        ToastBar.showErrorMessage("Veuillez importer une image valide !");
                                                    }
                                                    if ((tfNom.getText().length() == 0) || (tfDescription.getText().length() == 0) || (tfPrix.getText().length() == 0) || (fileNameProd.equals(""))) {
                                                        Dialog.show("Alert", "s'il vous plait remplissez tout les champs", new Command("OK"));
                                                    } else {
                                                        try {
                                                            Produit p = new Produit(tfNom.getText(), fileNameProd, Integer.parseInt(tfPrix.getText()), tfDescription.getText(), c.getSelectedItem().toString());
                                                            p.setUser_id(username.getText());
                                                            //System.out.println(username.getText());
                                                            if (ServiceProduit.getInstance().addProduit(p)) {
                                                                try {
                                                                    Dialog.show("SUCCES", "Produit en attente de Confirmation par l'administrateur.", new Command("OK"));
                                                                    String pathToBeStored = "file://C:/wamp64/www/symfony/web/events/" + fileNameProd;
                                                                    OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                                                                    ImageIO.getImageIO().save(img2Prod, os, ImageIO.FORMAT_PNG, 0.9f);
                                                                    os.close();
                                                                } catch (Exception ex) {
                                                                    System.out.println(ex);
                                                                }
                                                            } else {
                                                                Dialog.show("ERREUR", "Server error", new Command("OK"));
                                                            }
                                                        } catch (NumberFormatException e) {
                                                            Dialog.show("ERREUR", "Le prix doit etre un entier merci de vérifier", new Command("OK"));
                                                        }

                                                    }

                                                }
                                            });

                                            addprod.addAll(tfNom, tfDescription, image, tfPrix, c, btnValider, btnAnnuler, imgPROD);
                                            addprod.show();
                                        });
                                        Dialog ipProd = new InfiniteProgress().showInifiniteBlocking();
                                        current = this;

                                        Toolbar.setGlobalToolbar(true);
                                        //Style s = UIManager.getInstance().getComponentStyle("Title");

                                        TextField searchField = new TextField("", "Rechercher"); // <1>
                                        searchField.getHintLabel().setUIID("Title");
                                        searchField.setUIID("Title");
                                        searchField.getAllStyles().setAlignment(Component.LEFT);
                                        lp.getToolbar().setTitleComponent(searchField);

                                        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_EDIT_ATTRIBUTES, s);
                                        Button Recherche = new Button(FontImage.MATERIAL_SEARCH);
                                        Recherche.addActionListener((search) -> {
                                            Produit c = new Produit();
                                            ArrayList<Produit> listepr = new ArrayList<>();

                                            ServiceProduit es = new ServiceProduit();
                                            if (searchField.getText().length() == 0) {
                                                ToastBar.showErrorMessage("Veuillez saisir le Nom du Produit !");
                                            } else {
                                                listepr = es.Recherche(searchField.getText());

                                                if (!listepr.toString().contains("Produit")) {
                                                    ToastBar.showErrorMessage("Produit n'existe pas !", 1);
                                                }

                                                for (Produit i : listepr) {
                                                    if (!searchField.getText().equals(i.getNom())) {
                                                        ToastBar.showErrorMessage("Produit n'existe pas !", 1);
                                                    } else {
                                                        Form det = new Form();
                                                        det.setTitle("Résultat  " + i.getNom());

                                                        det.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, back -> lp.showBack());

                                                        det.setLayout(BoxLayout.yCenter());

                                                        EncodedImage enco1 = EncodedImage.createFromImage(theme.getImage("load.png"), false);
                                                        String url1 = "http://localhost/symfony/web/events/" + i.getImage();
                                                        Image im1 = URLImage.createToStorage(enco1, i.getImage(), url1);
                                                        ImageViewer img = new ImageViewer(im1);

                                                        SpanLabel nom1 = new SpanLabel("Nom : " + i.getNom());
                                                        SpanLabel description1 = new SpanLabel("Description : " + i.getDescription());
                                                        SpanLabel image = new SpanLabel("Image est :" + i.getImage());
                                                        SpanLabel categorie1 = new SpanLabel("Catégorie : " + i.getCategorie_nom());
                                                        SpanLabel prix1 = new SpanLabel("Prix : " + Integer.toString(i.getPrix()) + " TND");
                                                        SpanLabel usernameProduit = new SpanLabel("Publié par: " + i.getUser_id());
                                                        det.add(img);

                                                        det.add(String.valueOf("                             "));
                                                        Container ec = FlowLayout.encloseCenter(nom1);
                                                        Container e1 = FlowLayout.encloseCenter(description1);
                                                        Container e2 = FlowLayout.encloseCenter(categorie1);
                                                        Container e3 = FlowLayout.encloseCenter(prix1);
                                                        Container eu = FlowLayout.encloseCenter(usernameProduit);

                                                        det.addAll(ec, e1, e2, e3, eu);
                                                        det.add(String.valueOf("                             "));
                                                        det.show();

                                                        lp.getContentPane().animateLayout(250);
                                                    }
                                                }
                                            }
                                        });

                                        lp.getToolbar().addCommandToRightBar("", searchIcon, (es) -> {
                                            searchField.startEditingAsync(); // <4>
                                        });
                                        lp.add(String.valueOf("                                                  "));
                                        lp.add(Recherche);
                                        if (new LoginService().getUser(us).toString().contains("Fournisseur")) {
                                            lp.addAll(btnAddCategorie, btnAddProduit);
                                        }

                                        ArrayList<Produit> listeproduits = new ArrayList<>();
                                        ServiceProduit ps = new ServiceProduit();
                                        listeproduits = ps.getAllProduits();

                                        for (Produit i : listeproduits) {
                                            //    System.out.println(i);
                                            lp.add(AddItem(i, theme, lp));
                                        }
                                        lp.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, back -> aCatcol.showBack());
                                        lp.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                        });
                                        lp.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                            if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                                Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                                new loginForm(theme).show();
                                            }
                                        });
                                        lp.show();
                                    });
                                    aCatcol.getToolbar().addCommandToSideMenu("Forum", icon6, r -> {
                                        HomeForumForm hf1 = new HomeForumForm(aCatcol);
                                        hf1.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                        });
                                        hf1.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                            if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                                Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                                new loginForm(theme).show();
                                            }
                                        });
                                        hf1.show();
                                    });
                                    TextField typeCategorie = new TextField(null, "Type de la Catégorie");
                                    Button ajouter = new Button("Ajouter");
                                    aCatcol.add(typeCategorie);
                                    aCatcol.add(ajouter);
                                    aCatcol.show();
                                    ajouter.addActionListener((addCat) -> {
                                        if (typeCategorie.getText().length() == 0) {
                                            Dialog.show("Alert", "Champs Requis", "OK", null);
                                        } else {
                                            try {
                                                LoginService ls = new LoginService();
                                                for (int i = 0; i < ls.getUsers().size(); i++) {
                                                    if (ls.getUsers().get(i).getUsername().equals(username.getText())) {
                                                        user_id = ls.getUsers().get(i).getId();
                                                    }
                                                }
                                                categorieCollecte catCol = new categorieCollecte(typeCategorie.getText(), user_id);
                                                if (Dialog.show("Ajout Catégorie", "Confirmer l'ajout ?", "OK", "Cancel")) {
                                                    if (new CollecteService().addCatCollecte(catCol)) {

                                                        Dialog.show("SUCCES", "Catégorie ajoutée avec succés !", "OK", null);
                                                        Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                                        lf.show();
                                                        //img2 = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                                                    }
                                                }
                                            } catch (Exception e1) {
                                                Dialog.show("ERREUR", "Erreur de l'ajout", "OK", null);
                                            }
                                        }
                                    });
                                });

                                lf.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                });
                                lf.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                    if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                        Dialog ip4 = new InfiniteProgress().showInifiniteBlocking();
                                        new loginForm(theme).show();
                                    }
                                });
                                lf.getToolbar().addCommandToSideMenu("", theme.getImage("SaviorsLogo.png"), e1 -> {
                                    Dialog ip4 = new InfiniteProgress().showInifiniteBlocking();
                                    hf.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                    });
                                    hf.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                        if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                            new loginForm(theme).show();
                                        }
                                    });
                                    hf.show();
                                });
                                lf.getToolbar().addCommandToSideMenu("Accueil", icon2, e1 -> {
                                    Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                    hf.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                    });
                                    hf.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                        if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                            new loginForm(theme).show();
                                        }
                                    });
                                    hf.show();
                                });
                                lf.getToolbar().addCommandToSideMenu("Actualités", icon3, e1 -> {
                                });
                                lf.getToolbar().addCommandToSideMenu("A Propos", icon4, e1 -> {
                                });
                                lf.getToolbar().addCommandToSideMenu("Contact", icon5, e1 -> {
                                });
                                lf.getToolbar().addCommandToSideMenu("Evenement", icon7, e1 -> {
                                    int countEvent = 0;
                                    for (int prod = 0; prod < new WebService().getAllEventsP().size(); prod++) {
                                        if (new WebService().getAllEventsP().get(prod).getUser().equals(username.getText())) {
                                            countEvent++;
                                        }

                                        if (countEvent == 1) {
                                            Dialog.show("Information", "Vous avez " + countEvent + " evenement en attente de réponse par l'administrateur.", new Command("OK"));
                                        }
                                        if (countEvent > 0 && countEvent != 1) {
                                            Dialog.show("Information", "Vous avez " + countEvent + " evenements en attente de réponse par l'administrateur.", new Command("OK"));
                                        }
                                    }
                                    ListEvents le = new ListEvents(theme, lf);
                                    le.setName("Evenements");
                                    le.setTitle("Evenements");
                                    le.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                                    //Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
                                    FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
                                    FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
                                    le.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, back -> {
                                        hf.showBack();
                                    });
                                    Button addEventBtn = new Button("Ajouter un Evenement");
                                    if (new LoginService().getUser(us).toString().contains("Association")) {
                                        le.add(addEventBtn);
                                    } else {
                                        le.add(String.valueOf("      "));
                                    }
                                    addEventBtn.addActionListener((addEvent) -> {
                                        AddEvent ae = new AddEvent(theme, le);
                                        ae.setName("Ajouter un Evenement");
                                        ae.setTitle("Ajouter un Evenement");
                                        ae.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                                        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
                                        /**/
                                        Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                        Picker datePicker = new Picker();
                                        datePicker.setType(Display.PICKER_TYPE_DATE);
                                        TextField nom = new TextField();
                                        nom.setHint("Nom");
                                        TextField description = new TextField();
                                        description.setHint("Description");
                                        TextField lieu = new TextField();
                                        lieu.setHint("Lieu");

                                        Label Date = new Label("Date de l'Evenement : ");
                                        Button img = new Button("Ajouter une image", icone);

                                        Button b = new Button("Ajouter");
                                        WebService ws = new WebService();
                                        photos.add(nom);
                                        photos.add(description);
                                        photos.add(lieu);
                                        photos.add(Date);
                                        photos.add(datePicker);

                                        photos.add(img);
                                        photos.add(b);
                                        ae.add(photos);
                                        final String[] jobPic = new String[1];
                                        Label jobIcon = new Label();
                                        Button image = new Button(FontImage.MATERIAL_ADD_A_PHOTO);
                                        final String[] image_name = {""};
                                        final String[] filePath = {""};
                                        fileNameProd = "";
                                        ImageViewer imgPROD = new ImageViewer();
                                        img.addActionListener((ActionEvent actionEvent) -> {
                                            Display.getInstance().openGallery(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent ev) {

                                                    filePath[0] = (String) ev.getSource();
                                                    int fileNameIndex = filePath[0].lastIndexOf("/") + 1;
                                                    fileNameProd = filePath[0].substring(fileNameIndex);
                                                    try {
                                                        img2Prod = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                                                        jobIcon.setIcon(img2Prod);
                                                        imgPROD.setImage(img2Prod);

                                                    } catch (Exception e) {
                                                    }
                                                }
                                            }, Display.GALLERY_IMAGE);
                                        });
                                        b.addActionListener(ef -> {
                                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                            Date dateobj = new Date();
                                            Date da = datePicker.getDate();
                                            String st = df.format(dateobj);
                                            String st1 = df.format(da);
                                            Date dnow = new Date();
                                            Date dcc = new Date();
                                            try {
                                                dnow = df.parse(st);
                                                dcc = df.parse(st1);
                                            } catch (ParseException ex) {

                                            }
                                            if (nom.getText().equals("") || description.getText().equals("") || lieu.getText().equals("") || fileNameProd.equals("")) {
                                                Dialog.show("ERREUR", "Vérifiez vos informations", "OK", null);

                                            } else {
                                                Event ev = new Event();
                                                ev.setDateevent(st1);
                                                ev.setDescription(description.getText());
                                                ev.setImage(fileNameProd);
                                                ev.setNom(nom.getText());
                                                ev.setLieu(lieu.getText());
                                                ev.setUser(username.getText());
                                                try {
                                                    String pathToBeStored = "file://C:/wamp64/www/symfony/web/events/" + fileNameProd;
                                                    OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                                                    ImageIO.getImageIO().save(img2Prod, os, ImageIO.FORMAT_PNG, 0.9f);
                                                    os.close();
                                                } catch (Exception error) {
                                                    System.out.println(error);
                                                }
                                                ws.addEvent(ev);

                                            }
                                        });
                                        ae.show();
                                    });
                                    WebService ws = new WebService();
                                    EventService ds = new EventService();
                                    Map x = ws.getResponse("event/listmobile");
                                    ArrayList<Event> listevents = ds.getListsEvents(x);
                                    for (Event event : listevents) {
                                        FontImage fi = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                                        EncodedImage enco = EncodedImage.createFromImage(fi.scaled(fi.getWidth() * 15, fi.getHeight() * 10), false);
                                        Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                        ImageViewer imv = null;
                                        Image img;
                                        EncodedImage encoded = null;
                                        Label aa = new Label(event.getNom());
                                        img = URLImage.createToStorage(enco, event.getImage(), "http://localhost/symfony/web/events/" + event.getImage());
                                        imv = new ImageViewer(img);
                                        photos.add(imv);
                                        photos.add(aa);
                                        try {
                                            ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));

                                            photos.add(sep);
                                        } catch (IOException ex) {
                                        }
                                        le.add(photos);

                                        aa.addPointerPressedListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent evt) {

                                                EventDetails.e = event;
                                                EventDetails evd = new EventDetails(theme, le);
                                                WebService ws = new WebService();
                                                evd.setName("Détail de l'évenement");
                                                evd.setTitle("Détail de l'évenement");
                                                evd.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                                                Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
                                                FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
                                                FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
                                                if (!new LoginService().getUser(us).toString().contains("Association")) {
                                                    evd.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_SUBSCRIPTIONS, ev -> {
                                                        ws.addParticipate(aa.getText(), username.getText());
                                                    });
                                                }

                                                evd.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_LIST, ev -> {
                                                    ListComments ecom = new ListComments(theme, le);
                                                    ecom.setName("Commentaires");
                                                    ecom.setTitle("Commentaires");
                                                    ecom.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                                                    if (!new LoginService().getUser(us).toString().contains("Association")) {

                                                        ecom.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, evcom -> {
                                                            AddComment ac = new AddComment(theme, ecom);
                                                            ac.setName("Ajouter un Commentaire");
                                                            ac.setTitle("Ajouter un Commentaire");
                                                            ac.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

                                                            //WebService ws = new WebService();
                                                            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                                                            TextArea a = new TextArea();
                                                            a.setHint("Commentaire");
                                                            Button b = new Button("Ajouter");

                                                            photos.add(a);
                                                            photos.add(b);

                                                            ac.add(photos);

                                                            b.addPointerPressedListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent evt) {
                                                                    CommentEvent ce = new CommentEvent();
                                                                    ce.setInEvent(id);
                                                                    ce.setText(a.getText());
                                                                    ce.setNomEvent(aa.getText());
                                                                    ce.setUsername(username.getText());
                                                                    ws.addComment(ce);
                                                                    le.show();
                                                                }
                                                            });
                                                            ac.show();
                                                        });
                                                    }
                                                    ecom.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evcom -> {
                                                        evd.showBack();
                                                    });
                                                    EventService ds = new EventService();
                                                    Map x = ws.getResponse("event/ListCom/" + event.getId());
                                                    ArrayList<CommentEvent> listevents = ds.getListscomments(x);
                                                    for (CommentEvent evcom : listevents) {
                                                        Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                                        ImageViewer imv = null;
                                                        Image img;
                                                        EncodedImage encoded = null;
                                                        Label a = new Label(evcom.getText());
                                                        Label b = new Label("Par : " + evcom.getUsername());

                                                        photos.add(a);
                                                        photos.add(b);
                                                        try {
                                                            ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));

                                                            photos.add(sep);
                                                        } catch (IOException ex) {
                                                        }
                                                        ecom.add(photos);

                                                        a.addPointerPressedListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent evt) {

                                                            }
                                                        });

                                                    }
                                                    ecom.show();
                                                });

                                                System.out.println(e.toString());
                                                Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                                ImageViewer imv = new ImageViewer();
                                                //Image img;
                                                FontImage fiDet = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                                                EncodedImage encoDet = EncodedImage.createFromImage(fiDet.scaled(fiDet.getWidth() * 15, fiDet.getHeight() * 10), false);
                                                Slider rate = createStarRankSlider();
                                                Container CR = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                                Button bou = new Button("Donner une note");
                                                if (!new LoginService().getUser(us).toString().contains("Association")) {
                                                    CR.add(FlowLayout.encloseCenter(rate));
                                                    CR.add(FlowLayout.encloseCenter(bou));
                                                }
                                                Label a = new Label("Nom : " + event.getNom());
                                                Label b = new Label("Description : " + event.getDescription());
                                                Label c = new Label("Lieu  : " + event.getLieu());
                                                Label d = new Label("Date : " + event.getDateevent());
                                                Label ef = new Label("Nombre participants : " + event.getNbrInterest());
                                                Label ed = new Label("Note : " + event.getMoyenne());
                                                Label er = new Label(ws.getCountComment("event/countCom/" + event.getId()) + " Commentaires");
                                                Image img = URLImage.createToStorage(encoDet, event.getImage(), "http://localhost/symfony/web/app_dev.php/events/" + event.getImage());
                                                imv = new ImageViewer(img);
                                                photos.add(imv);

                                                try {
                                                    ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));

                                                    photos.add(sep);
                                                } catch (IOException ex) {
                                                }
                                                photos.add(a);
                                                photos.add(b);
                                                photos.add(c);
                                                photos.add(d);
                                                photos.add(ef);
                                                photos.add(ed);
                                                photos.add(er);
                                                photos.add(CR);
                                                evd.add(photos);

                                                bou.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent evt) {
                                                        int avis = rate.getProgress() / 2;
                                                        ws.addRate(event.getId(), avis, username.getText());
                                                        le.show();
                                                    }
                                                });
                                                evd.show();
                                            }
                                        });

                                    }
                                    le.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                    });
                                    le.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                        if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                            new loginForm(theme).show();
                                        }
                                    });
                                    le.show();
                                });
                                lf.getToolbar().addCommandToSideMenu("Publication", icon8, e1 -> {
                                    HomeForm_1 hf1 = new HomeForm_1(theme, lf);
                                    hf1.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                    });
                                    hf1.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                        if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                            new loginForm(theme).show();
                                        }
                                    });
                                    hf1.show();
                                });
                                lf.getToolbar().addCommandToSideMenu("Réclamation", icon9, e1 -> {
                                });
                                lf.getToolbar().addCommandToSideMenu("Collecte", icon10, e1 -> {
                                    Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                    lf.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                    });
                                    lf.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                        if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                            new loginForm(theme).show();
                                        }
                                    });
                                    cf.show();
                                });
                                lf.getToolbar().addCommandToSideMenu("Produit", icon11, e1 -> {
                                    int countProd = 0;
                                    for (int prod = 0; prod < new ServiceProduit().getAllProduitsP().size(); prod++) {
                                        if (new ServiceProduit().getAllProduitsP().get(prod).getUser_id().equals(username.getText())) {
                                            countProd++;
                                        }

                                        if (countProd == 1) {
                                            Dialog.show("Information", "Vous avez " + countProd + " produit en attente de réponse par l'administrateur.", new Command("OK"));
                                        }
                                        if (countProd > 0 && countProd != 1) {
                                            Dialog.show("Information", "Vous avez " + countProd + " produits en attente de réponse par l'administrateur.", new Command("OK"));
                                        }
                                    }
                                    ListProduitsForm lp = new ListProduitsForm(lf, theme);
                                    Button btnAddProduit = new Button(FontImage.MATERIAL_ADD_SHOPPING_CART);
                                    Button btnAddCategorie = new Button(FontImage.MATERIAL_ADD_CHART);

                                    btnAddCategorie.addActionListener((addCatProd) -> {
                                        Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                        AddCategorieForm acatproduit = new AddCategorieForm(lp);
                                        acatproduit.setTitle("Ajouter Une Categorie");
                                        acatproduit.setLayout(BoxLayout.y());

                                        TextField tfNom = new TextField("", "Nom");
                                        TextField tfDescription = new TextField("", "Description");

                                        Button btnValider = new Button(FontImage.MATERIAL_ADD);
                                        Button btnAnnuler = new Button(FontImage.MATERIAL_CANCEL);
                                        btnAnnuler.addActionListener((backtolist) -> {
                                            lp.showBack();
                                        });
                                        btnValider.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent evt) {

                                                if ((tfNom.getText().length() == 0) || (tfDescription.getText().length() == 0)) {
                                                    Dialog.show("Alerte", "S'il vous plait, remplissez tout les champs", new Command("OK"));
                                                } else {
                                                    try {
                                                        CategorieProduit p = new CategorieProduit(tfNom.getText(), tfDescription.getText());
                                                        if (ServiceCategorie.getInstance().addCategorie(p)) {

                                                            Dialog.show("Succés", "Categorie ajoutée avec succés", new Command("OK"));
                                                        } else {
                                                            Dialog.show("ERREUR", "Erreur du serveur", new Command("OK"));
                                                        }
                                                    } catch (NumberFormatException e) {
                                                        Dialog.show("ERROR", "Le champs doit etre un entier merci de vérifier", new Command("OK"));
                                                    }

                                                }

                                            }
                                        });

                                        acatproduit.addAll(tfNom, tfDescription, btnValider, btnAnnuler);
                                        acatproduit.show();
                                    });
                                    btnAddProduit.addActionListener((addProd) -> {
                                        AddProduitForm addprod = new AddProduitForm(lp);
                                        addprod.setTitle("Ajouter Un Produit");
                                        addprod.setLayout(BoxLayout.y());

                                        TextField tfNom = new TextField("", "Nom");
                                        TextField tfDescription = new TextField("", "Description");
                                        //     TextField tfImage= new TextField("", "image");
                                        TextField tfPrix = new TextField("", "Prix");

                                        ComboBox<String> c = new ComboBox();
                                        for (int i = 0; i < new ServiceCategorie().getAllCategories().size(); i++) {

                                            c.addItem(new ServiceCategorie().getAllCategories().get(i).toString());
                                        }
                                        final String[] jobPic = new String[1];
                                        Label jobIcon = new Label();
                                        Button image = new Button(FontImage.MATERIAL_ADD_A_PHOTO);
                                        final String[] image_name = {""};
                                        final String[] filePath = {""};
                                        fileNameProd = "";
                                        ImageViewer imgProduit = new ImageViewer();
                                        image.addActionListener((ActionEvent actionEvent) -> {
                                            Display.getInstance().openGallery(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent ev) {

                                                    filePath[0] = (String) ev.getSource();
                                                    // System.out.println(ev.getSource());
                                                    int fileNameIndex = filePath[0].lastIndexOf("/") + 1;
                                                    fileNameProd = filePath[0].substring(fileNameIndex);
                                                    try {
                                                        img2Prod = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                                                        jobIcon.setIcon(img2Prod);
                                                        imgProduit.setImage(img2Prod);

                                                    } catch (Exception e) {
                                                    }
                                                }
                                            }, Display.GALLERY_IMAGE);
                                        });
                                        Button btnValider = new Button(FontImage.MATERIAL_ADD);
                                        Button btnAnnuler = new Button(FontImage.MATERIAL_CANCEL);
                                        btnAnnuler.addActionListener((back) -> {
                                            lp.showBack();
                                        });
                                        btnValider.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent evt) {
                                                if (fileNameProd.equals("")) {
                                                    ToastBar.showErrorMessage("Veuillez importer une image valide !");
                                                }
                                                if ((tfNom.getText().length() == 0) || (tfDescription.getText().length() == 0) || (tfPrix.getText().length() == 0) || (fileNameProd.equals(""))) {
                                                    Dialog.show("Alert", "s'il vous plait remplissez tout les champs", new Command("OK"));
                                                } else {
                                                    try {
                                                        Produit p = new Produit(tfNom.getText(), fileNameProd, Integer.parseInt(tfPrix.getText()), tfDescription.getText(), c.getSelectedItem().toString());
                                                        p.setUser_id(username.getText());
                                                        //System.out.println(username.getText());
                                                        if (ServiceProduit.getInstance().addProduit(p)) {
                                                            try {
                                                                Dialog.show("SUCCES", "Produit en attente de Confirmation par l'administrateur.", new Command("OK"));
                                                                String pathToBeStored = "file://C:/wamp64/www/symfony/web/events/" + fileNameProd;
                                                                OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                                                                ImageIO.getImageIO().save(img2Prod, os, ImageIO.FORMAT_PNG, 0.9f);
                                                                os.close();
                                                            } catch (Exception ex) {
                                                                System.out.println(ex);
                                                            }
                                                        } else {
                                                            Dialog.show("ERREUR", "Erreur du Serveur", new Command("OK"));
                                                        }
                                                    } catch (NumberFormatException e) {
                                                        Dialog.show("ERREUR", "Le prix doit etre un entier merci de vérifier", new Command("OK"));
                                                    }

                                                }

                                            }
                                        });

                                        addprod.addAll(tfNom, tfDescription, image, tfPrix, c, btnValider, btnAnnuler, imgProduit);
                                        addprod.show();
                                    });
                                    Dialog ipProd = new InfiniteProgress().showInifiniteBlocking();

                                    current = this;

                                    Toolbar.setGlobalToolbar(true);

                                    TextField searchField = new TextField("", "Rechercher");
                                    searchField.getHintLabel().setUIID("Title");
                                    searchField.setUIID("Title");
                                    searchField.getAllStyles().setAlignment(Component.LEFT);
                                    lp.getToolbar().setTitleComponent(searchField);

                                    FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_EDIT_ATTRIBUTES, s);
                                    Button Recherche = new Button(FontImage.MATERIAL_SEARCH);
                                    Recherche.addActionListener((search) -> {
                                        Produit c = new Produit();
                                        ArrayList<Produit> listepr = new ArrayList<>();

                                        ServiceProduit es = new ServiceProduit();
                                        if (searchField.getText().length() == 0) {
                                            ToastBar.showErrorMessage("Veuillez saisir le Nom du Produit !");
                                        } else {
                                            listepr = es.Recherche(searchField.getText());

                                            if (!listepr.toString().contains("Produit")) {
                                                ToastBar.showErrorMessage("Produit n'existe pas !", 1);
                                            }

                                            for (Produit i : listepr) {
                                                if (!searchField.getText().equals(i.getNom())) {
                                                    ToastBar.showErrorMessage("Produit n'existe pas !", 1);
                                                } else {
                                                    Form det = new Form();
                                                    det.setTitle("Résultat " + i.getNom());

                                                    det.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, back -> lp.showBack());

                                                    det.setLayout(BoxLayout.yCenter());

                                                    EncodedImage enco1 = EncodedImage.createFromImage(theme.getImage("load.png"), false);
                                                    String url1 = "http://localhost/symfony/web/events/" + i.getImage();
                                                    Image im1 = URLImage.createToStorage(enco1, i.getImage(), url1);
                                                    ImageViewer img = new ImageViewer(im1);

                                                    SpanLabel nom1 = new SpanLabel("Nom : " + i.getNom());
                                                    SpanLabel description1 = new SpanLabel("Description : " + i.getDescription());
                                                    SpanLabel image = new SpanLabel("Image :" + i.getImage());
                                                    SpanLabel categorie1 = new SpanLabel("Catégorie : " + i.getCategorie_nom());
                                                    SpanLabel prix1 = new SpanLabel("Prix : " + Integer.toString(i.getPrix()) + " TND");
                                                    SpanLabel usernameProduit = new SpanLabel("Publié par: " + i.getUser_id());

                                                    det.add(img);

                                                    det.add(String.valueOf("                             "));
                                                    Container ec = FlowLayout.encloseCenter(nom1);
                                                    Container ec1 = FlowLayout.encloseCenter(description1);
                                                    Container e2 = FlowLayout.encloseCenter(categorie1);
                                                    Container e3 = FlowLayout.encloseCenter(prix1);
                                                    Container eu = FlowLayout.encloseCenter(usernameProduit);

                                                    det.addAll(ec, ec1, e2, e3, eu);
                                                    det.add(String.valueOf("                             "));
                                                    det.show();

                                                    lp.getContentPane().animateLayout(250);
                                                }
                                            }
                                        }
                                    });

                                    lp.getToolbar().addCommandToRightBar("", searchIcon, (es) -> {
                                        searchField.startEditingAsync(); // <4>
                                    });
                                    lp.add(String.valueOf("                                                  "));
                                    lp.add(Recherche);
                                    if (new LoginService().getUser(us).toString().contains("Fournisseur")) {
                                        lp.addAll(btnAddCategorie, btnAddProduit);
                                    }

                                    ArrayList<Produit> listeproduits = new ArrayList<>();
                                    ServiceProduit ps = new ServiceProduit();
                                    listeproduits = ps.getAllProduits();

                                    for (Produit i : listeproduits) {
                                        //    System.out.println(i);
                                        lp.add(AddItem(i, theme, lp));
                                    }
                                    lp.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, back -> lf.showBack());
                                    lp.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                    });
                                    lp.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                        if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                            new loginForm(theme).show();
                                        }
                                    });
                                    lp.show();
                                });
                                lf.getToolbar().addCommandToSideMenu("Forum", icon6, e1 -> {
                                    HomeForumForm hf1 = new HomeForumForm(lf);
                                    hf1.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                    });
                                    hf1.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                        if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                            Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                            new loginForm(theme).show();
                                        }
                                    });
                                    hf1.show();
                                });
                                //lf.addAll(créerCol, créerCat, imv);

                                lf.show();
                            });
                            cf.getToolbar().addCommandToSideMenu("Accueil", icon2, e1 -> {
                                Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                hf.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                });
                                hf.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                    if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                        new loginForm(theme).show();
                                    }
                                });
                                hf.show();
                            });
                            cf.getToolbar().addCommandToSideMenu("Actualités", icon3, e1 -> {
                            });
                            cf.getToolbar().addCommandToSideMenu("A Propos", icon4, e1 -> {
                            });
                            cf.getToolbar().addCommandToSideMenu("Contact", icon5, e1 -> {
                            });
                            cf.getToolbar().addCommandToSideMenu("Evenement", icon7, e1 -> {
                                int countEvent = 0;
                                for (int prod = 0; prod < new WebService().getAllEventsP().size(); prod++) {
                                    if (new WebService().getAllEventsP().get(prod).getUser().equals(username.getText())) {
                                        countEvent++;
                                    }

                                    if (countEvent == 1) {
                                        Dialog.show("Information", "Vous avez " + countEvent + " evenement en attente de réponse par l'administrateur.", new Command("OK"));
                                    }
                                    if (countEvent > 0 && countEvent != 1) {
                                        Dialog.show("Information", "Vous avez " + countEvent + " evenements en attente de réponse par l'administrateur.", new Command("OK"));
                                    }
                                }
                                ListEvents le = new ListEvents(theme, cf);
                                le.setName("Evenements");
                                le.setTitle("Evenements");
                                le.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                                //Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
                                FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
                                FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
                                le.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, back -> {
                                    hf.showBack();
                                });
                                Button addEventBtn = new Button("Ajouter un Evenement");
                                if (new LoginService().getUser(us).toString().contains("Association")) {
                                    le.add(addEventBtn);
                                } else {
                                    le.add(String.valueOf("      "));
                                }
                                addEventBtn.addActionListener((addEvent) -> {
                                    AddEvent ae = new AddEvent(theme, le);
                                    ae.setName("Ajouter un Evenement");
                                    ae.setTitle("Ajouter un Evenement");
                                    ae.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                                    FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
                                    /**/
                                    Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                    Picker datePicker = new Picker();
                                    datePicker.setType(Display.PICKER_TYPE_DATE);
                                    TextField nom = new TextField();
                                    nom.setHint("Nom");
                                    TextField description = new TextField();
                                    description.setHint("Description");
                                    TextField lieu = new TextField();
                                    lieu.setHint("Lieu");

                                    Label Date = new Label("Date de l'Evenement : ");
                                    Button img = new Button("Ajouter une image", icone);

                                    Button b = new Button("Ajouter");
                                    WebService ws = new WebService();
                                    photos.add(nom);
                                    photos.add(description);
                                    photos.add(lieu);
                                    photos.add(Date);
                                    photos.add(datePicker);

                                    photos.add(img);
                                    photos.add(b);
                                    ae.add(photos);
                                    final String[] jobPic = new String[1];
                                    Label jobIcon = new Label();
                                    Button image = new Button(FontImage.MATERIAL_ADD_A_PHOTO);
                                    final String[] image_name = {""};
                                    final String[] filePath = {""};
                                    fileNameProd = "";
                                    ImageViewer imgPROD = new ImageViewer();
                                    img.addActionListener((ActionEvent actionEvent) -> {
                                        Display.getInstance().openGallery(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent ev) {

                                                filePath[0] = (String) ev.getSource();
                                                int fileNameIndex = filePath[0].lastIndexOf("/") + 1;
                                                fileNameProd = filePath[0].substring(fileNameIndex);
                                                try {
                                                    img2Prod = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                                                    jobIcon.setIcon(img2Prod);
                                                    imgPROD.setImage(img2Prod);

                                                } catch (Exception e) {
                                                }
                                            }
                                        }, Display.GALLERY_IMAGE);
                                    });
                                    b.addActionListener(ef -> {
                                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                        Date dateobj = new Date();
                                        Date da = datePicker.getDate();
                                        String st = df.format(dateobj);
                                        String st1 = df.format(da);
                                        Date dnow = new Date();
                                        Date dcc = new Date();
                                        try {
                                            dnow = df.parse(st);
                                            dcc = df.parse(st1);
                                        } catch (ParseException ex) {

                                        }
                                        if (nom.getText().equals("") || description.getText().equals("") || lieu.getText().equals("") || fileNameProd.equals("")) {
                                            Dialog.show("ERREUR", "Vérifiez vos informations", "OK", null);

                                        } else {
                                            Event ev = new Event();
                                            ev.setDateevent(st1);
                                            ev.setDescription(description.getText());
                                            ev.setImage(fileNameProd);
                                            ev.setNom(nom.getText());
                                            ev.setLieu(lieu.getText());
                                            ev.setUser(username.getText());
                                            try {
                                                String pathToBeStored = "file://C:/wamp64/www/symfony/web/events/" + fileNameProd;
                                                OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                                                ImageIO.getImageIO().save(img2Prod, os, ImageIO.FORMAT_PNG, 0.9f);
                                                os.close();
                                            } catch (Exception error) {
                                                System.out.println(error);
                                            }
                                            ws.addEvent(ev);

                                        }
                                    });
                                    ae.show();
                                });
                                WebService ws = new WebService();
                                EventService ds = new EventService();
                                Map x = ws.getResponse("event/listmobile");
                                ArrayList<Event> listevents = ds.getListsEvents(x);
                                for (Event event : listevents) {
                                    FontImage fi = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                                    EncodedImage enco = EncodedImage.createFromImage(fi.scaled(fi.getWidth() * 15, fi.getHeight() * 10), false);
                                    Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                    ImageViewer imv = null;
                                    Image img;
                                    EncodedImage encoded = null;
                                    Label aa = new Label(event.getNom());
                                    img = URLImage.createToStorage(enco, event.getImage(), "http://localhost/symfony/web/events/" + event.getImage());
                                    imv = new ImageViewer(img);
                                    photos.add(imv);
                                    photos.add(aa);
                                    try {
                                        ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));

                                        photos.add(sep);
                                    } catch (IOException ex) {
                                    }
                                    le.add(photos);

                                    aa.addPointerPressedListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent evt) {

                                            EventDetails.e = event;
                                            EventDetails evd = new EventDetails(theme, le);
                                            WebService ws = new WebService();
                                            evd.setName("Détail de l'évenement");
                                            evd.setTitle("Détail de l'évenement");
                                            evd.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                                            Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
                                            FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
                                            FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
                                            if (!new LoginService().getUser(us).toString().contains("Association")) {
                                                evd.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_SUBSCRIPTIONS, ev -> {
                                                    ws.addParticipate(aa.getText(), username.getText());
                                                });
                                            }

                                            evd.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_LIST, ev -> {
                                                ListComments ecom = new ListComments(theme, le);
                                                ecom.setName("Commentaires");
                                                ecom.setTitle("Commentaires");
                                                ecom.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                                                if (!new LoginService().getUser(us).toString().contains("Association")) {

                                                    ecom.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, evcom -> {
                                                        AddComment ac = new AddComment(theme, ecom);
                                                        ac.setName("Ajouter un Commentaire");
                                                        ac.setTitle("Ajouter un Commentaire");
                                                        ac.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

                                                        //WebService ws = new WebService();
                                                        Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                                                        TextArea a = new TextArea();
                                                        a.setHint("Commentaire");
                                                        Button b = new Button("Ajouter");

                                                        photos.add(a);
                                                        photos.add(b);

                                                        ac.add(photos);

                                                        b.addPointerPressedListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent evt) {
                                                                CommentEvent ce = new CommentEvent();
                                                                ce.setInEvent(id);
                                                                ce.setText(a.getText());
                                                                ce.setNomEvent(aa.getText());
                                                                ce.setUsername(username.getText());
                                                                ws.addComment(ce);
                                                                le.show();
                                                            }
                                                        });
                                                        ac.show();
                                                    });
                                                }
                                                ecom.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evcom -> {
                                                    evd.showBack();
                                                });
                                                EventService ds = new EventService();
                                                Map x = ws.getResponse("event/ListCom/" + event.getId());
                                                ArrayList<CommentEvent> listevents = ds.getListscomments(x);
                                                for (CommentEvent evcom : listevents) {
                                                    Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                                    ImageViewer imv = null;
                                                    Image img;
                                                    EncodedImage encoded = null;
                                                    Label a = new Label(evcom.getText());
                                                    Label b = new Label("Par : " + evcom.getUsername());

                                                    photos.add(a);
                                                    photos.add(b);
                                                    try {
                                                        ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));

                                                        photos.add(sep);
                                                    } catch (IOException ex) {
                                                    }
                                                    ecom.add(photos);

                                                    a.addPointerPressedListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent evt) {

                                                        }
                                                    });

                                                }
                                                ecom.show();
                                            });

                                            System.out.println(e.toString());
                                            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                            ImageViewer imv = new ImageViewer();
                                            //Image img;
                                            FontImage fiDet = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                                            EncodedImage encoDet = EncodedImage.createFromImage(fiDet.scaled(fiDet.getWidth() * 15, fiDet.getHeight() * 10), false);
                                            Slider rate = createStarRankSlider();
                                            Container CR = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                            Button bou = new Button("Donner une note");
                                            if (!new LoginService().getUser(us).toString().contains("Association")) {
                                                CR.add(FlowLayout.encloseCenter(rate));
                                                CR.add(FlowLayout.encloseCenter(bou));
                                            }
                                            Label a = new Label("Nom : " + event.getNom());
                                            Label b = new Label("Description : " + event.getDescription());
                                            Label c = new Label("Lieu  : " + event.getLieu());
                                            Label d = new Label("Date : " + event.getDateevent());
                                            Label ef = new Label("Nombre participants : " + event.getNbrInterest());
                                            Label ed = new Label("Note : " + event.getMoyenne());
                                            Label er = new Label(ws.getCountComment("event/countCom/" + event.getId()) + " Commentaires");
                                            Image img = URLImage.createToStorage(encoDet, event.getImage(), "http://localhost/symfony/web/app_dev.php/events/" + event.getImage());
                                            imv = new ImageViewer(img);
                                            photos.add(imv);

                                            try {
                                                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));

                                                photos.add(sep);
                                            } catch (IOException ex) {
                                            }
                                            photos.add(a);
                                            photos.add(b);
                                            photos.add(c);
                                            photos.add(d);
                                            photos.add(ef);
                                            photos.add(ed);
                                            photos.add(er);
                                            photos.add(CR);
                                            evd.add(photos);

                                            bou.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent evt) {
                                                    int avis = rate.getProgress() / 2;
                                                    ws.addRate(event.getId(), avis, username.getText());
                                                    le.show();
                                                }
                                            });
                                            evd.show();
                                        }
                                    });

                                }
                                le.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                });
                                le.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                    if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                        Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                        new loginForm(theme).show();
                                    }
                                });
                                le.show();
                            });
                            cf.getToolbar().addCommandToSideMenu("Publication", icon8, e1 -> {
                                HomeForm_1 hf1 = new HomeForm_1(theme, cf);
                                hf1.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                });
                                hf1.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                    if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                        Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                        new loginForm(theme).show();
                                    }
                                });
                                hf1.show();
                            });
                            cf.getToolbar().addCommandToSideMenu("Réclamation", icon9, e1 -> {
                            });
                            cf.getToolbar().addCommandToSideMenu("Collecte", icon10, e1 -> {
                            });
                            cf.getToolbar().addCommandToSideMenu("Produit", icon11, e1 -> {
                                int countProd = 0;
                                for (int prod = 0; prod < new ServiceProduit().getAllProduitsP().size(); prod++) {
                                    if (new ServiceProduit().getAllProduitsP().get(prod).getUser_id().equals(username.getText())) {
                                        countProd++;
                                    }

                                    if (countProd == 1) {
                                        Dialog.show("Information", "Vous avez " + countProd + " produit en attente de réponse par l'administrateur.", new Command("OK"));
                                    }
                                    if (countProd > 0 && countProd != 1) {
                                        Dialog.show("Information", "Vous avez " + countProd + " produits en attente de réponse par l'administrateur.", new Command("OK"));
                                    }
                                }
                                ListProduitsForm lp = new ListProduitsForm(cf, theme);
                                Button btnAddProduit = new Button(FontImage.MATERIAL_ADD_SHOPPING_CART);
                                Button btnAddCategorie = new Button(FontImage.MATERIAL_ADD_CHART);

                                btnAddCategorie.addActionListener((addCatProd) -> {
                                    Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                    AddCategorieForm acatproduit = new AddCategorieForm(lp);
                                    acatproduit.setTitle("Ajouter Une Categorie");
                                    acatproduit.setLayout(BoxLayout.y());

                                    TextField tfNom = new TextField("", "Nom");
                                    TextField tfDescription = new TextField("", "Description");

                                    Button btnValider = new Button(FontImage.MATERIAL_ADD);
                                    Button btnAnnuler = new Button(FontImage.MATERIAL_CANCEL);
                                    btnAnnuler.addActionListener((backtolist) -> {
                                        lp.showBack();
                                    });
                                    btnValider.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent evt) {

                                            if ((tfNom.getText().length() == 0) || (tfDescription.getText().length() == 0)) {
                                                Dialog.show("Alerte", "S'il vous plait, remplissez tout les champs", new Command("OK"));
                                            } else {
                                                try {
                                                    CategorieProduit p = new CategorieProduit(tfNom.getText(), tfDescription.getText());
                                                    if (ServiceCategorie.getInstance().addCategorie(p)) {

                                                        Dialog.show("Succés", "Categorie ajoutée avec succés", new Command("OK"));
                                                    } else {
                                                        Dialog.show("ERREUR", "Erreur du serveur", new Command("OK"));
                                                    }
                                                } catch (NumberFormatException e) {
                                                    Dialog.show("ERROR", "Le champs doit etre un entier merci de vérifier", new Command("OK"));
                                                }

                                            }

                                        }
                                    });

                                    acatproduit.addAll(tfNom, tfDescription, btnValider, btnAnnuler);
                                    acatproduit.show();
                                });
                                btnAddProduit.addActionListener((addProd) -> {
                                    AddProduitForm addprod = new AddProduitForm(lp);
                                    addprod.setTitle("Ajouter Un Produit");
                                    addprod.setLayout(BoxLayout.y());

                                    TextField tfNom = new TextField("", "Nom");
                                    TextField tfDescription = new TextField("", "Description");
                                    //     TextField tfImage= new TextField("", "image");
                                    TextField tfPrix = new TextField("", "Prix");

                                    ComboBox<String> c = new ComboBox();
                                    for (int i = 0; i < new ServiceCategorie().getAllCategories().size(); i++) {

                                        c.addItem(new ServiceCategorie().getAllCategories().get(i).toString());
                                    }
                                    final String[] jobPic = new String[1];
                                    Label jobIcon = new Label();
                                    Button image = new Button(FontImage.MATERIAL_ADD_A_PHOTO);
                                    final String[] image_name = {""};
                                    final String[] filePath = {""};
                                    fileNameProd = "";
                                    ImageViewer imgProduit = new ImageViewer();
                                    image.addActionListener((ActionEvent actionEvent) -> {
                                        Display.getInstance().openGallery(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent ev) {

                                                filePath[0] = (String) ev.getSource();
                                                // System.out.println(ev.getSource());
                                                int fileNameIndex = filePath[0].lastIndexOf("/") + 1;
                                                fileNameProd = filePath[0].substring(fileNameIndex);
                                                try {
                                                    img2Prod = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                                                    jobIcon.setIcon(img2Prod);
                                                    imgProduit.setImage(img2Prod);

                                                } catch (Exception e) {
                                                }
                                            }
                                        }, Display.GALLERY_IMAGE);

                                    });
                                    Button btnValider = new Button(FontImage.MATERIAL_ADD);
                                    Button btnAnnuler = new Button(FontImage.MATERIAL_CANCEL);
                                    btnAnnuler.addActionListener((back) -> {
                                        lp.showBack();
                                    });
                                    btnValider.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent evt) {
                                            if (fileNameProd.equals("")) {
                                                ToastBar.showErrorMessage("Veuillez importer une image valide !");
                                            }
                                            if ((tfNom.getText().length() == 0) || (tfDescription.getText().length() == 0) || (tfPrix.getText().length() == 0) || (fileNameProd.equals(""))) {
                                                Dialog.show("Alert", "s'il vous plait remplissez tout les champs", new Command("OK"));
                                            } else {
                                                try {
                                                    Produit p = new Produit(tfNom.getText(), fileNameProd, Integer.parseInt(tfPrix.getText()), tfDescription.getText(), c.getSelectedItem().toString());
                                                    p.setUser_id(username.getText());
                                                    //System.out.println(username.getText());
                                                    if (ServiceProduit.getInstance().addProduit(p)) {
                                                        try {
                                                            Dialog.show("SUCCES", "Produit en attente de Confirmation par l'administrateur.", new Command("OK"));
                                                            String pathToBeStored = "file://C:/wamp64/www/symfony/web/events/" + fileNameProd;
                                                            OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                                                            ImageIO.getImageIO().save(img2Prod, os, ImageIO.FORMAT_PNG, 0.9f);
                                                            os.close();
                                                        } catch (Exception ex) {
                                                            System.out.println(ex);
                                                        }
                                                    } else {
                                                        Dialog.show("ERREUR", "Server error", new Command("OK"));
                                                    }
                                                } catch (NumberFormatException e) {
                                                    Dialog.show("ERREUR", "Le prix doit etre un entier merci de vérifier", new Command("OK"));
                                                }

                                            }

                                        }
                                    });

                                    addprod.addAll(tfNom, tfDescription, image, tfPrix, c, btnValider, btnAnnuler, imgProduit);
                                    addprod.show();
                                });
                                Dialog ipProd = new InfiniteProgress().showInifiniteBlocking();
                                current = this;

                                Toolbar.setGlobalToolbar(true);
                                //Style s = UIManager.getInstance().getComponentStyle("Title");

                                TextField searchField = new TextField("", "Rechercher"); // <1>
                                searchField.getHintLabel().setUIID("Title");
                                searchField.setUIID("Title");
                                searchField.getAllStyles().setAlignment(Component.LEFT);
                                lp.getToolbar().setTitleComponent(searchField);

                                FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_EDIT_ATTRIBUTES, s);
                                Button Recherche = new Button(FontImage.MATERIAL_SEARCH);
                                Recherche.addActionListener((search) -> {
                                    Produit c = new Produit();
                                    ArrayList<Produit> listepr = new ArrayList<>();

                                    ServiceProduit es = new ServiceProduit();
                                    if (searchField.getText().length() == 0) {
                                        ToastBar.showErrorMessage("Veuillez saisir le Nom du Produit !");
                                    } else {
                                        listepr = es.Recherche(searchField.getText());

                                        if (!listepr.toString().contains("Produit")) {
                                            ToastBar.showErrorMessage("Produit n'existe pas !", 1);
                                        }

                                        for (Produit i : listepr) {
                                            if (!searchField.getText().equals(i.getNom())) {
                                                ToastBar.showErrorMessage("Produit n'existe pas !", 1);
                                            } else {
                                                Form det = new Form();
                                                det.setTitle("Résultat  " + i.getNom());

                                                det.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, back -> lp.showBack());

                                                det.setLayout(BoxLayout.yCenter());

                                                EncodedImage enco1 = EncodedImage.createFromImage(theme.getImage("load.png"), false);
                                                String url1 = "http://localhost/symfony/web/events/" + i.getImage();
                                                Image im1 = URLImage.createToStorage(enco1, i.getImage(), url1);
                                                ImageViewer img = new ImageViewer(im1);

                                                SpanLabel nom1 = new SpanLabel("Nom : " + i.getNom());
                                                SpanLabel description1 = new SpanLabel("Description : " + i.getDescription());
                                                SpanLabel image = new SpanLabel("Image est :" + i.getImage());
                                                SpanLabel categorie1 = new SpanLabel("Catégorie : " + i.getCategorie_nom());
                                                SpanLabel prix1 = new SpanLabel("Prix : " + Integer.toString(i.getPrix()) + " TND");
                                                SpanLabel usernameProduit = new SpanLabel("Publié par: " + i.getUser_id());

                                                det.add(img);

                                                det.add(String.valueOf("                             "));
                                                Container ec = FlowLayout.encloseCenter(nom1);
                                                Container ec2 = FlowLayout.encloseCenter(description1);
                                                Container e2 = FlowLayout.encloseCenter(categorie1);
                                                Container e3 = FlowLayout.encloseCenter(prix1);
                                                Container eu = FlowLayout.encloseCenter(usernameProduit);

                                                det.addAll(ec, ec2, e2, e3, eu);
                                                det.add(String.valueOf("                             "));
                                                det.show();

                                                lp.getContentPane().animateLayout(250);
                                            }
                                        }
                                    }
                                });

                                lp.getToolbar().addCommandToRightBar("", searchIcon, (es) -> {
                                    searchField.startEditingAsync(); // <4>
                                });
                                lp.add(String.valueOf("                                                  "));
                                lp.add(Recherche);
                                if (new LoginService().getUser(us).toString().contains("Fournisseur")) {
                                    lp.addAll(btnAddCategorie, btnAddProduit);
                                }

                                ArrayList<Produit> listeproduits = new ArrayList<>();
                                ServiceProduit ps = new ServiceProduit();
                                listeproduits = ps.getAllProduits();

                                for (Produit i : listeproduits) {
                                    //    System.out.println(i);
                                    lp.add(AddItem(i, theme, lp));
                                }
                                lp.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, back -> cf.showBack());
                                lp.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                });
                                lp.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                    if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                        Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                        new loginForm(theme).show();
                                    }
                                });
                                lp.show();
                            });
                            cf.getToolbar().addCommandToSideMenu("Forum", icon6, e1 -> {
                                HomeForumForm hf1 = new HomeForumForm(cf);
                                hf1.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                                });
                                hf1.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                    if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                        Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                        new loginForm(theme).show();
                                    }
                                });
                                hf1.show();
                            });
                            cf.show();
                        });
                        Label logo = new Label("");
                        Label space = new Label("        ");
                        Label space3 = new Label("      ");
                        Label citation = new Label();
                        Label citation2 = new Label();
                        Label citation3 = new Label();
                        Label author = new Label();
                        Label numberUsers = new Label();
                        Label c = new Label();
                        Label eventsT = new Label();
                        Label spacee = new Label("          ");
                        Image solidaire = theme.getImage("solidarity.jpg");
                        Label solidarity = new Label();
                        solidarity.setIcon(solidaire);
                        int n = new LoginService().getUsers().size();
                        int nevents = new WebService().getAllEventsExist().size();
                        eventsT.setText(Integer.toString(nevents) + " Evenements");
                        c.setText(Integer.toString(n));
                        Label u = new Label(c.getText() + " Utilisateurs");
                        //u.getStyle().setFgColor(111111);
                        u.setAlignment(CENTER);
                        eventsT.setAlignment(CENTER);
                        int nc = new CollecteService().getAllCollects().size();
                        Label col = new Label(Integer.toString(nc) + " Collectes");
                        //col.getStyle().setFgColor(111111);
                        col.setAlignment(CENTER);
                        logo.setIcon(theme.getImage("SaviorsLogo.png"));
                        logo.setAlignment(CENTER);
                        citation.setText("\"Sans solidarité,");
                        citation2.setText("performances ni durables");
                        citation3.setText("ni honorables\"");
                        author.setText("François Proust");
                        citation.setAlignment(CENTER);
                        citation2.setAlignment(CENTER);
                        citation3.setAlignment(CENTER);
                        author.setAlignment(CENTER);
                        solidarity.setAlignment(CENTER);
                        Label space2 = new Label(" ");
                        citation.getStyle().setFgColor(654321);
                        citation2.getStyle().setFgColor(654321);
                        citation3.getStyle().setFgColor(654321);
                        author.getStyle().setFgColor(654321);
                        hf.getToolbar().addCommandToSideMenu("Produit", icon11, e -> {
                            int countProd = 0;
                            for (int prod = 0; prod < new ServiceProduit().getAllProduitsP().size(); prod++) {
                                if (new ServiceProduit().getAllProduitsP().get(prod).getUser_id().equals(username.getText())) {
                                    countProd++;
                                }
                            }
                            if (countProd == 1) {
                                Dialog.show("Information", "Vous avez " + countProd + " produit en attente de réponse par l'administrateur.", new Command("OK"));
                            }
                            if (countProd > 0 && countProd != 1) {
                                Dialog.show("Information", "Vous avez " + countProd + " produits en attente de réponse par l'administrateur.", new Command("OK"));
                            }
                            ListProduitsForm lp = new ListProduitsForm(hf, theme);
                            Button btnAddProduit = new Button(FontImage.MATERIAL_ADD_SHOPPING_CART);
                            Button btnAddCategorie = new Button(FontImage.MATERIAL_ADD_CHART);

                            btnAddCategorie.addActionListener((addCatProd) -> {
                                Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                AddCategorieForm acatproduit = new AddCategorieForm(lp);
                                acatproduit.setTitle("Ajouter Une Categorie");
                                acatproduit.setLayout(BoxLayout.y());

                                TextField tfNom = new TextField("", "Nom");
                                TextField tfDescription = new TextField("", "Description");

                                Button btnValider = new Button(FontImage.MATERIAL_ADD);
                                Button btnAnnuler = new Button(FontImage.MATERIAL_CANCEL);
                                btnAnnuler.addActionListener((backtolist) -> {
                                    lp.showBack();
                                });
                                btnValider.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {

                                        if ((tfNom.getText().length() == 0) || (tfDescription.getText().length() == 0)) {
                                            Dialog.show("Alerte", "S'il vous plait, remplissez tout les champs", new Command("OK"));
                                        } else {
                                            try {
                                                CategorieProduit p = new CategorieProduit(tfNom.getText(), tfDescription.getText());
                                                if (ServiceCategorie.getInstance().addCategorie(p)) {

                                                    Dialog.show("Succés", "Categorie ajoutée avec succés", new Command("OK"));
                                                } else {
                                                    Dialog.show("ERREUR", "Erreur du serveur", new Command("OK"));
                                                }
                                            } catch (NumberFormatException e) {
                                                Dialog.show("ERROR", "Le champs doit etre un entier merci de vérifier", new Command("OK"));
                                            }

                                        }

                                    }
                                });

                                acatproduit.addAll(tfNom, tfDescription, btnValider, btnAnnuler);
                                acatproduit.show();
                            });
                            btnAddProduit.addActionListener((addProd) -> {
                                AddProduitForm addprod = new AddProduitForm(lp);
                                addprod.setTitle("Ajouter Un Produit");
                                addprod.setLayout(BoxLayout.y());

                                TextField tfNom = new TextField("", "Nom");
                                TextField tfDescription = new TextField("", "Description");
                                //     TextField tfImage= new TextField("", "image");
                                TextField tfPrix = new TextField("", "Prix");

                                ComboBox<String> cc = new ComboBox();
                                for (int i = 0; i < new ServiceCategorie().getAllCategories().size(); i++) {

                                    cc.addItem(new ServiceCategorie().getAllCategories().get(i).toString());
                                }
                                final String[] jobPic = new String[1];
                                Label jobIcon = new Label();
                                Button image = new Button(FontImage.MATERIAL_ADD_A_PHOTO);
                                final String[] image_name = {""};
                                final String[] filePath = {""};
                                fileNameProd = "";
                                ImageViewer imgProduit = new ImageViewer();
                                image.addActionListener((ActionEvent actionEvent) -> {
                                    Display.getInstance().openGallery(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent ev) {

                                            filePath[0] = (String) ev.getSource();
                                            // System.out.println(ev.getSource());
                                            int fileNameIndex = filePath[0].lastIndexOf("/") + 1;
                                            fileNameProd = filePath[0].substring(fileNameIndex);
                                            try {
                                                img2Prod = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                                                jobIcon.setIcon(img2Prod);
                                                imgProduit.setImage(img2Prod);

                                            } catch (Exception e) {
                                            }
                                        }
                                    }, Display.GALLERY_IMAGE);
                                });
                                Button btnValider = new Button(FontImage.MATERIAL_ADD);
                                Button btnAnnuler = new Button(FontImage.MATERIAL_CANCEL);
                                btnAnnuler.addActionListener((back) -> {
                                    lp.showBack();
                                });
                                btnValider.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        if (fileNameProd.equals("")) {
                                            ToastBar.showErrorMessage("Veuillez importer une image valide !");
                                        }
                                        if ((tfNom.getText().length() == 0) || (tfDescription.getText().length() == 0) || (tfPrix.getText().length() == 0) || (fileNameProd.equals(""))) {
                                            Dialog.show("Alert", "s'il vous plait remplissez tout les champs", new Command("OK"));
                                        } else {
                                            try {
                                                Produit p = new Produit(tfNom.getText(), fileNameProd, Integer.parseInt(tfPrix.getText()), tfDescription.getText(), cc.getSelectedItem().toString());
                                                p.setUser_id(username.getText());
                                                //System.out.println(username.getText());
                                                if (ServiceProduit.getInstance().addProduit(p)) {
                                                    try {
                                                        Dialog.show("SUCCES", "Produit en attente de Confirmation par l'administrateur.", new Command("OK"));
                                                        String pathToBeStored = "file://C:/wamp64/www/symfony/web/events/" + fileNameProd;
                                                        OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                                                        ImageIO.getImageIO().save(img2Prod, os, ImageIO.FORMAT_PNG, 0.9f);
                                                        os.close();
                                                    } catch (Exception ex) {
                                                        System.out.println(ex);
                                                    }
                                                } else {
                                                    Dialog.show("ERREUR", "Server error", new Command("OK"));
                                                }
                                            } catch (NumberFormatException e) {
                                                Dialog.show("ERREUR", "Le prix doit etre un entier merci de vérifier", new Command("OK"));
                                            }

                                        }

                                    }
                                });

                                addprod.addAll(tfNom, tfDescription, image, tfPrix, cc, btnValider, btnAnnuler, imgProduit);
                                addprod.show();
                            });
                            Dialog ipProd = new InfiniteProgress().showInifiniteBlocking();
                            current = this;

                            Toolbar.setGlobalToolbar(true);
                            //Style s = UIManager.getInstance().getComponentStyle("Title");

                            TextField searchField = new TextField("", "Rechercher"); // <1>
                            searchField.getHintLabel().setUIID("Title");
                            searchField.setUIID("Title");
                            searchField.getAllStyles().setAlignment(Component.LEFT);
                            lp.getToolbar().setTitleComponent(searchField);

                            FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_EDIT_ATTRIBUTES, s);
                            Button Recherche = new Button(FontImage.MATERIAL_SEARCH);
                            Recherche.addActionListener((search) -> {
                                Produit produit = new Produit();
                                ArrayList<Produit> listepr = new ArrayList<>();

                                ServiceProduit es = new ServiceProduit();

                                if (searchField.getText().length() == 0) {
                                    ToastBar.showErrorMessage("Veuillez saisir le Nom du Produit !");
                                } else {
                                    listepr = es.Recherche(searchField.getText());

                                    if (!listepr.toString().contains("Produit")) {
                                        ToastBar.showErrorMessage("Produit n'existe pas !", 1);
                                    }

                                    for (Produit i : listepr) {
                                        if (!searchField.getText().equals(i.getNom())) {
                                            ToastBar.showErrorMessage("Produit n'existe pas !", 1);
                                        } else {
                                            Form det = new Form();
                                            det.setTitle("Résultat " + i.getNom());

                                            det.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, back -> lp.showBack());

                                            det.setLayout(BoxLayout.yCenter());

                                            EncodedImage enco1 = EncodedImage.createFromImage(theme.getImage("load.png"), false);
                                            String url1 = "http://localhost/symfony/web/events/" + i.getImage();
                                            Image im1 = URLImage.createToStorage(enco1, i.getImage(), url1);
                                            ImageViewer img = new ImageViewer(im1);

                                            SpanLabel nom1 = new SpanLabel("Nom : " + i.getNom());
                                            SpanLabel description1 = new SpanLabel("Description : " + i.getDescription());
                                            //SpanLabel image = new SpanLabel("Image est :" + i.getImage());
                                            SpanLabel categorie1 = new SpanLabel("Catégorie : " + i.getCategorie_nom());
                                            SpanLabel prix1 = new SpanLabel("Prix : " + Integer.toString(i.getPrix()) + " TND");
                                            SpanLabel usernameProduit = new SpanLabel("Publié par: " + i.getUser_id());
                                            det.add(img);

                                            det.add(String.valueOf("                             "));
                                            Container ec = FlowLayout.encloseCenter(nom1);
                                            Container e1 = FlowLayout.encloseCenter(description1);
                                            Container e2 = FlowLayout.encloseCenter(categorie1);
                                            Container e3 = FlowLayout.encloseCenter(prix1);
                                            Container eu = FlowLayout.encloseCenter(usernameProduit);

                                            det.addAll(ec, e1, e2, e3, eu);
                                            det.add(String.valueOf("                             "));
                                            det.show();

                                            lp.getContentPane().animateLayout(250);

                                        }
                                    }
                                }
                            });

                            lp.getToolbar().addCommandToRightBar("", searchIcon, (es) -> {
                                searchField.startEditingAsync(); // <4>
                            });
                            lp.add(String.valueOf("                                                  "));
                            lp.add(Recherche);
                            if (new LoginService().getUser(us).toString().contains("Fournisseur")) {
                                lp.addAll(btnAddCategorie, btnAddProduit);
                            }

                            ArrayList<Produit> listeproduits = new ArrayList<>();
                            ServiceProduit ps = new ServiceProduit();
                            listeproduits = ps.getAllProduits();

                            for (Produit i : listeproduits) {
                                //    System.out.println(i);
                                lp.add(AddItem(i, theme, lp));
                            }
                            lp.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, back -> hf.showBack());
                            lp.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                            });
                            lp.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                    Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                    new loginForm(theme).show();
                                }
                            });
                            lp.show();
                        });
                        hf.getToolbar().addCommandToSideMenu("Forum", icon6, e -> {
                            HomeForumForm hf1 = new HomeForumForm(hf);
                            hf1.getToolbar().addCommandToRightBar(username.getText(), null, (evtt) -> {
                            });
                            hf1.getToolbar().addCommandToRightBar(null, theme.getImage("disconnect.png"), (evtt) -> {
                                if (Dialog.show("Déconnexion", "Confirmer la déconnexion ?", "OK", "Cancel")) {
                                    Dialog ip3 = new InfiniteProgress().showInifiniteBlocking();
                                    new loginForm(theme).show();
                                }
                            });
                            hf1.show();
                        });
                        hf.addAll(space, solidarity, space3, citation, citation2, citation3, space2, author, spacee, u, col, eventsT);
                        hf.show();
                        //new HomeForm(theme).getUsernameL().setText(username.getText());

                    } else {
                        Dialog.show("ERREUR", "Connexion échouée !", "OK", null);
                    }
                } catch (Exception e) {
                    Dialog.show("ERREUR", "Connexion échouée !", "OK", null);
                }
            }
        }
        );

        this.addAll(img, username, password, login);
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }
    public static String codex;
    public static final String ACCOUNT_SID = "AC131848a87fecf99be837175861639fc2";
    public static final String AUTH_TOKEN = "037b167e9e6177aa1e92c55bab6c86c3";
    String phonenumber = "+21693195113";

    public Container AddItem(Produit c, Resources theme, ListProduitsForm listProd) {

        current = this;

        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        EncodedImage enco = EncodedImage.createFromImage(theme.getImage("load.png"), false);
        String url = "http://localhost/symfony/web/events/" + c.getImage();
        Image im = URLImage.createToStorage(enco, c.getImage(), url);
        ImageViewer imv = new ImageViewer(im);
        c1.add(imv);

        //  ImageViewer img=new ImageViewer(theme.getImage(c.getImg()));
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        // SpanLabel l = new SpanLabel("id est :" + Integer.toString(c.getId()));
        SpanLabel nom = new SpanLabel("Nom : " + c.getNom());
        Button supp = new Button(FontImage.MATERIAL_DELETE);
        Button detail = new Button(FontImage.MATERIAL_COMMENT);
        Button modif = new Button(FontImage.MATERIAL_UPDATE);

        Button msg = new Button(FontImage.MATERIAL_SMARTPHONE);
        SpanLabel description = new SpanLabel("Description : " + c.getDescription());
        //SpanLabel image = new SpanLabel("L image est :" + c.getImage());
        SpanLabel categorie = new SpanLabel("Catégorie : " + c.getCategorie_nom());

        SpanLabel prix = new SpanLabel("Prix : " + Integer.toString(c.getPrix()) + " TND");
        SpanLabel usernameProd = new SpanLabel("Ajoutée par: " + c.getUser_id());
        // c2.add(l);
        c2.add(nom);
        c2.add(description);
        // c2.add(image);
        c2.add(categorie);
        c2.add(prix);
        c2.add(usernameProd);
        c2.add(detail);

        UserSignup ul = new UserSignup(username.getText(), password.getText());
        //System.out.println(usernameProd.getText());
        if (new LoginService().getUser(ul).toString().contains("Fournisseur") && usernameProd.getText().toString().contains(username.getText())) {
            c2.add(modif);
            c2.add(supp);
        }
        if (!new LoginService().getUser(ul).toString().contains("Fournisseur")) {
            c2.add(msg);
        }
        c1.add(c2);
        c1.getStyle().setBorder(Border.createLineBorder(2));
        c1.getStyle().setMargin(1, 1, 1, 1);
        c1.getStyle().setPadding(0, 0, 0, 0);
        c1.getStyle().setBgColor(0xffabab);
        msg.addActionListener((evt) -> {

            System.out.println("=========================");
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < 5) { // length of the random string.
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                salt.append(SALTCHARS.charAt(index));
            }
            String saltStr = salt.toString();
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            com.twilio.rest.api.v2010.account.Message messages = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber(phonenumber),
                    new PhoneNumber("+12563686010"), "je veux commander ce produit : " + c.getNom() + "," + saltStr).create();

            codex = saltStr;
            System.out.println("======================");
            System.out.println("======================");
            System.out.println("======================");
            System.out.println(codex);
            Dialog.show("succes", "sms envoyer au responsable du prduit merci d'avance *_* ", "ok", null);
        });

        supp.addActionListener(evt -> {
            ServiceProduit es = new ServiceProduit();
            if (Dialog.show("Confirmation", "Supprimer ce Produit ?", "OK", "CANCEL")) {
                es.delete(c.getId());
                HomeForm_2 h = new HomeForm_2(theme, listProd);
                listProd.show();
            }
        });

        imv.addPointerReleasedListener(ev
                -> {
            Form det = new Form();
            det.setTitle("Detail du Produit");

            det.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> listProd.showBack());

            det.setLayout(BoxLayout.yCenter());

            EncodedImage enco1 = EncodedImage.createFromImage(theme.getImage("load.png"), false);
            String url1 = "http://localhost/symfony/web/events/" + c.getImage();
            Image im1 = URLImage.createToStorage(enco, c.getImage(), url1);
            ImageViewer img = new ImageViewer(im);

            SpanLabel nom1 = new SpanLabel("Nom : " + c.getNom());
            SpanLabel description1 = new SpanLabel("Description : " + c.getDescription());
            SpanLabel image = new SpanLabel("Image est :" + c.getImage());
            SpanLabel categorie1 = new SpanLabel("Catégorie : " + c.getCategorie_nom());
            SpanLabel prix1 = new SpanLabel("Prix : " + Integer.toString(c.getPrix()) + " DT");

            //Button btnAnnuler = new Button(FontImage.MATERIAL_CANCEL);
            /*btnAnnuler.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ListProduitsForm h = new ListProduitsForm(listProd, theme);
                    h.show();
                }
            });*/
            det.add(img);

            det.add(String.valueOf("                             "));
            Container e = FlowLayout.encloseCenter(nom1);
            Container e1 = FlowLayout.encloseCenter(description1);
            Container e2 = FlowLayout.encloseCenter(categorie1);
            Container e3 = FlowLayout.encloseCenter(prix1);
            /*det.add(nom);
                det.add(description);
                det.add(categorie);
                det.add(prix);*/
            det.addAll(e, e1, e2, e3);
            det.add(String.valueOf("                             "));
            //det.add(btnAnnuler);
            det.show();

            ServiceProduit ser = new ServiceProduit();
            ser.Getproduit(c.getId());

        });

        detail.addPointerPressedListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Form det = new Form();
                det.setTitle("Commentaire");

                det.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> listProd.showBack());

                det.setLayout(BoxLayout.yCenter());

                EncodedImage enco = EncodedImage.createFromImage(theme.getImage("load.png"), false);
                String url = "http://localhost/symfony/web/events/" + c.getImage();
                Image im = URLImage.createToStorage(enco, c.getImage(), url);
                ImageViewer imv = new ImageViewer(im);

                SpanLabel nom = new SpanLabel("Nom : " + c.getNom());
                SpanLabel description = new SpanLabel("Description : " + c.getDescription());
                // SpanLabel image = new SpanLabel("L image est :" + c.getImage());
                SpanLabel categorie = new SpanLabel("Catégorie : " + c.getCategorie_nom());
                ServiceProduit se = new ServiceProduit();
                SpanLabel prix = new SpanLabel("Prix : " + Integer.toString(c.getPrix()) + " DT");
                TextArea commentaire = new TextArea();
                commentaire.setHint("Publier un Commentaire");

                Button clickbtn = new Button(FontImage.MATERIAL_LIST);

                clickbtn.addPointerPressedListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Accordion accr = new Accordion();
                        String contenu = "";
                        Label ContentL = new Label();
                        String usernameProd = "";
                        for (int i = 0; i < se.getAllCommentsCols().size(); i++) {
                            if (se.getAllCommentsCols().get(i).getProduitPending_id().equals(c.getNom())) {
                                usernameProd = se.getAllCommentsCols().get(i).getUser_id();
                                contenu = se.getAllCommentsCols().get(i).getContent();
                                ContentL.setText(contenu);
                                accr.addContent(usernameProd, new SpanLabel(ContentL.getText()));
                            }
                        }

                        if (se.getAllCommentsCols().size() == 0) {
                            Dialog.show("ERREUR", "Pas de commentaires pour ce Produit !", new Command("OK"));
                        }
                        det.add(accr);

                        det.show();
                    }
                });

                Button btnComment = new Button(FontImage.MATERIAL_ADD_COMMENT);
                Button btnAnnuler = new Button(FontImage.MATERIAL_CANCEL);
                btnComment.addPointerPressedListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (commentaire.getText().length() == 0) {
                            Dialog.show("ERREUR", "Champ requis !", new Command("OK"));
                        } else {
                            try {
                                ProduitComment p = new ProduitComment();
                                p = new ProduitComment(c.getNom(), commentaire.getText(), username.getText());

                                if (ServiceProduit.getInstance().addComment(p)) {
                                    Dialog.show("Succés", "Commentaire ajouté avec succés !", new Command("OK"));
                                    commentaire.setText("");
                                    /*ListProduitsForm h = new ListProduitsForm(current, theme);
                                h.current.show();*/
                                } else {
                                    Dialog.show("ERREUR", "error", new Command("OK"));
                                }
                            } catch (NumberFormatException e) {
                                Dialog.show("ERREUR", "Statut doit être un Nombre.", new Command("OK"));
                            }
                        }
                    }
                });

                det.add(imv);

                det.add(String.valueOf("                             "));
                Container e = FlowLayout.encloseCenter(nom);
                Container e1 = FlowLayout.encloseCenter(description);
                Container e2 = FlowLayout.encloseCenter(categorie);
                Container e3 = FlowLayout.encloseCenter(prix);
                /*det.add(nom);
                det.add(description);
                det.add(categorie);
                det.add(prix);*/
                det.addAll(e, e1, e2, e3);
                det.add(String.valueOf("                             "));
                //UserSignup ul = new UserSignup(username.getText(), password.getText());
                //System.out.println(usernameProd.getText());
                if (!usernameProd.getText().toString().contains(username.getText())) {
                    det.add(commentaire);
                    det.add(btnComment);
                }

                det.add(clickbtn);
                det.show();
                ServiceProduit se1 = new ServiceProduit();
                se1.Getproduit(c.getId());

            }

        }
        );

        modif.addPointerPressedListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Form fod = new Form();
                fod.setTitle("Modifier Produit");

                fod.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> listProd.showBack());

                fod.setLayout(BoxLayout.y());
                ServiceProduit ser = new ServiceProduit();
                TextField tfNom = new TextField(c.getNom());
                TextField tfDescription = new TextField(c.getDescription());
                //TextField tfImage = new TextField(c.getImage());

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

                TextField tfPrix = new TextField(c.getPrix() + "");
                ComboBox<String> cx = new ComboBox();
                for (int i = 0; i < new ServiceCategorie().getAllCategories().size(); i++) {

                    cx.addItem(new ServiceCategorie().getAllCategories().get(i).toString());
                }
                Button bo = new Button(FontImage.MATERIAL_SAVE);
                Button btnAnnuler = new Button(FontImage.MATERIAL_CANCEL);
                btnAnnuler.addPointerPressedListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        ListProduitsForm h = new ListProduitsForm(listProd, theme);
                        listProd.show();
                    }
                });

                fod.add(tfNom);
                fod.add(tfDescription);
                fod.add(image);
                fod.add(tfPrix);
                fod.add(cx);
                fod.add(bo);
                fod.add(btnAnnuler);
                fod.add(imvAdd);
                fod.show();
                bo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        ser.Modifierproduit(c.getId(), tfNom.getText(), fileName, tfDescription.getText(), Integer.parseInt(tfPrix.getText()), cx.getSelectedItem());

                        try {
                            String pathToBeStored = "file://C:/wamp64/www/symfony/web/events/" + fileName;
                            OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                            ImageIO.getImageIO().save(img2, os, ImageIO.FORMAT_PNG, 0.9f);
                            os.close();
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                        ListProduitsForm h = new ListProduitsForm(listProd, theme);
                        listProd.show();

                    }
                });

            }

        }
        );

        return c1;

    }

}
