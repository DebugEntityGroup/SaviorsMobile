package forms;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import services.CollecteService;
import services.LoginService;

public class UsersForm extends Form {
    
    public UsersForm() {
        super("Utilisateurs", BoxLayout.y());

        this.add(new SpanLabel(new CollecteService().getCategories().toString()));
    }
    
}
