package io.github.utilityframework.boot.example.web.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Haxin Wu
 * @since 1.0.0
 */
@RestController
@RequestMapping("/stream")
public class StreamController {

    @RequestMapping("/print/{content}")
    public void print(@PathVariable String content, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.print(content);
    }

    @RequestMapping("/download")
    public InputStreamResource download() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("download.txt");
        return new InputStreamResource(classPathResource.getInputStream());
    }


}
