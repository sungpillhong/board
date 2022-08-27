package com.insight.board.controller;

import com.insight.board.configuration.RecaptchaConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@PropertySource("classpath:application.properties")
@RestController
public class RecaptchaController {
    @Value("${recaptcha.secretKey}")
    private String secretKey;

    @ResponseBody
    @RequestMapping(value = "board/write/google/VerifyRecaptcha", method = RequestMethod.POST)
    public int VerifyRecaptcha(HttpServletRequest request) {
        // 시크릿 키를 캡챠를 받아올수 있는 Class에 보내서 그곳에서 값을 출력한다
        RecaptchaConfiguration.setSecretKey(secretKey);
        String gRecaptchaResponse = request.getParameter("recaptcha");
        try {
            if(RecaptchaConfiguration.verify(gRecaptchaResponse)){
                return 0; // 성공
            } else {
                return 1; // 실패
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; //에러
        }
    }
}
