package academy.prog;

import jakarta.servlet.http.HttpServletRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static byte [] requestBodyToArray (HttpServletRequest request) throws IOException {
        InputStream inputStream = request.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        do {
            length = inputStream.read(buffer);
            if (length > 0) byteArrayOutputStream.write(buffer, 0, length);
        } while (length != -1);

            return byteArrayOutputStream.toByteArray();
        }
    }

