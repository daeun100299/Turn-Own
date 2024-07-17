package com.pde.turnown.auth.interceptor;

import com.pde.turnown.common.AuthConstants;
import com.pde.turnown.util.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.rmi.RemoteException;

public class JwtTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader(AuthConstants.AUTH_HEADER);
        String token = TokenUtils.splitHeader(header);

        if (token != null) {
            if (TokenUtils.isValidToken(token)) {
                return true;
            } else {
                throw new RemoteException("token이 만료 되었습니다.");
            }
        } else {
            throw new RemoteException("token 정보가 없습니다.");
        }
    }
}
