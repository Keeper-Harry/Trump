package jp.kobe_u.cs.daikibo.SMF.controller;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class StockForm {
    @NotBlank
    String name; //食材名
    String amount; //量
    String expirationDate; //賞味期限
}