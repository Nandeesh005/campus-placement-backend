package com.jobportal.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

@RestController

@RequestMapping("/files")

@CrossOrigin("*")
public class FileUploadController {

    // ==========================================
    // UPLOAD DIRECTORY
    // ==========================================
    private static final String UPLOAD_DIR =
            System.getProperty("user.dir")
            + File.separator
            + "uploads";

    // ==========================================
    // UPLOAD FILE
    // ==========================================
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(

            @RequestParam("file")
            MultipartFile file

    ) {

        try {

            // CHECK EMPTY
            if (file.isEmpty()) {

                return ResponseEntity
                        .badRequest()
                        .body("File is empty");
            }

            // CREATE FOLDER
            File folder =
                    new File(UPLOAD_DIR);

            if (!folder.exists()) {

                folder.mkdirs();
            }

            // SAFE FILE NAME
            String fileName =
                    System.currentTimeMillis()
                    + "_"
                    + file.getOriginalFilename()
                            .replace(" ", "_");

            // FILE PATH
            Path filePath =
                    Paths.get(
                            UPLOAD_DIR,
                            fileName
                    );

            // SAVE FILE
            Files.write(
                    filePath,
                    file.getBytes()
            );

            // RETURN FILE PATH
            return ResponseEntity.ok(
                    "/uploads/" + fileName
            );

        } catch (IOException e) {

            e.printStackTrace();

            return ResponseEntity
                    .badRequest()
                    .body(
                      "Upload failed"
                    );
        }
    }

    // ==========================================
    // DOWNLOAD FILE
    // ==========================================
    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadFile(

            @PathVariable
            String fileName

    ) {

        try {

            Path filePath =
                    Paths.get(
                            UPLOAD_DIR,
                            fileName
                    );

            File file =
                    filePath.toFile();

            // CHECK EXISTS
            if (!file.exists()) {

                return ResponseEntity
                        .notFound()
                        .build();
            }

            // READ FILE
            byte[] fileContent =
                    Files.readAllBytes(
                            filePath
                    );

            return ResponseEntity.ok()

                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename="
                                    + fileName
                    )

                    .contentType(
                            MediaType.APPLICATION_PDF
                    )

                    .body(fileContent);

        } catch (IOException e) {

            e.printStackTrace();

            return ResponseEntity
                    .badRequest()
                    .body(
                      "Download failed"
                    );
        }
    }
}