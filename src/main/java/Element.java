import java.util.Random;
import java.util.SplittableRandom;

public class Element {
    public char hydrophobic;
    public int index;
    public Direction direction;
    public Vector2 position = new Vector2(0,0);
    public ScreenDirection screenDirection;

    public Element(char hydrophobic, int index) {
        this.hydrophobic = hydrophobic;
        this.index = index;
        if(index == 0){
            direction = Direction.values()[2];
        } else {
        Random random = new Random();
        int randomDirection = random.nextInt(3);
        direction = Direction.values()[randomDirection];}
       // System.out.println("Index = " + index + " direction = " + s_Direction.name());
    }

    public enum Direction{
        Left,
        Right,
        Straight,
        None
    }
    enum ScreenDirection {
        North,
        East,
        South,
        West
    }
    int Offset = 40;
    float Size = 20;

    char getHydrophobic() {
        return hydrophobic;
    }
    boolean isHydrophobic(){
        if(hydrophobic == '1')
            return true;
        else
            return false;
    }
    int getIndex() {
        return index;
    }
    void setIndex(int index){this.index = index;}
    void setHydrophobic(char hydrophobic){this.hydrophobic = hydrophobic; }
    Direction getDirection(){
        return direction;
    }
    void setDirection(Direction direction) {
        this.direction = direction;
    }
    ScreenDirection getScreenDirection(){
        return screenDirection;
    }
    void setScreenDirection(ScreenDirection screenDirection) {
        this.screenDirection = screenDirection;
    }
    //Vector2 getCoordinates(){}
    void setPosition(Vector2 position){
        this.position.x = position.x;
        this.position.y = position.y;
    }
    Vector2 getPosition(){
        return position;
    }
}
