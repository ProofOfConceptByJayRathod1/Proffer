package com.proxibid.util;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {

	public static String getCookieByName(HttpServletRequest request, String cookieName) {

		if (request.getCookies() != null) {
			Cookie ck = Arrays.asList(request.getCookies()).stream().filter(c -> c.getName().equals(cookieName))
					.findFirst().orElse(null);
			if (ck != null) {
				return ck.getValue();
			}

		}
		return null;
	}

}
