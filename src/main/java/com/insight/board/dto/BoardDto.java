package com.insight.board.dto;

import lombok.Data;

import java.util.Date;

//@Data는 롬복의 어노테이션으로 모든 필드에 getter,setter,toString,hashCode,equals 메소드도 생성해줌
@Data
public class BoardDto { 
    //DTO란 애플리케이션내의 각 계층 간 데이터를 주고 받는데 사용되는 객체
    private int boardIdx;
    private String title;
    private String contents;
    private int hitCnt;
    private String creatorId;
    private String createdDatetime;
    private String updaterId;
    private String updatedDatetime;
    //t_board 테이블컬럼과 매칭되는 변수
}
