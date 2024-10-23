package action;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dto.BoardDTO;
import lombok.AllArgsConstructor;
import service.BoardServiceImpl;
import service.BoardService;

@AllArgsConstructor

public class BoardReplytAction2 implements Action {

	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BoardDTO replydDto = new BoardDTO();
		replydDto.setTitle(request.getParameter("title"));
		replydDto.setContent(request.getParameter("content"));
		replydDto.setName(request.getParameter("name"));
		replydDto.setPassword(request.getParameter("password"));
		
		// hidden 부모 정보
		replydDto.setReRef(Integer.parseInt(request.getParameter("reRef")));
		replydDto.setReLev(Integer.parseInt(request.getParameter("reLev")));
		replydDto.setReSeq(Integer.parseInt(request.getParameter("reSeq")));
		replydDto.setBno(Integer.parseInt(request.getParameter("bno")));
		
		BoardService service = new BoardServiceImpl();
		boolean replyFlag = service.reply(replydDto);
		
		if(replyFlag) {
			
		} else {
			path = "/replyView.do?bno="+ replydDto.getBno();
		}
		
		return new ActionForward(path, true);
	}
	
}
