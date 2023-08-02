import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Pizza implements Comparable<Pizza> {

  //  private static final Set<String> names = new HashSet<>();
//  private static final Set<String> sizes = new HashSet<>();
//  private static final String SEP = ";";
//  // Map<Название, Map<Размер, Стоимость>>
//  private static final Map<String, Map<String, Double>> prices = readFromCsv("res/pizzas.csv");

  private final String name;
  private final String size;
  private final double price;

  public Pizza(String name, String size, double price) {
    this.name = name;
    this.size = size;
    this.price = price;
  }

//  public Pizza(String name, String size) {
//    if (!prices.containsKey(name)) {
//      throw new IllegalArgumentException("Некорректное название пиццы: " + name);
//    }
//    this.name = name;
//    Map<String, Double> sizeToPrice = prices.get(name); // стоимость в зависимости от размера
//    if (!sizeToPrice.containsKey(size)) {
//      throw new IllegalArgumentException("Некорректный размер: " + size);
//    }
//    this.size = size;
//    price = sizeToPrice.get(size);
//  }

//  private static String readValueFromSet(Scanner scanner, Set<String> values, String title) {
//    System.out.println("Выберите " + title + ": ");
//    for (String name : values) {
//      System.out.println("- " + name);
//    }
//    System.out.println("Введите " + title + ": ");
//    String value = scanner.nextLine();
//    while (!values.contains(value)) {
//      System.out.println("Некорректное " + title + " пиццы: " + value);
//      System.out.println("Введите " + title + ": ");
//      value = scanner.nextLine();
//    }
//    return value;
//  }

  // фабричный метод:
  // прочитает параметры пиццы в интерактивном режиме
  // (когда есть взаимодействие)
//  public static Pizza readInteractive(Scanner scanner) {
//    String name = readValueFromSet(scanner, prices.keySet(), "название");
//    String size = readValueFromSet(scanner, prices.get(name).keySet(), "размер");
//    return new Pizza(name, size);
//  }

//  private static Map<String, Map<String, Double>> readFromCsv(String filename) {
//    // CVS - comma separated values - значения, разделённые запятой (или точкой с запятой)
//    // самый простой формат таблиц
//    Map<String, Map<String, Double>> result = new HashMap<>();
//    File pizzasFile = new File(filename);
//    try {
//      Scanner scanner = new Scanner(pizzasFile);
//      while (scanner.hasNextLine()) {
//        // line = "Capricciosa;Small;7.5"
//        String line = scanner.nextLine();
//        // cells = ["Capricciosa", "Small", "7.5"]
//        String[] cells = line.split(SEP);
//        if (cells.length != 3) {
//          System.out.println("Некорректная строка файла: " + line);
//          continue;
//        }
//        try {
//          // pizza = "Cappricciosa"
//          String pizza = cells[0];
//          // size = "Small"
//          String size = cells[1];
//          // price = Double.parseDouble("7.5") = 7.5
//          double price = Double.parseDouble(cells[2]);
//          if (!result.containsKey(pizza)) { // пицца встретилась впервые
//            result.put(pizza, new HashMap<>()); // кладём ей пока пустой словарь "размер-цена"
//          }
//          // теперь словарь "размер-цена" для пиццы точно есть
//          result.get(pizza).put(size, price);
//        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
//          System.out.println("Некорректная строка файла: " + e);
//        }
//      }
//      scanner.close();
//    } catch (FileNotFoundException e) {
//      System.out.println("Не найден файл: " + e);
//    }
//    return result;
//  }

  // определение "естественного" порядка, натур сортировки
  // natural order, natural sort
  @Override
  public String toString() {
    return "Pizza{" +
        "name='" + name + '\'' +
        ", size='" + size + '\'' +
        ", price=" + price +
        '}';
  }

  @Override
  public int compareTo(Pizza o) {
    // метод compareTo сравнивает текущий объект (this) с аргументом (o - object - other)
    // если this < o, то метод должен вернуть любое отриц число       - ответ < 0
    // если this = o, то метод должен вернуть 0                       - ответ = 0
    // если this > o, то метод должен вернуть любое положит число     - ответ > 0

    // a < b ---> r < 0 ---> a - b < b - b (0)
    // a = b ---> r = 0 ---> a - b = b - b (0)
    // a > b ---> r > 0 ---> a - b > b - b (0)
    // метод compareTo устроен так, чтобы возвращать разницу (this - o)
    // если сравнение объектов можно превратить в сравнение чисел (есть метрика)
    // то такие числа будут иногда называться ключами сравнения (comparison key)

    // если в числа превратить нельзя или сложно
    // если названия разные, то по названиям

//    int result = name.compareTo(o.name);
//    if (result != 0) { // compareTo != 0 --> названия не равны
//      return result; // по названиям
//    }

    if (!name.equals(o.name)) { // compareTo != 0 --> названия не равны
      return name.compareTo(o.name); // по названиям
    }
    // если названия одинаковые, но размеры разные, то пор азмерам
    if (!size.equals(o.size)) {
      return size.compareTo(o.size);
    }
    // если названия одинаковые, то по цене
    // Math.signum - знак числа (отриц в -1.0, полож. +1.0)

//    return (int) Math.signum(this.price - o.price);
    // Double a = x
    // Double a = y
    return Double.compare(price, o.price);

    // размер игнорируем, тк он связан с ценой

    // переопределение equals() должно приводить к переопределению метода equals()
    // это приводит к переопределению метода haschCode()
    // эти три метода должны быть КОНСИСТЕНТНЫМИ: вести себя одинаково
  }
}