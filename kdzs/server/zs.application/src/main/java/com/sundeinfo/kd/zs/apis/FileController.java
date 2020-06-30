package com.sundeinfo.kd.zs.apis;

import com.sundeinfo.foundation.mvc.controller.AbstractController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;


@CrossOrigin
@RestController
@RequestMapping("images")
public class FileController extends AbstractController<FileController> {

    String url = "E:/images";

    @GetMapping(value = "file")
    public void getFile(HttpServletResponse response, @RequestParam String path) throws IOException {
        if (path.equals("null")) return;
        response.setCharacterEncoding("UTF-8");

        String[] strArray = path.split("\\.");
        int suffixIndex = strArray.length -1;
        if (strArray[suffixIndex].toLowerCase().equals("png")) {
            response.addHeader("Content-type", "image/png");
        } else {
            response.addHeader("Content-type", "image/jpeg;charset=UTF-8");
        }
        response.addHeader("cache-control", "max-age=2628000");
        String filePath = url+ "/" + path;
        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);
        int len = 0;
        byte[] buffer = new byte[1024];
        OutputStream out = response.getOutputStream();
        while ((len = inputStream.read(buffer)) > 0) {
            out.write(buffer,0,len);
        }
        inputStream.close();
    }
}
