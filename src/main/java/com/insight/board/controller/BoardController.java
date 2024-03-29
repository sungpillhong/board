package com.insight.board.controller;

import com.insight.board.dto.BoardDto;
import com.insight.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@PropertySource("classpath:application.properties")
@Log4j2
public class BoardController {

    @Autowired
    private BoardService boardService;

    /*
      @RequestMapping의 값으로 /board/openBoardList.do가 지정되어 있는데 웹 브라우저에서
      /board/openBoardList.do라는 주소를 호출하면 스프링 디스패처는 호출된 주소와 @RequestMapping
      어노테이션의 값이 동일한 메서드를 찾아서 한다

    @RequestMapping("/board/openBoardList.do")
    public ModelAndView openBoardList() throws Exception{
        //templates 폴더 아래에 있는 board/boardList.html을 의미한다.
        ModelAndView mv = new ModelAndView("board/boardList");
//        int i = 10 / 0 ;
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

    @RequestMapping("/board/insertBoard.do")
    public ModelAndView insertBoard(BoardDto boardDto)throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/board/openBoardList.do");
        boardService.insertBoard(boardDto);

         //글작성후 openBoardList.do로 리다이렉션
        return new ModelAndView("redirect:"+ url);
    }

    @RequestMapping("/board/openBoardDetail.do")
    public ModelAndView openBoardDetail(int boardIdx)throws Exception{
        ModelAndView mv = new ModelAndView("board/boardDetail");

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

    */

    /*
      RestAPI로 변경
      value 속성으로 주소를 지정하고 method 속성으로 요청 방식을 정의
     */
    @RequestMapping(value = "board", method = RequestMethod.GET)
    public ModelAndView openBoardList() throws Exception{
        ModelAndView mv = new ModelAndView("board/restBoardList");

        List<BoardDto> list = boardService.selectBoardList();
        mv.addObject("list",list);

        return mv;
    }

    @RequestMapping(value = "board/write", method = RequestMethod.GET)
    public ModelAndView openBoardWrite() throws Exception{
        ModelAndView mv = new ModelAndView("board/restBoardWrite");
        return mv;
    }

    @RequestMapping(value = "board/write", method = RequestMethod.POST)
    public ModelAndView insertBoard(BoardDto boardDto)throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/board");
        boardService.insertBoard(boardDto);
        return mv;
    }

    /*
     @PathVariable = 메서드의 파라미터가 URI의 변수로 사용되는 것을 의미한다.
     */
    @RequestMapping(value = "board/{boardIdx}",method = RequestMethod.GET)
    @ApiOperation(value = "게시글 상세조회", response = BoardDto.class)
    public ModelAndView openBoardDetail(@PathVariable("boardIdx") int boardIdx)throws Exception{
        ModelAndView mv = new ModelAndView("board/restBoardDetail");

        BoardDto boardDto = boardService.selectBoardDetail(boardIdx);
        mv.addObject("board",boardDto);

        return mv;
    }

    /*
    https://imbf.github.io/spring/2020/05/03/Spring-HiddenHttpMethodFilter.html
    HTML은 Post와 Get 방식의 요청만 지원하지만 스프링은 웹 브라우저에서 사용되는 Post,Get 방식을
    이용하여 Put과 Delete 방식을 사용할 수 있는 기능을 지원하는데 HiddenHttpMethodFilter가 그것이다. 위의 블로그에 잘 정리되어있다.

     */
    @RequestMapping(value = "board/{boardIdx}", method = RequestMethod.POST)
    public ModelAndView updateBoard(BoardDto boardDto)throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/board");

        boardService.updateBoard(boardDto);
        return mv;
    }

    @RequestMapping(value = "boardDel/{boardIdx}", method = RequestMethod.POST)
    public ModelAndView deleteBoard(@PathVariable("boardIdx") int boardIdx)throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/board");

        boardService.deleteBoard(boardIdx);
        return mv;
    }

}
