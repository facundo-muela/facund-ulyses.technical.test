package com.septeo.ulyses.technical.test;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger("api-logger");
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		long start = (Long) request.getAttribute("startTime");
		long durationMs = System.currentTimeMillis() - start;

		String timestamp = ZonedDateTime.now().format(formatter);
		String method = request.getMethod();
		String url = request.getRequestURI();
		int status = response.getStatus();

		logger.info("[{}] {} {} -> {} ({} ms)", timestamp, method, url, status, durationMs);
	}
}
