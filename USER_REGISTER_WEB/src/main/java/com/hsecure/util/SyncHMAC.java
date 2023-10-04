package com.hsecure.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;

public class SyncHMAC
{
  private Logger logger;

  public SyncHMAC(Logger l)
  {
    this.logger = l;
  }

  public String getHMAC(String orgKey, String currentDate)
  {
    String rtv = "";
    try
    {
      String secret = "hsecuresecrethmackey";
      String message = orgKey + currentDate;

      this.logger.debug("HMAC NAME : " + orgKey);
      this.logger.debug("HMAC DATE : " + currentDate);

      Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
      SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
      sha256_HMAC.init(secret_key);

      rtv = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes("UTF-8")));
    }
    catch (Exception e)
    {
    	logger.error(e.getMessage());
    }

    return rtv;
  }
}