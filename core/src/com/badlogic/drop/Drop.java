package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Drop extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	private GameScreen gameScreen;
	private GameOverMenu gameOverMenu;

	public void create() {
		batch = new SpriteBatch();
		//Use LibGDX's default Arial font.
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render(); //important!
	}

	public void setGameScreen(GameScreen gs)
	{
		this.gameScreen = gs;
	}

	public void setGameOverMenu(GameOverMenu gom)
	{
		this.gameOverMenu = gom;
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
		if (gameScreen != null)
		{
			gameScreen.dispose();
		}
		if (gameOverMenu != null)
		{
			gameOverMenu.dispose();
		}
	}

}