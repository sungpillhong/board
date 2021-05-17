package com.insight.board.controller;


import com.insight.board.service.JpaBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class JpaBoardController {

    @Autowired
    private JpaBoardService jpaBoardService;
}
