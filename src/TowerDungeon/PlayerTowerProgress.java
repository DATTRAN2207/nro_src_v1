package TowerDungeon;

public class PlayerTowerProgress {
    private int highestFloor;

    public PlayerTowerProgress(int highestFloor) {
        this.highestFloor = highestFloor;
    }

    public int getHighestFloor() {
        return highestFloor;
    }

    public void setHighestFloor(int highestFloor) {
        this.highestFloor = highestFloor;
    }
}