package org.scoula.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardVO {
    private Long no;
    private String title;
    private String content;
    private String writer;

    private List<BoardAttachmentVO> attaches; // VO 객체를 list로 가짐

    private Date regDate; // sql 네이밍이랑 다름
    private Date updateDate; // sql 네이밍이랑 다름
}
