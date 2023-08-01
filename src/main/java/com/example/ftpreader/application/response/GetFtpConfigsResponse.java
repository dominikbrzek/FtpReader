package com.example.ftpreader.application.response;

import com.example.ftpreader.application.model.FtpConfigSimpleData;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Get FTP configs response
 * <br>
 * <p/>
 * Creation date: 01/08/2023<br>
 *
 * @author dobr
 */
@AllArgsConstructor(staticName = "of")
public class GetFtpConfigsResponse {

    public List<FtpConfigSimpleData> configs;
}
