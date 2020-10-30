package com.yidiandian.validate;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/22 20:23
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public class ImageCode extends ValidateCode {
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn){
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
        super(code, expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
