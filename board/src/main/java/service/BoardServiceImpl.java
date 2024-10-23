package service;

import java.util.List;

import dao.BoradDAO;
import dto.BoardDTO;
import dto.SearchDTO;



public class BoardServiceImpl implements BoardService {

	private BoradDAO dao = new BoradDAO();
	@Override
	public List<BoardDTO> listAll(SearchDTO sDto) {
		return dao.getList(sDto);
	}

	@Override
	public BoardDTO getRow(int bno) {
		return dao.read(bno);
	}

	@Override
	public boolean update(BoardDTO updateDto) {
		return dao.update(updateDto)==1?true:false;
	}

	@Override
	public boolean delete(BoardDTO deleteDto) {
		return dao.delete(deleteDto)==1?true:false;
	}

	@Override
	public boolean create(BoardDTO insertDto) {
		return dao.create(insertDto)==1?true:false;
	}

	@Override
	public boolean hitUpdate(int bno) {
		return dao.updateRead(bno)==1?true:false;
	}

	@Override
	public boolean reply(BoardDTO replyDto) {
		return dao.replyInsert(replyDto)==1?true:false;
	}

	@Override
	public int getTogalRows() {
		return dao.getTogalRows();
	}

}
