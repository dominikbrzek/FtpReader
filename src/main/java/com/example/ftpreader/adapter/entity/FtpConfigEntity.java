package com.example.ftpreader.adapter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

/**
 * FTP config entity
 * <br>
 * <p/>
 * Creation date: 01/08/2023<br>
 *
 * @author dobr
 */
@Entity
@Getter
@Setter
@Table(name = "FTP_CONFIG")
public class FtpConfigEntity {

    @Id
    @Column(name = "ID")
    @JdbcTypeCode(Types.VARCHAR)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SERVER_ADDRESS")
    private String address;

    @Column(name = "SERVER_LOGIN")
    private String login;

    @Column(name = "SERVER_PASSWORD")
    private String password;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "SERVER_PORT")
    private Integer port;

    @Column(name = "SCHEDULING_ENABLED")
    private boolean schedulingEnabled;
}
