/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Ahmed
 */
public class messagesForm extends BaseForm {

    public static String codex;
    public static final String ACCOUNT_SID = "AC131848a87fecf99be837175861639fc2";
    public static final String AUTH_TOKEN = "037b167e9e6177aa1e92c55bab6c86c3";

    public messagesForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Forget");

        TextField numtel = new TextField("", "Your number phone", 20, TextField.EMAILADDR);
        numtel.setSingleLineTextArea(false);

        Button forget = new Button("forget");

        Container content = BoxLayout.encloseY(
                new Label("Forget Password", "LogoLabel"),
                new FloatingHint(numtel)
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                forget
        ));
        forget.requestFocus();

        forget.addActionListener((ActionListener) (ActionEvent evt) -> {
            if (numtel.getText().isEmpty()) {
                Dialog.show("Number phone!!", "ok", (Command) null);
            } else {

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
                com.twilio.rest.api.v2010.account.Message messages = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+21693195113"),
                        new PhoneNumber("+12563686010"), "Votre Code est : " + saltStr).create();

                codex = saltStr;
                System.out.println("======================");
                System.out.println("======================");
                System.out.println("======================");
                System.out.println(codex);

            }
        });
    }
}
