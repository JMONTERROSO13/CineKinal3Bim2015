package gt.edu.kinal.jmonterroso.movies;

import java.util.ArrayList;

import gt.edu.kinal.jmonterroso.movies.models.Users;

/**
 * Created by JMONTERROSO on 04/06/2015.
 */
public class HandlerUsers {
    public static ArrayList<Users> userList = new ArrayList<Users>();
    private static HandlerUsers instance;

    public static HandlerUsers getInstance(){
        if(instance == null){
            instance = new HandlerUsers();
        }
        return instance;
    }
    public void addUsers(Users user){
        userList.add(user);
    }

    public ArrayList<Users> getUsers(){
        return this.userList;
    }

}
