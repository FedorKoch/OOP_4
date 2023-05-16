import java.util.ArrayList;

public class FruitBox {

    public static void main(String[] args) {

        System.out.println("*** создаем немного яблок и апельсинов ***");
        Apple apple1 = new Apple();
        System.out.printf("создан фрукт %s\n", apple1);
        Apple apple2 = new Apple();
        System.out.printf("создан фрукт %s\n", apple2);
        Apple apple3 = new Apple();
        System.out.printf("создан фрукт %s\n", apple3);
        Apple apple4 = new Apple();
        System.out.printf("создан фрукт %s\n", apple4);
        Apple apple5 = new Apple();
        System.out.printf("создан фрукт %s\n", apple5);
        Apple apple6 = new Apple();
        System.out.printf("создан фрукт %s\n", apple6);
        System.out.println();

        Orange orange1 = new Orange();
        System.out.printf("создан фрукт %s\n", orange1);
        Orange orange2 = new Orange();
        System.out.printf("создан фрукт %s\n", orange2);
        Orange orange3 = new Orange();
        System.out.printf("создан фрукт %s\n", orange3);
        Orange orange4 = new Orange();
        System.out.printf("создан фрукт %s\n", orange4);
        System.out.println();

        System.out.println("*** создаем ящики для яблок и апельсинов ***");
        Box<Apple> box1 = new Box<Apple>(Apple.class);
        System.out.printf("создан %s\n", box1);
        Box<Orange> box2 = new Box<Orange>(Orange.class);
        System.out.printf("создан %s\n", box2);
        Box<Apple> box3 = new Box<Apple>(Apple.class);
        System.out.printf("создан %s\n", box3);
        System.out.println();

        System.out.println("*** раскладываем все фрукты по ящикам ***");
        box1.addFruit(apple1);
        box1.addFruit(apple2);
        box1.addFruit(apple3);
        box1.addFruit(apple4);

        box2.addFruit(orange1);
        box2.addFruit(orange2);
        box2.addFruit(orange3);
        box2.addFruit(orange4);

        box3.addFruit(apple5);
        box3.addFruit(apple6);
        System.out.println();

        System.out.printf("*** сравниваем %s и %s по весу ***\n", box1, box2);

        if (box1.compare(box2)) {
            System.out.println("вес ящиков одинаков");
        } else {
            System.out.println("вес ящиков отличатеся");
        }
        System.out.println();

        System.out.printf("*** пересыпаем фрукты из %s в %s ***\n", box3, box1);
        box3.transferTo(box1);
        System.out.println();

        System.out.printf("*** сравниваем еще раз %s и %s по весу ***\n", box1, box2);

        if (box1.compare(box2)) {
            System.out.println("вес ящиков одинаков");
        } else {
            System.out.println("вес ящиков отличатеся");
        }
        System.out.println();

    }

}

abstract class Fruit {

    private String name;
    private double weight;

    public Fruit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name;
    }

}

class Apple extends Fruit {

    private final double WEIGHT = 1.0;
    private static int counter = 1;

    {
        counter++;
    }

    public Apple() {
        super("Яблоко №" + counter);
    }

    @Override
    public double getWeight() {
        return WEIGHT;
    }

}

class Orange extends Fruit {
    private final double WEIGHT = 1.5;
    private static int counter = 1;

    {
        counter++;
    }

    public Orange() {
        super("Апельсин №" + counter);
    }

    @Override
    public double getWeight() {
        return WEIGHT;
    }

}

class Box<T extends Fruit> {

    private ArrayList<T> fruits;
    private String name;
    private static int counter;
    private Class<T> type;

    {
        counter++;
    }

    public Box(Class<T> type) {
        this.type = type;
        name = "ящик №" + counter;
        fruits = new ArrayList<T>();
    }

    public void addFruit(T fruit) {
        fruits.add(fruit);
        System.out.printf("в %s добавден фрукт %s\n", name, fruit);
    }

    public double getWeight() {
        if (fruits.isEmpty())
            return 0;
        T fruit = fruits.get(0);
        double weight = fruit.getWeight() * fruits.size();
        return weight;
    }

    public boolean compare(Box<?> box) {
        return Math.abs(this.getWeight() - box.getWeight()) < 0.0001;
    }

    public void transferTo(Box<T> box) {
        if (this == box)
            return;
        box.fruits.addAll(fruits);
        fruits.clear();
        System.out.printf("все фрукты из %s пересыпаны в %s\n", this, box);
    }

    @Override
    public String toString() {
        if (type.getSimpleName().equals("Apple")) {
            return name + " для яблок";
        } else {
            return name + " для апельсинов";
        }
    }

}