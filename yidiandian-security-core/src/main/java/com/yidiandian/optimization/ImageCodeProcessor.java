package com.yidiandian.optimization;

import com.yidiandian.validate.ImageCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/28 20:27
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Component("imageCodeProcess")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws IOException {
        ImageIO.write( imageCode.getImage(),"JPEG",request.getResponse().getOutputStream() );
    }
}
