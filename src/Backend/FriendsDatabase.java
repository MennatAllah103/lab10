/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FriendManagementBackend;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author daree
 */
public class FriendsDatabase {
    
    private static FriendsDatabase friendsDB=null;
    ArrayList<Friends> friends=new ArrayList<>();

    private FriendsDatabase() {
        
        friends = loadFile();
    }
    
    
    public static FriendsDatabase getinstance()
    {
        if(friendsDB==null)
            friendsDB=new FriendsDatabase();
        
        return friendsDB;
        
    }
    
   
    public ArrayList<Friends> loadFile(){ 
           try {
          String  json = new String(Files.readAllBytes(Paths.get("friends.json")));
            JSONArray friendsArray = new JSONArray(json);
             for (int i = 0; i < friendsArray.length(); i++)
    {
        JSONObject friendsJson = friendsArray.getJSONObject(i);
                String Userid1  = friendsJson.getString("Userid1");
                String Userid2  = friendsJson.getString("Userid2");
                friends.add(new Friends(Userid1,Userid2));
               
    }
             
          
             
           } 
           
           catch (IOException ex) {
            Logger.getLogger(FriendsDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
 
     
         return friends;

}
 
    public void saveFile(ArrayList<Friends> friends)
    {
        JSONArray friendsArray = new JSONArray();
        for(Friends f : friends)
        {
            JSONObject j = new JSONObject();
            j.put("Userid1", f.getUserid1());
            j.put("Userid2", f.getUserid2());
            friendsArray.put(j);
            
        }
        
         
        try {
          FileWriter  file = new FileWriter("friends.json");
          file.write(friendsArray.toString(4));
          file.close();
        } catch (IOException ex) {
            Logger.getLogger(FriendsDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    
    }

    public ArrayList<Friends> getALLFriends() {
        
      //  ArrayList<Friends> friends =friendsDB.loadFile();
        return friends;
    }
    
  
    
}