package gamelibrary.service.utility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class RequestUtility {

    private RequestUtility(){}


    public static Optional<Long> readResourceId(String id, HttpServletResponse response) throws IOException {
        Optional<Long> optionalId;
        try{
            optionalId = Optional.of(Long.parseLong(id));
        } catch (NumberFormatException e){
            optionalId = Optional.empty();
        }

        if(optionalId.isEmpty()){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        return optionalId;
    }

    public static void sendRedirect(HttpServletResponse response,HttpServletRequest request, String path) throws IOException{
        String url = String.format("%s/%s",request.getContextPath(),path);
        response.sendRedirect(url);
    }
}
