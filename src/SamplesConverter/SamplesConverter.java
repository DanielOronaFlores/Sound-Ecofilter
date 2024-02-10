package SamplesConverter;

public class SamplesConverter {
    private final int samplesPerSegment;
    private final byte[] buffer;
    private final double[] samples;

    public SamplesConverter(int samplesPerSegment, byte[] buffer, double[] samples) {
        this.samplesPerSegment = samplesPerSegment;
        this.buffer = buffer;
        this.samples = samples;

    }

    public void convertSamples() {
        for (int i = 0; i < samplesPerSegment; i++) {
            samples[i] = ((buffer[i * 2 + 1] << 8) | (buffer[i * 2] & 0xFF)) / 32768.0;
        }
    }

    public double[] getSamples() {
        return samples;
    }
}
