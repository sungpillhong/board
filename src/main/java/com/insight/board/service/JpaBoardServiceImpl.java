package com.insight.board.service;

import com.insight.board.entity.BoardEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaBoardServiceImpl implements  JpaBoardService{
    @Override
    public List<BoardEntity> selectBoardList() throws Exception {
        return null;
    }

    @Override
    public void saveBoard(BoardEntity board) throws Exception {

    }

    @Override
    public BoardEntity selectBoardDetail(int boardIdx) throws Exception {
        return null;
    }

    @Override
    public void deleteBoard(int boardIdx) {

    }
}
