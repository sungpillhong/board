package com.insight.board.repository;

import com.insight.board.entity.BoardEntity;
import org.springframework.data.repository.CrudRepository;

public interface JpaBoardRepository extends CrudRepository<BoardEntity, Integer> {

}
