package RootMeanSquare;

import java.util.Arrays;

public class RootMeanSquare {
    private double thresholdMultiplier;

    public RootMeanSquare(double thresholdMultiplier) {
        this.thresholdMultiplier = thresholdMultiplier;
    }

    public double calculateRMS(double[] samples) {
        return Arrays.stream(samples).map(x -> x * x).sum();
    }

    public double[] removeAudioLowerByRMS(double[] samples, double rms, double threshold) {
        if (rms < threshold) { //TODO: Cambiar el signo de la condición.
            Arrays.fill(samples, 0.0);
        }
        return samples;
    }

    public double calculateAmplitudeThreshold(double totalSumOfSquares, int totalSamples) {
        return thresholdMultiplier * Math.sqrt(totalSumOfSquares / totalSamples);
    }
}