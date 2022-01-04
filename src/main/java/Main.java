import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    int populationSize = 10000;
    int generations = 50;
    //Imaging i = new Imaging();

    String SEQ20 = "10100110100101100101";
    String SEQ24 =    "110010010010010010010011";
    String SEQ25 = "0010011000011000011000011";
    String SEQ36 =    "000110011000001111111001100001100100";
    String SEQ48 =    "001001100110000011111111110000001100110010011111";
    String SEQ50 = "11010101011110100010001000010001000101111010101011";
     Population population = new Population(SEQ50, populationSize);
  //     population.genPopulation(SEQ20, populationSize);
     //  Conformation conformation = new Conformation(SEQ20);
     //  conformation.genConformation(SEQ20);
       GraphikBeispiel g = new GraphikBeispiel();
       g.Graphik(population.bestConformation);
     //  conformation.calculateEnergy();
     GeneticAlgorithm ga = new GeneticAlgorithm();
     ga.GeneticAlgorithm(generations, populationSize, SEQ50);
    //Folding best = ga.findBestFolding();

   // i.drawFolding(best, sequence, "best.png");
}


}
