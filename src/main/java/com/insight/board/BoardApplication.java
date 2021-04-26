package com.insight.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

}
//    t_board 테이블 생성 쿼리
//    create table t_board(
//        board_idx INT(11) NOT NULL auto_increment COMMENT '글 번호',
//        title varchar(300) not null comment '제목',
//        contents text not null comment '내용',
//        hit_cnt smallint(10) not null default '0' comment '조회수',
//        created_datetime datetime not null comment '작성시간',
//        creator_id varchar(50) not null comment '작성자',
//        updated_datetime datetime not null comment '수정시간',
//        updater_id varchar(50) not null comment '수정자',
//        deleted_yn char(1) not null default 'N' comment '삭제여부',
//        primary key (board_idx)
//        );