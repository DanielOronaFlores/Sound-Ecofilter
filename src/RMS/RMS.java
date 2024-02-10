package RMS;

import java.util.Arrays;

public class RMS {
    private double totalSumOfSquares;
    private int totalSamples;
    private double sumOfSquares;
    private final double thresholdMultiplier;

    public RMS(double thresholdMultiplier) {
        this.thresholdMultiplier = thresholdMultiplier;
    }

    public void calculateRMS(double[] samples, int samplesPerSegment) {
        sumOfSquares = Arrays.stream(samples).map(x -> x * x).sum();
        totalSumOfSquares += sumOfSquares;
        totalSamples += samplesPerSegment;
    }

    public double getSumOfSquares() {
        return totalSumOfSquares;
    }
    public int getTotalSamples() {
        return totalSamples;
    }
    public double getThresholdMultiplier() {
        return thresholdMultiplier;
    }
    public double getTotalSumOfSquares() {
        return totalSumOfSquares;
    }
}
