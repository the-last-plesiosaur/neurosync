package dev.plesiosaur.model;

import java.time.ZonedDateTime;
import java.util.Random;

public class ShardScheduling {

    /*
    A shifted polynomial function is the simplest way to achieve this "slow start, fast growth" behavior

    decayWindow = 0..9

    days = decayWindow^2

    Adjustment = 1 + 19 * ( x - 1 / 9)^2 where x is the decay window

     */

    //     private int computeRandom(Random r) {
    //        return r.nextInt((upper - lower) + 1) + lower;
    //    }
    //}

    public static ZonedDateTime computeNextJack(Shard s) {

        double decayWindow = s.getDecayWindow();

        //double adjustment = 1.0 + 19.0 * Math.pow( (decayWindow - 1) / 9.0, 2.0);
        //long adjustmentRound = Math.round(adjustment);
        //long days = (long) Math.pow(decayWindow, 2.0);
        //long lowerBound = days - adjustmentRound;
        //long upperBound = days + adjustmentRound;

       // InclusiveRange dayRange = new InclusiveRange(days - adjustment, days + adjustment);

        int adjustment = computeAdjustment(decayWindow);
        int days = computeBaseDays(decayWindow);
        int randomDays = randomFromInclusiveRange(days - adjustment, days + adjustment);

        return ZonedDateTime.now().plusDays(randomDays);
    }

    protected static int computeAdjustment(double decayWindow) {
        return (int) Math.round(1.0 + 19.0 * Math.pow( (decayWindow - 1) / 9.0, 2.0));
    }

    protected static int computeBaseDays(double decayWindow) {
        return (int) Math.pow(decayWindow, 2.0);
    }

    protected static int randomFromInclusiveRange(int lower, int upper) {
        return new Random().nextInt((upper - lower) + 1) + lower;
    }

}
