package com.rpt.admin.common.main;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

public class CommonUtil {
	
	/**
	 * ��й�ȣ�� ��ȣȭ�ϴ� ���(��ȣȭ�� �Ǹ� �ȵǹǷ� SHA-256 ���ڵ� ��� ����)
	 *
	 * @param String data ��ȣȭ�� ��й�ȣ
	 * @return String sha-256���� ��ȣȭ�ϰ� Base64�� ���ڵ��� ��й�ȣ
	 * @exception Exception
	 */
	public static String encryptPassword(String data) throws Exception {
		String result = "";
		if (data == null) {
			return "";
		}

		byte[] plainText = null; // ��
		byte[] hashValue = null; // �ؽ���
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
