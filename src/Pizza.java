import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Pizza {

  //  private static final Set<String> names = new HashSet<>();
//  private static final Set<String> sizes = new HashSet<>();
  private static final String SEP = ";";
  // Map<Название, Map<Размер, Стоимость>>
  private static final Map<String, Map<String, Double>> prices = readFromCsv("res/pizzas.csv");

  private final String name;
  private final String size;
  private final double price;

  public Pizza(String name, String size) {
    if (!prices.containsKey(name)) {
      throw new IllegalArgumentException("Некорректное название пиццы: " + name);
    }
    this.name = name;
    Map<String, Double> sizeToPrice = prices.get(name); // стоимость в зависимости от размера
    if (!sizeToPrice.containsKey(size)) {
      throw new IllegalArgumentException("Некорректный размер: " + size);
    }
    this.size = size;
    price = sizeToPrice.get(size);
  }

  public static Pizza readData(Scanner scanner) {
    System.out.print("Выберите пиццу: ");
    for (String name : prices.keySet()) {
      System.out.println("- " + name);
    }
    System.out.print("Введите название: ");
    String name = scanner.nextLine();
    while (!prices.containsKey(name)) {
      System.out.println("Некорректное название пиццы: " + name);
      System.out.print("Введите название: ");
      name = scanner.nextLine();
    }
    Set<String> sizes = prices.get(name).keySet();
    System.out.println("Выберите размер: ");
    for (String size : sizes) {
      System.out.println("- " + size);
    }
    System.out.print("Введите размер: ");
    String size = scanner.nextLine();
    while (!sizes.contains(size)) {
      System.out.println("Некорректный размер: " + size);
      System.out.println("Введите размер: ");
      size = scanner.nextLine();
    }
    return new Pizza(name, size);
  }

  private static Map<String, Map<String, Double>> readFromCsv(String filename) {
    // CVS - comma separated values - значения, разделённые запятой (или точкой с запятой)
    // самый простой формат таблиц
    Map<String, Map<String, Double>> result = new HashMap<>();
    File pizzasFile = new File(filename);
    try {
      Scanner scanner = new Scanner(pizzasFile);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] cells = line.split(SEP);
        if (cells.length != 3) {
          System.out.println("Некорректная строка файла: " + line);
          continue;
        }
        try {
          String pizza = cells[0];
          String size = cells[1];
          double price = Double.parseDouble(cells[2]);
          if (!result.containsKey(pizza)) { // пицца встретилась впервые
            result.put(pizza, new HashMap<>()); // кладём ей пока пустой словарь "размер-цена"
          }
          // теперь словарь "размер-цена" для пиццы точно есть
          result.get(pizza).put(size, price);
        } catch (NumberFormatException e) {
          System.out.println("Некорректная строка файла: " + e);
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("Не найден файл: " + e);
    }
    return result;
  }
}

