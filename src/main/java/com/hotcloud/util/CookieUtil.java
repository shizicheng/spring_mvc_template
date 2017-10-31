package com.hotcloud.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	public static Map<String, String> getCookies(HttpServletRequest request) {
		Map<String, String> sCookie = new HashMap<>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			String cookiePre = ConfigUtil.config.get("cookiePre");
			int prelength = Common.strlen(cookiePre);
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if (name != null && name.startsWith(cookiePre)) {

					sCookie.put(name.substring(prelength), Common.urlDecode(Common.addSlashes(cookie.getValue())));
				}
			}
		}
		return sCookie;
	}

	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name != null && cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String key) {
		setCookie(request, response, key, "", 0);
	}

	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String key, String value) {
		setCookie(request, response, key, value, -1);/*-1表示关闭浏览器时立即清除cookie*/
	}

	/** 将cookie写入到response中 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String key, String value,
			int maxAge) {
		Cookie cookie = new Cookie(ConfigUtil.config.get("cookiePre") + key, Common.urlEncode(value));
		cookie.setMaxAge(maxAge);
		cookie.setPath(ConfigUtil.config.get("cookiePath"));
		if (!Common.empty(ConfigUtil.config.get("cookieDomain"))) {
			cookie.setDomain(ConfigUtil.config.get("cookieDomain"));

		}
		cookie.setSecure(request.getServerPort() == 443 ? true : false);
		response.addCookie(cookie);
	}

	@SuppressWarnings("unchecked")
	public static void clearCookie(HttpServletRequest request, HttpServletResponse response) {
		removeCookie(request, response, "auth");
		Map<String, Object> sGlobal = (Map<String, Object>) request.getAttribute("sGlobal");
		sGlobal.put("supe_uid", 0);
		sGlobal.put("supe_username", "");
		sGlobal.remove("member");
	}
}