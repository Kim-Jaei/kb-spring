package org.scoula.board.mapper;

import java.util.*;
import org.scoula.board.domain.BoardVO;

public interface BoardMapper {
    public int create(BoardVO board);

    public List<BoardVO> getList();

    public BoardVO get(long id);

    public int update(BoardVO board);

    public int delete(Long no);

}
