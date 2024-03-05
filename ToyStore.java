import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.PriorityQueue;

public class ToyStore {
    private PriorityQueue<Toy> toyQueue;

    public ToyStore() {
        toyQueue = new PriorityQueue<>();
    }

    public void put(String input) {
        String[] parts = input.split(" ");
        int id = Integer.parseInt(parts[0]);
        String name = parts[2]; // Название игрушки
        int frequency = Integer.parseInt(parts[1]); // Частота выпадения игрушки
        Toy toy = new Toy(id, name, frequency);
        toyQueue.add(toy);
    }

    public Toy getToy() {
        int totalWeight = toyQueue.stream().mapToInt(Toy::getFrequency).sum();
        int randomNumber = (int) (Math.random() * totalWeight) + 1;
        int cumulativeWeight = 0;

        for (Toy toy : toyQueue) {
            cumulativeWeight += toy.getFrequency();
            if (randomNumber <= cumulativeWeight) {
                return toy;
            }
        }
        return null; // should not reach here
    }

    public void runAndWriteToFile() {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), StandardCharsets.UTF_8)))) {
            for (int i = 0; i < 10; i++) {
                Toy toy = getToy();
                if (toy != null) {
                    writer.println(toy.getName() + " " + toy.getFrequency());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}