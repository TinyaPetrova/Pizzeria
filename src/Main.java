import java.util.Scanner;

public class Main {

  // MVP - minimum viable product - минимально жизнеспособный продукт
  // Продукт, который можно использовать для демонстрации идеи

  // Написать небольшую программу, используемую для обработки заказов в пиццерии

  // Команды:
  // - начать заказ
  //   - указать данные о пицце: (на любом этапе можно вернуться к предыдущему этапу / отменить заказ)
  //      - название
  //      - размер (выбор из списка)
  //      - автоматический расчёт стоимости
  //   - данные о заказе добавляются в файл (подаются на кухню) (заказ завершён)
  // - выход

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      MenuCommand command = MenuCommand.readCommand(scanner);
      switch (command) {
        case START:
          Pizza pizza = Pizza.readInteractive(scanner);
          break;
        case UNEXPECTED:
          System.out.println("Некорректная команда");
        case EXIT:
          return; // завершение работы метода main()
      }
    }
  }
}