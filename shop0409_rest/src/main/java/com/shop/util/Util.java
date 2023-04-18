package com.shop.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;

public class Util {
  public static void saveFile(MultipartFile mf, String custdir) {
    byte [] data;
    String imgname = mf.getOriginalFilename();
    try {
      data = mf.getBytes();
      FileOutputStream fo2 = new FileOutputStream(custdir+imgname);
      fo2.write(data);
      fo2.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}