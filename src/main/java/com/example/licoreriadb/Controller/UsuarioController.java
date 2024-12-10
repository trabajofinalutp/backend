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
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario existingUsuario = usuarioOptional.get();

            // Update basic fields
            existingUsuario.setNombre(usuarioDetails.getNombre());
            existingUsuario.setCorreo(usuarioDetails.getCorreo());
            existingUsuario.setTelefono(usuarioDetails.getTelefono());
            existingUsuario.setRole(usuarioDetails.getRole());
            existingUsuario.setActivo(usuarioDetails.getActivo());

            // Password handling - do not touch password if not provided in request
            String newPassword = usuarioDetails.getPassword();
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                existingUsuario.setPassword(jwtUtil.encodePassword(newPassword));
            }
            // If no password provided, keep existing encrypted password unchanged

            Usuario savedUsuario = usuarioService.save(existingUsuario);
            return ResponseEntity.ok(savedUsuario);
        }
        return ResponseEntity.notFound().build();
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