import java.util.*;

public class Conformation {
    ArrayList<Element> conformation = new ArrayList<>();
    int overlaps = 0;
    int energy = 0;
    ArrayList<ArrayList<Element>> matrix = new ArrayList<ArrayList<Element>>();

    public Conformation(String sequence) {
        Element.ScreenDirection screenDirection1 = null;
        Element.ScreenDirection screenDirection2 = null;
        for (int i = 0; i < sequence.length(); i++) {
            Element element = new Element(sequence.charAt(i), i);
            if (i == 0) {
                screenDirection1 = Element.ScreenDirection.values()[0];
                element.setScreenDirection(screenDirection1);
            } else {
                screenDirection2 = calculateScreenDirection(screenDirection1,
                        element.getDirection());
                element.setScreenDirection(screenDirection2);
                //   System.out.println("screendirection1 " + screenDirection1 + " " + screenDirection2);
                screenDirection1 = screenDirection2;

            }
            conformation.add(element);
        }
        //System.out.println("conformation size " + conformation.size());
        matrix = createConformationMatrix();
    }

    public Element.ScreenDirection calculateScreenDirection(Element.ScreenDirection sDir, Element.Direction dir) {
        boolean LE = (sDir == Element.ScreenDirection.East && dir == Element.Direction.Left);
        boolean SN = (sDir == Element.ScreenDirection.North && dir == Element.Direction.Straight);
        boolean RW = (sDir == Element.ScreenDirection.West && dir == Element.Direction.Right);

        boolean SE = (sDir == Element.ScreenDirection.East && dir == Element.Direction.Straight);
        boolean LS = (sDir == Element.ScreenDirection.South && dir == Element.Direction.Left);
        boolean RN = (sDir == Element.ScreenDirection.North && dir == Element.Direction.Right);

        boolean SS = (sDir == Element.ScreenDirection.South && dir == Element.Direction.Straight);
        boolean LW = (sDir == Element.ScreenDirection.West && dir == Element.Direction.Left);
        boolean RE = (sDir == Element.ScreenDirection.East && dir == Element.Direction.Right);

        boolean SW = (sDir == Element.ScreenDirection.West && dir == Element.Direction.Straight);
        boolean LN = (sDir == Element.ScreenDirection.North && dir == Element.Direction.Left);
        boolean RS = (sDir == Element.ScreenDirection.South && dir == Element.Direction.Right);

        // North
        if (LE || SN || RW)
            return Element.ScreenDirection.North;

            // South
        else if (SS || LW || RE)
            return Element.ScreenDirection.South;

            // East
        else if (SE || LS || RN)
            return Element.ScreenDirection.East;

            // West
        else // if (SW || LN || RS)
            return Element.ScreenDirection.West;
        // System.out.println("return " + screenDirection);

    }

    ArrayList<ArrayList<Element>> createConformationMatrix() {
        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;
        int x = 0;
        int y = 0;

        Element.ScreenDirection currDir = Element.ScreenDirection.North;
        for (int i = 0; i < conformation.size(); ++i) {
            Element.Direction elemDir = conformation.get(i).getDirection();
            currDir = conformation.get(i).getScreenDirection();
            //  System.out.println("screendirection " + elemDir + " " + currDir);
            switch (currDir) {
                case North:
                    y++;
                    if (y > top) {
                        top++;
                    }
                    break;
                case South:
                    y--;
                    if (y < bottom) {
                        bottom--;
                    }
                    break;
                case East:
                    x++;
                    if (x > right) {
                        right++;
                    }
                    break;
                case West:
                    x--;
                    if (x < left) {
                        left--;
                    }
                    break;
                default:
                    break;
            }
        }

        int width = (-left) + right + 3;
        int height = (-bottom) + top + 3;
        // Create matrix using the bounding box
        //matrix.capacity(height, width);

        for (int i = 0; i < height; i++) {
            matrix.add(new ArrayList<Element>());
            for (int j = 0; j < width; j++) {
                matrix.get(i).add(null);
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix.get(i).get(j) != null)
                    System.out.println("not null " + matrix.get(i).get(j));
            }
        }
        // Zero point
        Vector2 position = new Vector2(-left + 1, -bottom + 1);

        Element.ScreenDirection screenDirection = Element.ScreenDirection.North;
        Vector2 position2 = null;
        // Fill matrix
        for (int j = 0; j < conformation.size(); ++j) {
            // Is overlapping?
            if (matrix.get(position.y).get(position.x) != null) {
                overlaps++;
            }
            conformation.get(j).setPosition(position);
            matrix.get(position.y).set(position.x, conformation.get(j));
            screenDirection = conformation.get(j).getScreenDirection();
            //direction = calculateDirection(direction, elements[e].getDirection());
            position2 = calculateNextElementPosition(position, screenDirection);
            position = position2;

        }

