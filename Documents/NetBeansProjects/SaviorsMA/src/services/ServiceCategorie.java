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
import models.CategorieProduit;
import connexion.Statics;

/**
 *
 * @author Ahmed
 */
public class ServiceCategorie {
        public ArrayList<CategorieProduit> categorie;

    public static ServiceCategorie instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCategorie() {
        req = new ConnectionRequest();
    }

    public static ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie();
        }
        return instance;
    }

    public boolean addCategorie(CategorieProduit p) {
        String url = Statics.BASE2_URL + "add?nom=" + p.getNom()+ "&description=" + p.getDescription();
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

    public ArrayList<CategorieProduit> parseCategorie(String jsonText) {
        try {
            categorie = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
               // System.out.println(obj);
                CategorieProduit p = new CategorieProduit();              
                p.setNom(obj.get("nom").toString());
                p.setDescription(obj.get("description").toString());
                categorie.add(p);
            }
        } catch (IOException ex) {
        
        }
        return categorie;
    }
  public ArrayList<CategorieProduit> getAllCategories(){
        String url = Statics.BASE2_URL+"read";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categorie = parseCategorie(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categorie;
    }
}
