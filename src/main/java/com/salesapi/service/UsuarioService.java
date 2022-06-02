package com.salesapi.service;

import com.salesapi.model.Usuario;
import com.salesapi.model.UsuarioDto;
import com.salesapi.model.UsuarioDtoRegister;
import com.salesapi.model.ValidacaoResponse;
import com.salesapi.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByemail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
                user.isAccountNonExpired(), user.isAccountNonLocked(), user.getAuthorities());
    }

    public UsuarioDto createUser(UsuarioDtoRegister userDtoRegister, String fila) {
        Usuario usuario = modelMapper.map(userDtoRegister, Usuario.class);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        HashSet<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        usuario.setAuthorities(authorities);
        usuario.setEnabled(false);
        usuarioRepository.save(usuario);
        UsuarioDto usuarioDto = modelMapper.map(usuario, UsuarioDto.class);
        rabbitTemplate.convertAndSend(fila, usuarioDto);
        return usuarioDto;
    }

    public void activeUsuario(ValidacaoResponse response) {
        if (response.isValid()) {
            usuarioRepository.findByemail(response.getUsuarioDto().getEmail())
                    .ifPresentOrElse((user) -> {
                                user.setEnabled(true);
                                usuarioRepository.save(user);
                                },
                            () -> {
                                response.getError().add("Usuario nao encotrado na base de dados");
                                rabbitTemplate.convertAndSend("amq.direct", "filalErrorCadastro", response);
                            });
        } else {
            response.getError().add("erro Generico");
            rabbitTemplate.convertAndSend("amq.direct", "filaErrorCadastro", response);
        }
    }

    public List<UsuarioDto> getAllUsuario() {
        return modelMapper.map(usuarioRepository.findAll(), new TypeToken<List<UsuarioDto>>() {
        }.getType());
    }
}
