import java.util.Scanner;

public enum MenuCommand {
  UNEXPECTED(""), // служебное значение, которого не должно быть (его не должен видеть юзер)
  START("Начать заказ"),
  EXIT("Выйти");

  private final String message;

  MenuCommand(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  // фабричный метод - статический метод, который порождает
  // (производит) экземпляр этого класса
  public static MenuCommand readCommand(Scanner scanner) {
    printMenu();
    System.out.print("Введите команду: ");
    if (!scanner.hasNext()) {
      throw new RuntimeException("Ожидается ввод команды");
    }
    String input = scanner.next();
    scanner.nextLine();
    switch (input.toLowerCase()) {
      case "1": // Integer.toString(START.ordinal())
      case "start":
      case "начать":
        return START;
      case "2": // Integer.toString(EXIT.ordinal())
      case "exit":
      case "выйти":
      case "выход":
        return EXIT;
      default:
        return UNEXPECTED;
    }
  }

  public static void printMenu() {
    for (MenuCommand command : values()) {
      if (!command.message.isEmpty()) {
        System.out.println(command.ordinal() + ". " + command.message);
      }
    }
  }
}