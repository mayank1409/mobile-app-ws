package com.mayankmadhav.demo.app.mobileappws.jwts.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mayankmadhav.demo.app.mobileappws.jwts.utils.JwtUtils;
import com.mayankmadhav.demo.app.mobileappws.service.CustomUserDetailsService;
import com.mayankmadhav.demo.app.mobileappws.utils.RestResponse;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");

        String username = null;
        String token = null;
        UserDetails userDetails = null;

        try {

            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {

                token = bearerToken.substring(7);
                username = jwtUtils.extractUsername(token);
                userDetails = userDetailsService.loadUserByUsername(username);

                Boolean isValid = jwtUtils.validateToken(token, userDetails);
                log.info("is token Valid {}", isValid);
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UsernamePasswordAuthenticationToken authContext = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                authContext.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authContext);
            }

        } catch (Exception e) {
            String msg = "";

            if (e instanceof MalformedJwtException) {
                msg = "Malformed Jwt";
            } else if (e instanceof ExpiredJwtException) {
                msg = "Expired Jwt";
            } else if (e instanceof ClaimJwtException) {
                msg = "Invalid Claims";
            } else if (e instanceof JwtException) {
                msg = "Invalid Jwt";
            }
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter()
                    .write(mapper.writeValueAsString(new RestResponse<Object>(msg, HttpStatus.UNAUTHORIZED)));
            return;
        }
        filterChain.doFilter(request, response);
    }

}
