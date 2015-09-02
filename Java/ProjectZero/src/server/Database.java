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
        Room bedRoom = new Room("bedroom");
        bedRoom.addToRoom("tableLight", ElementType.LIGHT);
        bedRoom.addToRoom("tv", ElementType.DEVICE);

        Room kitchen = new Room("kitchen");
        kitchen.addToRoom("ceilingLight", ElementType.LIGHT);
        kitchen.addToRoom("coffeeMachine", ElementType.MACHINE);

        roomList.add(bedRoom);
        roomList.add(kitchen);

    }

    private Room getRoom(String name) {
        for (Room r : roomList) {
            if (r.getName().equalsIgnoreCase(name)) {
                return r;
            }
        }
        try {
            throw new Exception("Room not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getStatusAll() {
        StringBuilder s = new StringBuilder();
        for (Room r : roomList) {
            s.append(r.getStatus()+"#");
        }
        s.append("#");
        return s.toString();
    }

}
