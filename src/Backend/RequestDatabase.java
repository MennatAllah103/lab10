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
 * @author daree
 */
public class RequestDatabase {

    private static RequestDatabase requestsDB = null;
    ArrayList<Request> requests = new ArrayList<>();

    private RequestDatabase() {
        
           requests=loadFile();

    }

    public static RequestDatabase getinstance() {
        if (requestsDB == null) {
            requestsDB = new RequestDatabase();
        }

        return requestsDB;

    }

    public ArrayList<Request> loadFile() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("requests.json")));
            JSONArray requestsArray = new JSONArray(json);
            for (int i = 0; i < requestsArray.length(); i++) {
                JSONObject requestJson = requestsArray.getJSONObject(i);
                String senderID = requestJson.getString("senderID");
                String receiverID = requestJson.getString("receiverID");
                String requestStatus= requestJson.getString("requestStatus");
                Request R=new Request(senderID, receiverID);
                R.setRequestStatus(requestStatus);
                requests.add(R);
                
            }

       
        } catch (IOException ex) {
            Logger.getLogger(RequestDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     return requests;
    }

    public void saveFile(ArrayList<Request> requests) {
        JSONArray requestsArray = new JSONArray();
        for (Request r : requests) {
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
            Logger.getLogger(RequestDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Request> getALLRequests() {
        
     
        return requests;
    }
    
   

}
