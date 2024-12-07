/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FriendManagementBackend;

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
 * @author daree
 */
public class RequestsDatabase {

    private static RequestsDatabase requestsDB = null;
    ArrayList<Requests> requests = new ArrayList<>();

    private RequestsDatabase() {
        
           requests=loadFile();

    }

    public static RequestsDatabase getinstance() {
        if (requestsDB == null) {
            requestsDB = new RequestsDatabase();
        }

        return requestsDB;

    }

    public ArrayList<Requests> loadFile() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("requests.json")));
            JSONArray requestsArray = new JSONArray(json);
            for (int i = 0; i < requestsArray.length(); i++) {
                JSONObject requestJson = requestsArray.getJSONObject(i);
                String senderID = requestJson.getString("senderID");
                String receiverID = requestJson.getString("receiverID");
                String requestStatus= requestJson.getString("requestStatus");
                Requests R=new Requests(senderID, receiverID);
                R.setRequestStatus(requestStatus);
                requests.add(R);
                
            }

       
        } catch (IOException ex) {
            Logger.getLogger(RequestsDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     return requests;
    }

    public void saveFile(ArrayList<Requests> requests) {
        JSONArray requestsArray = new JSONArray();
        for (Requests r : requests) {
            JSONObject j = new JSONObject();
            j.put("senderID", r.senderID);
            j.put("receiverID", r.receiverID);
            j.put("requestStatus", r.requestStatus);
            requestsArray.put(j);

        }

        try {
            FileWriter file = new FileWriter("requests.json");
            file.write(requestsArray.toString(4));
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(RequestsDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Requests> getALLRequests() {
        
       // ArrayList<Requests> requests=requestsDB.loadFile();
        return requests;
    }
    
   

}
