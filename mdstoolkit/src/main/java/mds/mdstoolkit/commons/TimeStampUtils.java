package mds.mdstoolkit.commons;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by vivekjha on 15/01/16.
 * Utility for date time
 */
public class TimeStampUtils {

    public static String now(String format)
    {
        DateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getTimeStamp(String format,long millis)
    {
        Date date = new Date(millis);
        DateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(date);
    }

    public static long getCurrentTimeInMillis() {
        Date date = new Date();
        return date.getTime();
    }

    /**
     * Get day from date
     * @return day from date
     */
    public static String getDayFromDate(long timeInMillis)
    {
        try {
            Date newDate = new Date(timeInMillis);
            return new SimpleDateFormat("EEEE", Locale.getDefault()).format(newDate);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * get Month from date
     * @return Month of this date
     */
    public static String getMonthFromDate(long timeInMillis)
    {
        try {
            Date newDate = new Date(timeInMillis);
            return new SimpleDateFormat("MMM", Locale.getDefault()).format(newDate);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Convert timestamp into milliseconds
     * @param timestamp timestamp
     * @param timestampFormat format of timestamp provided
     * @return time in millis
     */
    public static long convertTimestampIntoMillis(String timestamp, String timestampFormat)
    {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timestampFormat, Locale.getDefault());
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

            Date date = simpleDateFormat.parse(timestamp);
            return date.getTime();
        }
        catch (ParseException pe)
        {
            pe.printStackTrace();
            return 0;
        }
    }


    /**
     * Calulate time difference
     * @param fromTime Time to calculate difference from
     * @param toTime Time to calculate difference to
     * @return difference in milliseconds
     */
    public static long calculateTimeDiff(long fromTime, long toTime) {
            Date fromTimeD = new Date(fromTime);
            Date toTimeD = new Date(toTime);
            return fromTimeD.getTime() - toTimeD.getTime();
    }

    /**
     * Get relative date time format as '2 hourse ago , 2 days ago , 2 minutes ago'
     * @param timeInMillis time in millis seconds
     * @return relative date time
     */
    public static String getRelativeTimeDifference(long timeInMillis)
    {
        long difference = 0;
        long mCurrentDate = System.currentTimeMillis();

        if(mCurrentDate > timeInMillis)
        {
            difference = mCurrentDate - timeInMillis;
            final long seconds = difference/1000;
            final long minutes = seconds/60;
            final long hours = minutes/60;
            final long days = hours/24;
            final long months = days/31;
            final long years = days/365;
            if (seconds < 0)
            {
                return "not yet";
            }
            else if (seconds < 60)
            {
                return seconds == 1 ? "one second ago" : seconds + " seconds ago";
            }
            else if (seconds < 120)
            {
                return "a minute ago";
            }
            else if (seconds < 2700) // 45 * 60
            {
                return minutes + " minutes ago";
            }
            else if (seconds < 5400) // 90 * 60
            {
                return "an hour ago";
            }
            else if (seconds < 86400) // 24 * 60 * 60
            {
                return hours + " hours ago";
            }
            else if (seconds < 172800) // 48 * 60 * 60
            {
                return "yesterday";
            }
            else if (seconds < 2592000) // 30 * 24 * 60 * 60
            {
                return days + " days ago";
            }
            else if (seconds < 31104000) // 12 * 30 * 24 * 60 * 60
            {

                return months <= 1 ? "one month ago" : days + " months ago";
            }
            else
            {

                return years <= 1 ? "one year ago" : years + " years ago";
            }
        }
        return null;
    }
}
