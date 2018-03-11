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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.codec.digest.DigestUtils;

@WebFilter("/PasswordFilter")
public class PasswordFilter implements Filter {

	private ServletContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		ServletResponse hashResponse = response;
		
		if (request instanceof HttpServletRequest) {
			hashResponse = new CharResponseWrapper((HttpServletResponse) response);
		}
		
		chain.doFilter(request, hashResponse);
		
		if (hashResponse instanceof CharResponseWrapper) {
			String text = hashResponse.toString();
			if (text != null) {
				text = DigestUtils.sha256Hex(text);
				response.getWriter().write(text);
			}
		}
	}

	public void destroy() {
		//we can close resources here
	}
	
	class CharResponseWrapper extends HttpServletResponseWrapper {
		  protected CharArrayWriter charWriter;

		  protected PrintWriter writer;

		  protected boolean getOutputStreamCalled;

		  protected boolean getWriterCalled;

		  public CharResponseWrapper(HttpServletResponse response) {
		    super(response);

		    charWriter = new CharArrayWriter();
		  }

		  public ServletOutputStream getOutputStream() throws IOException {
		    if (getWriterCalled) {
		      throw new IllegalStateException("getWriter already called");
		    }

		    getOutputStreamCalled = true;
		    return super.getOutputStream();
		  }

		  public PrintWriter getWriter() throws IOException {
		    if (writer != null) {
		      return writer;
		    }
		    if (getOutputStreamCalled) {
		      throw new IllegalStateException("getOutputStream already called");
		    }
		    getWriterCalled = true;
		    writer = new PrintWriter(charWriter);
		    return writer;
		  }

		  public String toString() {
		    String s = null;

		    if (writer != null) {
		      s = charWriter.toString();
		    }
		    return s;
		  }
		}

}