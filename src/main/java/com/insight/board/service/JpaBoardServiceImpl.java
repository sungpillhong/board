package com.insight.board.service;

import com.insight.board.entity.BoardEntity;
import com.insight.board.repository.JpaBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaBoardServiceImpl implements  JpaBoardService{

    @Autowired
    JpaBoardRepository jpaBoardRepository;

    @Override
    public List<BoardEntity> selectBoardList() throws Exception {
        return jpaBoardRepository.findAllByOrderByBoardIdxDesc();
    }

    @Override
    public void saveBoard(BoardEntity board) throws Exception {
        board.setCreatorId("admin");
        jpaBoardRepository.save(board);
    }

    @Override
    public BoardEntity selectBoardDetail(int boardIdx) throws Exception {
        //Optional 클래스는 어떤객체의 값으로 null이 아닌 값을 가지고 있다.
        //즉, Optional 클래스는 절대로 null이 아니기 때문에 NPE이 발생하지 않는다.
        //만약 객체의 값이 존재한다면 isPresent 메서드는 true를 반환하고 get메서드로 값을 가져올 수 있다.
        Optional<BoardEntity> optional = jpaBoardRepository.findById(boardIdx);
        if(optional.isPresent()){
            BoardEntity board = optional.get();
            board.setHitCnt(board.getHitCnt()+1);
            jpaBoardRepository.save(board);
            return board;
        }else{
            throw new NullPointerException();
        }
    }

    @Override
    public void deleteBoard(int boardIdx) {
        jpaBoardRepository.deleteById(boardIdx);
    }
}
