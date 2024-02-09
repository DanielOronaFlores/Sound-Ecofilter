import AudioFileData.AudioFileData;

import javax.sound.sampled.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String audioFilePath = System.getProperty("user.dir") + "\\src\\audio.wav"; //Replace with the path to your audio file.

        AudioFileData audioFileData = new AudioFileData(audioFilePath, 10);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFileData.getAudioFile());

        int samplesPerSegment = audioFileData.getSamplesPerSegment(audioStream);
        float sampleRate = audioStream.getFormat().getSampleRate();
    }
}