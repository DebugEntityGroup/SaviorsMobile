package forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.File;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;
import models.UserSignup;
import services.SignupService;

public class signupForm extends Form {

    public signupForm(Form retour, Resources theme) {

        super("Inscription", BoxLayout.y());
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
        //hi.show();
        ImageViewer img = new ImageViewer();
        img.setImage(theme.getImage("SaviorsLogo.png"));
        TextField username = new TextField(null, "Nom d'utilisateur");
        TextField email = new TextField(null, "Email");
        TextField password = new TextField(null, "Mot de Passe", 0, TextField.PASSWORD);
        TextField confirmPassword = new TextField(null, "Confirmer Mot de Passe", 0, TextField.PASSWORD);
        ComboBox<String> roles = new ComboBox<>("Association", "Fournisseur", "Membre");
        Validator validator = new Validator();
        validator.addConstraint(email, RegexConstraint.validEmail());
        Button signup = new Button("S'inscrire");
        //Button login = new Button("Se Connecter");
        /*login.setAlignment(CENTER);
        login.getAllStyles().setFgColor(0x953250);*/
        //Image green = Image.createImage(100, 100, 0xff00ff00);
        //login.getAllStyles().setBgImage(green);

        /*login.addActionListener((evt) -> {
            new loginForm(this).show();
        });*/
        this.getToolbar().addCommandToRightBar("Connexion", null, (evt) -> {
            new loginForm(theme).show();
        });

        /*this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            retour.showBack();
        });*/

        signup.addActionListener((evt) -> {
            if ((username.getText().length() == 0) || (email.getText().length() == 0) || (password.getText().length() == 0) || (confirmPassword.getText().length() == 0)) {
                Dialog.show("Alert", "Champs Requis", "OK", null);
            } else if (!validator.isValid()) {
                ToastBar.showErrorMessage("Veuillez saisir une adresse email valide !");
            } else {
                if (!password.getText().equals(confirmPassword.getText())) {
                    Dialog.show("Alert", "Les deux mots de passes ne sont pas identiques !", "OK", null);
                } else {
                    if (roles.getSelectedItem().equals("Association")) {
                        try {
                            UserSignup us = new UserSignup(username.getText(), email.getText(), password.getText(), confirmPassword.getText(), "ROLE_ASSOC");
                            if (new SignupService().addUser(us)) {
                                Dialog.show("SUCCES", "Inscription effectuée avec succés !", "OK", null);
                                username.setText("");
                                email.setText("");
                                password.setText("");
                                confirmPassword.setText("");
                                //new loginForm().show();
                            } else {
                                Dialog.show("ERREUR", "Erreur d'inscription", "OK", null);
                            }
                        } catch (Exception e) {
                            Dialog.show("ERREUR", "Erreur d'inscription", "OK", null);
                        }
                    }

                    if (roles.getSelectedItem().equals("Fournisseur")) {
                        try {
                            UserSignup us = new UserSignup(username.getText(), email.getText(), password.getText(), confirmPassword.getText(), "ROLE_FOURN");
                            if (new SignupService().addUser(us)) {
                                Dialog.show("SUCCES", "Inscription effectuée avec succés !", "OK", null);
                                username.setText("");
                                email.setText("");
                                password.setText("");
                                confirmPassword.setText("");
                                //new loginForm().show();
                            } else {
                                Dialog.show("ERREUR", "Erreur d'inscription", "OK", null);
                            }
                        } catch (Exception e) {
                            Dialog.show("ERREUR", "Erreur d'inscription", "OK", null);
                        }
                    }

                    if (roles.getSelectedItem().equals("Membre")) {
                        try {
                            UserSignup us = new UserSignup(username.getText(), email.getText(), password.getText(), confirmPassword.getText(), "ROLE_MEMBER");
                            if (new SignupService().addUser(us)) {
                                Dialog.show("SUCCES", "Inscription effectuée avec succés !", "OK", null);
                                username.setText("");
                                email.setText("");
                                password.setText("");
                                confirmPassword.setText("");
                                //new loginForm().show();
                            } else {
                                Dialog.show("ERREUR", "Erreur d'inscription", "OK", null);
                            }
                        } catch (Exception e) {
                            Dialog.show("ERREUR", "Erreur d'inscription", "OK", null);
                        }
                    }
                }
            }

        });
        this.addAll(img, username, email, password, confirmPassword, roles, signup);
    }
}
