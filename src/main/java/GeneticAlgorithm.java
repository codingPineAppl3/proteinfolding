
import java.io.FileWriter;
public class GeneticAlgorithm {
    public void GeneticAlgorithm(int generations, int populationSize, String sequence) {
      //  CSVWriter csvWriter = new CSVWriter(new FileWriter("example.csv"));
        Population population = new Population(sequence, populationSize);
        for (int i = 0; i < generations; i++) {
            Selection selection = new Selection();
            selection.selectBest(population);

        }
    }
}
