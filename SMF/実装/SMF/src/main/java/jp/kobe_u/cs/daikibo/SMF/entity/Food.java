package jp.kobe_u.cs.daikibo.SMF.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long fid;
    String name;

    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt; //作成日時

    Date expirationDate;//賞味期限
    
}