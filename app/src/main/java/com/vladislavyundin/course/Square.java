package com.vladislavyundin.course;

/**
 * Created by vladislavyundin on 01.03.17.
 */
public class Square {
    private Player player = null;

    public void fill(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
