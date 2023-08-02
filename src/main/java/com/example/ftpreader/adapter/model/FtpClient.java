package com.example.ftpreader.adapter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Ftp client
 * <br>
 * <p/>
 * Creation date: 01/08/2023<br>
 *
 * @author dobr
 */
@Getter
@Builder
@Slf4j
@AllArgsConstructor
public class FtpClient {
    private String server;
    private int port;
    private String user;
    private String password;
    private String filePath;
    private FTPClient ftp;

    public void open() throws IOException {
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        ftp.connect(server, port);
        if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
            ftp.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }
        if (!ftp.login(user, password)) {
            ftp.disconnect();
            throw new IOException("Exception in logging into FTP Server");
        }
        ftp.enterLocalPassiveMode();
        ftp.setFileType(FTP.ASCII_FILE_TYPE);
    }

    @SneakyThrows
    public void close() {
        ftp.disconnect();
    }

    public Stream<String> listFileNamesStream() throws IOException {
        FTPFile[] files = ftp.listFiles();
        return Arrays.stream(files).map(FTPFile::getName);
    }

    public void downloadFile(String source, String destination) {
        try {
            FileOutputStream out = new FileOutputStream(destination);
            ftp.retrieveFile(source, out);
        } catch (Exception e) {
            log.error("Exception during downloading file: {}", source, e);
        }
    }
}
