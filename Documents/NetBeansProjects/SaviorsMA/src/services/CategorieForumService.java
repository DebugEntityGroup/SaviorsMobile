package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.CategorieForum;

/**
 *
 * @author Yasmine
 */
public class CategorieForumService {
     public ArrayList<CategorieForum> cat;
    public static CategorieForumService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public CategorieForumService() {
        req = new ConnectionRequest();
    }

    public static CategorieForumService getInstance() {
        if (instance == null) {
            instance = new CategorieForumService();
        }
        return instance;
    }

    public ArrayList<CategorieForum> parseForum(String jsonText) {
        try {
            cat = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                CategorieForum p = new CategorieForum();
            //    System.out.println(obj);
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                p.setNom(obj.get("nom").toString());
               
                cat.add(p);
            }

        } catch (IOException ex) {

        }
        return cat;
    }

    public ArrayList<CategorieForum> getAllCats() {
        String url = "http://localhost/symfony/web/app_dev.php/apiF/allcats";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cat = parseForum(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cat;
    }
    
}
