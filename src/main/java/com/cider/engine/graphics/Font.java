package com.cider.engine.graphics;

public class Font {
    public static final Font STANDARD = new Font("fonts/standard.png");

    private Image imageFont;
    private int[] offsets;
    private int[] width;

    public Font(String path) {
        imageFont = new Image(path);

        offsets = new int[59];
        width = new int[59];

        int unicode = 0;

        for (int i = 0; i < imageFont.getWidth(); i++) {
            if (imageFont.getPixel()[i] == 0xff0000ff) {
                offsets[unicode] = i;
            }

            if (imageFont.getPixel()[i] == 0xffffff00) {
                width[unicode] = i - offsets[unicode];
                unicode++;
            }
        }
    }

    public Image getImageFont() {
        return imageFont;
    }

    public void setImageFont(Image imageFont) {
        this.imageFont = imageFont;
    }

    public int[] getOffsets() {
        return offsets;
    }

    public void setOffsets(int[] offsets) {
        this.offsets = offsets;
    }

    public int[] getWidth() {
        return width;
    }

    public void setWidth(int[] width) {
        this.width = width;
    }
}
