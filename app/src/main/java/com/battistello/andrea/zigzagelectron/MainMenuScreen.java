package com.battistello.andrea.zigzagelectron;

import com.battistello.andrea.framework.Game;
import com.battistello.andrea.framework.Input;
import com.battistello.andrea.framework.Input.TouchEvent;
import com.battistello.andrea.framework.gl.Camera2D;
import com.battistello.andrea.framework.gl.SpriteBatcher;
import com.battistello.andrea.framework.impl.GLGame;
import com.battistello.andrea.framework.impl.GLScreen;
import com.battistello.andrea.framework.math.OverlapTester;
import com.battistello.andrea.framework.math.Rectangle;
import com.battistello.andrea.framework.math.Vector2;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Andrea on 05/12/15.
 */
public class MainMenuScreen extends GLScreen{
    private final Rectangle playBounds;
    private final SpriteBatcher spriteBatcher;
    private final Vector2 touchPoint;
    Camera2D guiCam;


    public MainMenuScreen(Game game) {
        super(game);

        guiCam = new Camera2D(glGraphics, 320, 480);
        spriteBatcher = new SpriteBatcher(glGraphics, 100);

        playBounds = new Rectangle(160 - 150, 200 + 18, 300, 36);

        touchPoint = new Vector2();

    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();

        for (int i = 0; i < len; i++) {
            TouchEvent e = touchEvents.get(i);

            if (e.type == TouchEvent.TOUCH_UP) {
                touchPoint.set(e.x, e.y);
                guiCam.touchToWorld(touchPoint);

                /* Check if the user pressed the PLAY button */
                if (OverlapTester.pointInRectangle(playBounds, touchPoint)) {
                    game.setScreen(new GameScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        guiCam.setViewportAndMatrices();

        gl.glEnable(GL10.GL_TEXTURE_2D);

        spriteBatcher.beginBatch(Assets.background);
        spriteBatcher.drawSprite(160, 240, 320, 480, Assets.backgroundRegion);
        spriteBatcher.endBatch();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        spriteBatcher.beginBatch(Assets.items);

        spriteBatcher.drawSprite(160, 200, 300, 110, Assets.mainMenu);

        spriteBatcher.endBatch();

        gl.glDisable(GL10.GL_BLEND);

    }

    @Override
    public void pause() {
        Settings.save(game.getFileIO());
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
