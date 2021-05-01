package com.insight.board.service;

import com.insight.board.dto.BoardDto;

import java.util.List;

public interface BoardService {

    List<BoardDto> selectBoardList() throws Exception;
    void insertBoard(BoardDto boardDto)throws Exception;
}
