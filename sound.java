import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class sound {
	private static Clip bgMusic, collisionMusic;
	private File soundFile2, soundFile3;
	private AudioInputStream audioIn2, audioIn3;

	public sound()
	{
		// Load sound files
		try
		{
			soundFile2 = new File("music.mid");
			audioIn2 = AudioSystem.getAudioInputStream(soundFile2);
			bgMusic = AudioSystem.getClip();
			bgMusic.open(audioIn2);
		}
		catch (Exception e){}

		try
		{
			soundFile3 = new File("credit.wav");
			audioIn3 = AudioSystem.getAudioInputStream(soundFile3);
			collisionMusic = AudioSystem.getClip();
			collisionMusic.open(audioIn3);	 		
		}
		catch (Exception e){}
	}
	// Start Sound when frame is made
	public void bgSound()
	{
		bgMusic.start();
		bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
	}
	// Stop Sound when Game begins
	public void bgSoundStop()
	{
		bgMusic.stop();
	}
	// Start Sound when collision with pellets occur
	public void collisionSound()
	{
		collisionMusic.start();
		collisionMusic.loop(Clip.LOOP_CONTINUOUSLY);
	}
	// Stop Sound when collision with ghost occurs
	public void collisionSoundStop()
	{
		collisionMusic.stop();
	}
}
