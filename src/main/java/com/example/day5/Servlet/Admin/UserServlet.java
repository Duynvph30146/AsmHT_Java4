package com.example.day5.Servlet.Admin;

import com.example.day5.Entity.Favorite;
import com.example.day5.Service.UserDAO;
import com.example.day5.Entity.User;
import jakarta.persistence.RollbackException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet({"/bai1", "/addUser", "/login", "/viewLogin", "/deleteUser", "/updateUser", "/logout"})
public class UserServlet extends HttpServlet {
    UserDAO dao = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/addUser")) {
            viewAdd(req, resp);
        } else if (uri.contains("/bai1")) {
            listUser(req, resp);
        } else if (uri.contains("/viewLogin")) {
            ViewLogin(req, resp);
        } else if (uri.contains("/deleteUser")) {
            deleteUser(req, resp);
        } else if (uri.contains("/updateUser")) {
            viewUpdate(req, resp);
        } else if (uri.contains("/logout")) {
            logout(req, resp);
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("fullname");
        resp.sendRedirect("loadVideo");
    }


    private void viewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("user", dao.findByID(id));
        req.getRequestDispatcher("Admin/updateUser.jsp").forward(req, resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        List<Favorite> list = new ArrayList<>();
        List<User> listUser = new ArrayList<>();
        for (Favorite favorite : list) {
            for (User user : listUser) {
                if (favorite.getUser().getId().equals(user.getId())) {
                    resp.getWriter().write("Id trùng khóa ngoại");
                    return;
                }
            }
        }
        User user = dao.findByID(id);
        if (user != null) {
            dao.delete(id);
            resp.sendRedirect("bai1");
        }
    }

    private void ViewLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Account/login.jsp").forward(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullname = req.getParameter("fullname");
        String password = req.getParameter("password");
        if (dao.checkLogin(fullname, password)) {
            if (dao.phanQuyen(fullname)) {
                req.getSession().setAttribute("fullname", fullname);
                resp.sendRedirect("bai1");
            } else {
                req.getSession().setAttribute("fullname", fullname);
                resp.sendRedirect("loadVideo");
            }
        } else {
            req.setAttribute("error", "Invalid username/password");
            req.getRequestDispatcher("Account/login.jsp").forward(req, resp);
        }
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", dao.findAll());
        req.getRequestDispatcher("Admin/TrangChu.jsp").forward(req, resp);
    }

    private void viewAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Admin/addUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains(("/addUser"))) {
            addUser(req, resp);
        } else if (uri.contains("/viewLogin")) {
            login(req, resp);
        } else if (uri.contains("/updateUser")) {
            updateUser(req, resp);
        }
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        String fullname = req.getParameter("fullname");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Boolean admin = Boolean.parseBoolean(req.getParameter("admin"));
        if (id.trim().isEmpty()) {
            req.setAttribute("errid", "ID không được để trống");
            req.getRequestDispatcher("Admin/addUser.jsp").forward(req, resp);
            return;
        }
        if (email.trim().isEmpty()) {
            req.setAttribute("erremail", "Email không được để trống");
            req.getRequestDispatcher("Admin/addUser.jsp").forward(req, resp);
            return;
        }
        if (password.trim().isEmpty()) {
            req.setAttribute("errpassword", "Password không được để trống");
            req.getRequestDispatcher("Admin/addUser.jsp").forward(req, resp);
            return;
        }
        if (fullname.trim().isEmpty()) {
            req.setAttribute("errfullname", "Full Name không được để trống");
            req.getRequestDispatcher("Admin/addUser.jsp").forward(req, resp);
            return;
        }
        if (dao.checkTon(id)) {
            req.setAttribute("error", "ID đã tồn tại");
            req.getRequestDispatcher("Admin/addUser.jsp").forward(req, resp);
            return;
        }
        User u = new User();
        u.setId((id));
        u.setFullname(fullname);
        u.setPassword(password);
        u.setEmail(email);
        u.setAdmin(admin);
        dao.insert(u);
        resp.sendRedirect("bai1");
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = dao.findByID(req.getParameter("id"));
        user.setPassword(req.getParameter("password"));
        user.setFullname(req.getParameter("fullname"));
        user.setEmail(req.getParameter("email"));
        user.setAdmin(req.getParameter("admin") != null);
        dao.update(user);
        resp.sendRedirect("bai1");
    }
}
