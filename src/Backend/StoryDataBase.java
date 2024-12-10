/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author yaras
 */
public class StoryDataBase {

    private static StoryDataBase instance; // Singleton instance
    private ArrayList<Story> storyList = new ArrayList<>();
      Management management = new Management();


    private StoryDataBase() {

        storyList = ReadStoriesFromFile();
    }

   
    public static StoryDataBase getInstance() 
    {
        if (instance == null)
        {
            instance = new StoryDataBase();
        }

        return instance;
    }

    public void SaveStoriesToFile(ArrayList<Story> Stories) {
        
        JSONArray storiesArray = new JSONArray();
        for (Story s : Stories) {
         //   if (!s.isExpired())
         //lesa m3mltsh lw expired
           {
                JSONObject j = new JSONObject();
                j.put("contentID", s.getContentID());
                j.put("authorID", s.getAuthorID());
                j.put("content", s.getContent());
                j.put("timeStamp", s.getTimestamp());
                j.put("imagePath", s.getImagePath());
                storiesArray.put(j);
            }
        }
        try {
            FileWriter file = new FileWriter("stories.json");
            file.write(storiesArray.toString(4));
            file.close();
        } catch (IOException ex) {
            //Logger.getLogger(StoryDataBase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error saivng stories to file.");
        }
    }

    public ArrayList<Story> ReadStoriesFromFile() {

        try {
            String json = new String(Files.readAllBytes(Paths.get("stories.json")));
            JSONArray storiesArray = new JSONArray(json);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            for (int i = 0; i < storiesArray.length(); i++) {

                JSONObject storyJson = storiesArray.getJSONObject(i);
                String contentID = storyJson.getString("contentID");
                String authorID = storyJson.getString("authorID");
                String content = storyJson.getString("content");
                String timeStamp = storyJson.getString("timeStamp");
                String imagePath = "";
                try {
                    imagePath = storyJson.getString("imagePath");
                } catch (JSONException e) {
                    // Handle the case where "imagePath" is missing
                    imagePath = "";  // or provide a default value
                }

                //  String imagePath = storyJson.getString("imagePath");
                Story story = new Story();
                story.setContentID(contentID);
                story.setAuthorID(authorID);
                story.setContent(content);
                story.setTimestamp(LocalDateTime.parse(timeStamp, formatter));
                story.setImagePath(imagePath);
                storyList.add(story);

            }

        } catch (IOException e) {
            System.err.println("Error reading stories from file: " + e.getMessage());
        } catch (JSONException e) {
            System.err.println("Error parsing JSON data: " + e.getMessage());
        }
        return storyList;
    }

    public ArrayList<Story> ViewUserStories(String userId) {

        ArrayList<Story> userStories = new ArrayList<>();

        for (Story s : storyList) {
            if (userId.equals(s.getAuthorID())) {
                userStories.add(s);
            }
        }
        return userStories;
    }

    public ArrayList<Story> ViewFriendsStories(String userId) {
      
        ArrayList<String> friendsIds = management.getUserFriendsIDs(userId);
        ArrayList<Story> friendsStories = new ArrayList<>();

        for (Story story : storyList) {
            if (friendsIds.contains(story.getAuthorID())) { 
                friendsStories.add(story);
            }
        }

        return friendsStories;
    }

   /* public void removedstories(String contentID) {
        stories = ReadStoriesFromFile(); // Load stories
        for (int i = 0; i < stories.size(); i++) {
            if (stories.get(i).getContentID().equals(contentID) || stories.get(i).isExpired()) {
                stories.remove(i); // Remove the story by matching ID
                break; // Exit the loop once the story is found and removed
            }
        }
        SaveStoriesToFile(stories); // Save back to file
    }
*/
    public void addStory(Story s) {
        storyList.add(s);
       SaveStoriesToFile(storyList);
    }
    
    public void deleteStory(Story s) 
     {
        storyList.remove(s);
        SaveStoriesToFile(storyList);
        
     }

    public ArrayList<Story> getStories() {
     
        return storyList;
    }

   
    public void updateStories() 
    {
     
     
        for (int i =0 ; i< storyList.size();i++)
        {
            Content content = storyList.get(i);
            Duration duration = Duration.between(content.getTimestamp(),LocalDateTime.now());
            long hours =duration.toHours();
            if(hours>=24)
            {
                deleteStory((Story)content);
                i--;
            }
        }
    
   }
    
}