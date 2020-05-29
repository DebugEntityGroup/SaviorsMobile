package forms;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class HomeForumForm extends Form{
    Form current;
    public HomeForumForm(Form previous) {
        current=this;
        setTitle("Accueil");
        setLayout(BoxLayout.y());
        
        add(new Label("Saviors"));
        Button btnAddTask = new Button("Ajouter Forum");
        Button btnListTasks = new Button("Liste des Forum");
        
        btnAddTask.addActionListener(e-> new AjouterForm(current).show());
        btnListTasks.addActionListener(e-> new ListForumForm(current).show());
        addAll(btnAddTask,btnListTasks);
        
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
