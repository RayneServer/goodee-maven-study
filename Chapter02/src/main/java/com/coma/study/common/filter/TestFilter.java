package com.coma.study.common.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

public class TestFilter extends HttpFilter implements Filter {

	public TestFilter() {
    super();
  }

  public void init(FilterConfig fConfig) throws ServletException {
  	// No body
  }

	public void destroy() {
		// No body
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Test Filter In");
		
		chain.doFilter(request, response);
		
		System.out.println("Test Filter Out");
	}


}
