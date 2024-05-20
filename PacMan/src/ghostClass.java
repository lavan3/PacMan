import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;

public class ghostClass {
	// Declare field variables
	private ImageIcon redg, yellowg, pinkg, blueg,pinkgL,yellowgL, bluegL ;
	private int xPos,yPos;
	private boolean left,right,up,down;
	private Rectangle2D mask;

	// Constructor to initialize variables 
	public ghostClass() {
		redg= new ImageIcon ("redg.gif");
		pinkg= new ImageIcon ("pinkg.gif");
		yellowg= new ImageIcon ("yellowg.gif");
		blueg= new ImageIcon ("blueg.gif");
		pinkgL= new ImageIcon ("pinkg left.gif");
		yellowgL= new ImageIcon ("yellowg left.gif");
		bluegL= new ImageIcon ("blueg left.gif");

	}
	// Loads image based on the number passed in
	public ImageIcon getImage(int num)
	{
		if (num==0)
		{
			return redg;
		}
		else if (num==1)
		{
			return pinkg;
		}
		else if (num==2)
		{
			return yellowg;
		}
		else if (num==3)
		{
			return blueg;
		}
		else if (num==4)
		{
			return pinkgL;
		}
		else if (num==5)
		{
			return yellowgL;
		}
		else if (num==6)
		{
			return bluegL;
		}
		// Return red Ghost Image by default
		return redg;
	}
	// Setting xPos
	public void setX(int x)
	{
		xPos=x;
	}
	// Setting yPos
	public void setY(int y)
	{
		yPos=y;
	}
	// Returning xPos
	public int getX()
	{
		return xPos;
	}
	// Returning yPos
	public int getY()
	{
		return yPos;
	}
	// Setting the mask based on X and Y position passed in 
	public void setMask(int x, int y, int num)
	{
		xPos=x;
		yPos=y;
		mask=new Rectangle2D.Double(xPos, yPos, getImage(num).getIconWidth(), getImage(num).getIconHeight());
	}
	// Returning mask
	public Rectangle2D getMask()
	{
		return mask;
	}
	// Method to move left
	public void moveLeft(boolean move)
	{
		if (move==true)
		{
			xPos-=10;
		}
	}
	// Method to move right
	public void moveRight(boolean move)
	{
		if (move==true)
		{
			xPos+=10;
		}
	}
	// Method to move down
	public void moveDown(boolean move)
	{
		if (move==true)
		{
			yPos+=10;
		}
	}
	// Method to move up
	public void moveUp(boolean move)
	{
		if (move==true)
		{
			yPos-=10;
		}
	}
}
