package me.sores.Orion.util;

/**
 * Created by LavaisWatery on 2017-08-18.
 */
public class Range {

    public Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    private int min, max;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public boolean matches(int range) {
        return range >= min && range <= max;
    }

    public void set(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int genRange() {
        return min == max ? min : NumberUtil.getIntBetween(Math.min(min, max), Math.max(min, max));
    }

    public String toPointRange() {
        return (min == max ? "" + max : "" + min + "-" + max);
    }

    @Override
    public String toString() { return "" + min + ":" + max; };

}
