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

    //게시글작성 폼
    @RequestMapping("/board/openBoardWrite.do")
    public ModelAndView openBoardWrite() throws Exception{
        ModelAndView mv = new ModelAndView("/board/boardWrite");
        return mv;
    }

    /*
     <form>의 action 속성에 지정된 insertBoard.do를 확인할 수 있다.
     */
    @RequestMapping("/board/insertBoard.do")
    public ModelAndView insertBoard(BoardDto boardDto)throws Exception{
        String url = "/board/openBoardList.do";
        boardService.insertBoard(boardDto);
        /*
         글작성후 openBoardList.do로 리다이렉션
         */
        return new ModelAndView("redirect:"+ url);
    }

    @RequestMapping("/board/openBoardDetail.do")
    public ModelAndView openBoardDetail(int boardIdx)throws Exception{
        ModelAndView mv = new ModelAndView("/board/boardDetail");

        BoardDto board = boardService.selectBoardDetail(boardIdx);
        mv.addObject("board",board);

        return mv;
    }

    @RequestMapping("/board/updateBoard.do")
    public ModelAndView updateBoard(BoardDto board) throws Exception{
        String url = "/board/openBoardList.do";

        boardService.updateBoard(board);

        return new ModelAndView("redirect:"+url);

    }

    @RequestMapping("/board/deleteBoard.do")
    public ModelAndView deleteBoard(int boardIdx) throws Exception{
        String url ="/board/openBoardList.do";

        boardService.deleteBoard(boardIdx);

        return new ModelAndView("redirect:"+url);
    }
}
