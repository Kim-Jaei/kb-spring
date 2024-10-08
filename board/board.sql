drop table if exists tbl_board;

create table tbl_board(
    no  integer auto_increment primary key, -- 클러스터형 index
    title   varchar(200) not null,
    content text, -- 길이 제한 없는 데이터형
    writer  varchar(50) not null, -- FK
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

select * from tbl_board;

