package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.util.List;
import com.codename1.ui.events.ActionListener;
import connexion.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import models.Publication;

/**
 *
 * @author lenovo
 */
public class PublicationService {
    public ArrayList<Publication> publication;
    
     public static PublicationService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public PublicationService() {
        req = new ConnectionRequest();
    }

    public static PublicationService getInstance() {
        if (instance == null) {
            instance = new PublicationService();
        }
        return instance;
    }

    public boolean addPublication(Publication p) {
        String url = Statics.BASE_URLPub + "add?titre=" + p.getTitre()+ "&image=" + p.getBrochure_filename()+ "&description=" + p.getDescription() + "&video=" + p.getVideo();
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
    
    public ArrayList<Publication> parsePublication(String jsonText) {
        try {
            publication = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Publication p = new Publication();
               // System.out.println(obj);
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                p.setTitre(obj.get("titre").toString());
                p.setDescription(obj.get("description").toString());
                p.setBrochure_filename(obj.get("brochureFilename").toString());
                p.setVideo(obj.get("video").toString());
                
                publication.add(p);
            }

        } catch (IOException ex) {

        }
        return publication;
    }

    public ArrayList<Publication> getAllPubs() {
        String url = Statics.BASE_URLPub + "read";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                publication = parsePublication(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return publication;
    }
     public void deletePubs(int id) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = Statics.BASE_URLPub +"delete";
      //  con.setPost(false);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.addArgument("id", id + "");

        NetworkManager.getInstance().addToQueue(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }

    public void ModifierPubs(int id, String titre, String image,String description, String video) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = Statics.BASE_URLPub +"edit?id=" + id + "&titre=" + titre + "&description=" + description + "&image=" + image +"&video=" + video;// création de l'URL
       //con.setPost(false);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        
        NetworkManager.getInstance().addToQueue(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
        public void DetailPubs(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = Statics.BASE_URLPub +"detail?id=" + id;
        con.setUrl(Url);

        
         NetworkManager.getInstance().addToQueue(con);
    }
} 