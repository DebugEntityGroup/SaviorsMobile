package services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import connexion.DataSource;
import connexion.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Collecte;
import models.DonCollecte;
import models.UserLogin;
import models.UserSignup;
import models.categorieCollecte;
import models.commentaireCollecte;

public class CollecteService {

    private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<UserLogin> users;
    public ArrayList<categorieCollecte> cats;
    public ArrayList<Collecte> cols;
    public ArrayList<Collecte> colsP;
    public ArrayList<Collecte> colsEdit;
    public ArrayList<commentaireCollecte> commentsCols;
    public ArrayList<DonCollecte> colsDons;

    public CollecteService() {
        request = DataSource.getInstance().getRequest();
    }
    
    public boolean addMoneyCollecte(Collecte c) {
        String url = Statics.BASE_URL + "/collect/donate?collecte="+c.getNomCollecte()+"&fondAtteint="+c.getNbreAtteint();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
    
    public boolean faireUnDon(DonCollecte donation) {
        String url = Statics.BASE_URL + "/collect/details/donate?money="+donation.getMoneyDonated()+"&date="+donation.getDateHour()+"&user="+donation.getUsername()+"&collecte="+donation.getCollectPending();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public boolean addCollecte(Collecte c) {

        /*String url = Statics.BASE_URL + "/collect/add?nomCollecte=" + c.getNomCollecte()+ "&budgetCollecte=" +c.getBudget()+ "&descriptionCollecte=" + c.getDescriptionCollecte()+"&image="+c.getImage()+"&categorie="+c.getCatégorie()+"&user_id="+c.getUser_id();
        request.setUrl(url);*/
        //MultipartRequest cr = new MultipartRequest();
        //cr.setUrl("http://localhost/symfony/web/app_dev.php/collect/add?nomCollecte=" + c.getNomCollecte()+ "&budgetCollecte=" +c.getBudget()+ "&descriptionCollecte=" + c.getDescriptionCollecte()+"&image="+c.getImage()+"&categorie="+c.getCatégorie()+"&user_id="+c.getUser_id());
        //cr.setPost(true);
        //String mime = "image/png";
        //String fichernom = System.currentTimeMillis() + ".png";
        //cr.setFilename("file", fichernom);
        //System.out.println(cr.getUrl());
        /*InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        cr.setDisposeOnCompletion(dlg);*/
 /*cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = cr.getResponseCode() == 200;
                cr.removeResponseListener(this);
            }
        });*/
        //NetworkManager.getInstance().addToQueueAndWait(cr);
        String url = Statics.BASE_URL + "/collect/add?nomCollecte=" + c.getNomCollecte() + "&budgetCollecte=" + c.getBudget() + "&descriptionCollecte=" + c.getDescriptionCollecte() + "&image=" + c.getImage() + "&categorie=" + c.getCatégorie() + "&user_id=" + c.getUser_id();

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
    
    public boolean addCommentCollecte(commentaireCollecte comCol) {
        String url = Statics.BASE_URL + "/collect/comment/add?contenu="+comCol.getContenu()+"&user="+comCol.getUser_id()+"&collect="+comCol.getCollectP();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public boolean addCatCollecte(categorieCollecte cat) {
        String url = Statics.BASE_URL + "/collect/categorie/add?typeCategorie=" + cat.getTypeCategorie() + "&user_id="+cat.getUser_id();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public boolean modCollecte(int id, String nc, int budget, String desc, String im, String cat) {
        String url = Statics.BASE_URL + "/collect/edit/" + id + "?nomCollecte=" + nc + "&budgetCollecte=" + budget + "&descriptionCollecte=" + desc + "&image=" + im + "&categorie=" + cat;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        //System.out.println(responseResult);
        return responseResult;
    }

    public boolean delCollecte(int id) {
        String url = Statics.BASE_URL + "/collect/delete/" + id;
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseResult;
    }

    public ArrayList<categorieCollecte> getCategories() {
        String url = Statics.BASE_URL + "/allCategoriesCollecte";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cats = parseCats(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return cats;
    }

    public ArrayList<categorieCollecte> parseCats(String jsonText) {
        try {
            cats = new ArrayList<>();
            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                String typeCategorie = obj.get("typeCategorie").toString();
                String username = obj.get("username").toString();
                //int userID = Integer.parseInt(obj.get("user_id").toString());
                cats.add(new categorieCollecte(typeCategorie, username));
            }

        } catch (IOException ex) {
        }

        return cats;
    }
    
    public ArrayList<Collecte> getAllCollectsP() {
        String url = Statics.BASE_URL + "/collectsP/all";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                colsP = parseColsP(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return colsP;
    }

    public ArrayList<Collecte> getAllCollects() {
        String url = Statics.BASE_URL + "/collects/all";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cols = parseCols(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return cols;
    }
    
    public ArrayList<commentaireCollecte> getAllCommentsCols() {
        String url = Statics.BASE_URL + "/collect/comments/all";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commentsCols = parseComments(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return commentsCols;
    }
    
    public ArrayList<commentaireCollecte> parseComments(String jsonText) {
        try {
            commentsCols = new ArrayList<>();
            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                String contenuCom = obj.get("contenu").toString();
                String usernameCom = obj.get("username").toString();
                String nomCollecteCom = obj.get("nomCollecte").toString();
                commentaireCollecte comCol = new commentaireCollecte();
                comCol.setContenu(contenuCom);
                comCol.setUser_id(usernameCom);
                comCol.setCollectP(nomCollecteCom);
                commentsCols.add(comCol);
            }

        } catch (IOException ex) {
        }

        return commentsCols;
    }
    
    public ArrayList<DonCollecte> getAllDons() {
        String url = Statics.BASE_URL + "/collect/allDons";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                colsDons = parseDons(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return colsDons;
    }
    
    public ArrayList<DonCollecte> parseDons(String jsonText) {
        try {
            colsDons = new ArrayList<>();
            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                String nomCollecte = obj.get("nomCollecte").toString();
                String username = obj.get("username").toString();
                String dateHour = obj.get("dateHour").toString();
                int moneyDonated = (int) Float.parseFloat(obj.get("moneyDonated").toString());
                DonCollecte dc = new DonCollecte();
                dc.setCollectPending(nomCollecte);
                dc.setUsername(username);
                dc.setDateHour(dateHour);
                dc.setMoneyDonated(moneyDonated);
                
                colsDons.add(dc);
            }

        } catch (IOException ex) {
        }

        return colsDons;
    }
    
    

    public ArrayList<Collecte> getAllCollectsEdit() {
        String url = Statics.BASE_URL + "/collects/allE";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                colsEdit = parseColsEdit(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return colsEdit;
    }
    
    public ArrayList<Collecte> parseColsP(String jsonText) {
        try {
            colsP = new ArrayList<>();
            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int) Float.parseFloat(obj.get("id").toString());
                String nomCollecte = obj.get("nomCollecte").toString();
                String descriptionCollecte = obj.get("descriptionCollecte").toString();
                String image = obj.get("image").toString();
                String typeCategorie = obj.get("typeCategorie").toString();
                int budgetCollecte = (int) Float.parseFloat(obj.get("budgetCollecte").toString());
                int fondAtteintCollecte = (int) Float.parseFloat(obj.get("nombreAtteint").toString());
                int user_id = (int) Float.parseFloat(obj.get("id").toString());
                int nbreParticipantsCollecte = (int) Float.parseFloat(obj.get("nombreParticipantsCollecte").toString());
                String username = obj.get("username").toString();
                Collecte c = new Collecte();
                c.setUser_id(user_id);
                c.setNomCollecte(nomCollecte);
                c.setImage(image);
                c.setCatégorie(typeCategorie);
                c.setId(id);
                c.setBudget(budgetCollecte);
                c.setNbreAtteint(fondAtteintCollecte);
                c.setDescriptionCollecte(descriptionCollecte);
                c.setNbreParticipantsC(nbreParticipantsCollecte);
                c.setUsername(username);
                //c.setUser_id(userID);
                colsP.add(c);
            }

        } catch (IOException ex) {
        }

        return colsP;
    }

    public ArrayList<Collecte> parseCols(String jsonText) {
        try {
            cols = new ArrayList<>();
            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int) Float.parseFloat(obj.get("id").toString());
                String nomCollecte = obj.get("nomCollecte").toString();
                String descriptionCollecte = obj.get("descriptionCollecte").toString();
                String image = obj.get("image").toString();
                String typeCategorie = obj.get("typeCategorie").toString();
                int budgetCollecte = (int) Float.parseFloat(obj.get("budgetCollecte").toString());
                int fondAtteintCollecte = (int) Float.parseFloat(obj.get("nombreAtteint").toString());
                int user_id = (int) Float.parseFloat(obj.get("id").toString());
                int nbreParticipantsCollecte = (int) Float.parseFloat(obj.get("nombreParticipantsCollecte").toString());
                Collecte c = new Collecte();
                c.setUser_id(user_id);
                c.setNomCollecte(nomCollecte);
                c.setImage(image);
                c.setCatégorie(typeCategorie);
                c.setId(id);
                c.setBudget(budgetCollecte);
                c.setNbreAtteint(fondAtteintCollecte);
                c.setDescriptionCollecte(descriptionCollecte);
                c.setNbreParticipantsC(nbreParticipantsCollecte);
                //c.setUser_id(userID);
                cols.add(c);
            }

        } catch (IOException ex) {
        }

        return cols;
    }

    public ArrayList<Collecte> parseColsEdit(String jsonText) {
        try {
            colsEdit = new ArrayList<>();
            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int) Float.parseFloat(obj.get("id").toString());
                String nomCollecte = obj.get("nomCollecte").toString();
                String descriptionCollecte = obj.get("descriptionCollecte").toString();
                String image = obj.get("image").toString();
                String typeCategorie = obj.get("typeCategorie").toString();
                int budgetCollecte = (int) Float.parseFloat(obj.get("budgetCollecte").toString());
                //int user_id = (int) Float.parseFloat(obj.get("id").toString());
                Collecte c = new Collecte();
                //c.setUser_id(user_id);
                c.setNomCollecte(nomCollecte);
                c.setImage(image);
                c.setCatégorie(typeCategorie);
                c.setId(id);
                c.setBudget(budgetCollecte);
                c.setDescriptionCollecte(descriptionCollecte);
                //c.setUser_id(userID);
                colsEdit.add(c);
            }

        } catch (IOException ex) {
        }

        return colsEdit;
    }

}
