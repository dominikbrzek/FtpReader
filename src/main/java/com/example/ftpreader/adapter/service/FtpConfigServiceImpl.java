package com.example.ftpreader.adapter.service;

import com.example.ftpreader.adapter.entity.FtpConfigEntity;
import com.example.ftpreader.adapter.repository.FtpConfigEntityRepository;
import com.example.ftpreader.application.model.FtpConfigDetails;
import com.example.ftpreader.application.model.FtpConfigSimpleData;
import com.example.ftpreader.application.service.FtpConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.ftpreader.adapter.utils.StreamUtils.toStreamSafe;

/**
 * FTP config service implementation
 * <br>
 * <p/>
 * Creation date: 01/08/2023<br>
 *
 * @author dobr
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class FtpConfigServiceImpl implements FtpConfigService {

    private final FtpConfigEntityRepository repository;

    @Override
    public List<FtpConfigSimpleData> getFtpConfigs() {
        log.info("Get FTP configs");
        return toStreamSafe(repository.findAll())
                .map(entity -> FtpConfigSimpleData.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .schedulingEnabled(entity.isSchedulingEnabled())
                        .build())
                .toList();
    }

    @Override
    public FtpConfigDetails getFtpConfigDetails(UUID id) {
        log.info("Get FTP config for id: {}", id);
        return repository.findById(id)
                .map(e -> FtpConfigDetails.builder()
                        .name(e.getName())
                        .login(e.getLogin())
                        .address(e.getAddress())
                        .password(e.getPassword())
                        .port(e.getPort())
                        .schedulingEnabled(e.isSchedulingEnabled())
                        .build())
                .orElseThrow();
    }

    @Override
    public void createFtpConfig(FtpConfigDetails request) {
        log.info("Create FTP config with name: {}", request.getName());
        var entity = new FtpConfigEntity();
        populateEntityAndSave(entity, request);
    }

    @Override
    public void updateFtpConfig(UUID id, FtpConfigDetails request) {
        log.info("Update FTP config for id: {}", id);
        var entity = repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Not found FTP configuration with given id"));
        populateEntityAndSave(entity, request);
    }

    @Override
    public void deleteFtpConfig(UUID id) {
        repository.deleteById(id);
    }

    private void populateEntityAndSave(FtpConfigEntity entity, FtpConfigDetails request) {
        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        if (request.getPassword() != null) {
            entity.setPassword(request.getPassword());
        }
        if (request.getLogin() != null) {
            entity.setLogin(request.getLogin());
        }
        if (request.getAddress() != null) {
            entity.setAddress(request.getAddress());
        }
        if (request.getFilePath() != null) {
            entity.setFilePath(request.getFilePath());
        }
        if (request.getPort() != null) {
            entity.setPort(request.getPort());
        }
        if (request.getSchedulingEnabled() != null) {
            entity.setSchedulingEnabled(request.getSchedulingEnabled());
        }
        repository.save(entity);
    }

}
