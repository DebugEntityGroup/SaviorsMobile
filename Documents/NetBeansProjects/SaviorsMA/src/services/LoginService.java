package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import connexion.DataSource;
import connexion.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.UserLogin;
import models.UserSignup;
import models.categorieCollecte;

public class LoginService {

    private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<UserLogin> users;
    public ArrayList<categorieCollecte> cats;
    public ArrayList<UserSignup> user;

    public LoginService() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean connectUser(UserLogin user) {
        String url = Statics.BASE_URL + "/signin?username=" + user.getUsername() + "&password=" + user.getPassword();

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
                //System.out.println(responseResult);
                if (getUsers().contains(user)) {
                    responseResult = true;
                }

            }
        });

        //System.out.println(responseResult);
        NetworkManager.getInstance().addToQueueAndWait(request);

        //return false;
        return responseResult;
    }

    public ArrayList<UserLogin> getUsers() {
        String url = Statics.BASE_URL + "/allusers";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseTasks(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return users;
    }
    
    public ArrayList<UserSignup> getUser(UserSignup userr) {
        String url = Statics.BASE_URL + "/find/"+userr.getUsername();
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                user = parseUsers(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return user;
    }
    
    public ArrayList<categorieCollecte> getCategories() {
        String url = Statics.BASE_URL + "/allcats";
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

    public ArrayList<UserLogin> parseTasks(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                //int id = Integer.parseInt(obj.get("id").toString());
                int id = (int) Float.parseFloat(obj.get("id").toString());
                String username = obj.get("username").toString();
                String password = obj.get("password").toString();
                users.add(new UserLogin(id, username, password));
            }

        } catch (IOException ex) {
        }

        return users;
    }
    
    public ArrayList<UserSignup> parseUsers(String jsonText) {
        try {
            user = new ArrayList<>();
            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                String username = obj.get("username").toString();
                String password = obj.get("password").toString();
                String email = obj.get("email").toString();
                String role = obj.get("roles").toString();
                user.add(new UserSignup(username, password, email, role));
            }

        } catch (IOException ex) {
        }

        return user;
    }
    
    public ArrayList<categorieCollecte> parseCats(String jsonText) {
        try {
            cats = new ArrayList<>();
            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                String typeCategorie = obj.get("typeCategorie").toString();
                int user_id = (int) Float.parseFloat(obj.get("user_id").toString());
                //int userID = Integer.parseInt(obj.get("user_id").toString());
                cats.add(new categorieCollecte(typeCategorie, user_id));
            }

        } catch (IOException ex) {
        }

        return cats;
    }

}
