package com.mygdx.game_1_4_05;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * TODO: Start here
 *
 * In this exercise, you'll create an OrthographicCamera, and use it to zoom in on a moving circle
 */
public class MyGdxGame extends ApplicationAdapter {

	private static final float BALL_RADIUS = 20;
	private static final float PERIOD = 2000;
	private static final float X_AMPLITUDE = 40;
	private static final float Y_AMPLITUDE = 20;
	private static final float X_CENTER = 100;
	private static final float Y_CENTER = 100;

	ShapeRenderer renderer;
	long timeCreated;

	//TODO: Declare an OrthographicCamera
	private OrthographicCamera camera;

	@Override
	public void create() {
		renderer = new ShapeRenderer();
		timeCreated = TimeUtils.millis();

		// TODO: Initialize the camera
		camera = new OrthographicCamera();

		// TODO: Set the camera's position to the center of the circle's movement (X_CENTER, Y_CENTER)
		camera.translate(X_CENTER, Y_CENTER, 0);
	}

	@Override
	public void dispose() {
		renderer.dispose();
	}

	@Override
	public void resize(int width, int height) {

		// TODO: Calculate the aspect ratio (width / height)
		float aspect_ratio = 1.0f * width / height;

		// TODO: Set the camera's viewport height taking into account the ball's movement and radius
		camera.viewportHeight = BALL_RADIUS*2 + 2*Y_AMPLITUDE;

		// TODO: Set the camera's viewport width to maintain the aspect ratio
		camera.viewportWidth = aspect_ratio * camera.viewportHeight;
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// TODO: Call update() on the camera
		camera.update();

		// TODO: Set the SceneRenderer's projection matrix equal to the camera's combined matrix
		// renderer.setProjectionMatrix(camera.combined);

		renderer.begin(ShapeRenderer.ShapeType.Filled);
		float interval = TimeUtils.timeSinceMillis(timeCreated);
		float x = X_CENTER + X_AMPLITUDE * MathUtils.sin(MathUtils.PI2 * interval /PERIOD);
		float y = Y_CENTER + Y_AMPLITUDE * MathUtils.sin(2* MathUtils.PI2 * interval / PERIOD);
		renderer.circle(x, y, BALL_RADIUS);
		renderer.end();
	}
}