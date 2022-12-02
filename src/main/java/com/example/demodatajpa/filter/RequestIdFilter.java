package com.example.demodatajpa.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestIdFilter extends OncePerRequestFilter {
	
	@Autowired
	private RequestIdExtractor extractor;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	 try {
		 CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(request);
		 String reqId = extractor.requestIdExtract(cachedBodyHttpServletRequest);
		 MDC.put("requestId", reqId);
		 filterChain.doFilter(cachedBodyHttpServletRequest, response);
	 } finally {
		 MDC.remove("requestId");
	 }
	 
	}

}
