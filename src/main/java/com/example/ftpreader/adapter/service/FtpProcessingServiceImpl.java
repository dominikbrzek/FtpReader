package com.example.ftpreader.adapter.service;

import com.example.ftpreader.adapter.entity.FtpConfigEntity;
import com.example.ftpreader.adapter.model.EntityType;
import com.example.ftpreader.adapter.model.FtpClient;
import com.example.ftpreader.adapter.repository.FtpConfigEntityRepository;
import com.example.ftpreader.application.service.FtpProcessingService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Element;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.example.ftpreader.adapter.utils.StreamUtils.toStreamSafe;

/**
 * FTP processing service implementation
 * <br>
 * <p/>
 * Creation date: 01/08/2023<br>
 *
 * @author dobr
 */
@Service
@Slf4j
public class FtpProcessingServiceImpl implements FtpProcessingService {

    private final FtpConfigEntityRepository repository;
    private final File downloadDirectory;
    @PersistenceContext
    private EntityManager entityManager;

    public FtpProcessingServiceImpl(@Autowired FtpConfigEntityRepository repository,
                                    @Value("${ftp.download-path}") String downloadDirectory) {
        this.repository = repository;
        this.downloadDirectory = new File(downloadDirectory);
    }

    @Override
    @Transactional
    public void process() {
        log.info("Ftp processing started");
        var ftpConfigs = repository.findFtpConfigEntitiesBySchedulingEnabledIsTrue();
        if (ftpConfigs.isEmpty()) {
            return;
        }
        clearLocalFiles();
        downloadFilesFromRemote(ftpConfigs);
        processLocalFiles();
    }


    @SuppressWarnings(value = "all")
    private void clearLocalFiles() {
        log.info("Clearing download directory from old files");
        toStreamSafe(this.downloadDirectory.listFiles()).forEach(File::delete);
    }

    private void downloadFilesFromRemote(List<FtpConfigEntity> ftpConfigs) {
        toStreamSafe(ftpConfigs)
                .map(config -> FtpClient.builder()
                        .user(config.getLogin())
                        .password(config.getPassword())
                        .filePath(config.getFilePath())
                        .port(config.getPort())
                        .server(config.getAddress())
                        .build())
                .forEach(this::downloadFiles);
    }

    private void downloadFiles(FtpClient client) {
        try {
            client.open();
            client.listFileNamesStream().forEach(fileName ->
                    client.downloadFile(fileName, downloadDirectory.getPath() + "/" + fileName));
        } catch (Exception e) {
            log.error("Error downloading files from ftp client: {}", client.getServer());
        } finally {
            client.close();
        }
    }

    private void processLocalFiles() {
        toStreamSafe(this.downloadDirectory.listFiles())
                .forEach(file -> Arrays.stream(EntityType.values()).forEach(type -> {
                            try {
                                var list = XMLHelper.getElementsByTagName(file, type.getTag());
                                if (list != null && list.getLength() != 0) {
                                    for (int i = 0; i < list.getLength(); i++) {
                                        entityManager.persist(type.getType().getDeclaredConstructor(Element.class).newInstance(list.item(i)));
                                    }
                                }
                            } catch (Exception e) {
                                log.error("Error during processing entities", e);
                            }
                        })
                );
    }
}
