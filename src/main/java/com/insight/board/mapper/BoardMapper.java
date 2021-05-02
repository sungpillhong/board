package com.insight.board.mapper;

import com.insight.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardDto> selectBoardList()throws Exception;

    void insertBoard(BoardDto boardDto)throws Exception;

    void updateHitCount(int boardIdx)throws Exception;

    BoardDto selectBoardDetail(int boardIdx)throws Exception;
}
