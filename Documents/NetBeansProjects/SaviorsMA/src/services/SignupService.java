package services;

import com.codename1.db.Database;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import connexion.DataSource;
import connexion.Statics;
import java.util.ArrayList;
import models.UserSignup;

public class SignupService {
    
    private ConnectionRequest request;
    private boolean responseResult;
    //public ArrayList<Task> tasks;
    private Database db;
    
    public SignupService() {
        request = DataSource.getInstance().getRequest();
    }
    
    public boolean addUser(UserSignup user) {
        String url = Statics.BASE_URL + "/signup?username=" + user.getUsername()+ "&password=" + user.getPassword()+"&email="+user.getEmail()+"&roles="+user.getRole();
        
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
    
}
