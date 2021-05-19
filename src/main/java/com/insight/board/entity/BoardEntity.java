package com.insight.board.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_board")
@Data
@NoArgsConstructor
public class BoardEntity {

    //@Id = PK를 나타냄
    //@GeneratedValue = 기본키의 생생 전략 1씩증가
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int boardIdx;

    //컬럼에 not null
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private int hitCnt=0;

    @Column(nullable = false)
    private String creatorId;

    //초기값 셋팅
    @Column(nullable = false)
    private LocalDateTime createdDatetime = LocalDateTime.now();

    private String updaterId = "admin";

    private LocalDateTime updatedDatetime = LocalDateTime.now();


}
