package com.qf.controller;

import com.qf.bean.Page;
import com.qf.bean.Product;
import com.qf.service.ProductService;
import com.qf.service.impl.ProductServiceImpl;
import com.qf.utils.FileUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/product")
public class ProductServlet extends BaseServlet {

    private ProductService productService = new ProductServiceImpl();

    /**
     * 显示商品列表
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取商品类别,当前页数
        int tid = Integer.parseInt(request.getParameter("tid"));
        String curPageStr = request.getParameter("curPage");
        if (null == curPageStr) {
            curPageStr = "1";
        }
        int curPage = Integer.parseInt(curPageStr);

        //每页记录数
        int pageNum = 4;

        Page<Product> page = productService.showProductByPage(tid, curPage, pageNum);

        //设置page对象,转发到goodsList.jsp
        request.setAttribute("page", page);
//        request.getRequestDispatcher("goodsList.jsp").forward(request, response);
        return "forward:/goodsList.jsp";
    }


    /**
     * 查看商品详情
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected String getProductByPid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pidStr = req.getParameter("pid");
        int pid = Integer.parseInt(pidStr);

        Product product = productService.getProductByPid(pid);

        req.setAttribute("goods", product);
        return "forward:/goodsDetail.jsp";
    }

    /**
     * 添加商品
     *
     * @param
     * @param
     * @throws ServletException
     * @throws IOException
     */
    protected void addGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Map<String, Object> map = upload(request, response);
//            System.out.println(map);

            Date pubdate = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(map.get("pubdate")));
            map.put("pubdate",pubdate);
            Product product = new Product();
            BeanUtils.populate(product,map);

            productService.addProduct(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //创建磁盘文件项工厂对象
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        //获取核心解析对象
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        //解决上传文件名称中文乱码问题
        servletFileUpload.setHeaderEncoding("utf-8");
        //解析请求

        HashMap<String, Object> map = new HashMap<>();
        String fileName = null;

        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);

            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    //文件项
                    //获取文件名称
                    fileName = fileItem.getName();
                    fileName = FileUtils.getNewFileName(fileName);

                    String dirPath = request.getServletContext().getRealPath("/image");
                    File dirFile = new File(dirPath);
                    if (!dirFile.exists()) {
                        dirFile.mkdir();
                    }

                    BufferedInputStream bis = new BufferedInputStream(fileItem.getInputStream());
                    //获取服务器磁盘路径
                    String filePath = dirPath + File.separator + fileName;
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
                    IOUtils.copy(bis, bos);
                    IOUtils.closeQuietly(bis);
                    IOUtils.closeQuietly(bos);
//                    byte[] bys = new byte[8192];
//                    int len = -1;
//                    while ((len = bis.read(bys)) != -1) {
//                        bos.write(bys, 0, len);
//                    }
//                    bis.close();
//                    bos.close();
                } else {
//                    普通项
                    //解决普通项中文乱码问题
                    String fieldName = fileItem.getFieldName();
                    String value = fileItem.getString("utf-8");
                    map.put(fieldName, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("picture","image/" + fileName);
        return map;
    }
}
