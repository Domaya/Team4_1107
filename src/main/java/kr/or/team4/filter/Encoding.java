package kr.or.team4.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter("/Encoding")
public class Encoding extends HttpFilter implements Filter {
       
	//member field 생성
	private String encoding;
	
    public Encoding() {
        super();
    }
	public void destroy() {
		
	}
	public void init(FilterConfig fConfig) throws ServletException {
		//최초 요청시 컴파일되고 한번만 실행
		//web.xml에 설정되어 있는 초기값을 read(FilterConfig)
		this.encoding = fConfig.getInitParameter("encoding");
	}
	//함수를 통해서 요청/ 응답 필터링 제어
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//request 요청에 대한 필터 실행 코드 영역
		if(request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(this.encoding);
		}
		chain.doFilter(request, response);
		//response 응답에 대한 필터 실행 코드 영역
		if(response.getCharacterEncoding() == null) {
			response.setCharacterEncoding(this.encoding);
		}
	}
	

}
