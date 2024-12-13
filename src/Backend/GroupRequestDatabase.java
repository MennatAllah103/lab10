/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author LENOVO
 */
public class GroupRequestDatabase {
    private static GroupRequestDatabase requestDatabase = null;
    ArrayList<GroupRequests> requests = new ArrayList<>();
    
    
     public static GroupRequestDatabase getinstance() {
        if (requestDatabase == null) {
           requestDatabase = new GroupRequestDatabase();
        }

        return requestDatabase;

    }

    public ArrayList<GroupRequests> loadFile() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("GroupRequests.json")));
            JSONArray requestsArray = new JSONArray(json);
            for (int i = 0; i < requestsArray.length(); i++) {
                JSONObject requestJson = requestsArray.getJSONObject(i);
                String memberID = requestJson.getString("memberID");
                String groupID = requestJson.getString("groupID");
                GroupRequests R=new GroupRequests(memberID, groupID);
                requests.add(R);
                
            }

       
        } catch (IOException ex) {
            Logger.getLogger(GroupRequestDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     return requests;
    }

    public void saveFile(ArrayList<GroupRequests> requests) {
        JSONArray requestsArray = new JSONArray();
        for (GroupRequests r : requests) {
            JSONObject j = new JSONObject();
            j.put("memberID",r.getMemberId());
            j.put("groupID", r.getGroupId());
            requestsArray.put(j);

        }

        try {
            FileWriter file = new FileWriter("GroupRequests.json");
            file.write(requestsArray.toString(4));
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(GroupRequestDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<GroupRequests> getALLRequests() {
        
     
        return requests;
    }
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
