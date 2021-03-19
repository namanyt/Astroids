package com.cider.engine.graphics;

import com.cider.engine.manager.Engine;

import java.awt.image.DataBufferInt;

public class Renderer {

    private int pixelWidth, pixelHeight;
    private int[] pixel;

    private Font font = Font.STANDARD;

    public Renderer(Engine engine) {
        pixelWidth = engine.getScreenWidth();
        pixelHeight = engine.getScreenHeight();
        pixel = ((DataBufferInt)(engine.getWindow().getBufferedImage().getRaster().getDataBuffer())).getData();
    }

    public void clear() {
        for (int i = 0; i < pixel.length; i++) {
            pixel[i] = 0;
        }
    }

    public void setPixel(int x, int y, int value) {
        if ((x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) || value == 0xffff00ff) {
            return;
        }

        pixel[x + y * pixelWidth] = value;
    }

    public void drawText(String text, int offX, int offY, int color) {
        text = text.toUpperCase();
        int offset = 0;

        for (int i = 0; i < text.length(); i++) {
            int unicode = text.codePointAt(i) - 32;

            for (int y = 0; y < font.getImageFont().getHeight(); y++) {
                for(int x = 0; x < font.getWidth()[unicode]; x++) {
                    if(font.getImageFont().getPixel()[(x + font.getOffsets()[unicode]) + y * font.getImageFont().getWidth()] == 0xffffffff) {
                        setPixel(x + offX + offset, y + offY, color);
                    }
                }
            }

            offset += font.getWidth()[unicode];
        }
    }

    public void drawImage(Image image, int offX, int offY) {
        int newX = 0;
        int newY = 0;
        int newWidth = image.getWidth();
        int newHeight= image.getHeight();

        // Render checks BRO
        if (offX < -newWidth){
            return;
        }
        if (offY < -newHeight) {
            return;
        }
        if (offX >= pixelWidth) {
            return;
        }
        if (offY >= pixelHeight) {
            return;
        }

        // CLipping check BRO
        if (offX < 0) {
            newX -= offX;
        }
        if (offY < 0) {
            newY -= offY;
        }
        if (newWidth + offX >= pixelWidth) {
            newWidth -= (newWidth + offX - pixelWidth);
        }
        if (newHeight + offY >= pixelHeight) {
            newHeight -= (newHeight + offY - pixelHeight);
        }

        for (int y = newY; y < newHeight; y++) {
            for (int x = newX; x < newWidth; x++){
                setPixel(x + offX, y + offY, image.getPixel()[x + y * image.getWidth()]);
            }
        }
    }

    public void drawImageTile(ImageTile image, int offX, int offY, int TileX, int TileY) {
        int newX = 0;
        int newY = 0;
        int newWidth = image.getTileWidth();
        int newHeight= image.getTileHeight();

        // Render checks BRO
        if (offX < -image.getTileWidth()){
            return;
        }
        if (offY < -image.getTileHeight()) {
            return;
        }
        if (offX >= pixelWidth) {
            return;
        }
        if (offY >= pixelHeight) {
            return;
        }

        // CLipping check BRO
        if (offX < 0) {
            newX -= offX;
        }
        if (offY < 0) {
            newY -= offY;
        }
        if (newWidth + offX >= pixelWidth) {
            newWidth -= (newWidth + offX - pixelWidth);
        }
        if (newHeight + offY >= pixelHeight) {
            newHeight -= (newHeight + offY - pixelHeight);
        }

        for (int y = newY; y < newHeight; y++) {
            for (int x = newX; x < newWidth; x++){
                setPixel(x + offX, y + offY, image.getPixel()[(x + TileX * image.getTileWidth()) + (y + TileY * image.getTileHeight()) * image.getWidth()]);
            }
        }
    }
}
