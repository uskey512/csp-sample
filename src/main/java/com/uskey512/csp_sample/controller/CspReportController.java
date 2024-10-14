package com.uskey512.csp_sample.controller;

import org.slf4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class CspReportController {

    private static final Logger logger = LoggerFactory.getLogger(CspReportController.class.getName());

    // ObjectMapper to pretty print JSON
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectWriter prettyWriter = objectMapper.writerWithDefaultPrettyPrinter();

    @PostMapping(value = "/csp_reports", consumes = { "application/csp-report" })
    public ResponseEntity<String> postCspReport(@RequestBody Object jsonData, @RequestHeader("Content-Type") String contentType) {
        try {
            String prettyJson = prettyWriter.writeValueAsString(jsonData);
            logger.info("Received JSON:\n{}", prettyJson);
        } catch (Exception e) {
            logger.error("Error processing JSON", e);
            return ResponseEntity.badRequest().body("Invalid JSON format");
        }

        // Return a success response
        return ResponseEntity.ok("JSON received and logged");
    }
}
