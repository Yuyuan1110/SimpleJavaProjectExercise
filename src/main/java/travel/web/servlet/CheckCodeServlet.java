package travel.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    private static int width = 100;
    private static int height = 40;
    private static BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private static Graphics graphics = bufferedImage.getGraphics();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        graphics.setColor(Color.yellow);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.CYAN);
        graphics.drawRect(0, 0, width - 1, height - 1);

        String str = "ABCDEFGHIJKLMNOPQRSTUBWXYZabcdefghijklmnopqrstuvwxyz1234567890";

        StringBuilder sb = new StringBuilder();
        Random ran = new Random();
        for (int i = 1; i < 6; i++) {
            graphics.setColor(Color.BLUE);
            int index = ran.nextInt(str.length());
            char c = str.charAt(index);
            sb.append(c);
            graphics.drawString(c + "", i * 16, 25);
            dawRedLine();
        }

        String checkCode_session = sb.toString();
        req.getSession().setAttribute("checkCode_session", checkCode_session);


        ImageIO.write(bufferedImage, "jpg", resp.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    private static void dawRedLine() {
        graphics.setColor(Color.RED);
        graphics.drawLine((int) (Math.random() * 50),
                (int) (Math.random() * 20),
                (int) (Math.random() * 100 - 50) + 50,
                (int) (Math.random() * 50 - 20) + 20);
    }
}
