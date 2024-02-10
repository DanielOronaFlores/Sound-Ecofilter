import AudioFileData.AudioFileData;
import FrequencyFilter.FrequencyFilter;
import RootMeanSquare.RootMeanSquare;
import AudioSamplesConverter.AudioSamplesConverter;
import org.jtransforms.fft.DoubleFFT_1D;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

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

        RootMeanSquare rms;
        FrequencyFilter filter;

        double totalSumOfSquares = 0;
        int totalSamples = 0;
        double thresholdMultiplier = 1.0;

        AudioSamplesConverter samplesConverter = new AudioSamplesConverter(samplesPerSegment, buffer, samples);
        while (audioStream.read(buffer) != -1) {
            samplesConverter.convertSamples();
            samples = samplesConverter.getSamples();

            rms = new RootMeanSquare(1.0);
            double sumOfSquares = rms.calculateRMS(samples);
            totalSumOfSquares += sumOfSquares;
            totalSamples += samplesPerSegment;

            DoubleFFT_1D fft = new DoubleFFT_1D(samplesPerSegment);
            fft.realForward(samples);

            filter = new FrequencyFilter(samplesPerSegment, samples, sampleRate);
            filter.filterLowerFrequenciesByHertz(300);
            samples = filter.getSamples();

            double currentRMS = Math.sqrt(sumOfSquares / samplesPerSegment);
            double threshold = rms.calculateAmplitudeThreshold(totalSumOfSquares, totalSamples);
            samples = rms.removeAudioLowerByRMS(samples, currentRMS, threshold);


        }
    }
}