# Realmwalker

**Realmwalker** is a modular, console-based Java text adventure game that demonstrates strong object-oriented design. Built to model a fantasy exploration game, this project serves as a great foundation for understanding Java fundamentals, user input handling, class interactions, and state management.

---

## 📁 Project Structure

The project is divided into the following key components:

### 🔸 Source Files (`/src`)

- `TextAdventure.java`  
  Entry point of the application. Manages the game loop, user interaction, and command parsing.

- `AdventureModel.java`  
  Central controller that initializes the game world. Connects rooms, defines the winning condition, and manages the adventurer's state.

- `Adventurer.java`  
  Encapsulates player data, including current room and inventory. Provides methods to move, collect, and use items.

- `Room.java`  
  Represents a room in the game world. Manages exits, items, and door lock mechanisms using Java collections.

---

## ⚙️ Core Concepts & Implementation

### ✅ Object-Oriented Design

- **Encapsulation**: Each class manages its own data and behavior.
- **Composition**: `AdventureModel` composes multiple `Room` and `Adventurer` objects.
- **Modularity**: Classes are loosely coupled and easily extendable.

### 📦 Data Structures

- `Map<String, Room>` – for managing room exits (directions).
- `Map<String, Boolean>` – to track locked exits.
- `Map<String, String>` – to store room items and adventurer inventory.

### 🔄 Game Flow

1. The game starts from `TextAdventure.java`.
2. The `AdventureModel` sets up a network of rooms.
3. The player navigates using directional commands (`go north`, `take key`, etc.).
4. Room transitions and inventory actions are handled through class interactions.

---

## 📲 Commands Overview

Here are some built-in commands supported in the game:

| Command       | Action                                      |
|---------------|---------------------------------------------|
| `go <dir>`    | Move to a direction (e.g., `go north`)      |
| `look`        | Get current room description and contents   |
| `take <item>` | Pick up an item from the room               |
| `use <item>`  | Use an item to unlock a path                |
| `inventory`   | View your current inventory                 |
| `help`        | Display a list of available commands        |

---

## 🚀 Getting Started

### Requirements

- Java JDK 8 or above
- Terminal or Java IDE (e.g., IntelliJ IDEA)

### Setup & Run

1. **Clone the repository**
```bash
git clone https://github.com/your-username/Realmwalker.git
cd Realmwalker
