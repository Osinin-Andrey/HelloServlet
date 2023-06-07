package by.osinin.helloservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

@WebServlet(value = "/hi/*")
public class MySecondServlet extends HttpServlet {
    private int counter;
    @Override
    public void init() throws ServletException {
        counter = 100;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Servlet path: " + req.getServletPath());
        System.out.println("Servlet URI: " + req.getRequestURI());

        //http://localhost:8080/hi/123?my=parametr&another=empty
        System.out.println("Paramentr: " + req.getParameter("my"));

        HttpSession session = req.getSession();
        Integer currenParam = (Integer) session.getAttribute("count");
        System.out.println("Current param: " + currenParam);
        if (currenParam == null) {
            currenParam = counter;
        }
        session.setAttribute("count", currenParam+1);



        PrintWriter writer = resp.getWriter();
        writer.println("This is my second response" + ". Current param: " + currenParam);
        Iterator<String> headersIterator = req.getHeaderNames().asIterator();
        while (headersIterator.hasNext()) {
            writer.println("\nHeaders: " + headersIterator.next());
        }
        writer.println("\nHost: " + req.getHeader("host"));
        resp.setHeader("myHeader", "abracadabra");
        writer.println("\nCookies: " + Arrays.asList(req.getCookies())
                .stream()
                .map(cookie -> cookie.getName()+ " " + cookie.getValue() + " " + cookie.getMaxAge())
                .collect(Collectors.toList()));



        if (currenParam>102) {
            resp.sendRedirect("/here");
            Person person = new Person();
            //resp.setStatus(500);
            //resp.sendError(500, "Too much");
        } //ошибку сделать


        //writer.println("\nHeaders: " + req.getHeaderNames());

    }
}
