package com.demo03.demo03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.demo03.demo03.model.testModel;
import com.demo03.demo03.repository.testRepository;

@Service
public class testService {
    @Autowired
    private testRepository tRepository;
    @Autowired
    private JavaMailSender mailSender;

    public String userInfo(testModel tModel) {
        boolean checkId = tRepository.findById(tModel.getId()).isEmpty();
        try {
            if (checkId == true) {
                tRepository.save(tModel);
            }
        } catch (Exception e) {

            System.out.println("에러");
        }
        return "test";
    }

    public boolean userGetId(String userIdCheck) {
        boolean checkId = tRepository.findById(userIdCheck).isEmpty();
        return checkId;
    }

    public String emailCheck(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        String checkNum = "";
        for (int i = 0; i < 4; i++) {
            int randomNum = (int) (Math.random() * 10);
            checkNum += Integer.toString(randomNum);
        }
        String numOk = checkNum;
        message.setTo(email);
        message.setSubject("인증번호");
        message.setText(numOk);
        message.setFrom("tlatlek@naver.com");
        mailSender.send(message);
        return numOk;
    }
}
