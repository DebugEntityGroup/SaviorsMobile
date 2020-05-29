/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author lenovo
 */
public class HomeForm_1 extends Form{
  //  Resources theme;
Form current;
    public HomeForm_1(Resources theme, Form previous) {
        current=this;
        setTitle("Publication");
        setLayout(BoxLayout.y());
        
        add(new Label("Saviors"));
        Button btnAddTask = new Button("Ajouter une Publication");
        Button btnListTasks = new Button("Liste des Publications");
        
        btnAddTask.addActionListener(e-> new AddPublicationForm(current).show());
        btnListTasks.addActionListener(e-> new ListPubsForm(current,theme).show());
        addAll(btnAddTask,btnListTasks);
        
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            previous.showBack();
        });
        
        
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