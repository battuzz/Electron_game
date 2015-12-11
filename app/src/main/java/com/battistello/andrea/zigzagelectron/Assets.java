package com.battistello.andrea.zigzagelectron;

import com.battistello.andrea.framework.Music;
import com.battistello.andrea.framework.gl.Texture;
import com.battistello.andrea.framework.gl.TextureRegion;
import com.battistello.andrea.framework.impl.GLGame;

/**
 * Created by Andrea on 05/12/15.
 */
public class Assets {
    public static Music music;
    public static Texture background;
    public static TextureRegion backgroundRegion;
    public static Texture items;
    public static TextureRegion mainMenu;

    public static Texture electron;
    public static TextureRegion electronRegion;
    public static Texture positiveField;
    public static TextureRegion positiveFieldLeftRegion;
    public static TextureRegion positiveFieldRightRegion;


    public static Texture negativeField;
    public static TextureRegion negativeFieldRightRegion;
    public static TextureRegion negativeFieldLeftRegion;


    public static void load(GLGame game) {
//        background = new Texture(game, "background.png");
//        backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
        background = new Texture(game, "background2.png");
        backgroundRegion = new TextureRegion(background, 0, 0, 512, 512);


        electron = new Texture(game, "electron.png");
        electronRegion = new TextureRegion(electron, 0, 0, 512, 512);


        positiveField = new Texture(game, "positive_field.png");
        positiveFieldLeftRegion = new TextureRegion(positiveField, 0, 0, 128, 128);
        positiveFieldRightRegion = new TextureRegion(positiveField, 128, 0, -128, 128);


        negativeField = new Texture(game, "negative_field.png");
        negativeFieldLeftRegion = new TextureRegion(negativeField, 0, 0, 128, 128);
        negativeFieldRightRegion = new TextureRegion(negativeField, 128, 0, -128, 128);


        items = new Texture(game, "items.png");
        mainMenu = new TextureRegion(items, 0, 224, 300, 110);

    }
    public static void reload() {
        background.reload();
        items.reload();
    }
}
