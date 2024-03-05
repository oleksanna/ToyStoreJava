public class Main {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();
        toyStore.put("1 2 конструктор");
        toyStore.put("2 2 робот");
        toyStore.put("3 6 кукла");
        toyStore.runAndWriteToFile();
    }
}