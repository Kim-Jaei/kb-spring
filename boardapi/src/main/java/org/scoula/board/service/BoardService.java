package org.scoula.board.service;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;

import java.util.List;
import java.util.Optional;

public interface BoardService
{
    public List<BoardDTO> getList();

    public BoardDTO get(Long no);

    public BoardDTO create(BoardDTO board);

    public BoardDTO update(BoardDTO board);

    public BoardDTO delete(Long no);

    public BoardAttachmentVO getAttachment(Long no); // 다운 받을 때 사용

    public boolean deleteAttachment(Long no); // 수정할 때 삭제
}
