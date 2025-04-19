import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AdventureModel {
    private Room startingRoom;
    private Room treasureRoom;
    private Adventurer adventurer;

    public AdventureModel() {
        createRooms();
        adventurer = new Adventurer(startingRoom);
    }

    private void createRooms() {
        // Defining rooms
        Room room1 = new Room("You are in a dark room.");
        Room room2 = new Room("You are in a bright room.");
        Room room3 = new Room("You are in a spooky room.");
        Room room4 = new Room("You are in a mysterious room.");
        Room room5 = new Room("You are in a treasure room.");

        // Adding exits
        room1.addExit("north", room2);
        room2.addExit("south", room1);
        room2.addExit("east", room3);
        room3.addExit("west", room2);
        room3.addExit("north", room4);
        room4.addExit("south", room3);
        room4.addExit("east", room5);
        room5.addExit("west", room4);

        // Lock the treasure room
        room4.lockExit("east");

        // Ensure key is always in the bright room (room2)
        room2.addItem("key", "A mysterious key");

        // Randomly distribute other items
        List<String> items = Arrays.asList("sword", "shield", "treasure");
        Room[] rooms = {room1, room3, room4, room5};
        Random random = new Random();
        for (String item : items) {
            Room room = rooms[random.nextInt(rooms.length)];
            room.addItem(item, "A mysterious " + item);
        }

        // Set the starting room to be one of the rooms except room2 (bright room)
        List<Room> possibleRooms = Arrays.asList(room1, room3, room4, room5);
        possibleRooms.remove(room2); // Remove room2 from possible starting rooms
        startingRoom = possibleRooms.get(random.nextInt(possibleRooms.size())); // Choose randomly

        // Randomly choose an exit direction from the starting room
        String[] directions = {"north", "south", "east", "west"};
        String randomDirection = directions[random.nextInt(directions.length)];

        // Ensure there's an exit in the randomly chosen direction from the starting room
        Room nextRoom = startingRoom.getExit(randomDirection);
        if (nextRoom != null) {
            startingRoom.addExit(randomDirection, nextRoom); // Set the exit in the starting room randomly
        }

        treasureRoom = room5;
    }


    public String processCommand(String command) {
        String[] parts = command.split(" ");
        String action = parts[0].toLowerCase();

        switch (action) {
            case "go":
                return go(parts);
            case "look":
                return look();
            case "take":
                return take(parts);
            case "drop":
                return drop(parts);
            case "inventory":
                return adventurer.showInventory();
            case "unlock":
                return unlock(parts);
            case "help":
                return "Available commands: go [direction], look, take [item], drop [item], inventory, unlock [direction], exit";
            case "exit":
                return "Goodbye!";
            default:
                return "Invalid command. Type 'help' to see the available commands.";
        }
    }

    private String go(String[] parts) {
        if (parts.length < 2) {
            return "Go where?";
        }
        String direction = parts[1].toLowerCase();
        if (adventurer.getCurrentRoom().isLocked(direction)) {
            return "The door is locked. You need a key.";
        }
        Room nextRoom = adventurer.getCurrentRoom().getExit(direction);
        if (nextRoom == null) {
            return "There is no exit in that direction.";
        }
        adventurer.setCurrentRoom(nextRoom);

        // Check the winning condition
        if (nextRoom.equals(treasureRoom) && adventurer.hasItem("key")) {
            return "Congratulations! You unlocked the treasure and won the game![Respect++]";
        }

        return nextRoom.getDescription();
    }

    private String look() {
        String roomDescription = adventurer.getCurrentRoom().getDescription();
        String itemDescription = adventurer.getCurrentRoom().getItemsDescription();
        // Return room description and items when the 'look' command is used
        return roomDescription + " " + itemDescription;
    }

    private String take(String[] parts) {
        if (parts.length < 2) {
            return "Take what?";
        }
        String itemName = parts[1].toLowerCase();
        String itemDescription = adventurer.getCurrentRoom().getItemDescription(itemName);
        if (itemDescription == null) {
            return "There is no such item in this room.";
        }
        adventurer.getCurrentRoom().removeItem(itemName);
        return adventurer.addItem(itemName, itemDescription);
    }

    private String drop(String[] parts) {
        if (parts.length < 2) {
            return "Drop what?";
        }
        String itemName = parts[1].toLowerCase();
        if (!adventurer.hasItem(itemName)) {
            return "You don't have that item.";
        }
        adventurer.removeItem(itemName);
        adventurer.getCurrentRoom().addItem(itemName, "lying on the ground");
        return "You drop the " + itemName + ".";
    }

    private String unlock(String[] parts) {
        if (parts.length < 2) {
            return "Unlock what?";
        }
        String direction = parts[1].toLowerCase();

        // Check if the player has the key
        if (!adventurer.hasItem("key")) {
            return "You don't have the key to unlock this door.";
        }

        // Check if the exit is locked
        Room currentRoom = adventurer.getCurrentRoom();
        if (!currentRoom.isLocked(direction)) {
            return "This door is not locked.";
        }

        // Unlock the door
        currentRoom.unlockExit(direction);
        return "You used the key to unlock the door to the " + direction + ".";
    }
}
