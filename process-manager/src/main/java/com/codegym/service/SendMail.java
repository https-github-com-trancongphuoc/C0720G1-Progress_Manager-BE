package com.codegym.service;

import com.codegym.dto.AppreciateDTO;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface SendMail {
    void Appreciate(AppreciateDTO appreciateDTO) throws MessagingException, UnsupportedEncodingException;
}
