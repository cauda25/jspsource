package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BookDTO;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookCreateAction implements Action {

	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		// 1. 가져오기
		BookDTO insertDto = new BookDTO();
		insertDto.setCode(Integer.parseInt(request.getParameter("code")));
		insertDto.setTitle(request.getParameter("title"));
		insertDto.setWriter(request.getParameter("writer"));
		insertDto.setPrice(Integer.parseInt(request.getParameter("price")));
		insertDto.setDescription(request.getParameter("description"));
		// 2. service 호출
		BookService service = new BookServiceImpl();
		boolean insertRow = service.insert(insertDto);

		if (insertRow) {
			path += "?code="+ insertDto.getCode();
		} else {
			path = "/book/create.jsp";
		}

		return new ActionForward(path, true);
	}
}
