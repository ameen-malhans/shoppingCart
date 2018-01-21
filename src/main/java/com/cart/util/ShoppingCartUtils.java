package com.cart.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import com.cart.model.ShoppingCart;

public class ShoppingCartUtils {
	
	public static boolean isLineItemsExists(ShoppingCart cart) {
		if(cart!=null && !cart.getLinesItems().isEmpty()){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public static String getHostName(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI();
		return url.substring(0, url.indexOf(uri));
	}
	
	public static String encrypted(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());
		byte byteData[] = md.digest();
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
