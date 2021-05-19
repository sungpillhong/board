package com.insight.board.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


//@Data는 롬복의 어노테이션으로 모든 필드에 getter,setter,toString,hashCode,equals 메소드도 생성해줌
@Data
@ApiModel(value = "BoardDto : 게시글 내용", description = "게시글 내용")
public class BoardDto { 
    //DTO란 애플리케이션내의 각 계층 간 데이터를 주고 받는데 사용되는 객체
    @ApiModelProperty(value = "게시글 번호", example = "1", required = true, position = 1)
    private int boardIdx;

    @ApiModelProperty(value = "게시글 제목")
    private String title;

    @ApiModelProperty(value = "게시글 내용")
    private String contents;

    @ApiModelProperty(value = "조회수")
    private int hitCnt;

    @ApiModelProperty(value = "작성자 아이디")
    private String creatorId;

    @ApiModelProperty(value = "작성시간")
    private String createdDatetime;

    @ApiModelProperty(value = "수정자 아이디")
    private String updaterId;

    @ApiModelProperty(value = "수정시간")
    private String updatedDatetime;
    //t_board 테이블컬럼과 매칭되는 변수
}
