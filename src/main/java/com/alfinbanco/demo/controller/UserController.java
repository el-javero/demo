package com.alfinbanco.demo.controller;

import com.alfinbanco.demo.dto.UserDto;
import com.alfinbanco.demo.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDto>> listar() {
    return ResponseEntity.ok(userService.listUsers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> obtener(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getById(id));
  }

  @PostMapping
  public ResponseEntity<UserDto> crear(@RequestBody UserDto dto) {
    return ResponseEntity.ok(userService.createUser(dto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDto> actualizar(@PathVariable Long id, @RequestBody UserDto dto) {
    return ResponseEntity.ok(userService.updateUser(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
}
