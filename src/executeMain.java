/* NAME: Lavanya Divakaran
 * DATE: May 19, 2024
 * DESCRIPTION: This is a console based game which involves the use of keyboard event. The objective of the game is to move Pacman around
 * while collecting the pellets and avoiding the ghosts. This is a quick mini game which lasts 1 minute. After the minute is over and Pacman
 * is still alive, the user wins displaying a winner message. If Pacman collides with the ghost, Pacman dies. The game is boundary checked,
 * so Pacman and the ghosts can only stay within the frame and in the maze. 
 */
//Import all necessary classes
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

//Extend JPanel and implement event handlers
public class executeMain extends JPanel implements ActionListener, KeyListener, MouseListener { 
	// Declare field variables 
	private String name, userTag, first2, last2, middle, swap;
	private boolean nameLen, initalScreen,pressed,left,right,up,down, downRed,leftRed, upRed, rightRed, downYellow,
	rightYellow, leftYellow, upYellow, downPink, upPink,rightPink,leftPink, downBlue, rightBlue,leftBlue,upBlue;
	private int x,y, strWidth, strWidth2, screenNum, pacmanX, pacmanY, gameMins, counter1,counter2,counter3,counter4,counter5,
	counter6,counter7,counter8,counter9,counter10,counter11, counter12, counter13, counter14, counter15, counter16, 
	counter17,counter18,counter19, score;
	private ImageIcon bckground, mainbg2, continueButton, continueButton2, gameScreenBG, pacman, pacmanL,pacmanU,pacmanD; 
	private Line2D l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16, l17,
	l18,l19,l20,l21,l22,l23,l24,l25,l26,l27,l28,l29,l30,l31,l32,l33,l34,l35,l36,l37,
	l38,l39,l40,l41,l42,l43,l44,l45,l46,l47,l48,l49,l50,l51,l52,l53,l54, l55,l56, l57,l58, l59,l60,l61;
	private Font f2; 
	private FontMetrics fm2;
	private Image resizeContinueButton,resizeMainScreenbg;
	private JFrame frame;
	private Rectangle2D pacmanMask;
	private Timer gameTime,pacmanTime, ghostTime;
	// Objects to access the other class 
	private energy[] energy,energy2, energy3,energy4,energy5,energy6,energy7,energy8,energy9,energy10,energy11,energy12
	,energy13,energy14,energy15,energy16,energy17,energy18,energy19;
	private ghostClass yellow,red, blue, pink;
	private sound sound;

