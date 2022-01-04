import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Population {
    int cumulativeEnergy = 0;
    int bestEnergy = 0;
    int cumulativeOverlaps = 0;
    Conformation bestConformation = null;
    ArrayList<Conformation> population = new ArrayList<>();
    ArrayList<ArrayList<Element>> matrix = new ArrayList<ArrayList<Element>>();

     public Population(String sequence, int populationSize) {

        for (int k = 0; k < populationSize; k++) {

            Conformation conformation = new Conformation(sequence);


        //    matrix = conformation.createConformationMatrix();
            conformation.calculateEnergy();
            if(bestConformation == null && conformation.getOverlaps() == 0){
                bestConformation = conformation;
            }
            population.add(conformation);
            cumulativeEnergy += conformation.getEnergy();
            cumulativeOverlaps += conformation.getOverlaps();
            if(conformation.getEnergy() > bestEnergy && conformation.getOverlaps()==0){
                bestEnergy = conformation.getEnergy();
                bestConformation = conformation;
            }
        }
        System.out.println("bestenergy " + bestEnergy);
        System.out.println("cumuEnergy " + cumulativeEnergy);
         System.out.println("cumuOverlaps " + cumulativeOverlaps);
         System.out.println("population size " + population.size());

    }
     Conformation getBestConformation(){
         return bestConformation;
     }

// Calculates the energy for each conformation.

 /*   public float evaluation()
    {
        int cumulativeEnergy = 0;
        int bestEnergy = 0;

        // For each conformation
        for (int i = 0; i < population.size(); ++i)
        {
            for(int j = 0; j < population.get(i))
            if (population.get(i)..hasChanged()) {
                std::vector<Element> &elements = m_Conformations[i].getElements();
                std::vector<std::vector<Element*>> matrix = createMatrix(m_Conformations[i]);

                // Calculate energy using matrix and list
                Conformation conformation = population.get(i);
                int energy = population.get(i).culateEnergy(elements, matrix);

                // We need to subtract all the overlapping elements to get a good value
                // energy -= (overlaps);
                energy /= (overlaps + 1);

                // Still, energy should not be negative
                if (energy < 0) energy = 0;
                m_Conformations[i].setEnergy(energy);
            }

            // Is this the highest energy value in this population?
            if (m_Conformations[i].getEnergy() > m_BestEnergy)
            {
                m_BestEnergy = m_Conformations[i].getEnergy();
                m_BestConformation = &m_Conformations[i];
            }

            m_CumulativeEnergy += m_Conformations[i].getEnergy();
            m_Conformations[i].setHasChanged(false);
        }

        // Return average fitness
        return m_CumulativeEnergy / (float)m_PopulationSize;
    }
        }*/
}
