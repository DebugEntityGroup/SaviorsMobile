package services;


import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import models.CommentEvent;
import models.Event;

public class EventService {
    
    public  ArrayList<Event> getListsEvents(Map m){
        ArrayList<Event> listDisponibilite = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Events");
        
        //Map f =  (Map) d.get(0);
        

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Event p = new Event();
            Double ll = (Double) f.get("id");
            
            Double ldd = (Double) f.get("nbrInterest");
            Double idd = (Double) f.get("moyenne");
            p.setId(ll.intValue());
            p.setMoyenne(idd.intValue());
            p.setNbrInterest(ldd.intValue());
            p.setLieu((String)f.get("Lieu"));
            p.setNom((String) f.get("name"));
            p.setDescription((String)f.get("description"));
            p.setImage((String)f.get("image"));
            Map map1 = ((Map) f.get("Dateevent"));
            Date date1 = new Date((((Double)map1.get("timestamp")).longValue()*1000)); 
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s1 = formatter.format(date1);
            p.setDateevent(s1);
            /**
            e.setTitre((String) f.get("titre"));
            e.setDescription((String) f.get("description"));
            e.setCategorie((String) f.get("categorie"));
            e.setPhoto((String) f.get("photo"));
            e.setDateDebut((Date)f.get("dateDebut"));
            e.setDateFin((Date)f.get("dateFin"));
            //e.setCreatedAt((Date)f.get("createdAt"));
            e.setLieu((String) f.get("lieu"));
            
            e.setNb_max(((Double) f.get("nbMax")).intValue());**/
            listDisponibilite.add(p);  
        }        
        System.out.println(listDisponibilite);
        return listDisponibilite;
        
    }
    public  ArrayList<CommentEvent> getListscomments(Map m){
        ArrayList<CommentEvent> listDisponibilite = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("commentaire");
        System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            CommentEvent p = new CommentEvent();
            Double ll = (Double) f.get("id");
            
            
            p.setId(ll.intValue());
           
            p.setText((String)f.get("text"));
            Map map1 = ((Map) f.get("user"));
            p.setUsername((String)map1.get("username"));
            System.out.println(map1);
            
            
            /**
            e.setTitre((String) f.get("titre"));
            e.setDescription((String) f.get("description"));
            e.setCategorie((String) f.get("categorie"));
            e.setPhoto((String) f.get("photo"));
            e.setDateDebut((Date)f.get("dateDebut"));
            e.setDateFin((Date)f.get("dateFin"));
            //e.setCreatedAt((Date)f.get("createdAt"));
            e.setLieu((String) f.get("lieu"));
            
            e.setNb_max(((Double) f.get("nbMax")).intValue());**/
            listDisponibilite.add(p);  
        }        
        return listDisponibilite;
        
    }
    
}
