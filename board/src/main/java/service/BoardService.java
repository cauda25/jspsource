package service;

import java.util.List;

import dto.BoardDTO;
import dto.SearchDTO;



public interface BoardService {
	List<BoardDTO> listAll(SearchDTO sDto);
	BoardDTO getRow(int bno);
	boolean update(BoardDTO updateDto);
	boolean delete(BoardDTO deleteDto);
	boolean create(BoardDTO insertDto);
	boolean hitUpdate(int bno);
	boolean reply(BoardDTO replyDto);
	int getTogalRows(SearchDTO sDto);
}
