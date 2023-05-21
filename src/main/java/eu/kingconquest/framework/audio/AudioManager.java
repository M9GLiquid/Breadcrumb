package eu.kingconquest.framework.audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * The AudioType class represents a type of audio that can be played within the game.
 * It includes an audio clip and the volume at which the clip should be played.
 * <p>
 * This class is responsible for loading the audio file from the resources folder
 * and creating a Clip instance that can be played.
 * <p>
 * Example usage:
 * <pre>
 *     AudioType myAudio = new AudioType("audio_file.wav", 0.5f);
 *     Clip clip = myAudio.getAudioClip();
 *     float volume = myAudio.getAudioVolume();
 * </pre>
 */
public class AudioManager {
    protected Clip audioClip;

    protected float audioVolume;

    /**
     * Constructs a new AudioType instance with the given audio file path and volume.
     *
     * @param audioPath   The path to the audio file within the resources' folder.
     * @param audioVolume The volume at which the audio should be played, ranging from 0.0f (silent) to 1.0f (maximum volume).
     */
    public AudioManager(String audioPath, float audioVolume) {
        // Volume should not be greater than 100%
        if (audioVolume > 1)
            audioVolume = 1;
        else if (audioVolume < 0)
            audioVolume = 0;

        this.audioVolume = audioVolume;
        try {
            audioClip = AudioSystem.getClip();
            audioClip.open(loadAudio(audioPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the audio file from the specified path and returns an AudioInputStream.
     * If the audio file is not found or an error occurs while loading the file, this method returns null.
     *
     * @param fileName The name of the audio file to load from the resources' folder.
     * @return An AudioInputStream containing the loaded audio, or null if an error occurs.
     */
    private AudioInputStream loadAudio(String fileName){
        InputStream inputStream = AudioManager.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            System.err.println("Audio not found: " + fileName);
            return null;
        }

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        AudioInputStream audioInputStream;

        try {
            audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
            try {
                inputStream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return null;
        }
        return audioInputStream;
    }

    /**
     * Plays the audio clip if it's not already playing.
     * This method sets the frame position to the beginning, adjusts the volume, and starts the clip.
     */
    public void play() {
        if (audioClip == null || audioClip.isRunning())
            return;

        audioClip.setFramePosition(0);
        setClipVolume(audioClip, audioVolume);
        audioClip.start();
    }

    /**
     * Sets the volume of the specified clip.
     *
     * @param clip   The Clip instance whose volume should be adjusted.
     * @param volume The desired volume, ranging from 0.0f (silent) to 1.0f (maximum volume).
     */
    public void setClipVolume(Clip clip, float volume) {
        if (clip == null || !clip.isOpen())
            return;

        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * volume) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }
}
