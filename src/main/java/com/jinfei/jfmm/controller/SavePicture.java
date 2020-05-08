package com.jinfei.jfmm.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/SavePicture")
public class SavePicture {
    /**
     * 获取图片返回到前端
     * @param img_path
     * @return
     */
    @RequestMapping("/getFileList")
    @ResponseBody
    protected String getFileList(@RequestParam("img_path") String img_path, HttpServletResponse response) throws IOException {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
//            ips = new FileInputStream(new File(img_path.substring(0,img_path.indexOf("."))));
            ips = new FileInputStream(new File(img_path));
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        } catch (Exception  e) {
            e.printStackTrace();
        }finally {
            out.close();
            ips.close();
        }

//        ArrayList<String> fileList = new ArrayList<>();
//        try {
//            fileList = getFiles(img_path);
//            return fileList;
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
        return null;
    }

    /**
     * 上传图片到服务器并返回图片保存路径
     * @param request
     * @param
     * @return
     */
    @RequestMapping(value = "upload")
    @ResponseBody
    public Map<String,Object> upload(HttpServletRequest request,HttpServletResponse response,MultipartFile file){
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
//        Map<String,MultipartFile> files = multipartRequest.getFileMap();
//
        try {
            Map<String,Object> map = new HashMap<>();
//
//            String path = "D:/jfmm_idetl_picture/";//上传保存的路径
//            if (mkDirectory(path)) {
//                System.out.println(path + "路径建立完毕");
//            }
//
//            System.out.println("asdf---"+files);
//
//            for(Map.Entry<String, MultipartFile> entity:files.entrySet()) {
//                MultipartFile f = entity.getValue();
//                if(f!=null&&!f.isEmpty()){
//                    System.out.println("name-------"+f.getOriginalFilename());
//                }
//            }
//            for (MultipartFile file:files ){
//                System.out.println("123---"+file);
//                String name = file.getOriginalFilename();
//                String fileName = changeName(name);
//                String rappendix = "D:/jfmm_idetl_picture/" + fileName;
//                fileName = path + "\\" + fileName;
//                File file1 = new File(fileName);
//                file.transferTo(file1);
//
//                map.put("path_"+fileName,rappendix);
//            }
//            Cookie cookie = new Cookie("img_path", JSON.toJSONString(map));
//            cookie.setMaxAge(3600);
//            cookie.setPath("/");
//            response.addCookie(cookie);


            String name = file.getOriginalFilename();
//            String path = request.getServletContext().getRealPath("")+"/TransferOrders/";//上传保存的路径
            String path = "D:/jfmm_idetl_picture/";//上传保存的路径
            String fileName = changeName(name);

            System.out.println(fileName);

            String rappendix = "D:/jfmm_idetl_picture/" + fileName;
            fileName = path + "\\" + fileName;
            File file1 = new File(fileName);
            if (mkDirectory(path)) {
                System.out.println(path + "路径建立完毕");
            }
            file.transferTo(file1);

            System.out.println(rappendix);
            Cookie cookie = new Cookie("img_path",rappendix);
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            response.addCookie(cookie);

            map.put("path",rappendix);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    /**
     * 更改图片名称
     * @param oldName
     * @return
     */
    public static String changeName(String oldName){
        Random r = new Random();
        Date d = new Date();
        String newName = oldName.substring(oldName.indexOf('.'));
        newName = r.nextInt(99999999) + d.getTime() + newName;
        return newName;
    }

    /**
     * 判断图片保存路径，如果不存在则新建路径
     * @param path
     * @return
     */
    public static boolean mkDirectory(String path) {
        File file = null;
        try {
            file = new File(path);
            if (!file.exists()) {
                return file.mkdirs();
            }
            else{
                return false;
            }
        } catch (Exception e) {
        } finally {
            file = null;
        }
        return false;
    }

    /**
     * 通过ajax请求获取传入的文件路径里边的文件fileList数组
     * @param filePath
     * @return
     */
    public static ArrayList<String> getFiles(String filePath) {
        ArrayList<String> fileList = new ArrayList<String>();
        File root = new File(filePath.substring(0,filePath.indexOf(".")));
        File[] files = root.listFiles();
        System.out.println(files);
        for (File file : files) {
            if (file.isDirectory()) {
                /*
                 * 递归调用
                 */
                getFiles(file.getAbsolutePath());
                fileList.add(file.getAbsolutePath());
            } else {
                String picPathStr = file.getAbsolutePath();
//              String picPathStr = file.getAbsolutePath().replaceAll("\\\\","//");
                fileList.add(picPathStr);
            }
        }
        /*for(String str:fileList){
            System.out.println(str);
        }*/
        return fileList;
    }
}
