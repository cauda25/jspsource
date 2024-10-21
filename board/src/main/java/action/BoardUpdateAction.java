package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import lombok.AllArgsConstructor;
import service.BoardServiceImpl;
import service.BoardService;

@AllArgsConstructor

public class BoardUpdateAction implements Action {

	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BoardDTO dto = new BoardDTO();
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		dto.setBno(Integer.parseInt(request.getParameter("bno")));
		dto.setPassword(request.getParameter("password"));
	
		BoardService service = new BoardServiceImpl();
		boolean updateRow = service.update(dto);
		
		if(updateRow) {
			path += "?bno="+dto.getBno();
		}else {
			path = "/modify.do?bno="+dto.getBno();
		}
		
		return new ActionForward(path, true);
	}

}
