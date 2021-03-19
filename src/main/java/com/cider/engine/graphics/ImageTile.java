package com.cider.engine.graphics;

public class ImageTile extends Image{

    private int TileWidth, TileHeight;

    public ImageTile(String path, int TileWidth, int TileHeight) {
        super(path);
        this.TileHeight = TileHeight;
        this.TileWidth = TileWidth;
    }

    public int getTileWidth() {
        return TileWidth;
    }

    public void setTileWidth(int tileWidth) {
        TileWidth = tileWidth;
    }

    public int getTileHeight() {
        return TileHeight;
    }

    public void setTileHeight(int tileHeight) {
        TileHeight = tileHeight;
    }
}
