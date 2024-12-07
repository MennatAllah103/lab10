/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FriendManagementBackend;

import java.util.ArrayList;

/**
 *
 * @author daree
 */
public class Management {
    
    FriendsDatabase FriendsDB =FriendsDatabase.getinstance();
    ArrayList<Friends> friendsList=FriendsDB.getALLFriends();
    
   RequestsDatabase RequestsDB = RequestsDatabase.getinstance();
  ArrayList<Requests> requestsList = RequestsDB.getALLRequests();
  
   BlocksDatabase BlocksDB= BlocksDatabase.getinstance();
  ArrayList<BlockedUsers> blocksList=BlocksDB.getALLBlockedUsers();
  
  
   public void addfriend(Friends F)
    {
        friendsList.add(F);
       FriendsDB.saveFile(friendsList);
    }
    
    public void removefriend(Friends F)
    {
        friendsList.remove(F);
       FriendsDB.saveFile(friendsList);
    }
    
     public ArrayList<String> getUserFriendsIDs(String Userid1)
    {
        ArrayList<String> userfriendsIDs = new ArrayList<>();
        
        for(Friends f : friendsList)
        {
           if(Userid1.equals(f.getUserid1()))
                    userfriendsIDs .add(f.getUserid2());
           else if(Userid1.equals(f.getUserid2()))
                    userfriendsIDs .add(f.getUserid1());
         
            
        }
        return userfriendsIDs ;
    }
     
         public ArrayList<Friends> getUserFriends(String Userid1)
    {
        
    friendsList=FriendsDB.loadFile();
        ArrayList<Friends> userfriends = new ArrayList<>();
        
        for(Friends f : friendsList)
        {
           if(Userid1.equals(f.getUserid1()))
                    userfriends .add(f);
           else if(Userid1.equals(f.getUserid2()))
                    userfriends .add(f);
         
            
        }
        return userfriends ;
    }
     
     
      public void addrequest(Requests R)
    {
        
        requestsList.add(R);
        RequestsDB.saveFile(requestsList);
    }
    
    public void deleterequest(Requests R)
    {
        requestsList.remove(R);
     
       RequestsDB.saveFile(requestsList);
    }
    
    public void acceptrequest(Requests R)
    {
     
      String Userid1 = R.getSenderID();
      String Userid2 = R.getReceiverID();
      
    Friends friends= new Friends(Userid1,Userid2);
   
    deleterequest(R);
    addfriend(friends);
      
    }
    
    public void declinerequest(Requests R)
    {
     
        deleterequest(R);
       RequestsDB.saveFile(requestsList);
         
    }
    
    
     public ArrayList<Requests> getUserRequests(String Userid)
    {
        ArrayList<Requests> userrequests = new ArrayList<>();
        ArrayList<Requests> requests =RequestsDB.getALLRequests();
        for(Requests r:requests)
        {
            if(Userid.equals(r.getReceiverID()))
                   userrequests.add(r);
        }
        return userrequests ;
    }
      
       public ArrayList<String> getUserSenderRequestsIDS(String Userid)
    {
        ArrayList<String> userSenderRequestsIDs = new ArrayList<>();
        ArrayList<Requests> requests =RequestsDB.getALLRequests();
        for(Requests r:requests)
        {
            if(Userid.equals(r.getReceiverID()))
                   userSenderRequestsIDs.add(r.getSenderID());
        }
        return userSenderRequestsIDs ;
    }
       
       
     public void BlockUser(String Blocker, String Blocked)
  {
      BlockedUsers b= new BlockedUsers(Blocker,Blocked);
      blocksList.add(b);
      BlocksDB.saveFile(blocksList);
      
      
  }
  
  public void RemoveBlock(BlockedUsers b)
  {
      blocksList.remove(b);
       BlocksDB.saveFile(blocksList);
      
  }
  
  public  ArrayList<String> getAllUsersBlockedForaUser(String UserID)
  {
     blocksList=BlocksDB.loadFile();
      
      ArrayList<String> blockedIDs=new ArrayList<>();
      
      for(BlockedUsers B : blocksList)
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
  //w h add bshrt mykonsh blocked aw 3nde aw fe bena request  getUserSenderRequestsIDS ,getAllUsersBlockedForaUser
  
   public ArrayList<String> getSuggestedFriends(String Userid1) 
    {
        
        
        ArrayList<String>  requestsSendersIDs=getUserSenderRequestsIDS(Userid1);
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
   
    public Requests getRequest(String senderID,String receiverID)
    {
      
        for(Requests R : requestsList)
        {
            if(R.senderID.equals(senderID)&&R.receiverID.equals(receiverID))
                return R;
        }
        
        return null;
    }
    
      public Friends getFriend(String id1,String id2)
    {
      
        for(Friends F : friendsList )
        {
            
           
            if((F.Userid1.equals(id1)&&F.Userid2.equals(id2)) ||(F.Userid1.equals(id2)&&F.Userid2.equals(id1)))
                return F;
        }
        
        return null;
    }
}
