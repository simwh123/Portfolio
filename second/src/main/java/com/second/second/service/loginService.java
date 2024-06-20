package com.second.second.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.second.second.model.loginUser;
import com.second.second.repository.loignRepository;

@Service
public class loginService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private final loignRepository login;

    private String numOk;

    private String findUserId;

    public loginService(loignRepository login) {
        this.login = login;
    }

    public boolean checkID(String Id, String pw) {
        boolean ck = false;
        if (login.findById(Id).isPresent()) {
            loginUser loginck = login.findById(Id).get();
            String userId = loginck.getId();
            String userPw = loginck.getPw();
            if (Id.equals(userId) && pw.equals(userPw)) {
                ck = true;
            }
        }
        return ck;
    }

    public boolean findID(String name, String email) {
        Optional<loginUser> userEmail = login.findByEmail(email);
        boolean check = false;
        if (userEmail.isPresent()) {
            loginUser user = userEmail.get();
            String userName = user.getName();
            this.findUserId = user.getId();
            if (name.equals(userName)) {

                check = true;
            }
        }
        return check;
    }

    public String checkNom(String email) {
        this.numOk = Nomber();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("인증번호");
        message.setText(numOk);
        message.setFrom("tlatlek@naver.com");
        mailSender.send(message);

        return numOk;
    }

    public String pw(String pw) {
        String check = "";
        if (pw.equals(this.numOk)) {
            check = "인증성공";
        } else {
            check = "인증실패";
        }
        return check;
    }

    public String Nomber() {
        String numOk = "";
        for (int i = 0; i < 4; i++) {
            int randomNum = (int) (Math.random() * 10);
            numOk += Integer.toString(randomNum);
        }
        return numOk;
    }

    public String userId() {
        return this.findUserId;
    }
}