	// Constructor to initialize variables 
	public executeMain() {
		screenNum=1;
		nameLen=true;
		pressed=false;
		initalScreen=true;

		// Position of green continue button
		x=230;
		y=540;

		// Initial Score
		score=0;

		// Load images
		bckground = new ImageIcon ("title.png");
		resizeMainScreenbg = bckground.getImage().getScaledInstance(820,800,Image.SCALE_SMOOTH);
		mainbg2 = new ImageIcon (resizeMainScreenbg);

		continueButton=new ImageIcon ("continueButton.png");
		resizeContinueButton= continueButton.getImage().getScaledInstance(400,170,Image.SCALE_SMOOTH);
		continueButton2 = new ImageIcon (resizeContinueButton);

		gameScreenBG=new ImageIcon ("maze.png");

		pacman=new ImageIcon ("pacmanImage.gif");
		pacmanL=new ImageIcon ("pacman Left.gif");
		pacmanU=new ImageIcon ("pacman Up.gif");
		pacmanD=new ImageIcon ("pacman Down .gif");

		
		// Load font that is going to be displayed
		try {
			f2 = Font.createFont(Font.TRUETYPE_FONT, new File("FZ BASIC 56 MANGLED EX Normal.ttf")).deriveFont(70f);
		} catch (IOException| FontFormatException e) {}

		// Initial position of Pacman
		pacmanX=9;
		pacmanY=7;

		// Mask of Pacman
		pacmanMask=new Rectangle2D.Double(pacmanX,pacmanY,pacman.getIconWidth()+1,pacman.getIconHeight()+1);

		// Time for how long the game runs in seconds. When reaches 0, game ends
		gameMins=60;

		// Timer to run the game and control movement of ghosts and Pacman
		gameTime=new Timer(1000, this);
		pacmanTime=new Timer(50, this);
		ghostTime=new Timer(50, this);

		// Energy pellets placed across the map
		counter1=56;
		energy=new energy[20];
		for (int i = 0; i < energy.length; i++)
		{
			energy[i] = new energy();
			energy[i].setX(counter1+30);
			energy[i].setY(29);
			energy[i].setMask(energy[i].getX(), energy[i].getY());
			counter1=energy[i].getX();
		}
		counter2=72;
		energy2=new energy[20];
		for (int i = 0; i < energy2.length; i++)
		{
			energy2[i] = new energy();
			energy2[i].setX(27);
			energy2[i].setY(counter2+30);
			energy2[i].setMask(energy2[i].getX(), energy2[i].getY());
			counter2=energy2[i].getY();
		}
		counter3=28;
		energy3=new energy[20];
		for (int i = 0; i < energy.length; i++)
		{
			energy3[i] = new energy();
			energy3[i].setX(counter3+30);
			energy3[i].setY(683);
			energy3[i].setMask(energy3[i].getX(), energy3[i].getY());
			counter3=energy3[i].getX();
		}
		counter4=38;
		energy4=new energy[21];
		for (int i = 0; i < energy4.length; i++)
		{
			energy4[i] = new energy();
			energy4[i].setX(672);
			energy4[i].setY(counter4+30);
			energy4[i].setMask(energy4[i].getX(), energy4[i].getY());
			counter4=energy4[i].getY();
		}
		counter5=35;
		energy5=new energy[18];
		for (int i = 0; i < energy5.length; i++)
		{
			energy5[i] = new energy();
			energy5[i].setX(110);
			energy5[i].setY(counter5+30);
			energy5[i].setMask(energy5[i].getX(), energy5[i].getY());
			counter5=energy5[i].getY();
		}
		counter6=97;
		energy6=new energy[10];
		for (int i = 0; i < energy6.length; i++)
		{
			energy6[i] = new energy();
			energy6[i].setX(236);
			energy6[i].setY(counter6+30);
			energy6[i].setMask(energy6[i].getX(), energy6[i].getY());
			counter6=energy6[i].getY();
		}
		counter7=180;
		energy7=new energy[9];
		for (int i = 0; i < energy7.length; i++)
		{
			energy7[i] = new energy();
			energy7[i].setX(counter7+30);
			energy7[i].setY(414);
			energy7[i].setMask(energy7[i].getX(), energy7[i].getY());
			counter7=energy7[i].getX();
		}
		counter8=395;
		energy8=new energy[8];
		for (int i = 0; i < energy8.length; i++)
		{
			energy8[i] = new energy();
			energy8[i].setX(counter8+30);
			energy8[i].setY(554);
			energy8[i].setMask(energy8[i].getX(), energy8[i].getY());
			counter8=energy8[i].getX();
		}
		counter9=344;
		energy9=new energy[5];
		for (int i = 0; i < energy9.length; i++)
		{
			energy9[i] = new energy();
			energy9[i].setX(counter9+30);
			energy9[i].setY(328);
			energy9[i].setMask(energy9[i].getX(), energy9[i].getY());
			counter9=energy9[i].getX();
		}
		counter10=100;
		energy10=new energy[3];
		for (int i = 0; i < energy10.length; i++)
		{
			energy10[i] = new energy();
			energy10[i].setX(counter10+30);
			energy10[i].setY(599);
			energy10[i].setMask(energy10[i].getX(), energy10[i].getY());
			counter10=energy10[i].getX();
		}
		counter11=401;
		energy11=new energy[8];
		for (int i = 0; i < energy11.length; i++)
		{
			energy11[i] = new energy();
			energy11[i].setX(276);
			energy11[i].setY(counter11+30);
			energy11[i].setMask(energy11[i].getX(), energy11[i].getY());
			counter11=energy11[i].getY();
		}
		counter12=103;
		energy12=new energy[4];
		for (int i = 0; i < energy12.length; i++)
		{
			energy12[i] = new energy();
			energy12[i].setX(counter12+30);
			energy12[i].setY(109);
			energy12[i].setMask(energy12[i].getX(), energy12[i].getY());
			counter12=energy12[i].getX();
		}
		counter13=238;
		energy13=new energy[13];
		for (int i = 0; i < energy13.length; i++)
		{
			energy13[i] = new energy();
			energy13[i].setX(counter13+30);
			energy13[i].setY(153);
			energy13[i].setMask(energy13[i].getX(), energy13[i].getY());
			counter13=energy13[i].getX();
		}
		counter14=401;
		energy14=new energy[6];
		for (int i = 0; i < energy14.length; i++)
		{
			energy14[i] = new energy();
			energy14[i].setX(191);
			energy14[i].setY(counter14+30);
			energy14[i].setMask(energy14[i].getX(), energy14[i].getY());
			counter14=energy11[i].getY();
		}
		counter15=148;
		energy15=new energy[5];
		for (int i = 0; i < energy15.length; i++)
		{
			energy15[i] = new energy();
			energy15[i].setX(365);
			energy15[i].setY(counter15+30);
			energy15[i].setMask(energy15[i].getX(), energy15[i].getY());
			counter15=energy15[i].getY();
		}
		counter16=338;
		energy16=new energy[6];
		for (int i = 0; i < energy16.length; i++)
		{
			energy16[i] = new energy();
			energy16[i].setX(490);
			energy16[i].setY(counter16+30);
			energy16[i].setMask(energy16[i].getX(), energy16[i].getY());
			counter16=energy16[i].getY();
		}
		counter17=542;
		energy17=new energy[4];
		for (int i = 0; i < energy17.length; i++)
		{
			energy17[i] = new energy();
			energy17[i].setX(404);
			energy17[i].setY(counter17+30);
			energy17[i].setMask(energy17[i].getX(), energy17[i].getY());
			counter17=energy17[i].getY();
		}
		counter18=164;
		energy18=new energy[12];
		for (int i = 0; i < energy18.length; i++)
		{
			energy18[i] = new energy();
			energy18[i].setX(588);
			energy18[i].setY(counter18+30);
			energy18[i].setMask(energy18[i].getX(), energy18[i].getY());
			counter18=energy18[i].getY();
		}
		counter19=321;
		energy19=new energy[2];
		for (int i = 0; i < energy19.length; i++)
		{
			energy19[i] = new energy();
			energy19[i].setX(counter19+227);
			energy19[i].setY(92);
			energy19[i].setMask(energy19[i].getX(), energy19[i].getY());
			counter19=energy19[i].getY();
		}

		// Set pacman's initial movement to false
		left=right=up=down=false;

		// Boundary check for the maze. Checks if Pacman or Ghost intersect
		l1=new Line2D.Double(53,56,85,56);
		l2=new Line2D.Double(51,56,51,663);
		l3=new Line2D.Double(88,55,88,632);
		l4=new Line2D.Double(54,699,254,699);
		l5=new Line2D.Double(89,625,218,625);
		l6=new Line2D.Double(135,53,294,53);
		l7=new Line2D.Double(136,90,261,90);
		l8=new Line2D.Double(262,92,262,128);
		l9=new Line2D.Double(296,62,296,128);
		l10=new Line2D.Double(136,143,136,578);
		l11=new Line2D.Double(141,140,211,140);
		l12=new Line2D.Double(213,141,213,390);
		l13=new Line2D.Double(174,392,210,392);
		l14=new Line2D.Double(171,393,171,578);
		l15=new Line2D.Double(144,582,166,582);
		l16=new Line2D.Double(353,54,518,54);
		l17=new Line2D.Double(346,58,346,128);
		l18=new Line2D.Double(350,134,516,134);
		l19=new Line2D.Double(522,59,522,131);
		l20=new Line2D.Double(576,53,643,53);
		l21=new Line2D.Double(571,57,571,129);
		l22=new Line2D.Double(576,133,642,133);
		l23=new Line2D.Double(647,59,647,130);
		l24=new Line2D.Double(613,187,613,531);
		l25=new Line2D.Double(649,189,649,532);
		l26=new Line2D.Double(619,184,644,184);
		l27=new Line2D.Double(616,535,645,535);
		l28=new Line2D.Double(395,183,559,183);
		l29=new Line2D.Double(389,190,389,300);
		l30=new Line2D.Double(392,307,513,307);
		l31=new Line2D.Double(514,305,514,530);
		l32=new Line2D.Double(518,538,562,538);
		l33=new Line2D.Double(564,185,564,534);
		l34=new Line2D.Double(265,182,337,182);
		l35=new Line2D.Double(262,186,262,390);
		l36=new Line2D.Double(266,393,463,393);
		l37=new Line2D.Double(466,359,466,389);
		l38=new Line2D.Double(341,355,461,355);
		l39=new Line2D.Double(339,185,339,357);
		l40=new Line2D.Double(309,441,463,441);
		l41=new Line2D.Double(304,442,304,662);
		l42=new Line2D.Double(306,666,377,666);
		l43=new Line2D.Double(381,536,381,662);
		l44=new Line2D.Double(381,536,463,536);
		l45=new Line2D.Double(465,446,465,533);
		l46=new Line2D.Double(435,585,644,585);
		l47=new Line2D.Double(430,590,430,664);
		l48=new Line2D.Double(434,667,645,667);
		l49=new Line2D.Double(647,590,647,663);
		l50=new Line2D.Double(220,445,220,630);
		l51=new Line2D.Double(224,442,252,442);
		l52=new Line2D.Double(255,446,255,665);
		l53=new Line2D.Double(136,55,136,80);
		l54=new Line2D.Double(264,134,294,134);
		l55=new Line2D.Double(92,93,116,93);
		l56=new Line2D.Double(472,440,510,440);
		l57=new Line2D.Double(217,395,217,439);
		l58=new Line2D.Double(218,137,263,137);
		l59=new Line2D.Double(386,136,386,180);
		l60=new Line2D.Double(609,537,609,582);
		l61=new Line2D.Double(341,146,341,181);

		// Create Objects for class we made
		red=new ghostClass();
		blue=new ghostClass();
		yellow=new ghostClass();
		pink=new ghostClass();
		
		sound= new sound();
		
		// Start sound
		sound.bgSound();

		// Set mask of ghosts
		red.setMask(red.getX(), red.getY(), 0);
		yellow.setMask(yellow.getX(),yellow.getY(), 2);
		pink.setMask(pink.getX(),pink.getY(), 1);
		blue.setMask(blue.getX(),blue.getY(), 3);

		// Set initial positions of ghosts
		red.setX(645);
		red.setY(9);
		blue.setX(469);
		blue.setY(396);
		yellow.setX(218);
		yellow.setY(143);
		pink.setX(348);
		pink.setY(149);

		// Set initial movement of the ghosts
		downRed=true;
		downYellow=true;
		downPink=true;
		downBlue=true;

		// Set the properties of the JPanel
		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);
		requestFocus();

