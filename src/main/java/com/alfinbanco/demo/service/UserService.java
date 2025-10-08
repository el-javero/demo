package com.alfinbanco.demo.service;

import com.alfinbanco.demo.dto.UserDto;
import java.util.List;

public interface UserService {
  List<UserDto> listUsers();
  UserDto getById(Long id);
  UserDto createUser(UserDto dto);
  UserDto updateUser(Long id, UserDto dto);
  void deleteUser(Long id);
}
