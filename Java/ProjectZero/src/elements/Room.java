package elements;

import java.util.Vector;

/**
 * Created by jufey on 02/09/15.
 */
public class Room {
    String name;
    Vector<HomeElement> elementList;


    public Room(String name) {
        this.name = name;
        elementList = new Vector<HomeElement>();
    }

    public void addToRoom(String name, ElementType type) {
        elementList.add(new HomeElement(name, type, this));
    }

    public String getStatus() {
        StringBuilder out = new StringBuilder();
        out.append(this.getName() + " ");
        for (HomeElement h : elementList) {
            out.append(h.getName() + "," + h.getType() + "," + h.isStatus()+"$");

        }

        return out.toString().substring(0, out.length()-1);
    }

    public String getName() {
        return name;
    }


}
