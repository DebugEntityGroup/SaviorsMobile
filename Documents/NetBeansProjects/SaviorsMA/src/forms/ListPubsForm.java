package forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import models.Publication;
import services.PublicationService;

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
            PublicationService es = new PublicationService();
            if (Dialog.show("confirmer", "tu veut supprimer ?", "ok", "Cancel")) {
                es.deletePubs(c.getId());
                HomeForm_1 h = new HomeForm_1(theme, current);
                h.getCurrent().show();
            }
        });
        modifier.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form modif = new Form();
                modif.setTitle("Modifier publication");

                modif.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> current.showBack());

                modif.setLayout(BoxLayout.y());
                PublicationService ser = new PublicationService();
                TextField Titre = new TextField(c.getTitre());
                TextField Description = new TextField(c.getDescription());
                TextField Image = new TextField(c.getBrochure_filename());
                TextField Video = new TextField(c.getVideo());

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
                modif.add(Image);
                modif.add(Video);
                modif.add(Valider);
                modif.add(Annuler);
                modif.show();
                Valider.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        ser.ModifierPubs(c.getId(), Titre.getText(), Image.getText(), Description.getText(), Video.getText());
                        HomeForm_1 h = new HomeForm_1(theme, current);
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

            detail.add(imag1);

            detail.add(String.valueOf("                             "));
            Container e = FlowLayout.encloseCenter(titre1);
            Container e1 = FlowLayout.encloseCenter(description1);
            Container e2 = FlowLayout.encloseCenter(video1);

            detail.addAll(e, e1, e2);

            detail.show();

            PublicationService ser = new PublicationService();
            ser.DetailPubs(c.getId());

        });

        return c1;
    }
}
