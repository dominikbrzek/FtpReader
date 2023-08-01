package com.example.ftpreader.application.model;

import lombok.Builder;
import lombok.Data;

/**
 * Create FTP config request
 * <br>
 * <p/>
 * Creation date: 01/08/2023<br>
 *
 * @author dobr
 */
@Data
@Builder
public class FtpConfigDetails {

    public String name;
    public String address;
    public String login;
    public String password;
    public Integer port;
    public Boolean shedullingEnabled;

}
