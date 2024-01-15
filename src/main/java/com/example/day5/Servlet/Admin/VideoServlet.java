package com.example.day5.Servlet.Admin;

import com.example.day5.Entity.Favorite;
import com.example.day5.Entity.User;
import com.example.day5.Entity.Video;
import com.example.day5.HibernateUtil;
import com.example.day5.Service.VideoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet({"/bai2", "/loadVideo", "/detail", "/favorite", "/addVideo", "/updateVideo", "/deleteVideo", "/likeVideo", "/unlikeVideo"})
public class VideoServlet extends HttpServlet {
    VideoDAO dao = new VideoDAO();
    List<Video> list;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/bai2")) {
            req.setAttribute("listMovie", dao.findAll());
            req.getRequestDispatcher("Admin/movie.jsp").forward(req, resp);
        } else if (uri.contains("/loadVideo")) {
            loadVideo(req, resp);
        } else if (uri.contains("/detail")) {
            detail(req, resp);
        } else if (uri.contains("/favorite")) {
            viewFavorite(req, resp);
        } else if (uri.contains("/addVideo")) {
            viewAdd(req, resp);
        } else if (uri.contains("/updateVideo")) {
            viewUpdate(req, resp);
        } else if (uri.contains("/deleteVideo")) {
            deleteVideo(req, resp);
        } else if (uri.contains("/likeVideo")) {
            likeVideo(req, resp);
        } else if (uri.contains("/unlikeVideo")) {
            unlikeVideo(req, resp);
        }
    }

    private void likeVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String videoid = req.getParameter("id");
        String userid = (String) req.getSession().getAttribute("fullname");
        Favorite favorite = new Favorite();
        Video video = dao.findByID(videoid);
        User user = dao.findByIDUser(userid);
        favorite.setVideo(video);
        favorite.setUser(user);
        dao.like(favorite);
        List<Video> items = dao.searchFavorite(userid, "");
        req.setAttribute("items", items);
        req.getRequestDispatcher("Account/favorite.jsp").forward(req, resp);
    }

    private void unlikeVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String videoid = req.getParameter("id");
        String userid = (String) req.getSession().getAttribute("fullname");
        List<Favorite> lst = dao.findAllFavorite();
        Favorite fv = new Favorite();
        for (Favorite a : lst) {
            if (a.getUser().getId().equals(userid) && a.getVideo().getId().equals(videoid)) {
                fv = a;
            }
        }
        dao.unlike(fv);
        List<Video> items = dao.searchFavorite(userid, null);
        req.setAttribute("items", items);
        req.getRequestDispatcher("Account/favorite.jsp").forward(req, resp);
    }

    private void loadVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        if (keyword == null) keyword = "";
        String jpql = "SELECT DISTINCT o.video FROM Favorite o "
                + " WHERE o.video.title LIKE :keyword";
        EntityManager em = HibernateUtil.createEntityManager();
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setParameter("keyword", "%" + keyword + "%");
        list = query.getResultList();
        req.setAttribute("list", dao.findAll());
        req.getRequestDispatcher("Account/home.jsp").forward(req, resp);
    }

    private void deleteVideo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (id != null) {
            Video video = dao.findByID(id);
            if (video != null) {
                dao.delete(id);
                resp.sendRedirect("bai2");
            }
        }
    }

    private void viewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Video video = dao.findByID(id);
        req.setAttribute("movie", video);
        req.getRequestDispatcher("Admin/updateVideo.jsp").forward(req, resp);
    }

    private void viewAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Admin/addVideo.jsp").forward(req, resp);
    }

    private void viewFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("requestURI", req.getRequestURI());
        String username = (String) req.getSession().getAttribute("fullname");
        if (username == null) {
            resp.sendRedirect("viewLogin");
            return;
        }
        String keyword = req.getParameter("keyword");
        if (keyword == null) keyword = "";
        System.out.println("In favorite, keyword=" + keyword);
        List<Video> items = dao.searchFavorite(username, keyword);
        req.setAttribute("items", items);
        req.getRequestDispatcher("Account/favorite.jsp").forward(req, resp);
    }

    private void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String userid = (String) req.getSession().getAttribute("fullname");
        List<Favorite> list = dao.findAllFavorite();
        int check = -1;
        for (Favorite fa : list) {
            if (fa.getVideo().getId().equals(id) && fa.getUser().getId().equals(userid)) {
                check = 1;
            }
        }
        req.setAttribute("check", check);
        req.setAttribute("video", dao.findByID(id));
        req.getRequestDispatcher("Account/detail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/addVideo")) {
            add(req, resp);
        } else if (uri.contains("/updateVideo")) {
            update(req, resp);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Boolean active = Boolean.parseBoolean(req.getParameter("active"));
        String poster = req.getParameter("poster");
        if (id.trim().isEmpty()) {
            req.setAttribute("errid", "ID không được để trống");
            req.getRequestDispatcher("Admin/addVideo.jsp").forward(req, resp);
            return;
        }
        if (title.trim().isEmpty()) {
            req.setAttribute("errtilte", "Title không được để trống");
            req.getRequestDispatcher("Admin/addVideo.jsp").forward(req, resp);
            return;
        }
        if (description.trim().isEmpty()) {
            req.setAttribute("errdescription", "Description không được để trống");
            req.getRequestDispatcher("Admin/addVideo.jsp").forward(req, resp);
            return;
        }
        if (poster.trim().isEmpty()) {
            req.setAttribute("errposter", "Poster không được để trống");
            req.getRequestDispatcher("Admin/addVideo.jsp").forward(req, resp);
            return;
        }
        if (dao.checkTon(id)) {
            req.setAttribute("error", "ID đã tồn tại");
            req.getRequestDispatcher("Admin/addVideo.jsp").forward(req, resp);
            return;
        }
        Video video = new Video();
        video.setId(id);
        video.setTitle(title);
        video.setDescription(description);
        video.setActive(active);
        video.setPoster(poster);
        dao.add(video);
        resp.sendRedirect("bai2");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Video video = dao.findByID(id);
        video.setId(id);
        video.setTitle(req.getParameter("title"));
        video.setDescription(req.getParameter("description"));
        video.setActive(req.getParameter("active") != null);
        video.setPoster(req.getParameter("poster"));
        dao.update(video);
        resp.sendRedirect("bai2");
    }
}
