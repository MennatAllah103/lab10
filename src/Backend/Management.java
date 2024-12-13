/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author daree
 */
public class Management {
   
    FriendDatabase FriendsDB =FriendDatabase.getinstance();
    ArrayList<Friend> friendsList=FriendsDB.getALLFriends();
    
   RequestDatabase RequestsDB = RequestDatabase.getinstance();
  ArrayList<Request> requestsList = RequestsDB.getALLRequests();
  
   BlockDatabase BlocksDB= BlockDatabase.getinstance();
  ArrayList<Block> blocksList=BlocksDB.getALLBlockedUsers();
 
  
   public void addfriend(Friend F)
    {
        friendsList.add(F);
       FriendsDB.saveFile(friendsList);
    }
    
    public void removefriend(Friend F)
    {
        friendsList.remove(F);
       FriendsDB.saveFile(friendsList);
    }
    
     public ArrayList<String> getUserFriendsIDs(String Userid1)
    {
        ArrayList<String> userfriendsIDs = new ArrayList<>();
        
        for(Friend f : friendsList)
        {
           if(Userid1.equals(f.getUserid1()))
                    userfriendsIDs .add(f.getUserid2());
           else if(Userid1.equals(f.getUserid2()))
                    userfriendsIDs .add(f.getUserid1());
         
            
        }
        return userfriendsIDs ;
    }
     
         public ArrayList<Friend> getUserFriends(String Userid1)
    {
        ArrayList<Friend> userfriends = new ArrayList<>();
        
        for(Friend f : friendsList)
        {
           if(Userid1.equals(f.getUserid1()))
                    userfriends .add(f);
           else if(Userid1.equals(f.getUserid2()))
                    userfriends .add(f);
         
            
        }
        return userfriends ;
    }
     
     
      public void sendRequest(Request R)
    { 
        requestsList.add(R);
        NotificationManager manager = new NotificationManager();
        String senderid = R.getSenderID();
        UserDataBase userdatabase = UserDataBase.getDatabase();
        User sender = userdatabase.getUserById(senderid);
        manager.createNotification(R.receiverID, "Friend Request", sender.getUsername()+ " sent you a friend request!");
        RequestsDB.saveFile(requestsList);
    }
    
    public void deleterequest(Request R)
    {
        requestsList.remove(R);
     
       RequestsDB.saveFile(requestsList);
    }
    
    public void acceptrequest(Request R)
    {
     
      String Userid1 = R.getSenderID();
      String Userid2 = R.getReceiverID();
      
    Friend friends= new Friend(Userid1,Userid2);
   
    deleterequest(R);
    addfriend(friends);
      
    }
    
    public void declinerequest(Request R)
    {
     
        deleterequest(R);
       RequestsDB.saveFile(requestsList);
         
    }
    
    //////getters lel requests w ids////////
    
    //al requests aly wslt le user mo3yn
     public ArrayList<Request> getUserReceivedRequests(String Userid)
    {
        ArrayList<Request> userrequests = new ArrayList<>();
    
        for(Request r:requestsList)
        {
            if(Userid.equals(r.getReceiverID()))
                   userrequests.add(r);
        }
        return userrequests ;
    }
    //al ids b3atet requests le user mo3yn
       public ArrayList<String> getUserRequestsSenderIDS(String Userid)
    {
        ArrayList<String> userRequestsSenderIDs = new ArrayList<>();
        ArrayList<Request> userrequests = getUserReceivedRequests(Userid);
        for(Request r:userrequests)
        {
            if(Userid.equals(r.getReceiverID()))
                   userRequestsSenderIDs.add(r.getSenderID());
        }
        return userRequestsSenderIDs ;
    }
    
       
     //al requests aly b3tha user mo3yn  
       
          public ArrayList<Request> getUserSentRequests(String Userid)
    {
        ArrayList<Request> userrequests = new ArrayList<>();
    
        for(Request r:requestsList)
        {
            if(Userid.equals(r.getSenderID()))
                   userrequests.add(r);
        }
        return userrequests ;
    }
          
      //al ids aly atb3tlha request mn user mo3yn
       public ArrayList<String> getUserRequestsReceiversIDS(String Userid)
    {
        ArrayList<String> userRequestsReceiversIDs = new ArrayList<>();
        ArrayList<Request> userrequests =getUserSentRequests (Userid);
        for(Request r:userrequests)
        {
            if(Userid.equals(r.getSenderID()))
                   userRequestsReceiversIDs.add(r.getReceiverID());
        }
        return userRequestsReceiversIDs ;
    }   
       