        return matrix;
    }

    Vector2 calculateNextElementPosition(Vector2 position, Element.ScreenDirection direction) {
        Vector2 nextElementPosition = position;
        switch (direction) {
            case North:
                nextElementPosition.y += 1;
                break;
            case South:
                nextElementPosition.y -= 1;
                break;
            case West:
                nextElementPosition.x -= 1;
                break;
            case East:
                nextElementPosition.x += 1;
                break;
            default:
                break;
        }

        return nextElementPosition;
    }

    void calculateEnergy() {

        boolean isAdjacentElement = false;
        boolean isSequenceNeighbour = false;
        for (int i = 0; i < conformation.size(); ++i) {
            if (conformation.get(i).isHydrophobic()) {
                int x = conformation.get(i).getPosition().x;
                int y = conformation.get(i).getPosition().y;
                int index = conformation.get(i).getIndex();

                // Check right
                if (isAdjacentElement(x + 1, y) &&
                        !isSequenceNeighbour(x + 1, y, index)) {
                    energy++;
                }
                // Check left
                if (isAdjacentElement(x - 1, y) &&
                        !isSequenceNeighbour(x - 1, y, index)) {
                    energy++;
                }

                // Check bottom
                if (isAdjacentElement(x, y - 1) &&
                        !isSequenceNeighbour(x, y - 1, index)) {
                    energy++;
                }

                // Check top
                if (isAdjacentElement(x, y + 1) &&
                        !isSequenceNeighbour(x, y + 1, index)) {
                    energy++;
                }
            }
        }

        // All neighbors are counted twice
        energy = energy / 2;
        System.out.println("energy " + energy);
        //return energy;
    }

    /*void mutate() {
        Random random = new Random();
        int element_index = random.nextInt(conformation.size());
        Element.Direction direction = conformation.get(element_index).getDirection();
        int randomDirection = random.nextInt(3);
        while (direction == Element.Direction.values()[randomDirection]) {
            randomDirection = random.nextInt(3);
        }
        conformation.get(element_index).setDirection(Element.Direction.values()[randomDirection]);
    }*/
    void mutate() {
        Random random = new Random();
        for(int i = 0; i < conformation.size(); ++i) {
            double ele_i = random.nextDouble();
            if (ele_i < 0.5) {

                Element.Direction direction = conformation.get(i).getDirection();
                int randomDirection = random.nextInt(3);
                while (direction == Element.Direction.values()[randomDirection]) {
                    randomDirection = random.nextInt(3);
                }
                conformation.get(i).setDirection(Element.Direction.values()[randomDirection]);
            }
        }
    }

    void crossover(Conformation conformation2) {
        Random random = new Random();
        int element_start = random.nextInt(conformation.size());
        for (int i = element_start; i < conformation.size(); ++i) {
            // swap direction
            Element.Direction temp = conformation.get(element_start).getDirection();
            conformation.get(element_start).setDirection(conformation2.conformation.get(element_start).getDirection());
            conformation2.conformation.get(element_start).setDirection(temp);
        }
    }

    int getEnergy() {
        return energy;
    }

    int getOverlaps() {
        return overlaps;
    }

    float getFitness(){
      return
              energy / overlaps + 1;
    }

    boolean isAdjacentElement(int x, int y) {
        if (matrix.get(y).get(x) == null) {
            return false;
        } else {
            if (matrix.get(y).get(x).isHydrophobic()) {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean isSequenceNeighbour(int x, int y, int index) {
        if (matrix.get(y).get(x).getIndex() == index - 1 || matrix.get(y).get(x).getIndex() == index + 1) {
            return true;
        } else {
            return false;
        }
    }

    public Conformation RenewConformation(Conformation conformation) {
        Element.ScreenDirection screenDirection1 = null;
        Element.ScreenDirection screenDirection2 = null;
        for (int i = 0; i < conformation.conformation.size(); i++) {
            Element element = conformation.conformation.get(i);
            if (i == 0) {
                screenDirection1 = Element.ScreenDirection.values()[0];
                element.setScreenDirection(screenDirection1);
            } else {
                screenDirection2 = calculateScreenDirection(screenDirection1,
                        element.getDirection());
                element.setScreenDirection(screenDirection2);
                //   System.out.println("screendirection1 " + screenDirection1 + " " + screenDirection2);
                screenDirection1 = screenDirection2;
            }
        }
        matrix = createConformationMatrix();
        calculateEnergy();
        return conformation;
    }
}
