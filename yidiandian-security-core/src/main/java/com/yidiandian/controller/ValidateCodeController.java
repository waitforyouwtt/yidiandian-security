package com.yidiandian.controller;

import com.yidiandian.constants.SecurityConstants;
import com.yidiandian.properties.SecurityProperties;
import com.yidiandian.optimization.ValidateCodeProcessor;
import com.yidiandian.validate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/22 20:32
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_CODE";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Autowired
    SmsCodeSender smsCodeSender;

   /* @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;*/

    /**
     * 第三次优化
     * @param request
     * @param response
     * @param type
     * @throws IOException
     * @throws ServletRequestBindingException
     */
    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws IOException, ServletRequestBindingException {
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }

    /**
     * 再次优化
     * @param request
     * @param response
     * @throws IOException
     */
    /*@GetMapping("/code/image")
    public void generateImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode) validateCodeGenerator.generateCode(new ServletWebRequest( request ));
        sessionStrategy.setAttribute( new ServletWebRequest( request ),SESSION_KEY+"_IMAGE",imageCode );
        ImageIO.write( imageCode.getImage(),"JPEG" ,response.getOutputStream());
    }

    @GetMapping("/code/sms")
    @ResponseBody
    public void generateSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        ValidateCode smsCode = validateCodeGenerator.generateCode(new ServletWebRequest( request ));
        sessionStrategy.setAttribute( new ServletWebRequest( request ),SESSION_KEY+"_SMS",smsCode );
        //请求里必须包含mobile 字段
        String mobile = ServletRequestUtils.getRequiredStringParameter( request,"mobile" );
        smsCodeSender.sendSmsCode( mobile,smsCode.getCode() );
    }*/


    /*@GetMapping("/code/image")
    public void generateImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //优化前
       *//* ImageCode imageCode = (ImageCode) createImageCode(request);
        sessionStrategy.setAttribute( new ServletWebRequest( request ),SESSION_KEY,imageCode );
        ImageIO.write( imageCode.getImage(),"JPEG" ,response.getOutputStream());*//*

       //优化后
        ImageCode imageCode = generateImageCode(new ServletWebRequest( request ));
        sessionStrategy.setAttribute( new ServletWebRequest( request ),SESSION_KEY,imageCode );
        ImageIO.write( imageCode.getImage(),"JPEG" ,response.getOutputStream());

    }*/

    private ImageCode generateImageCode(ServletWebRequest request){
      //优化点
      int width = ServletRequestUtils.getIntParameter( request.getRequest(),"width",securityProperties.getCode().getImage().getWidth() );
      int height = ServletRequestUtils.getIntParameter( request.getRequest(),"height",securityProperties.getCode().getImage().getHeight());
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        //优化点
        for (int i = 0; i < securityProperties.getCode().getImage().getLength(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();
        //优化点
        return new ImageCode( image,sRand,securityProperties.getCode().getImage().getExpireIn() );

    }
    private ImageCode createImageCode(HttpServletRequest request){
        int width = 67;
        int height = 23;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();
        return new ImageCode( image,sRand,60 );
    }

    /**
     * 生成随机背景条纹
     */
    private Color getRandColor(int fc, int bc){
        Random random = new Random(  );
        if (fc > 255){
            fc = 255;
        }
        if (bc > 255){
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color( r,g,b );
    }
}
