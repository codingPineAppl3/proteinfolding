import java.util.Random;

public class Selection {
    int tournamentSize = 10;
    void selectBest(Population population){
        Population old = population;
        for (int i = 0; i < population.population.size(); ++i) {
            Random random = new Random();
            int randomConformation = random.nextInt(population.population.size());
            Conformation bestConformation =
                    population.population.get(randomConformation);

            for (int k = 0; k < tournamentSize - 1; ++k) {
                int randomConformationOld = random.nextInt(population.population.size());
                if (old.population.get(randomConformationOld).getEnergy() > bestConformation.getEnergy()) {
                    bestConformation = old.population.get(randomConformationOld);
                }
                population.population.set(i,bestConformation);
           }
        }
    }
}
