package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        Optional<User> optionalUser=userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistException("Email Already Exist For  User");
        }
        // convert userdto into user jpa
      //  User user=new User(userDto.getId(),userDto.getFirstName(),userDto.getLastName(),userDto.getEmail());

      //  User user=UserMapper.mapToUser(userDto);
        User user=modelMapper.map(userDto,User.class);
        User userSaved=userRepository.save(user);


        // convert User Jpa into user dto

       // UserDto savedUserDto=new UserDto(userSaved.getId(),userSaved.getFirstName(),userSaved.getLastName(),userSaved.getEmail());
       // UserDto savedUserDto=UserMapper.mapToUserDto(userSaved);
        UserDto savedUserDto=modelMapper.map(userSaved,UserDto.class);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("user","id",userId)
        );
        //User user= optionalUser.get();
        //return UserMapper.mapToUserDto(user);
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users= userRepository.findAll();
       /* return users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());*/
        //return UserMapper.mapToUserDto(users);
        return users.stream().map((user)->modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user=UserMapper.mapToUser(userDto);
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                ()->new ResourceNotFoundException("User","id",user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);

//        return UserMapper.mapToUserDto(updatedUser);
        return modelMapper.map(updatedUser, UserDto.class);

    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("User","id",userId)
        );

        userRepository.deleteById(userId);
    }
}
