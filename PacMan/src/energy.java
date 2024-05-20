import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;

public class energy {
	// Declare field variables 
	private ImageIcon energyimg;
	private int xPos, yPos;
	private Rectangle2D mask;

	// Constructor to initialize variables 
	public energy() {
		energyimg= new ImageIcon("power.png");
		mask=new Rectangle2D.Double(xPos,yPos,energyimg.getIconWidth(),energyimg.getIconHeight());
	}
	// Return Image
	public ImageIcon getimg()
	{
		return energyimg;
	}
	// Set the X position
	public void setX(int x)
	{
		xPos=x;
	}
	// Set the Y position
	public void setY(int y)
	{
		yPos=y;
	}
	// Get the X position
	public int getX()
	{
		return xPos;
	}
	// Get the Y position
	public int getY()
	{
		return yPos;
	}
	// Return mask of energy
	public Rectangle2D getMask()
	{
		return mask;
	}
	// Set mask of energy
	public void setMask(int x, int y)
	{
		mask=new Rectangle2D.Double(x,y,energyimg.getIconWidth(),energyimg.getIconHeight());
	}
}

