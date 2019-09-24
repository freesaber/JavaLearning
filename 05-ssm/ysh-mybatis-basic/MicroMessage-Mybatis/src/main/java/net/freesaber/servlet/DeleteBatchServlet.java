package net.freesaber.servlet;

import net.freesaber.service.MaintainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

public class DeleteBatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码
        req.setCharacterEncoding("UTF-8");
        // 接收页面查询参数
        String[] ids = req.getParameterValues("id");
        MaintainService maintainService = new MaintainService();
        maintainService.deleteBatch(ids);

        String command = req.getParameter("command");
        req.setAttribute("command", command);
        String description = req.getParameter("description");
        req.setAttribute("description", description);

        // 向页面跳转
        req.getRequestDispatcher("/List.action").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
