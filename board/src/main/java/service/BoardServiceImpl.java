package service;

import java.util.List;

import dao.BoradDAO;
import dto.BoardDTO;



public class BoardServiceImpl implements BoardService {

	private BoradDAO dao = new BoradDAO();
	@Override
	public List<BoardDTO> listAll() {
		return dao.getList();
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
	public boolean delete(int bno) {
		return false;
	}

	@Override
	public boolean create(BoardDTO insertDto) {
		return false;
	}

}
