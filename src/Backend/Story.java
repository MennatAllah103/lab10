package ContentCreationBackend;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author yaras
 */
public class Story extends Content {

    private ArrayList<Story> storyList = new ArrayList<>();
StoryDataBase SDB = StoryDataBase.getInstance();
    public void addStory(Story s) {
        storyList.add(s);
        SDB.SaveStoriesToFile(storyList);
    }

    public ArrayList<Story> getStories() {
        //   ArrayList<Story> stories = new ArrayList<>();
        for (Story s : storyList) {
            if (!s.isExpired()) {
                storyList.add(s);
            } else {
                deleteStory(s);
            }
        }
        SDB.SaveStoriesToFile(storyList);
        return storyList;
    }

    public boolean isExpired() {
        return Duration.between(getTimestamp(), LocalDateTime.now()).toHours() >= 24;
    }

    public void deleteStory(Story s) {
        storyList.remove(s);
//         ArrayList<Story> stories = SDB.ReadStoriesFromFile() ;
//         for (int i =0 ; i< stories.size();i++)
//         {
//             Story s = stories.get(i);
//             Duration duration = Duration.between(p.ge, timestamp)
//         }
    }
}
