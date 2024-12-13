package com.ldts.crystalclash.model;

public class GemTile extends Tile {
    Color color;

    public GemTile(Position screenPosition, Position gridCoordinates, Color color) {
        super(screenPosition, gridCoordinates, color);
        this.symbol = "◼";
    }
}
