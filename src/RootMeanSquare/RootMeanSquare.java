package RootMeanSquare;

import java.util.Arrays;

public class RootMeanSquare {
    private double totalSumOfSquares;
    private int totalSamples;
    private double sumOfSquares;
    private final double thresholdMultiplier;

    public RootMeanSquare(double thresholdMultiplier) {
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
