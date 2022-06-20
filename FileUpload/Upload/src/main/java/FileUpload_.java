import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class FileUpload_ extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 判断是普通表单还是带文件表单
        if (!ServletFileUpload.isMultipartContent(req)){
            // 如果是普通表单
            System.out.println("123");
            return;
        }
        // 创建上传文件的保存路径，建议在WEB-INF目录下，安全，用户无法直接访问上传的文件
        String up = this.getServletContext().getRealPath("/WEB-INF/upload");
        // 如果目的路径不存在，则创建
        File uploadPath = new File(up);
        if (!uploadPath.exists()) {
            uploadPath.mkdir();
        }
//        // 缓存，临时文件
//        // 临时路径，如果文件大小超出预期，就试着将文件放到临时文件中，过几天自动删除或提示用户转为永久
        String temp = this.getServletContext().getRealPath("/WEB-INF/tempPath");
        File tempPath = new File(temp);
        if (!tempPath.exists()) {
            tempPath.mkdir();
        }

        // 建议使用Apache的文件上传组件实现，common-fileupload,它需要依赖commons-io组件

        // 创建DiskFileItemFactory对象，处理文件上传路径或者大小限制
//        DiskFileItemFactory factory = new DiskFileItemFactory();
        /*
        // 设置缓冲区，上传的文件大于这个缓冲区时，将他放在临时目录
        factory.setSizeThreshold(1024 * 1024); // 缓冲区大小
        factory.setRepository(tempFile); // 临时目录
         */

        // 获取ServletFileUpload
//        ServletFileUpload upload = new ServletFileUpload(factory);
        /*
        // 监听文件上传进度
        upload.setProgressListener(new ProgressListener() {
            @Override
            public void update(long pBytesRead, long pContentLength, int pItems) {
                System.out.println("总大小："+ pContentLength + "已上传：" + pBytesRead);
            }
        });
        // 处理乱码问题
        upload.setHeaderEncoding("UTF-8");
        // 设置单个文件最大值
        upload.setFileSizeMax(1024 * 1024 * 10);
        // 设置总共能上传的文件大小
        // 1024 = 1kb * 1024 = 1M * 10 = 10M
        upload.setSizeMax(1024 * 1024 * 10);
         */

        // 处理上传的文件
        // 把前端请求解析，封装成一个FileItem对象，需要从ServletFileUpload对象中获取
//        List<FileItem> fileItems = null;
//        try {
//            fileItems = upload.parseRequest(req);
//        } catch (FileUploadException e) {
//            e.printStackTrace();
//        }
//        for (FileItem fileItem:fileItems) {
//            // 判断是普通表单还是带有文件上传的表单
//            if (fileItem.isFormField()) {
//                // 普通表单
//                String name = fileItem.getFieldName();
//                String value = fileItem.getString("UTF-8"); // 处理乱码
//                System.out.println(name+" : "+value);
//            }else {
//                // 是带有文件的表单
//                String uploadFileName = fileItem.getName();
//                System.out.println("uploadFileName is : "+uploadFileName);
//                if (uploadFileName.trim().equals("") || uploadFileName == null){
//                    continue;
//                }
//                // 获取上传文件的名字
//                String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/")+1);
//                // 获取文件后缀名
//                String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
//                System.out.println("文件：[文件名："+fileName+"，后缀名："+fileExtName+" ]");
//
//                // 用UUID保证文件名唯一
//                String uuidPath = UUID.randomUUID().toString();
//
//                // 存放得到位置
//                String realPath = uploadPath + "/" + uuidPath;
//                // 给每个文件对应的文件夹
//                File realFilePath = new File(realPath);
//                if (!realFilePath.exists()){
//                    realFilePath.exists();
//                }
//
//                // 获得文件上传的流
//                InputStream inputStream = fileItem.getInputStream();
//                // 文件输出流
//                FileOutputStream outputStream = new FileOutputStream(realPath+"/"+fileName);
//
//                byte[] buffer = new byte[1024 * 1024];
//                int readLen = 0;
//                while ((readLen = inputStream.read(buffer)) != -1){
//                    outputStream.write(buffer,0,readLen);
//                }
//
//                outputStream.close();
//                inputStream.close();
//
//                // 上传成功，清楚临时文件
//                System.out.println("DONE!");
//                fileItem.delete();
//            }
//        }

    }

}
