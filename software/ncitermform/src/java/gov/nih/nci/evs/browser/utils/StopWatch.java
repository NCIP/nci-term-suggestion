/*L
 * Copyright Northrop Grumman Information Technology.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/nci-term-suggestion/LICENSE.txt for details.
 */

package gov.nih.nci.evs.browser.utils;

import java.text.*;

public class StopWatch {
    private static DecimalFormat _doubleFormatter = new DecimalFormat("0.00");
    private long _startMS = 0;

    public StopWatch() {
        start();
    }

    public void start() {
        _startMS = System.currentTimeMillis();
    }

    public long duration() {
        return System.currentTimeMillis() - _startMS;
    }

    public String getResult() {
        long time = duration();
        double timeSec = time / 1000.0;
        double timeMin = timeSec / 60.0;

        return "Run time: " + time + " ms, "
            + _doubleFormatter.format(timeSec) + " sec, "
            + _doubleFormatter.format(timeMin) + " min";
    }
}
