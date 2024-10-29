package TowerDungeon;

import java.util.HashMap;

public class TowerDungeonManager {
    private static HashMap<Integer, Integer> playerFloors = new HashMap<>();

    public static int getPlayerFloor(int playerId) {
        return playerFloors.getOrDefault(playerId, 1);
    }

    public static void setPlayerFloor(int playerId, int floor) {
        playerFloors.put(playerId, floor);
    }

    public static void advancePlayerFloor(int playerId) {
        int currentFloor = getPlayerFloor(playerId);
        setPlayerFloor(playerId, currentFloor + 1);
    }
}