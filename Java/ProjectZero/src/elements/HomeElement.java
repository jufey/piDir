package elements;

/**
 * Created by jufey on 02/09/15.
 */
public class HomeElement {
    private String name;
    private ElementType type;
    private Room room;
    private boolean status = false;

    public HomeElement(String name, ElementType type, Room room) {
        this.name = name;
        this.type = type;
        this.room = room;
    }

    public void setStatus(boolean s) {
        this.status = s;
    }

    public String getName() {
        return name;
    }

    public ElementType getType() {
        return type;
    }

    public Room getRoom() {
        return room;
    }

    public boolean isStatus() {
        return status;
    }
}
