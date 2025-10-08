package com.alfinbanco.demo.service.impl;

import com.alfinbanco.demo.dto.UserDto;
import com.alfinbanco.demo.entity.User;
import com.alfinbanco.demo.repository.UserRepository;
import com.alfinbanco.demo.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.alfinbanco.demo.exception.ResourceNotFoundException;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository repository;
  @Override
  public List<UserDto> listUsers() {
    return repository.findAll()
        .stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }

  @Override
  public UserDto getById(Long id) {
    User user = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
    return toDTO(user);
  }

  @Override
  public UserDto createUser(UserDto dto) {
    User user = repository.save(toEntity(dto));
    return toDTO(user);
  }

  @Override
  public UserDto updateUser(Long id, UserDto dto) {
    User user = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
    user.setNombre(dto.getNombre());
    user.setEmail(dto.getEmail());
    user.setEdad(dto.getEdad());
    return toDTO(repository.save(user));
  }

  @Override
  public void deleteUser(Long id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
    }
    repository.deleteById(id);
  }

  private UserDto toDTO(User u) {
    return UserDto.builder()
        .id(u.getId())
        .nombre(u.getNombre())
        .email(u.getEmail())
        .edad(u.getEdad())
        .build();
  }

  private User toEntity(UserDto dto) {
    return User.builder()
        .nombre(dto.getNombre())
        .email(dto.getEmail())
        .edad(dto.getEdad())
        .build();
  }
}
