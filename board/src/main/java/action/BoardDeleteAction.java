package action;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import dto.SearchDTO;
import lombok.AllArgsConstructor;
import service.BoardServiceImpl;
import service.BoardService;

@AllArgsConstructor

public class BoardDeleteAction implements Action {

	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BoardDTO dto = new BoardDTO();
		dto.setBno(Integer.parseInt(request.getParameter("bno")));
		dto.setPassword(request.getParameter("password"));

		// 페이지 나누기
		int page = (Integer.parseInt(request.getParameter("page")));
		int amount = (Integer.parseInt(request.getParameter("amount")));

		// 검색 추가
		String criteria = request.getParameter("criteria");
		String keyword = URLEncoder.encode(request.getParameter("keyword"), "utf-8") ;
		
		BoardService service = new BoardServiceImpl();
		boolean del = service.delete(dto);

		if (del) {
			path += "?page=" + page +"&amount="+ amount +"&criteria=" + criteria +"&keyword=" + keyword;
		} else {
			path = "/modify.do?bno=" + dto.getBno() + "&page=" + page +"&amount="+ amount +"&criteria=" + criteria +"&keyword=" + keyword;
		}

		return new ActionForward(path, true);
	}

}
