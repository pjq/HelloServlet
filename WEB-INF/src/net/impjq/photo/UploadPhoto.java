
package net.impjq.photo;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.impjq.base.BaseHttpServlet;
import net.impjq.util.Utils;

public class UploadPhoto extends BaseHttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -7769869574976977418L;

    private String uploadPath = "./upload/upload/";
    private String tempPath = "./upload/tmp";
    File tempPathFile;

    public void init() throws ServletException {
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }
        File tempPathFile = new File(tempPath);
        if (!tempPathFile.exists()) {
            tempPathFile.mkdirs();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        // super.doPost(req, resp);

        resp.setContentType("text/html;charset=UTF-8");
        String contentType = req.getContentType();
        int contentLength=req.getContentLength();
        Utils.storeImageFromInputStream(req.getInputStream(),contentLength);

        // String request = Utils.readFromInputStream(req.getInputStream());
        // Init the common vars.
        out = resp.getWriter();
        mPrintWriter = out;
        out.println("UploadPhoto done");
        out.println("request:");
        out.println("ContentType:" + contentType);
        out.println("ContentLength:" + contentLength);
        // out.println(request);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        // TODO Auto-generated method stub
        super.doGet(req, resp);
    }

}
