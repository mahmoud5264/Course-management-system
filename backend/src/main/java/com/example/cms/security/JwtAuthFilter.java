package com.example.cms.security;

import com.example.cms.models.User;
import com.example.cms.repositories.UserRepository;
import com.example.cms.responses.BaseResponse;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("doFilter");
        try{
            String auth = request.getHeader("Authorization");
            if(auth!=null && jwtUtils.validateToken(auth)){
                String username = jwtUtils.getUsernameFromToken(auth);
                CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (JwtException e) {
            BaseResponse baseResponse=new BaseResponse(403,"Invalid or expired token");
            response.setStatus(403);
            response.setContentType("application/json");
            response.getOutputStream().print(baseResponse.toString());
            return;
        }
        catch (Exception e) {
            BaseResponse baseResponse=new BaseResponse(403,"Unauthorized");
            response.setStatus(403);
            response.setContentType("application/json");
            response.getOutputStream().print(baseResponse.toString());
            return;
        }
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
//        System.out.println("shouldNotFilter");
        List<String> nonSecuredUrls = new ArrayList<>();
        nonSecuredUrls.add("/auth/**");
        AntPathMatcher pathMatcher = new AntPathMatcher();
        return nonSecuredUrls.stream().anyMatch(url-> pathMatcher.match(url, request.getServletPath()));
    }
}
