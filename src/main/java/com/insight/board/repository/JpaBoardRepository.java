package com.insight.board.repository;

import com.insight.board.entity.BoardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaBoardRepository extends CrudRepository<BoardEntity, Integer> {

    //게시글 번호로 정렬해서 전체 게시글을 조회
    //규칙에 맞도록 메서드를 추가하면 실행 시 메서드의 이름에 따라 쿼리가 생성
    List<BoardEntity> findAllByOrderByBoardIdxDesc();

}
