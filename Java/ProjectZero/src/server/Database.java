package server;
import java.util.Vector;
import elements.*;

/**
 * Created by jufey on 02/09/15.
 */
public class Database {
    Vector<Room> roomList;
    public Database() {
        roomList = new Vector<Room>();
        roomList.add(new Room("bedroom"));
        roomList.add(new Room("kitchen"));
        getRoom("kitchen").setStatus(false);
    }
    public boolean getStatus(String name){
        return getRoom(name).getStatus();
    }
    private Room getRoom(String name){
        for(Room r : roomList){
            if(r.getName().equalsIgnoreCase(name)){
                return r;
            }
        }
        try {
            throw new  Exception("Room not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
