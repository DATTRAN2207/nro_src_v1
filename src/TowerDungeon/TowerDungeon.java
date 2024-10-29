package TowerDungeon;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import assembly.Player;
import server.Service;

public class TowerDungeon {
    private Map<Integer, TowerFloor> floors = new HashMap<>();
    private Map<String, PlayerTowerProgress> playerProgress = new HashMap<>();

    public TowerDungeon() {
        initFloors();
    }

    private void initFloors() {
        for (int i = 1; i <= 50; i++) {
            floors.put(i, new TowerFloor(i));
        }
    }

    public TowerFloor getFloor(int floorNumber) {
        return floors.get(floorNumber);
    }

    public PlayerTowerProgress getPlayerProgress(String playerName) {
        return playerProgress.get(playerName);
    }

    public void updatePlayerProgress(String playerName, int floorReached) {
        PlayerTowerProgress progress = playerProgress.get(playerName);
        if (progress == null || progress.getHighestFloor() < floorReached) {
            playerProgress.put(playerName, new PlayerTowerProgress(floorReached));
        }
    }

    public static void MenuTowerDungeon(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        if (p.c.isNhanban) {
            Service.chatNPC(p, (short) 44, "Chức năng này không dành cho phân thân");
            return;
        }

        switch (menuId) {
            case 0:
                if (p.c.get().level < 100) {
                    Service.chatNPC(p, (short) npcid, "Yêu cầu trình độ cấp 100 mới có thể tham gia.");
                    return;
                }

                // Open Tower Dungeon
            case 1:
                if (p.c.get().level < 100) {
                    Service.chatNPC(p, (short) npcid, "Yêu cầu trình độ cấp 100 mới có thể tham gia.");
                    return;
                }

                // Open Tower Dungeon
        }
    }
}