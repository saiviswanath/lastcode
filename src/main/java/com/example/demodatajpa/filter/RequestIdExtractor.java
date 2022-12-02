package com.example.demodatajpa.filter;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RequestIdExtractor {
	
	@Value("${com.syf.requestIdInFormat:none}")
	private String requestIdInFormat;
	
	@Value("${com.syf.requestIdPath:}")
	private String requestIdPath;
	
	public String requestIdExtract(HttpServletRequest request) throws IOException {
		switch(requestIdInFormat) {
		case "header": 
				String header = request.getHeader("requestId");
				return header != null ? header : UUID.randomUUID().toString();
		case "query":
				String param = request.getParameter("requestId");
				return param != null  ? param : UUID.randomUUID().toString();
		case "body": 
			String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(body);
			return node.at(requestIdPath).asText();
			default:
				return UUID.randomUUID().toString();
		}
	}
}
