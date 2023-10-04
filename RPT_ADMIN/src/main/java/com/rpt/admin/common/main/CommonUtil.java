package com.rpt.admin.common.main;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

public class CommonUtil {
	
	/**
	 * 비밀번호를 암호화하는 기능(복호화가 되면 안되므로 SHA-256 인코딩 방식 적용)
	 *
	 * @param String data 암호화할 비밀번호
	 * @return String sha-256으로 암호화하고 Base64로 인코딩한 비밀번호
	 * @exception Exception
	 */
	public static String encryptPassword(String data) throws Exception {
		String result = "";
		if (data == null) {
			return "";
		}

		byte[] plainText = null; // 평문
		byte[] hashValue = null; // 해쉬값
		plainText = data.getBytes();

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		hashValue = md.digest(plainText);

		result = base64Encoding(hashValue);

		return result;
	}

	private static String base64Encoding(byte[] value) throws Exception {
		return new String(Base64.encodeBase64(value));
	}

	private static String encoding(byte[] value) throws Exception {
		int  iLen = value.length;
		StringBuffer stringBuffer = new StringBuffer();
		if (stringBuffer == null) return null;

		for (int i = 0; i < iLen; ++i) {
			stringBuffer.append(String.format("%02X", 0xFF & value[i]));
		}

		return stringBuffer.toString();
	}
	
	public static String getPrintStackTrace(Exception e) {
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }

}
