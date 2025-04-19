import java.util.HashMap;
import java.util.Map;

public class Room {
    private String description;
    private Map<String, Room> exits;
    private Map<String, String> items;
    private Map<String, Boolean> lockedExits;

    public Room(String description) {
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new HashMap<>();
        this.lockedExits = new HashMap<>();
    }

    // Room description without items (no items shown here)
    public String getDescription() {
        String exitDirections = String.join(", ", exits.keySet());
        return description + " Exits: " + exitDirections + ".";
    }

    // Method to show items when 'look' command is issued
    public String getItemsDescription() {
        if (!items.isEmpty()) {
            StringBuilder itemDescriptions = new StringBuilder();
            itemDescriptions.append("You see the following items: ");
            for (String itemName : items.keySet()) {
                itemDescriptions.append(itemName).append(" ");
            }
            return itemDescriptions.toString();
        } else {
            return "There are no items in this room.";
        }
    }

    public void addExit(String direction, Room nextRoom) {
        exits.put(direction, nextRoom);
        lockedExits.put(direction, false);
    }

    public void lockExit(String direction) {
        lockedExits.put(direction, true);
    }

    public void unlockExit(String direction) {
        lockedExits.put(direction, false);
    }

    public boolean isLocked(String direction) {
        return lockedExits.getOrDefault(direction, false);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void addItem(String itemName, String itemDescription) {
        items.put(itemName, itemDescription);
    }

    public String getItemDescription(String itemName) {
        return items.get(itemName);
    }

    public void removeItem(String itemName) {
        items.remove(itemName);
    }
}
