import AudioFileData.AudioFileData;
import RMS.RMS;
import SamplesConverter.SamplesConverter;
import org.jtransforms.fft.DoubleFFT_1D;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;

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

        RMS rms = new RMS(1.0);
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