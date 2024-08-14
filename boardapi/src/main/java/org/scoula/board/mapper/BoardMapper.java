package org.scoula.board.mapper;

import java.util.*;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;

public interface BoardMapper {
    public int create(BoardVO board);

    public List<BoardVO> getList();

    public BoardVO get(long id);

    public int update(BoardVO board);

    public int delete(Long no);

    public void createAttachment(BoardAttachmentVO attach);

    public List<BoardAttachmentVO> getAttachmentList(Long bno);

    public BoardAttachmentVO getAttachment(Long no);

    public int deleteAttachment(Long no);

}
