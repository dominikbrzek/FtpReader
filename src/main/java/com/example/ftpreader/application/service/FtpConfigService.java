package com.example.ftpreader.application.service;

import com.example.ftpreader.application.model.FtpConfigDetails;
import com.example.ftpreader.application.model.FtpConfigSimpleData;

import java.util.List;
import java.util.UUID;

/**
 * FTP config service
 * <br>
 * <p/>
 * Creation date: 01/08/2023<br>
 *
 * @author dobr
 */
public interface FtpConfigService {

    List<FtpConfigSimpleData> getFtpConfigs();

    FtpConfigDetails getFtpConfigDetails(UUID id);

    void createFtpConfig(FtpConfigDetails request);

    void updateFtpConfig(UUID id, FtpConfigDetails request);

    void deleteFtpConfig(UUID id);
}
