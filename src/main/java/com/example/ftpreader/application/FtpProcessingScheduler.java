package com.example.ftpreader.application;

import com.example.ftpreader.application.service.FtpProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * FTP processing scheduler
 * <br>
 * <p/>
 * Creation date: 01/08/2023<br>
 *
 * @author dobr
 */
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "ftp.scheduler.enabled")
public class FtpProcessingScheduler {

    private final FtpProcessingService service;

    @Scheduled(cron = "${ftp.scheduler.cron}")
    public void processFtpFiles() {
        service.process();
    }
}
