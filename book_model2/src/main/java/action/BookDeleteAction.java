package action;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BookDTO;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookDeleteAction implements Action {

	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1. 가져오기
		int code = Integer.parseInt(request.getParameter("code"));
		String keyword = request.getParameter("keyword");
		// 2. service 호출
		BookService service = new BookServiceImpl();
		boolean deleteRow = service.delete(code);
		
		if(!deleteRow) {
			path = "/modify.do?code="+code;
		} else {
			path += "?keyword="+URLEncoder.encode(keyword, "utf-8");
		}
		
		return new ActionForward(path, true);
	}

}
