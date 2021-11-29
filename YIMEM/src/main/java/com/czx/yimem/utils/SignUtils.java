package com.czx.yimem.utils;

import net.sf.json.JSONObject;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SignUtils {


	public static final String PRIVATEKEY="44=";

	private static final String ALGORITHM = "RSA";

	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

	private static final String DEFAULT_CHARSET = "UTF-8";

	private static String getAlgorithms(boolean rsa2) {
		return rsa2 ? SIGN_SHA256RSA_ALGORITHMS : SIGN_ALGORITHMS;
	}

	public static String sign(String content, String privateKey, boolean rsa2) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
					Base64.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature
					.getInstance(getAlgorithms(rsa2));

			signature.initSign(priKey);
			signature.update(content.getBytes(DEFAULT_CHARSET));

			byte[] signed = signature.sign();


			return Base64.encode(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean verify(String content, String sign, String publicKey, boolean rsa2) {
		try {
//			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(publicKey));
			// 构造X509EncodedKeySpec对象
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKey));
			KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
			PublicKey pubKey = keyf.generatePublic(keySpec);
			java.security.Signature signature = java.security.Signature
					.getInstance(getAlgorithms(rsa2));

			signature.initVerify(pubKey);
            signature.update(content.getBytes(DEFAULT_CHARSET));
			boolean isVerify = signature.verify(Base64.decode(sign));
//			return Base64.encode(signed);
			return isVerify;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}


	public static void main(String[] str){
    JSONObject jsonObject=new JSONObject();
    jsonObject.put("latitude",31.980896);
    jsonObject.put("longitude",120.88334);
    jsonObject.put("memberid",126);
    jsonObject.put("usescene","homepagebanner");
    jsonObject.put("classifyid",-1);
    jsonObject.put("isindex","0");
    System.out.println(jsonObject.toString());
		String s=sign(jsonObject.toString(),PRIVATEKEY,true);
		System.out.println(s);

    boolean b=verify(jsonObject.toString(),s,"44",true);
    System.out.println(b);

	}

}
