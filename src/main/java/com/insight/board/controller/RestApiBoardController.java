package com.insight.board.controller;

import com.insight.board.dto.BoardDto;
import com.insight.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class RestApiBoardController {

    @Autowired
    private BoardService boardService;

    /*
      RestAPI로 변경
      value 속성으로 주소를 지정하고 method 속성으로 요청 방식을 정의
     */
    @ApiOperation(value = "게시글 목록조회")
    @RequestMapping(value = "/api/board", method = RequestMethod.GET)
    public List<BoardDto> openBoardList() throws Exception{
        return boardService.selectBoardList();
    }

    @ApiOperation(value = "게시글 작성")
    @RequestMapping(value = "/api/board/write", method = RequestMethod.POST)
    public void insertBoard(@RequestBody BoardDto boardDto) throws Exception{
        boardService.insertBoard(boardDto);
    }

    /*
     @PathVariable = 메서드의 파라미터가 URI의 변수로 사용되는 것을 의미한다.
     */
    @RequestMapping(value = "/api/board/{boardIdx}",method = RequestMethod.GET)
    @ApiOperation(value = "게시글 상세조회")
    public BoardDto openBoardDetail(@PathVariable("boardIdx") @ApiParam(value = "게시글번호") int boardIdx)throws Exception{
        return boardService.selectBoardDetail(boardIdx);
    }

    /*
    https://imbf.github.io/spring/2020/05/03/Spring-HiddenHttpMethodFilter.html
    HTML은 Post와 Get 방식의 요청만 지원하지만 스프링은 웹 브라우저에서 사용되는 Post,Get 방식을
    이용하여 Put과 Delete 방식을 사용할 수 있는 기능을 지원하는데 HiddenHttpMethodFilter가 그것이다. 위의 블로그에 잘 정리되어있다.

     */
    @RequestMapping(value = "/api/board/{boardIdx}", method = RequestMethod.POST)
    public String updateBoard(@RequestBody BoardDto boardDto)throws Exception{
        boardService.updateBoard(boardDto);
        return "redirect:/api/board";
    }

    @RequestMapping(value = "/api/boardDel/{boardIdx}", method = RequestMethod.POST)
    public String deleteBoard(@PathVariable("boardIdx") int boardIdx)throws Exception{
        boardService.deleteBoard(boardIdx);
        return "redirect:/api/board";
    }

}