   ///////////////////////////////////////////////////  
       
     public void BlockUser(String Blocker, String Blocked)
  {
      Block b= new Block(Blocker,Blocked);
      blocksList.add(b);
      BlocksDB.saveFile(blocksList);
      
      
  }
  
  public void RemoveBlock(Block b)
  {
      blocksList.remove(b);
       BlocksDB.saveFile(blocksList);
      
  }
  
  public  ArrayList<String> getAllUsersBlockedForaUser(String UserID)
  {
     
      
      ArrayList<String> blockedIDs=new ArrayList<>();
      
      for(Block B : blocksList)
      {
          if(UserID.equals(B.getBlocked()))
          {
              blockedIDs.add(B.getBlocker());
          }
          
          else if(UserID.equals(B.getBlocker()))
          {
              blockedIDs.add(B.getBlocked());
          }
            
              
      }
      
      return blockedIDs;
      
      
  }
  
 
  
  //m3aya userId 3yza ageb friendsoffriends
  //gbt list mn idsFriends bto3o
  //b3den hageb friendsIds le kl ID mn list de
  //b3den h loop 3ala kol array mn friendsof friends ids
  //w h add bshrt mykonsh blocked aw 3nde aw fe bena request  getUserRequestsSenderIDS ,getAllUsersBlockedForaUser
  
   public ArrayList<String> getSuggestedFriends(String Userid1) 
    {
        
        
        ArrayList<String>  requestsSendersIDs=getUserRequestsSenderIDS(Userid1);
        ArrayList<String>  blockedUsersIDs =getAllUsersBlockedForaUser(Userid1);
        
        ArrayList<String>  suggestedFriendsIDs= new ArrayList<>();
        
        
        ArrayList<String> userfriendsIDS=getUserFriendsIDs(Userid1);
        for(String friendID : userfriendsIDS)
        {
          ArrayList<String>  FriendsofFriendsIDS=getUserFriendsIDs(friendID);
            for(String id : FriendsofFriendsIDS)
            {
                
                if(!(requestsSendersIDs.contains(id)||blockedUsersIDs.contains(id)||userfriendsIDS.contains(id)||suggestedFriendsIDs.contains(id)||id.equals(Userid1)))
                    suggestedFriendsIDs.add(id);
             
            }
            
   
        }
        
           return suggestedFriendsIDs;
    }
   
   
   public boolean  allowedToSendRequest(String SenderID , String ReceiverID)
   {
       
       //mykonsh howa b3tle request abl kda
       //mykonsh ana b3tlo request abl kda
       //mkonsh bb3at le nfseeeeee
       ArrayList<String>  requestsSendersIDs1=getUserRequestsSenderIDS(SenderID); //al ids b3atet request le user mo3yn
        ArrayList<String>  requestsSendersIDs2=getUserRequestsSenderIDS(ReceiverID);
       if((requestsSendersIDs1.contains(ReceiverID)||ReceiverID.equals(SenderID))||requestsSendersIDs2.contains(SenderID))
        {
            return false;
        }
        return true;
   }
   
   
    public boolean areUsersBlocked(String senderId, String receiverId) {
   
      ArrayList<String>  blockedUsersIDs =getAllUsersBlockedForaUser(senderId);
      if(blockedUsersIDs.contains(receiverId))
      {
          return true;
      }
       return false;
    }
    
    public boolean areUsersFriends(String senderId, String receiverId)
    {
         ArrayList<String> userfriendsIDS=getUserFriendsIDs(senderId);
         if(userfriendsIDS.contains(receiverId))
         {
             return true;
         }
         
         return false;
    }
    
    
    
    public Request getRequest(String senderID,String receiverID)
    {
      
        for(Request R : requestsList)
        {
            if((R.senderID.equals(senderID)&&R.receiverID.equals(receiverID))||(R.senderID.equals(receiverID)&&R.receiverID.equals(senderID)))
                return R;
        }
        
        return null;
    }
    
      public Friend getFriend(String id1,String id2)
    {
      
        for(Friend F : friendsList )
        {
            
           
            if((F.Userid1.equals(id1)&&F.Userid2.equals(id2)) ||(F.Userid1.equals(id2)&&F.Userid2.equals(id1)))
                return F;
        }
        
        return null;
    }
}
