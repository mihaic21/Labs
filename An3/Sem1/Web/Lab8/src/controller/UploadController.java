package controller;

import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@MultipartConfig
public class UploadController extends HttpServlet {
    public UploadController() {
        super();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        InputStream inputStream = null; // input stream of the upload file

        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photoPath");
        String photoPath = null;

        try {
            photoPath = "pictures/photo" + id + ".jpg";
            FileOutputStream out = new FileOutputStream(new File(photoPath));
            if (filePart != null) {
                // obtains input stream of the upload file
                inputStream = filePart.getInputStream();
            }

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = null;

        DatabaseHandler databaseHandler = new DatabaseHandler();
        User user = databaseHandler.getUserById(id);
        user.setPhotoPath(photoPath);

        databaseHandler.updateUser(user);

        rd = request.getRequestDispatcher("/index.jsp");
        request.setAttribute("user", user);

        rd.forward(request, response);

    }
}