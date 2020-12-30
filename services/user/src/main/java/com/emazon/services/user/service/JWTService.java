package com.emazon.services.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface JWTService {
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
