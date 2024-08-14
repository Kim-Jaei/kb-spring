package org.scoula.board.mapper;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.domain.BoardVO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j
class BoardMapperTest {
    @Autowired
    private BoardMapper mapper;

    @Test
    @DisplayName("BoardMapper의 목록 불러오기")
    void getList() {
        for (BoardVO board : mapper.getList()){
            log.info(board);
        }
    }

    @Test
    @DisplayName("BoardMapper의 게시글 읽기")
    void get() {
        BoardVO board = mapper.get(2L);
        log.info(board);
    }

    @Test
    @DisplayName("BoardMapper에 목록 추가하기")
    void create() {
        BoardVO board = new BoardVO();
        board.setTitle("제목");
        board.setContent("내용");
        board.setWriter("user0");

        mapper.create(board);

        int count = mapper.create(board);

        log.info(board);
        log.info("create count: " + count);
    }

    @Test
    @DisplayName("BoardMapper의 글 수정하기")
    void update() {
        BoardVO board = new BoardVO();
        board.setNo(5L);
        board.setTitle("수정 제목");
        board.setContent("수정 내용");
        board.setWriter("user00");

        int count = mapper.update(board);

        log.info(board);
        log.info("update count: " + count);
    }

    @Test
    @DisplayName("BoardMapper의 글 삭제하기")
    void delete() {
        Long no = 2L;

        BoardVO board = mapper.get(no);
        log.info("delete post: " + board);
        log.info("delete count: " + mapper.delete(no));
    }
}