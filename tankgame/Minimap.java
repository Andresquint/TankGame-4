package tankgame;

public class Minimap {

    private Tank tank;

    public Minimap(Tank tank){
        this.tank = tank;
    }

    public int getY(){
        if (tank.getY() > TRE.WORLD_HEIGHT / 2){
            return TRE.WORLD_HEIGHT/2;
        }
        return 0;
    }

}
