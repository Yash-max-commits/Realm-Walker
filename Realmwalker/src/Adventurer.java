import java.util.HashMap;
import java.util.Map;

public class Adventurer {
    private Room currentRoom;
    private Map<String, String> inventory;//Adventurs inventory

    public Adventurer(Room startingRoom) {
        this.currentRoom = startingRoom;
        this.inventory = new HashMap<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room newRoom) {
        this.currentRoom = newRoom;
    }

    public String addItem(String itemName, String itemDescription) {
        inventory.put(itemName, itemDescription);
        return itemName + " added to your inventory.";
    }

    public String removeItem(String itemName) {
        return inventory.remove(itemName) != null ? itemName + " removed from your inventory." : "Item not found.";
    }

    public boolean hasItem(String itemName) {
        return inventory.containsKey(itemName);
    }

    public String showInventory() {
        return inventory.isEmpty() ? "Your inventory is empty." : "Inventory: " + inventory.keySet();
    }
}
