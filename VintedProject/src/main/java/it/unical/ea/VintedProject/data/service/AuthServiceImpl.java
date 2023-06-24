package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.AuthService;
import it.unical.ea.VintedProject.dto.NewUserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Boolean signUp(NewUserDto newUserDto) {
        System.out.println(newUserDto);
        User user = modelMapper.map(newUserDto, User.class);
        if(user != null){
            System.out.println("ENTRATO "+user.toString());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);
            return true;
        }
        return false;
    }
}
