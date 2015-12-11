package com.battistello.andrea.zigzagelectron;

import com.battistello.andrea.framework.Game;
import com.battistello.andrea.framework.Input;
import com.battistello.andrea.framework.gl.Camera2D;
import com.battistello.andrea.framework.gl.SpriteBatcher;
import com.battistello.andrea.framework.impl.GLScreen;
import com.battistello.andrea.framework.math.Vector2;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Andrea on 05/12/15.
 */
public class GameScreen extends GLScreen {
    private final Camera2D guiCam;
    private final World.WorldListener worldListener;
    private final World world;
    private final Vector2 touchPoint = new Vector2();
    private final WorldRenderer renderer;

    private final SpriteBatcher batcher;

    private boolean running = true;


    public GameScreen(Game game) {
        super(game);
        guiCam = new Camera2D(glGraphics, 320, 480);


        this.worldListener = new World.WorldListener() {

            @Override
            public void gamePaused() {

            }

            @Override
            public void gameEnded() {
                running = false;
            }
        };

        this.world = new World(worldListener);

        batcher = new SpriteBatcher(glGraphics, 100);
        renderer = new WorldRenderer(glGraphics, batcher, world);

    }
    @Override
    public void update(float deltaTime) {

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();

        for (int i = 0; i < len; i++) {
            Input.TouchEvent e = touchEvents.get(i);

            if (e.type == Input.TouchEvent.TOUCH_UP) {
                world.invertPolarity();
            }
        }
        if (running) {
            world.update(deltaTime);
        }
        else {
            if (len > 0) {
                world.restart();
                running = true;
            }
        }


    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        if (running)
            renderer.render();

        else {
            batcher.beginBatch(Assets.electron);
            batcher.drawSprite(160, 240, 320, 320, Assets.electronRegion);
            batcher.endBatch();
        }

        /*batcher.beginBatch(Assets.background);
        batcher.drawSprite(160, 240, 320, 480, Assets.backgroundRegion);
        batcher.endBatch();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        batcher.beginBatch(Assets.background);
        batcher.drawSprite(0, 0, 160, 160, Assets.backgroundRegion);
        batcher.endBatch();

        batcher.beginBatch(Assets.electron);
        batcher.drawSprite(80, 80, 160, 160, Assets.electronRegion);
        batcher.endBatch();

        batcher.beginBatch(Assets.positiveField);
        batcher.drawSprite(240, 80, 128, 128, Assets.positiveFieldLeftRegion);
        batcher.drawSprite(80, 240, 128, 128, Assets.positiveFieldRightRegion);
        batcher.endBatch();

        batcher.beginBatch(Assets.negativeField);
        batcher.drawSprite(240, 240, 128, 128, Assets.negativeFieldLeftRegion);
        batcher.drawSprite(80, 400, 128, 128, Assets.negativeFieldRightRegion);
        batcher.endBatch();

        gl.glDisable(GL10.GL_BLEND);*/
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
