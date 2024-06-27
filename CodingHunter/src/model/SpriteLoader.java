package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteLoader {
	BufferedImage spriteSheet;   
	int width, height;
	int rows, columns;
	public int posX, posY;
	public BufferedImage[] sprites;
	public int aparencia;
	
	public SpriteLoader(String file, int aparencia, int columns, int rows, int posX, int posY) throws IOException {
		spriteSheet = ImageIO.read(getClass().getResource(file));
		this.aparencia=aparencia;

		
		this.width = spriteSheet.getWidth()/columns;
		this.height = spriteSheet.getHeight()/rows;

		this.rows = columns;
		this.columns = rows;
		this.posX=posX;
		this.posY=posY;

		sprites = new BufferedImage[columns * rows];
			for(int i = 0; i < columns; i++) {
			for(int j = 0; j < rows; j++) {
				sprites[(i * rows) + j] = spriteSheet.getSubimage(i * width, j * height, width, height);
			}
		}
	}
}