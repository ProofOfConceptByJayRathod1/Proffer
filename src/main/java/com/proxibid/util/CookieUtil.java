package com.proxibid.util;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

public class CookieUtil {

	public static String getCookieByName(HttpServletRequest request, String cookieName) {
		return Arrays.asList(request.getCookies()).stream().filter(c -> c.getName().equals(cookieName)).findFirst()
				.get().getValue();
	}

}
