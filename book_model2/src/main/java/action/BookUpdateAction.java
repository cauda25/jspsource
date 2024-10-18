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
public class BookUpdateAction implements Action {

	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		// 1. 가져오기
		BookDTO dto = new BookDTO();
		dto.setCode(Integer.parseInt(request.getParameter("code")));
		dto.setPrice(Integer.parseInt(request.getParameter("price")));
		dto.setDescription(request.getParameter("description"));
		
		// 검색 시 추가
		String keyword = request.getParameter("keyword");
		
		// 2. service 호출
		BookService service = new BookServiceImpl();
		boolean updateRow = service.update(dto);
		
		if(updateRow) {
			// 1 리턴 상세조회
			path += "?code="+ dto.getCode()+"&keyword="+URLEncoder.encode(keyword, "utf-8");
		} else {
			// 0 리턴 수정 페이지
			path = "/modify.do?code="+ dto.getCode()+"&keyword="+URLEncoder.encode(keyword, "utf-8");
		}
		
		return new ActionForward(path, true);
	}

}
