package com.insight.board.controller;

import com.insight.board.dto.BoardDto;
import com.insight.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    /*
      @RequestMapping의 값으로 /board/openBoardList.do가 지정되어 있는데 웹 브라우저에서
      /board/openBoardList.do라는 주소를 호출하면 스프링 디스패처는 호출된 주소와 @RequestMapping
      어노테이션의 값이 동일한 메서드를 찾아서 한다
     */
    @RequestMapping("/board/openBoardList.do")
    public ModelAndView openBoardList() throws Exception{
        //templates 폴더 아래에 있는 board/boardList.html을 의미한다.
        ModelAndView mv = new ModelAndView("/board/boardList");

        List<BoardDto> list = boardService.selectBoardList();
        //list라는 이름으로 뷰에서 사용할 수 있다.
        mv.addObject("list",list);

        return mv;
    }
}
