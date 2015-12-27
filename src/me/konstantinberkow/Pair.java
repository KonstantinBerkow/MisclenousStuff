package me.konstantinberkow;

import com.sun.istack.internal.NotNull;

/**
 * Created by konstantinberkov on 12/16/15.
 */
public final class Pair implements Comparable<Pair>, Cloneable {

    private final char character;
    private final float frequency;

    private int hash;

    public Pair(char character, float frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public char getCharacter() {
        return character;
    }

    public float getFrequency() {
        return frequency;
    }

    public float distance(@NotNull final Pair other) {
        return Math.abs(frequency - other.frequency);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        final Pair clone = new Pair(character, frequency);
        clone.hash = hash;
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (character != pair.character) return false;
        return Float.compare(pair.frequency, frequency) == 0;
    }

    @Override
    public int hashCode() {
        if (hash == 0) {
            hash = (int) character;
            hash = 31 * hash + (frequency != +0.0F ? Float.floatToIntBits(frequency) : 0);
        }
        return hash;
    }

    @Override
    public int compareTo(Pair o) {
        final int diff = Float.compare(frequency, o.frequency);
        if (diff == 0) {
            return Integer.compare(character, o.character);
        }
        return diff;
    }

    @Override
    public String toString() {
        return String.format("{%s=%.3f}", character, frequency);
    }
}
