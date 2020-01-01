package sci.gram.object;

public class Identifier {

    private int point_to;
    private boolean initiated;

    public Identifier(){
        this.initiated = false;
    }

    public void setPoint_to(int point_to) {
        this.point_to = point_to;
    }

    public void setInitiated(boolean initiated) {
        this.initiated = initiated;
    }

    public int getPoint_to() {
        return point_to;
    }

    public boolean isInitiated() {
        return initiated;
    }
}
