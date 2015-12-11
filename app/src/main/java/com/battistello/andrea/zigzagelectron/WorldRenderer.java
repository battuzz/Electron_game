package com.battistello.andrea.zigzagelectron;

import android.util.Log;

import com.battistello.andrea.framework.gl.SpriteBatcher;
import com.battistello.andrea.framework.impl.GLGraphics;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Andrea on 08/12/15.
 */
public class WorldRenderer {
    private final World world;
    private final SpriteBatcher batcher;
    private final GLGraphics glGraphics;

    public WorldRenderer(GLGraphics glGraphics, SpriteBatcher batcher, World world) {
        this.glGraphics = glGraphics;
        this.batcher = batcher;
        this.world = world;
    }

    public void render() {
        GL10 gl = glGraphics.getGL();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        renderBackground();
        renderMainElectron();

        gl.glDisable(GL10.GL_BLEND);
    }

    private void renderBackground() {
        batcher.beginBatch(Assets.background);
        batcher.drawSprite(160, 240, 320, 480, Assets.backgroundRegion);
        batcher.endBatch();

        batcher.beginBatch(Assets.positiveField);
        if (world.E.x > 0) {
            batcher.drawSprite(64, 240, 128, 480, Assets.positiveFieldLeftRegion);
        }
        else {
            batcher.drawSprite(World.WORLD_WIDTH-64, 240, 128, 480, Assets.positiveFieldRightRegion);
        }
        batcher.endBatch();

        batcher.beginBatch(Assets.negativeField);
        if (world.E.x < 0) {
            batcher.drawSprite(64, 240, 128, 480, Assets.negativeFieldLeftRegion);
        }
        else {
            batcher.drawSprite(World.WORLD_WIDTH-64, 240, 128, 480, Assets.negativeFieldRightRegion);
        }
        batcher.endBatch();


    }

    private void renderMainElectron() {
        batcher.beginBatch(Assets.electron);
        Particle main = world.getMainElectron();
        batcher.drawSprite(main.position.x, main.position.y, main.radius, main.radius, Assets.electronRegion);
        batcher.endBatch();
    }
}
