package lab14;

import lab14lib.Generator;

/**
 * @author 老爷保号
 */
public class AcceleratingSawToothGenerator implements Generator {
    private int state;

    private int period;

    private double factor;

    public AcceleratingSawToothGenerator(int period, double factor) {
        state = 0;
        this.period = period;
        this.factor = factor;
    }

    @Override
    public double next() {
        state = state + 1;
        if (state == period) {
            period *= factor;
            state = 0;
        }
        return normalize(state);
    }

    private double normalize(int x) {
        return (double) x * 2 / period - 1;
    }

}