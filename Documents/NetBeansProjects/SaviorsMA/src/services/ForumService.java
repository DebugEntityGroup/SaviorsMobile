package services;

import connexion.Statiques;
import models.Forum;
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

/**
 *
 * @author Yasmine
 */
public class ForumService {
 public ArrayList<Forum> forum;
    public static ForumService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ForumService() {
        req = new ConnectionRequest();
    }

    public static ForumService getInstance() {
        if (instance == null) {
            instance = new ForumService();
        }
        return instance;
    }

    public boolean AjouterForum(Forum p) {
        String url = "http://localhost/symfony/web/app_dev.php/apiF/forum/add?sujet=" + p.getSujet()+ "&categorie=" + p.getCategorie()+ "&description=" + p.getDescription();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Forum> parseForum(String jsonText) {
        try {
            forum = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Forum p = new Forum();
              //  System.out.println(obj);
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                p.setSujet(obj.get("sujet").toString());
                p.setDescription(obj.get("description").toString());
                p.setCategorie(obj.get("nom").toString());
                forum.add(p);
            }

        } catch (IOException ex) {

        }
        return forum;
    }

    public ArrayList<Forum> getAllForums() {
        String url = Statiques.BASE_URL + "read";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                forum = parseForum(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return forum;
    }
    
    
    public void delete(int id) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/symfony/web/app_dev.php/apiF/forum/delete?id="+id;
        con.setPost(false);
        con.setUrl(Url);

        NetworkManager.getInstance().addToQueue(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }

    public void ModifierForum(int id, String sujet, String description, String categorie) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/symfony/web/app_dev.php/apiF/forum/edit?id="+ id + "&sujet=" + sujet + "&description=" + description + "&categorie=" + categorie;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        
        NetworkManager.getInstance().addToQueue(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
    
}
