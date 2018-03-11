package servlets;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.codec.digest.DigestUtils;

@WebFilter("/PasswordFilter")
public class PasswordFilter implements Filter {

	private ServletContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
	}

	public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {  	
        chain.doFilter(new FilteredRequest(request), response);
    }

	public void destroy() {
		//we can close resources here
	}
	
	static class FilteredRequest extends HttpServletRequestWrapper {
			
		    public FilteredRequest(ServletRequest request) {
		            super((HttpServletRequest)request);
		    }
	
		    @Override   
	        public String getParameter(String paramName) {
	            String value = super.getParameter(paramName);
	        
	             
	            if ("password".equals(paramName)) {
	            	String unhashed = super.getParameter("password");
	            	value = DigestUtils.sha256Hex(unhashed);
	            }
	            return value;
	        }
	
	   }

}