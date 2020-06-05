package net.freesaber.servlet;

import net.freesaber.bean.Message;
import net.freesaber.service.QueryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 列表页面初始化控制
 */
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码
        req.setCharacterEncoding("UTF-8");
        // 接收页面查询参数
        String command = req.getParameter("command");
        String description = req.getParameter("description");
        // 回填查询条件
        req.setAttribute("command", command);
        req.setAttribute("description", description);

        // 获取消息列表
        QueryService queryService = new QueryService();
        List<Message> messageList = queryService.queryMessageList(command, description);
        req.setAttribute("messageList", messageList);

        // 向页面跳转
        req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
