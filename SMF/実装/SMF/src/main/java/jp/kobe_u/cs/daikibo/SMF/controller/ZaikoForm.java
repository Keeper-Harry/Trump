package jp.kobe_u.cs.daikibo.SMF.controller;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ZaikoForm {
    @NotBlank
    String name; //食材名
    String amount; //量
    Date expirationDate; //賞味期限
}