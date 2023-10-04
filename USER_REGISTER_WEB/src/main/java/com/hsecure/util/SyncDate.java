package com.hsecure.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;

public class SyncDate
{
  private Logger logger;

  public SyncDate(Logger l)
  {
    this.logger = l;
  }

  public String getCurrentDate(String format)
  {
    String rtv = "9999/12/31";
    try
    {
      DateFormat dateFormat = new SimpleDateFormat(format);
      Calendar calendar = Calendar.getInstance();
      rtv = dateFormat.format(calendar.getTime());
    } catch (Exception e) {
      logger.error(e.getMessage());
    }

    return rtv;
  }

  public String getCalculateMonth(String format, int months)
  {
    String rtv = "9999/12/31";
    try
    {
      Date date = new Date();

      SimpleDateFormat dateFormat = new SimpleDateFormat(format);
      Calendar calendar = Calendar.getInstance();

      calendar.setTime(date);
      calendar.add(2, months);

      Date calDate = calendar.getTime();

      rtv = dateFormat.format(calDate);
    }
    catch (Exception e) {
    	logger.error(e.getMessage());
    }

    return rtv;
  }

  public static long getTimeStamp()
  {
    Calendar cal = Calendar.getInstance();
    Timestamp time = new Timestamp(cal.getTime().getTime());
    return time.getTime();
  }
}