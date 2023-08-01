package com.example.ftpreader.adapter.repository;

import com.example.ftpreader.adapter.entity.FtpConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * FTP config entity repository
 * <br>
 * <p/>
 * Creation date: 30/07/2023<br>
 *
 * @author dobr
 */
public interface FtpConfigEntityRepository extends JpaRepository<FtpConfigEntity, UUID> {
}
