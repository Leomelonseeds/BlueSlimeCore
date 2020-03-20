package com.SirBlobman.api.nms;

import com.SirBlobman.api.nms.boss.bar.BossBarHandler;

public class NMS_1_8_R3 extends NMS_Handler {
    private final PlayerHandler playerHandler = new PlayerHandler_1_8_R3();
    private final EntityHandler entityHandler = new EntityHandler_1_8_R3();
    private final BossBarHandler bossBarHandler = new BossBarHandler_1_8_R3();
    private final ScoreboardHandler scoreboardHandler = new ScoreboardHandler_1_8_R3();
    
    @Override
    public BossBarHandler getBossBarHandler() {
        return this.bossBarHandler;
    }
    
    @Override
    public PlayerHandler getPlayerHandler() {
        return this.playerHandler;
    }
    
    @Override
    public EntityHandler getEntityHandler() {
        return this.entityHandler;
    }
    
    @Override
    public ScoreboardHandler getScoreboardHandler() {
        return this.scoreboardHandler;
    }
}