package com.galvanize.entities;


import com.google.common.base.Enums;

public enum Rank {
    ENSIGN, LIEUTENANT, COMMANDER, CAPTAIN, COMMODORE, ADMIRAL;

    public static Rank getIfPresent(String name) {
        return Enums.getIfPresent(Rank.class, name).orNull();
    }
}
