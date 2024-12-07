/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ContentCreationBackend;

import FriendManagementBackend.Management;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private ArrayList<Story> stories = new ArrayList<>();

    // Private constructor to prevent instantiation
    private StoryDataBase() {
    }

    // Public static method to get the singleton instance
    public static StoryDataBase getInstance() {
        if (instance == null) {
            synchronized (StoryDataBase.class) { // Ensure thread safety
                if (instance == null) {
                    instance = new StoryDataBase();
                }
            }
        }
        return instance;
    }

    //  ArrayList<Story> stories = new ArrayList<>();
    public void SaveStoriesToFile(ArrayList<Story> newStories) {
        ArrayList<Story> existingStories = ReadStoriesFromFile();

        // Add new posts to the existing list
        existingStories.addAll(newStories);
        JSONArray storiesArray = new JSONArray();
        for (Story s : existingStories) {
            if (!s.isExpired()) {
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
                 stories.add(story);
                

            }
           
        } catch (IOException e) {
            System.err.println("Error reading stories from file: " + e.getMessage());
        } catch (JSONException e) {
            System.err.println("Error parsing JSON data: " + e.getMessage());
        }
        return stories;
    }

    public ArrayList<Story> ViewUserStories(String userId) {

        ArrayList<Story> userStories = new ArrayList<>();
        ArrayList<Story> allStories = ReadStoriesFromFile();
        for (Story s : allStories) {
            if (userId.equals(s.getAuthorID())) {
                userStories.add(s);
            }
        }
        return userStories;
    }

    public ArrayList<Story> ViewFriendsStories(String userId) {
        Management management = new Management();
        ArrayList<String> friendsIds = management.getUserFriendsIDs(userId);

        ArrayList<Story> friendsStories = new ArrayList<>();
        ArrayList<Story> allStories = ReadStoriesFromFile();

        for (Story story : allStories) {
            if (friendsIds.contains(story.getAuthorID())) { // Check if post author is a friend
                friendsStories.add(story);
            }
        }

        return friendsStories;
    }

    public void removedstories(String contentID) {
        stories = ReadStoriesFromFile(); // Load stories
        for (int i = 0; i < stories.size(); i++) {
            if (stories.get(i).getContentID().equals(contentID) || stories.get(i).isExpired()) {
                stories.remove(i); // Remove the story by matching ID
                break; // Exit the loop once the story is found and removed
            }
        }
        SaveStoriesToFile(stories); // Save back to file
    }
}
