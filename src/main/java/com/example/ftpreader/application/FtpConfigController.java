package com.example.ftpreader.application;

import com.example.ftpreader.application.model.FtpConfigDetails;
import com.example.ftpreader.application.response.GetFtpConfigsResponse;
import com.example.ftpreader.application.service.FtpConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * FTP config controller
 * <br>
 * <p/>
 * Creation date: 01/08/2023<br>
 *
 * @author dobr
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/ftp/config",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class FtpConfigController {

    private final FtpConfigService ftpConfigService;

    @GetMapping("/configs")
    public ResponseEntity<GetFtpConfigsResponse> getFtpConfigs() {
        return ResponseEntity.ok(GetFtpConfigsResponse.of(ftpConfigService.getFtpConfigs()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FtpConfigDetails> getFtpConfigDetails(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(ftpConfigService.getFtpConfigDetails(id));
    }

    @PostMapping
    public ResponseEntity<Void> createFtpConfig(@RequestBody FtpConfigDetails request) {
        ftpConfigService.createFtpConfig(request);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFtpConfig(@PathVariable(name = "id") UUID id,
                                                @RequestBody FtpConfigDetails request) {
        ftpConfigService.updateFtpConfig(id, request);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFtpConfig(@PathVariable(name = "id") UUID id) {
        ftpConfigService.deleteFtpConfig(id);
        return ResponseEntity.ok(null);
    }
}

