package com.insight.board.service;

import com.insight.board.dto.BoardDto;
import com.insight.board.entity.BoardEntity;

import java.util.List;

public interface JpaBoardService {

    List<BoardEntity> selectBoardList() throws Exception;

    void saveBoard(BoardEntity board) throws Exception;

    BoardEntity selectBoardDetail(int boardIdx) throws Exception;

    void deleteBoard(int boardIdx);

}
