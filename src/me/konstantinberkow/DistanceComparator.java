package me.konstantinberkow;

import java.util.Comparator;

/**
 * Created by konstantinberkov on 12/17/15.
 */
public class DistanceComparator implements Comparator<Pair> {

    private final Pair mPair;

    public DistanceComparator(Pair pair) {
        mPair = pair;
    }

    @Override
    public int compare(Pair o1, Pair o2) {
        final float distance1 = mPair.distance(o1);
        final float distance2 = mPair.distance(o2);
        return Float.compare(distance1, distance2);
    }
}