		// Create JPanel to be displayed when game runs
		frame = new JFrame("PacMan");
		frame.setContentPane(this);
		frame.setSize(mainbg2.getIconWidth() , mainbg2.getIconHeight()); 
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	// Main program where code executes
	public static void main(String[] args) {
		new executeMain();
	}
	//Method to swap letters of input from user
	public String swapEnds(String word)
	{
		first2=word.substring(0,2);
		last2=word.substring(word.length()-2,word.length());
		middle=word.substring(2,word.length()-2);
		swap=last2+middle+first2;
		return swap;		
	}
	public void paint (Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g; 

		// Drawing components of first screen when program ran
		if (initalScreen)
		{
			g2.drawImage(mainbg2.getImage(), 0,0,this);		
			g2.drawImage(continueButton2.getImage(), x,y,this);
			
			fm2 = getFontMetrics(f2);
			strWidth2= fm2.stringWidth("Welcome");
			g2.setFont(f2);
			g2.setColor(new Color (171,219,227));
			g2.drawString("Welcome" , (getWidth() / 2 - strWidth2 / 2),115);

		}
		if (pressed)
		{
			// Displays the main game images and strings
			frame.setSize(gameScreenBG.getIconWidth(), gameScreenBG.getIconHeight()+27); 
			g2.drawImage(gameScreenBG.getImage(),0,0,this);
			g2.setColor(new Color (171,219,227));
			Font f = new Font("Chalkboard", Font.BOLD, 18);
			g2.setFont(f);
			g2.setColor(new Color (225,51,227));
			g2.drawString(userTag+" Score: "+score, 15, 25);
			g2.drawString("Time Left: "+gameMins, 567, 25);

			// Draw images of Pacman depending on way he turns
			if (left)
			{
				g2.drawImage(pacmanL.getImage(),pacmanX,pacmanY,this);
			}
			else if (up)
			{
				g2.drawImage(pacmanU.getImage(),pacmanX,pacmanY,this);
			}
			else if (down)
			{
				g2.drawImage(pacmanD.getImage(),pacmanX,pacmanY,this);
			}
			else
				g2.drawImage(pacman.getImage(),pacmanX,pacmanY,this);

			// Draw images of the ghosts. If ghost is moving left, display left image
			if (leftPink)
				g2.drawImage(pink.getImage(4).getImage(),pink.getX(),pink.getY(),this);	
			else
				g2.drawImage(pink.getImage(1).getImage(),pink.getX(),pink.getY(),this);

			if (leftYellow)
				g2.drawImage(yellow.getImage(5).getImage(),yellow.getX(),yellow.getY(),this);
			else
				g2.drawImage(yellow.getImage(2).getImage(),yellow.getX(),yellow.getY(),this);

			if (leftBlue)
				g2.drawImage(blue.getImage(6).getImage(),blue.getX(),blue.getY(),this);
			else
				g2.drawImage(blue.getImage(3).getImage(),blue.getX(),blue.getY(),this);
			g2.drawImage(red.getImage(0).getImage(),red.getX(),red.getY(),this);

			// Draw energy pellets across screen
			for (int i=0;i<energy.length;i++)
			{	
				g2.drawImage(energy[i].getimg().getImage(), energy[i].getX(),energy[i].getY(),this);	
			}
			for (int i=0;i<energy2.length;i++)
			{	
				g2.drawImage(energy2[i].getimg().getImage(), energy2[i].getX(),energy2[i].getY(),this);	
			}
			for (int i=0;i<energy3.length;i++)
			{	
				g2.drawImage(energy3[i].getimg().getImage(), energy3[i].getX(),energy3[i].getY(),this);	
			}
			for (int i=0;i<energy4.length;i++)
			{	
				g2.drawImage(energy4[i].getimg().getImage(), energy4[i].getX(),energy4[i].getY(),this);	
			}
			for (int i=0;i<energy5.length;i++)
			{	
				g2.drawImage(energy5[i].getimg().getImage(), energy5[i].getX(),energy5[i].getY(),this);	
			}
			for (int i=0;i<energy6.length;i++)
			{	
				g2.drawImage(energy6[i].getimg().getImage(), energy6[i].getX(),energy6[i].getY(),this);	
			}
			for (int i=0;i<energy7.length;i++)
			{	
				g2.drawImage(energy7[i].getimg().getImage(), energy7[i].getX(),energy7[i].getY(),this);	
			}
			for (int i=0;i<energy8.length;i++)
			{	
				g2.drawImage(energy8[i].getimg().getImage(), energy8[i].getX(),energy8[i].getY(),this);	
			}
			for (int i=0;i<energy9.length;i++)
			{	
				g2.drawImage(energy9[i].getimg().getImage(), energy9[i].getX(),energy9[i].getY(),this);	
			}
			for (int i=0;i<energy10.length;i++)
			{	
				g2.drawImage(energy10[i].getimg().getImage(), energy10[i].getX(),energy10[i].getY(),this);	
			}
			for (int i=0;i<energy11.length;i++)
			{	
				g2.drawImage(energy11[i].getimg().getImage(), energy11[i].getX(),energy11[i].getY(),this);	
			}
			for (int i=0;i<energy12.length;i++)
			{	
				g2.drawImage(energy12[i].getimg().getImage(), energy12[i].getX(),energy12[i].getY(),this);	
			}
			for (int i=0;i<energy13.length;i++)
			{	
				g2.drawImage(energy13[i].getimg().getImage(), energy13[i].getX(),energy13[i].getY(),this);	
			}
			for (int i=0;i<energy14.length;i++)
			{	
				g2.drawImage(energy14[i].getimg().getImage(), energy14[i].getX(),energy14[i].getY(),this);	
			}
			for (int i=0;i<energy15.length;i++)
			{	
				g2.drawImage(energy15[i].getimg().getImage(), energy15[i].getX(),energy15[i].getY(),this);	
			}
			for (int i=0;i<energy16.length;i++)
			{	
				g2.drawImage(energy16[i].getimg().getImage(), energy16[i].getX(),energy16[i].getY(),this);	
			}
			for (int i=0;i<energy17.length;i++)
			{	
				g2.drawImage(energy17[i].getimg().getImage(), energy17[i].getX(),energy17[i].getY(),this);	
			}
			for (int i=0;i<energy18.length;i++)
			{	
				g2.drawImage(energy18[i].getimg().getImage(), energy18[i].getX(),energy18[i].getY(),this);	
			}
			// Special pellet. Worth 35 points
			for (int i=0;i<energy19.length;i++)
			{	
				g2.drawImage(energy19[i].getimg().getImage(), energy19[i].getX(),energy19[i].getY(),this);	
				g2.draw(energy19[i].getMask());	
			}
		}
		repaint();
	}
	public void mouseClicked(MouseEvent e) {
		if (screenNum==1)
		{
			// Asks user for name making sure it is greater than 4 characters
			if (e.getX() >=  x && e.getX() <= x + continueButton2.getIconWidth() && e.getY() >= y && e.getY() <=  y+ continueButton2.getIconHeight())
			{
				while (nameLen)
				{
					name = JOptionPane.showInputDialog(null, "Enter your name:", "PacMan Elite", JOptionPane.QUESTION_MESSAGE);
					// Swaps user's name making it their gamer tag
					if (name.length()>3)
					{
						swap=swapEnds(name);
						userTag=swap.toUpperCase();
						JOptionPane.showMessageDialog(null, "Your Gamer Tag is "+ userTag, "PacMan Elite", JOptionPane.INFORMATION_MESSAGE);
						initalScreen=false;
						nameLen=false;   
						pressed=true;	

						// Start timers to begin game 
						sound.bgSoundStop();
						pacmanTime.start();
						gameTime.start();
						ghostTime.start();
					}
					else 
					{
						// Asks user to input name again if less than 4 characters
						JOptionPane.showMessageDialog(null, "Your name must have more than 4 characters!", "PacMan Elite", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}    
		}
	}
	// Pacman moves based on the key pressed by the user
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			left=true;
		}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			right=true;
		}
		else if (e.getKeyCode()==KeyEvent.VK_UP)
		{
			up=true;
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			down=true;
		}
	}
	// When key is not pressed, Pacman doesn't move
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			left=false;
		}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			right=false;
		}
		else if (e.getKeyCode()==KeyEvent.VK_UP)
		{
			up=false;
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			down=false;
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==gameTime)
		{
			// Decreases game minutes by 1 second each time
			gameMins--;
			// Displays user won if they last the full minute without colliding with the ghost
			if (gameMins==0)
			{
				pacmanTime.stop();
				gameTime.stop();
				ghostTime.stop();
				sound.collisionSoundStop();
				JOptionPane.showMessageDialog(null, "You Won! Great Work, you survived the ghost attack!\nYour Score was: "+score, "Game Over", JOptionPane.PLAIN_MESSAGE);	
			}
		}
		if (e.getSource()==pacmanTime)
		{
			// Controls the movement of Pacman
			if (gameMins>0)
			{
				// When user clicks right, ensures Pacman is within boundary. If not, Pacman cannot move
				if (right)
				{
					if (pacmanX+pacman.getIconWidth()>getWidth()-15|pacmanMask.intersectsLine(l2)|pacmanMask.intersectsLine(l10)|pacmanMask.intersectsLine(l53)
							|pacmanMask.intersectsLine(l50)|pacmanMask.intersectsLine(l35)|pacmanMask.intersectsLine(l41)|pacmanMask.intersectsLine(l29)
							|pacmanMask.intersectsLine(l17)|pacmanMask.intersectsLine(l47)|pacmanMask.intersectsLine(l21)|pacmanMask.intersectsLine(l24)
							|pacmanMask.intersectsLine(l8)|pacmanMask.intersectsLine(l31))
					{
						pacmanX-=0;
					}
					else
						pacmanX+=13;
				}
				// When user clicks left, ensures Pacman is within boundary. If not, Pacman cannot move
				if (left)
				{
					if (pacmanX<13|pacmanMask.intersectsLine(l3)|pacmanMask.intersectsLine(l14)|pacmanMask.intersectsLine(l52)|pacmanMask.intersectsLine(l12)
							|pacmanMask.intersectsLine(l9)|pacmanMask.intersectsLine(l39)|pacmanMask.intersectsLine(l43)|pacmanMask.intersectsLine(l45)|pacmanMask.intersectsLine(l37)
							|pacmanMask.intersectsLine(l33)|pacmanMask.intersectsLine(l49)|pacmanMask.intersectsLine(l25)|pacmanMask.intersectsLine(l23)|pacmanMask.intersectsLine(l19))
					{
						pacmanX+=0;
					}
					else
						pacmanX-=13;
				}
				// When user clicks up, ensures Pacman is within boundary. If not, Pacman cannot move
				if (up)
				{
					if (pacmanY<18|pacmanMask.intersectsLine(l4)|pacmanMask.intersectsLine(l15)|pacmanMask.intersectsLine(l7)|pacmanMask.intersectsLine(l54)
							|pacmanMask.intersectsLine(l44)|pacmanMask.intersectsLine(l36)|pacmanMask.intersectsLine(l30)|pacmanMask.intersectsLine(l32)
							|pacmanMask.intersectsLine(l48)|pacmanMask.intersectsLine(l27)|pacmanMask.intersectsLine(l22)|pacmanMask.intersectsLine(l18)|pacmanMask.intersectsLine(l42)
							|pacmanMask.intersectsLine(l13))
					{
						pacmanY+=0;
					}
					else
						pacmanY-=13;
				}
				// When user clicks down, ensures Pacman is within boundary. If not, Pacman cannot move
				if (down)
				{
					if (pacmanY+pacman.getIconHeight()>getHeight()-14||pacmanMask.intersectsLine(l1)|pacmanMask.intersectsLine(l5)|pacmanMask.intersectsLine(l11)
							|pacmanMask.intersectsLine(l6)|pacmanMask.intersectsLine(l34)|pacmanMask.intersectsLine(l38)|pacmanMask.intersectsLine(l40)|pacmanMask.intersectsLine(l28)|pacmanMask.intersectsLine(l16)
							|pacmanMask.intersectsLine(l20)|pacmanMask.intersectsLine(l46)|pacmanMask.intersectsLine(l26)|pacmanMask.intersectsLine(l51))
					{
						pacmanY+=0;
					}
					else
						pacmanY+=13;
				}
				// If Pacman intersects with the pellets, increase score and move image/mask off screen
				for (int i=0;i<energy.length;i++)
				{				
					if (pacmanMask.intersects(energy[i].getMask()))
					{
						score+=10;
						energy[i].setMask(-19, -20);
						energy[i].setX(-20);
						energy[i].setY(-10);
						sound.collisionSound();
					}

				}
				for (int i=0;i<energy2.length;i++)
				{				
					if (pacmanMask.intersects(energy2[i].getMask()))
					{
						score+=10;
						energy2[i].setMask(-19, -20);
						energy2[i].setX(-20);
						energy2[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy3.length;i++)
				{				
					if (pacmanMask.intersects(energy3[i].getMask()))
					{
						score+=10;
						energy3[i].setMask(-19, -20);
						energy3[i].setX(-20);
						energy3[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy4.length;i++)
				{				
					if (pacmanMask.intersects(energy4[i].getMask()))
					{
						score+=10;
						energy4[i].setMask(-19, -20);
						energy4[i].setX(-20);
						energy4[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy5.length;i++)
				{				
					if (pacmanMask.intersects(energy5[i].getMask()))
					{
						score+=10;
						energy5[i].setMask(-19, -20);
						energy5[i].setX(-20);
						energy5[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy6.length;i++)
				{				
					if (pacmanMask.intersects(energy6[i].getMask()))
					{
						score+=10;
						energy6[i].setMask(-19, -20);
						energy6[i].setX(-20);
						energy6[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy7.length;i++)
				{				
					if (pacmanMask.intersects(energy7[i].getMask()))
					{
						score+=10;
						energy7[i].setMask(-19, -20);
						energy7[i].setX(-20);
						energy7[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy8.length;i++)
				{				
					if (pacmanMask.intersects(energy8[i].getMask()))
					{
						score+=10;
						energy8[i].setMask(-19, -20);
						energy8[i].setX(-20);
						energy8[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy9.length;i++)
				{				
					if (pacmanMask.intersects(energy9[i].getMask()))
					{
						score+=10;
						energy9[i].setMask(-19, -20);
						energy9[i].setX(-20);
						energy9[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy10.length;i++)
				{				
					if (pacmanMask.intersects(energy10[i].getMask()))
					{
						score+=10;
						energy10[i].setMask(-19, -20);
						energy10[i].setX(-20);
						energy10[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy11.length;i++)
				{				
					if (pacmanMask.intersects(energy11[i].getMask()))
					{
						score+=10;
						energy11[i].setMask(-19, -20);
						energy11[i].setX(-20);
						energy11[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy12.length;i++)
				{				
					if (pacmanMask.intersects(energy12[i].getMask()))
					{
						score+=10;
						energy12[i].setMask(-19, -20);
						energy12[i].setX(-20);
						energy12[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy13.length;i++)
				{				
					if (pacmanMask.intersects(energy13[i].getMask()))
					{
						score+=10;
						energy13[i].setMask(-19, -20);
						energy13[i].setX(-20);
						energy13[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy14.length;i++)
				{				
					if (pacmanMask.intersects(energy14[i].getMask()))
					{
						score+=10;
						energy14[i].setMask(-19, -20);
						energy14[i].setX(-20);
						energy14[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy15.length;i++)
				{				
					if (pacmanMask.intersects(energy15[i].getMask()))
					{
						score+=10;
						energy15[i].setMask(-19, -20);
						energy15[i].setX(-20);
						energy15[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy16.length;i++)
				{				
					if (pacmanMask.intersects(energy16[i].getMask()))
					{
						score+=10;
						energy16[i].setMask(-19, -20);
						energy16[i].setX(-20);
						energy16[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy17.length;i++)
				{				
					if (pacmanMask.intersects(energy17[i].getMask()))
					{
						score+=10;
						energy17[i].setMask(-19, -20);
						energy17[i].setX(-20);
						energy17[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy18.length;i++)
				{				
					if (pacmanMask.intersects(energy18[i].getMask()))
					{
						score+=10;
						energy18[i].setMask(-19, -20);
						energy18[i].setX(-20);
						energy18[i].setY(-10);
						sound.collisionSound();
					}
				}
				for (int i=0;i<energy19.length;i++)
				{				
					if (pacmanMask.intersects(energy19[i].getMask()))
					{
						score+=35;
						energy19[i].setMask(-19, -20);
						energy19[i].setX(-20);
						energy19[i].setY(-10);
						sound.collisionSound();
					}
				}
			}
		}
		if (e.getSource()==ghostTime)
		{
			// Checks if Pacman and any one of the ghosts collide. If so, stop the game displaying user lost
			if (pacmanMask.intersects(red.getMask())||pacmanMask.intersects(yellow.getMask())||pacmanMask.intersects(pink.getMask())
					||pacmanMask.intersects(blue.getMask()))
			{
				pacmanTime.stop();
				gameTime.stop();
				ghostTime.stop();
				sound.collisionSoundStop();

				int option=JOptionPane.showConfirmDialog(null, "Sorry, you lost "+userTag+". The ghosts took over the maze.\nYour Score was: "+score+"\nDo you want to play again?", "Game Over",JOptionPane.YES_NO_OPTION);
				if (option==JOptionPane.YES_OPTION)
				{
					// User want to play again
					frame.dispose();
					new executeMain();
				}
				else if (option==JOptionPane.NO_OPTION)
				{
					// User doesn't want to play
					JOptionPane.showMessageDialog(null, "Hope you enjoyed the game!", "Game Over", JOptionPane.PLAIN_MESSAGE);
					System.exit(0);
				}
			}
			// Controls movement of Yellow ghost based on conditions met
			if (downYellow==true)
			{
				yellow.moveDown(true);
			}
			else if (rightYellow==true)
			{
				yellow.moveRight(true);
			}
			else if (leftYellow==true)
			{
				yellow.moveLeft(true);
			}
			else if (upYellow==true)
			{
				yellow.moveUp(true);
			}

			if (yellow.getMask().intersectsLine(l51)||yellow.getMask().intersectsLine(l5)&&!(yellow.getMask().intersectsLine(l3)))
			{
				// Left movement Yellow ghost
				downYellow=false;
				leftYellow=true;
				upYellow=false;
				rightYellow=false;
			}
			else if (yellow.getMask().intersectsLine(l14)||yellow.getMask().intersectsLine(l8))
			{
				// Down movement Yellow ghost
				downYellow=true;
				leftYellow=false;
				upYellow=false;
				rightYellow=false;
			}
			else if (yellow.getMask().intersectsLine(l55))
			{
				// Right movement Yellow ghost
				downYellow=false;
				leftYellow=false;
				upYellow=false;
				rightYellow=true;
			}
			else if (yellow.getMask().intersectsLine(l3)&&!(yellow.getMask().intersectsLine(l55)))
			{
				// Up movement Yellow ghost
				downYellow=false;
				leftYellow=false;
				upYellow=true;
				rightYellow=false;
			}

			// Controls movement of Pink ghost based on conditions met
			if (downPink==true)
			{
				pink.moveDown(true);
			}
			else if (rightPink==true)
			{
				pink.moveRight(true);
			}
			else if (leftPink==true)
			{
				pink.moveLeft(true);
			}
			else if (upPink==true)
			{
				pink.moveUp(true);
			}

			if (pink.getMask().intersectsLine(l56)||pink.getMask().intersectsLine(l5))
			{
				// Left movement Pink ghost
				downPink=false;
				leftPink=true;
				upPink=false;
				rightPink=false;
			}
			else if (pink.getMask().intersectsLine(l31)&&!(pink.getMask().intersectsLine(l56))||pink.getMask().intersectsLine(l5)||pink.getMask().intersectsLine(l59))
			{
				// Down movement Pink ghost
				downPink=true;
				leftPink=false;
				upPink=false;
				rightPink=false;
			}
			else if (pink.getMask().intersectsLine(l38)||pink.getMask().intersectsLine(l5)||pink.getMask().intersectsLine(l58))
			{
				// Right movement Pink ghost
				downPink=false;
				leftPink=false;
				upPink=false;
				rightPink=true;
			}
			else if (pink.getMask().intersectsLine(l57)||pink.getMask().intersectsLine(l5))
			{
				// Up movement Pink ghost
				downPink=false;
				leftPink=false;
				upPink=true;
				rightPink=false;
			}
			
			// Controls movement of Blue ghost based on conditions met
			if (downBlue==true)
			{
				blue.moveDown(true);
			}
			else if (rightBlue==true)
			{
				blue.moveRight(true);
			}
			else if (leftBlue==true)
			{
				blue.moveLeft(true);
			}
			else if (upBlue==true)
			{
				blue.moveUp(true);
			}

			if (blue.getMask().intersectsLine(l43)&&!(blue.getMask().intersectsLine(l43))||blue.getMask().intersectsLine(l61)||blue.getMask().intersectsLine(l31))
			{
				// Down movement Blue ghost
				downBlue=true;
				leftBlue=false;
				upBlue=false;
				rightBlue=false;
			}
			else if (blue.getMask().intersectsLine(l22))
			{
				// Left movement Blue ghost
				downBlue=false;
				leftBlue=true;
				upBlue=false;
				rightBlue=false;
			}
			else if (blue.getMask().intersectsLine(l60))
			{
				// Up movement Blue ghost
				downBlue=false;
				leftBlue=false;
				upBlue=true;
				rightBlue=false;
			}
			else if (blue.getMask().intersectsLine(l46)&&!(blue.getMask().intersectsLine(l60))||blue.getMask().intersectsLine(l38))
			{
				// Right movement Blue ghost
				downBlue=false;
				leftBlue=false;
				upBlue=false;
				rightBlue=true;
			}

			// Controls movement of Red ghost based on conditions met
			if(downRed==true)
			{
				red.moveDown(true);
			}
			else if (leftRed==true)
			{
				red.moveLeft(true);
			}
			else if (upRed==true)
			{
				red.moveUp(true);
			}
			else if (rightRed==true)
			{
				red.moveRight(true);
			}

			if (red.getY()+red.getImage(0).getIconHeight()>getHeight()&&!(red.getX()<0))
			{
				// Left movement Red ghost
				downRed=false;
				leftRed=true;
				upRed=false;
				rightRed=false;
			}
			else if (red.getX()<0&&!(red.getY()<0))
			{
				// Up movement Red ghost
				downRed=false;
				leftRed=false;
				upRed=true;
				rightRed=false;			
			}
			else if (red.getX()+red.getImage(0).getIconWidth()>getWidth()&&!(red.getY()+red.getImage(0).getIconHeight()>getHeight()))
			{
				// Down movement Red ghost
				rightRed=false;
				downRed=true;
				leftRed=false;
				upRed=false;
			}
			else if (red.getY()<0&&!(red.getX()+red.getImage(0).getIconWidth()>getWidth()))
			{
				// Right movement Red ghost
				leftRed=false;
				upRed=false;
				rightRed=true;
				downRed=false;
			}
		}
		// Update Pacman and ghosts mask
		pacmanMask=new Rectangle2D.Double(pacmanX,pacmanY,pacman.getIconWidth()+1,pacman.getIconHeight()+1);
		red.setMask(red.getX(), red.getY(), 0);
		yellow.setMask(yellow.getX(),yellow.getY(), 2);
		pink.setMask(pink.getX(),pink.getY(), 1);
		blue.setMask(blue.getX(),blue.getY(), 3);
		repaint();

	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void keyTyped(KeyEvent e) {}

}


