package com.threads.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CountingFactorizer implements Servlet{
	
	private final AtomicLong count=new AtomicLong(0);
	
	public long getCount(){
		return count.get();
	}
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
//		BigInteger i=extractFromRequest(req);
		
	}


	public static void main(String[] args) {

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
