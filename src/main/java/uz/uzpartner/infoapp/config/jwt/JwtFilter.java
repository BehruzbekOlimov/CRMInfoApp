package uz.uzpartner.infoapp.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;
import uz.uzpartner.infoapp.entity.User;
import uz.uzpartner.infoapp.repository.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getHeader(JwtUtils.TOKEN_HEADER) != null && request.getHeader(JwtUtils.TOKEN_HEADER).trim().length() > 7) {
            String token = request.getHeader(JwtUtils.TOKEN_HEADER).trim().substring(JwtUtils.TOKEN_PREFIX.length());

            if (jwtUtils.validateToken(token)) {
                User user = userRepository.findByEmail(jwtUtils.getEmailFromToken(token)).orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
                });

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
