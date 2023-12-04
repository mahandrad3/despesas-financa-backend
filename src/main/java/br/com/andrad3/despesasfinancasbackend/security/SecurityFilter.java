package br.com.andrad3.despesasfinancasbackend.security;

import br.com.andrad3.despesasfinancasbackend.repositories.UserRepository;
import br.com.andrad3.despesasfinancasbackend.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token != null){
            var login = tokenService.validateToken((String) token);
            UserDetails user = userRepository.findByLogin(login);
            if(!(user == null)){
                var authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                String name = authentication.getName();
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        liberacaoCors(response);
        filterChain.doFilter(request,response);
    }

    private void liberacaoCors(HttpServletResponse response) {
        if(response.getHeader("Access-Control-Allow-Origin") == null){
            response.addHeader("Access-Control-Allow-Origin","*");
        }

        if(response.getHeader("Access-Control-Allow-Headers") == null){
            response.addHeader("Access-Control-Allow-Headers","*");
        }
        if(response.getHeader("Access-Control-Request-Headers") == null){
            response.addHeader("Access-Control-Request-Headers","*");
        }

        if(response.getHeader("Access-Control-Allow-Methods") == null){
            response.addHeader("Access-Control-Allow-Methods","*");
        }

    }

    private Object recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return  null;
        return authHeader.replace("Bearer ","");
    }
}
