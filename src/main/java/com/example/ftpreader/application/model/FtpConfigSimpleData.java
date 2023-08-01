package com.example.ftpreader.application.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * FTP config simple data
 * <br>
 * <p/>
 * Creation date: 01/08/2023<br>
 *
 * @author dobr
 */
@Data
@Builder
public class FtpConfigSimpleData {

    public UUID id;
    public String name;
    public boolean schedulingEnabled;

}
