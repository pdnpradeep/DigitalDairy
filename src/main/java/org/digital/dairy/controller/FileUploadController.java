package org.digital.dairy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Pradeep.P on 18-09-2015.
 */
@Controller
public class FileUploadController {

    @RequestMapping("/upload")
    public String upload(){
        return "upload";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
  //  @ResponseBody
    public String uploadFileHandler(@RequestParam("file") MultipartFile file){

        ZipEntry zEntry = null;
        ZipInputStream zin =null;
        if(!file.isEmpty()){
            try {
                byte[] bytes = file.getBytes();
                System.out.println("filename is ::::"+file.getOriginalFilename());
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                 zin = new ZipInputStream(new BufferedInputStream(new FileInputStream(serverFile)));
                while((zEntry = zin.getNextEntry()) != null){
                    if(zEntry.isDirectory()){
                        File folder = new File(dir.getAbsolutePath()
                                + File.separator+zEntry.getName());
                        System.out.println("file folder::"+folder);
                        folder.mkdir();
                        continue;
                    }else {
                        System.out.println(dir.getAbsolutePath() + File.separator+zEntry.getName());
                        FileOutputStream fos = new FileOutputStream(dir.getAbsolutePath()
                                + File.separator+zEntry.getName());
                        int size = 0;
                        byte[] tmp = new byte[4*1024];
                        while((size = zin.read(tmp)) != -1){
                            fos.write(tmp, 0 , size);
                        }
                        fos.flush();
                        fos.close();
                    }
                }
            }
            catch (Exception e){
                System.out.println("Exception at time of file upload process");
            }
            finally {
                try {
                    zin.closeEntry();
                    zin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "upload";
    }
}
