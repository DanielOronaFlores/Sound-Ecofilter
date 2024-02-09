package AudioFileData;

import javax.sound.sampled.AudioInputStream;
import java.io.File;

public class AudioFileData {
    private final File audioFile;
    private final double segmentDuration;

    public AudioFileData(){
        audioFile = null;
        segmentDuration = 0;
    }
    public AudioFileData(String audioFilePath, int segmentDuration) {
        audioFile = new File(audioFilePath);
        if (!audioFile.exists()) {
            throw new IllegalArgumentException("El archivo de audio no existe: " + audioFilePath);
        }
        this.segmentDuration = segmentDuration;
    }
    public File getAudioFile() {
        return audioFile;
    }
    public int getSamplesPerSegment(AudioInputStream audioStream) {
        float sampleRate = audioStream.getFormat().getSampleRate();
        return (int) (sampleRate * segmentDuration);
    }
}
