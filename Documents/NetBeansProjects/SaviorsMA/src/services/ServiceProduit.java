package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import connexion.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Produit;
import models.ProduitComment;

/**
 *
 * @author Ahmed
 */
public class ServiceProduit {

    public ArrayList<Produit> produit;
    public ArrayList<Produit> produitp;
    public ArrayList<ProduitComment> comment;

    public static ServiceProduit instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceProduit() {
        req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }

    public boolean addProduit(Produit p) {
        String url = Statics.BASE_URLprod + "add?nom=" + p.getNom() + "&categorie=" + p.getCategorie_nom() + "&description=" + p.getDescription() + "&prix=" + p.getPrix() + "&image=" + p.getImage() + "&username=" + p.getUser_id();
        req.setUrl(url);
        System.out.println(url);
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
    
    public ArrayList<Produit> parseProduitP(String jsonText) {
        try {
            produitp = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Produit p = new Produit();
                // System.out.println(obj);
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                p.setPrix(((int) Float.parseFloat(obj.get("prix").toString())));
                p.setNom(obj.get("nomProd").toString());
                p.setDescription(obj.get("description").toString());
                p.setImage(obj.get("image").toString());
                p.setCategorie_nom(obj.get("nom").toString());
                p.setUser_id(obj.get("username").toString());
                produitp.add(p);
            }

        } catch (IOException ex) {

        }
        return produitp;
    }

    public ArrayList<Produit> parseProduit(String jsonText) {
        try {
            produit = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Produit p = new Produit();
                // System.out.println(obj);
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                p.setPrix(((int) Float.parseFloat(obj.get("prix").toString())));
                p.setNom(obj.get("nomProd").toString());
                p.setDescription(obj.get("description").toString());
                p.setImage(obj.get("image").toString());
                p.setCategorie_nom(obj.get("nom").toString());
                p.setUser_id(obj.get("username").toString());
                produit.add(p);
            }

        } catch (IOException ex) {

        }
        return produit;
    }

    public ArrayList<ProduitComment> parseComments(String jsonText) {
        try {
            comment = new ArrayList<>();
            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                String contenuCom = obj.get("content").toString();
                String usernameCom = obj.get("username").toString();
                String nomProduitCom = obj.get("nomProd").toString();
                ProduitComment comCol = new ProduitComment();
                comCol.setContent(contenuCom);
                comCol.setUser_id(usernameCom);
                comCol.setProduitPending_id(nomProduitCom);
                comment.add(comCol);
            }

        } catch (IOException ex) {
        }

        return comment;
    }

    public ArrayList<ProduitComment> getAllCommentsCols() {
        String url = Statics.BASE_URLprod + "readcomments";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                comment = parseComments(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return comment;
    }

    public ArrayList<Produit> getAllProduits() {
        String url = Statics.BASE_URLprod + "read";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produit = parseProduit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produit;
    }
    
    public ArrayList<Produit> getAllProduitsP() {
        String url = Statics.BASE_URLprod + "readp";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produitp = parseProduitP(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produitp;
    }

    public void Getproduit(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony/web/app_dev.php/apiprod/product/find?id=" + id;
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            // System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void delete(int id) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/symfony/web/app_dev.php/apiprod/product/delete";
        //  con.setPost(false);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.addArgument("id", id + "");

        NetworkManager.getInstance().addToQueue(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }

    public void Modifierproduit(int id, String nom, String image, String description, int prix, String categorie) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/symfony/web/app_dev.php/apiprod/product/edit?id=" + id + "&nom=" + nom + "&image=" + image + "&description=" + description + "&prix=" + prix + "&categorie=" + categorie;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            //  System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }

    public boolean addComment(ProduitComment c) {
        String url = Statics.BASE_URLprod + "Addcomments?content=" + c.getContent() + "&user=" + c.getUser_id() + "&produit=" + c.getProduitPending_id();
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

    public ArrayList<Produit> Recherche(String nom) {
        String Url = "http://localhost/symfony/web/app_dev.php/apiprod/product/findbynom?q=" + nom;
        req.setUrl(Url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produit = parseProduitFound(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produit;
    }
    
    public ArrayList<Produit> parseProduitFound(String jsonText) {
        try {
            produit = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Produit p = new Produit();
                // System.out.println(obj);
                //float id = Float.parseFloat(obj.get("id").toString());
                //p.setId((int) id);
                p.setPrix(((int) Float.parseFloat(obj.get("prix").toString())));
                p.setNom(obj.get("nomProd").toString());
                p.setDescription(obj.get("description").toString());
                p.setImage(obj.get("image").toString());
                p.setCategorie_nom(obj.get("nom").toString());
                p.setUser_id(obj.get("username").toString());
                produit.add(p);
            }

        } catch (IOException ex) {

        }
        return produit;
    }

}
