package services;


import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import connexion.DataSource;
import connexion.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import main.MyApplication;
import models.Collecte;
import models.CommentEvent;
import models.Event;


public class WebService {
    static Map h;
    static String status ="";
    static int c ;
    static String lg ;
    private ConnectionRequest request;
    private ConnectionRequest reqEvent;
    public ArrayList<Event> eventsP;
    public ArrayList<Event> eventsExist;
    
    public WebService() {
        reqEvent = DataSource.getInstance().getRequest();
    }
    
    public static Map<String, Object> getResponse(String url){
        url = "http://localhost/symfony/web/app_dev.php/"+url;
        System.out.println("url---------------"+url);
        ConnectionRequest r = new ConnectionRequest();
        System.out.println("url ::::::::: "+url);
        r.setUrl(url);
        r.setPost(false);
        System.out.println("url   :   "+r);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        r.setDisposeOnCompletion(dlg);
        r.addResponseListener((evt) -> {
            try {
                JSONParser p = new JSONParser();
                Reader targetReader = new InputStreamReader(new ByteArrayInputStream(r.getResponseData()));
                System.out.println(targetReader);
                h= p.parseJSON(targetReader);
                
            } catch (IOException ex) {
                //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return h; 
    }
   
     
    public void addEvent(Event e){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://localhost/symfony/web/app_dev.php/event/addEvent";
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("nom", e.getNom());
     con.addArgument("description", e.getDescription());
     con.addArgument("lieu", e.getLieu());
     con.addArgument("date", e.getDateevent());
     con.addArgument("image", e.getImage());
     con.addArgument("user", e.getUser());
     System.out.println(url);
     con.setPost(true);
        
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Succés", "Evenement en attente de confirmation par l'administrateur", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public ArrayList<Event> getAllEventsP() {
        String url = "http://localhost/symfony/web/app_dev.php/event/alleventsP";
        reqEvent.setUrl(url);
        reqEvent.setPost(false);
        reqEvent.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                eventsP = parseEventsP(new String(reqEvent.getResponseData()));
                reqEvent.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(reqEvent);

        return eventsP;
    }
    
    public ArrayList<Event> getAllEventsExist() {
        String url = "http://localhost/symfony/web/app_dev.php/event/alleventsExist";
        reqEvent.setUrl(url);
        reqEvent.setPost(false);
        reqEvent.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                eventsExist = parseEventsExist(new String(reqEvent.getResponseData()));
                reqEvent.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(reqEvent);

        return eventsExist;
    }
    
    public ArrayList<Event> parseEventsExist(String jsonText) {
        try {
            eventsExist = new ArrayList<>();
            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                String nameEC = obj.get("name").toString();
                String descriptionEC = obj.get("description").toString();
                String username = obj.get("username").toString();
                Event ec = new Event();
                ec.setNom(nameEC);
                ec.setDescription(descriptionEC);
                ec.setUser(username);
                //c.setUser_id(userID);
                eventsExist.add(ec);
            }

        } catch (IOException ex) {
        }

        return eventsExist;
    }
    
    public ArrayList<Event> parseEventsP(String jsonText) {
        try {
            eventsP = new ArrayList<>();
            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                String nameEC = obj.get("name").toString();
                String descriptionEC = obj.get("description").toString();
                String username = obj.get("username").toString();
                Event ec = new Event();
                ec.setNom(nameEC);
                ec.setDescription(descriptionEC);
                ec.setUser(username);
                //c.setUser_id(userID);
                eventsP.add(ec);
            }

        } catch (IOException ex) {
        }

        return eventsP;
    }
    
     public static int getCountComment(String url){
        url = "http://localhost/symfony/web/app_dev.php/"+url;
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        int i = Integer.parseInt(s);
                        System.out.println(s);
                        c = i ;
                        /**if (s.equals("subscribed")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }**/
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    
    return c;
    }
        
     public void addComment(CommentEvent e){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://localhost/symfony/web/app_dev.php/event/AddCom?event="+e.getNomEvent()+"&user="+e.getUsername()+"&text="+e.getText();
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("text", e.getText());
     con.addArgument("event", e.getNomEvent()+"");
     con.addArgument("user", e.getUsername()+"");
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "Succés", "OK", null);
                        } else {
                            Dialog.show("ERREUR", "ERREUR", "OK", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public void addRate(int event, int Note, String username) {
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://localhost/symfony/web/app_dev.php/event/Addrate?event="+event+"&note="+Note+"&user="+username;
        ConnectionRequest con = new ConnectionRequest();
         System.out.println(url);
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("note", Note+"");
     con.addArgument("event", event+"");
     con.addArgument("user", username+"");
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "Vous avez déja ajouté votre avis", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      public void addParticipate(String nomEvent, String usernameP){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://localhost/symfony/web/app_dev.php/event/part?nomEvent="+nomEvent;
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     
     
     
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "succés", "OK", null);
                        } else {
                            Dialog.show("ERREUR", "ERREUR", "OK", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
}
