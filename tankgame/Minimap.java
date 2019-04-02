package tankgame;

public class Minimap {

    private Tank tank;

    public Minimap(Tank tank){
        this.tank = tank;
    }

    public int getX(){
        if (tank.getX() > TRE.WORLD_WIDTH/2){
            return TRE.WORLD_WIDTH/2;
        }
        return 0;
    }

    public int getY(){
        if (tank.getY() > TRE.WORLD_HEIGHT / 2){
            return TRE.WORLD_HEIGHT/2;
        }
        return 0;
    }

}
