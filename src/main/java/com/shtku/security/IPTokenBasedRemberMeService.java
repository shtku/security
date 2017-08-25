package com.shtku.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

public class IPTokenBasedRemberMeService extends TokenBasedRememberMeServices {
	private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();
	
	public HttpServletRequest getContext(){
		return requestHolder.get();
	}
	
	public void setContext(HttpServletRequest context){
		requestHolder.set(context);
	}
	
	protected String getUserIPAddress(HttpServletRequest request){
		return request.getRemoteAddr();
	}

	@Override
	public void onLoginSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		try{
			setContext(request);
			super.onLoginSuccess(request, response, authentication);
		}
		finally{
			setContext(null);
		}
	}

	@Override
	protected String makeTokenSignature(long tokenExpiryTime, String username, String password) {
		 String data = username + ":" + tokenExpiryTime + ":" + password + ":" + getKey() + ":" + getUserIPAddress(getContext());
	        MessageDigest digest;
	        try {
	            digest = MessageDigest.getInstance("MD5");
	        } catch (NoSuchAlgorithmException e) {
	            throw new IllegalStateException("No MD5 algorithm available!");
	        }

	        return new String(Hex.encode(digest.digest(data.getBytes())));
	}

	@Override
	protected void setCookie(String[] tokens, int maxAge,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String[] tokensWithIPAddress = Arrays.copyOf(tokens, tokens.length+1);
		tokensWithIPAddress[tokensWithIPAddress.length-1] = getUserIPAddress(request);
		super.setCookie(tokensWithIPAddress, maxAge, request, response);
	}

	@Override
	protected UserDetails processAutoLoginCookie(String[] cookieTokens,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try{
			setContext(request);
			//take off last token
			String iPAddressToken = cookieTokens[cookieTokens.length-1];
			if(!getUserIPAddress(request).equals(iPAddressToken)){
				 throw new InvalidCookieException("Cookie IP Address did not contain a matching IP (contained '" +
						 iPAddressToken + "')");
			}
			return super.processAutoLoginCookie(Arrays.copyOf(cookieTokens, cookieTokens.length-1), request, response);
		}finally{
			setContext(null);
		}
	}

	@Override
	protected void onLoginFail(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		super.onLoginFail(request, response);
	}
	
	
	
	
	
}
