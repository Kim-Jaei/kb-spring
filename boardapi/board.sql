drop table if exists tbl_board;

create table tbl_board(
    no  integer auto_increment primary key,
    title varchar(200) not null,
    content text,
    writer  varchar(50) not null,
    reg_date    datetime default current_timestamp,
    update_date datetime default current_timestamp
);

insert into tbl_board(title, content, writer)
values
    ('테스트 제목1', '테스트 내용1', 'user00'),
    ('테스트 제목2', '테스트 내용2', 'user00'),
    ('테스트 제목3', '테스트 내용3', 'user00'),
    ('테스트 제목4', '테스트 내용4', 'user00'),
    ('테스트 제목5', '테스트 내용5', 'user00');

select * from tbl_board ORDER BY no ASC;
delete from tbl_board where no = 9;

DROP TABLE IF EXISTS tbl_board_attachment;
CREATE TABLE tbl_board_attachment (
              no INTEGER AUTO_INCREMENT PRIMARY KEY,
              filename VARCHAR(256) NOT NULL, -- 원본 파일 명
              path VARCHAR(256) NOT NULL, -- 서버에서의 파일 경로
              content_type VARCHAR(56), -- content-type
              size INTEGER, -- 파일의 크기
              bno INTEGER NOT NULL, -- 게시글 번호, FK
              reg_date DATETIME DEFAULT now(),
              CONSTRAINT FOREIGN KEY(bno) REFERENCES tbl_board(no)
);


select b.*, a.no as ano, a.bno, a.filename, a.path,
       a.content_type, a.size, a.reg_date as a_reg_date
from tbl_board b
         left outer join tbl_board_attachment a
                         on b.no = a.bno
where b.no = 10
order by filename;

alter table tbl_board_attachment
    add constraint fk_board_no
        foreign key (bno) references tbl_board(no)
            on delete cascade;

