package com.example.licoreriadb.Controller;

import com.example.licoreriadb.Util.jwtUtil;
import com.example.licoreriadb.Model.Usuario;
import com.example.licoreriadb.Service.CustomUserDetailsService;
import com.example.licoreriadb.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private jwtUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        usuario.setPassword(jwtUtil.encodePassword(usuario.getPassword()));
        return customUserDetailsService.saveUser(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioDetails) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        if (usuario.isPresent()) {
            Usuario updatedUsuario = usuario.get();
            updatedUsuario.setNombre(usuarioDetails.getNombre());
            updatedUsuario.setCorreo(usuarioDetails.getCorreo());
            updatedUsuario.setTelefono(usuarioDetails.getTelefono());
            updatedUsuario.setRole(usuarioDetails.getRole());
            updatedUsuario.setActivo(usuarioDetails.getActivo());

            if (usuarioDetails.getPassword() != null && !jwtUtil.matchesPassword(usuarioDetails.getPassword(), updatedUsuario.getPassword())) {
                updatedUsuario.setPassword(jwtUtil.encodePassword(usuarioDetails.getPassword()));  // Si la contraseña cambió, la encriptas
            }

            Usuario savedUsuario = customUserDetailsService.saveUser(updatedUsuario);
            return ResponseEntity.ok(savedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        if (usuarioService.findById(id).isPresent()) {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}