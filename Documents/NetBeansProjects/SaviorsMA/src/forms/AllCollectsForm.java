package forms;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import services.CollecteService;

public class AllCollectsForm extends Form {
    
    public AllCollectsForm() {
        super("Toutes les Collectes", BoxLayout.y());

        this.add(new SpanLabel(new CollecteService().getAllCollects().toString()));
    }
    
}
