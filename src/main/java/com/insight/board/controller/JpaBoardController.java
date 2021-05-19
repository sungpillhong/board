package com.insight.board.controller;


import com.insight.board.dto.BoardDto;
import com.insight.board.entity.BoardEntity;
import com.insight.board.service.JpaBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class JpaBoardController {

    @Autowired
    private JpaBoardService jpaBoardService;

    @RequestMapping(value = "/jpa/board", method = RequestMethod.GET)
    public ModelAndView openBoardList() throws Exception{
        ModelAndView mv = new ModelAndView("/board/jpaBoardList");

        List<BoardEntity> list = jpaBoardService.selectBoardList();
        mv.addObject("list",list);

        return mv;
    }

    @RequestMapping(value = "/jpa/board/write", method = RequestMethod.GET)
    public ModelAndView openBoardWrite() throws Exception{
        ModelAndView mv = new ModelAndView("board/jpaBoardWrite");
        return mv;
    }

    @RequestMapping(value = "/jpa/board/write", method = RequestMethod.POST)
    public ModelAndView insertBoard(BoardEntity boardEntity)throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/jpa/board");
        jpaBoardService.saveBoard(boardEntity);
        return mv;
    }

    /*
     @PathVariable = 메서드의 파라미터가 URI의 변수로 사용되는 것을 의미한다.
     */
    @RequestMapping(value = "/jpa/board/{boardIdx}",method = RequestMethod.GET)
    public ModelAndView openBoardDetail(@PathVariable("boardIdx") int boardIdx)throws Exception{
        ModelAndView mv = new ModelAndView("board/jpaBoardDetail");

        BoardEntity boardEntity = jpaBoardService.selectBoardDetail(boardIdx);
        mv.addObject("board",boardEntity);

        return mv;
    }

    /*
    https://imbf.github.io/spring/2020/05/03/Spring-HiddenHttpMethodFilter.html
    HTML은 Post와 Get 방식의 요청만 지원하지만 스프링은 웹 브라우저에서 사용되는 Post,Get 방식을
    이용하여 Put과 Delete 방식을 사용할 수 있는 기능을 지원하는데 HiddenHttpMethodFilter가 그것이다. 위의 블로그에 잘 정리되어있다.

     */
    @RequestMapping(value = "/jpa/board/{boardIdx}", method = RequestMethod.POST)
    public ModelAndView updateBoard(BoardEntity boardEntity)throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/jpa/board");

        jpaBoardService.saveBoard(boardEntity);
        return mv;
    }

    @RequestMapping(value = "/jpa/boardDel/{boardIdx}", method = RequestMethod.POST)
    public ModelAndView deleteBoard(@PathVariable("boardIdx") int boardIdx){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/jpa/board");

        jpaBoardService.deleteBoard(boardIdx);
        return mv;
    }

}
