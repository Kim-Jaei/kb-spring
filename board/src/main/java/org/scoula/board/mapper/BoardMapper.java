package org.scoula.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.scoula.board.domain.BoardVO;

public interface BoardMapper {

// map(ResultSet rs) -> MyBatis가 자동으로 해줌
// ex) select no, title ... -> setNo, setTitle 호출
//@Select("select * from tbl_board order by no desc")

    public List<BoardVO> getList();

    public BoardVO get(Long id);

    // 어차피 1개 생성하는 거라 굳이 int 해줄 필요는 ...
    public void create(BoardVO board);
}
