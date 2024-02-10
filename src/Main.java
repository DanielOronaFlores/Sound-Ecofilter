import AudioFileData.AudioFileData;
import RootMeanSquare.RootMeanSquare;
import SamplesConverter.SamplesConverter;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        String audioFilePath = System.getProperty("user.dir") + "\\src\\audio.wav"; //Replace with the path to your audio file.

        AudioFileData audioFileData = new AudioFileData(audioFilePath, 10);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFileData.getAudioFile());

        float sampleRate = audioStream.getFormat().getSampleRate();

        int samplesPerSegment = audioFileData.getSamplesPerSegment(audioStream);
        double[] samples = new double[samplesPerSegment];

        int BUFFER_SAMPLE_SIZE = 2;
        byte[] buffer = new byte[samplesPerSegment * BUFFER_SAMPLE_SIZE];

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        RootMeanSquare rms = new RootMeanSquare(1.0);
        double totalSumOfSquares = 0;
        int totalSamples = 0;
        double thresholdMultiplier = 1.0;

        SamplesConverter samplesConverter = new SamplesConverter(samplesPerSegment, buffer, samples);
        while (audioStream.read(buffer) != -1) {
            samplesConverter.convertSamples();
            samples = samplesConverter.getSamples();

            rms.calculateRMS(samples, samplesPerSegment);
            totalSumOfSquares = rms.getSumOfSquares();
            totalSamples = rms.getTotalSamples();
            double sumOfSquares = rms.getSumOfSquares();
        }
    }
}