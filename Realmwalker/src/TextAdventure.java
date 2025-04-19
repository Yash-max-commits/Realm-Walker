import java.util.Scanner;

public class TextAdventure {
    public static void main(String[] args) {
        AdventureModel model = new AdventureModel();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Text Adventure Game!");
        System.out.println("Type 'Help' to see available commands.");
        System.out.println(model.processCommand("look")); // Show starting room description

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();
            String output = model.processCommand(command);
            System.out.println(output);
            if (command.equalsIgnoreCase("exit")) {
                break;
            }
        }
    }
}
