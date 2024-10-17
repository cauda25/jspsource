package action;

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
		// 2. service 호출
		BookService service = new BookServiceImpl();
		boolean updateRow = service.update(dto);
		
		if(updateRow == false) {
			response.sendRedirect("/modify.do?code="+ dto.getCode());
		} else {
			response.sendRedirect("/list.do");
		}
		
		// response.sendRedirect => forward => true
		return new ActionForward(path, true);
	}

}
