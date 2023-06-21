package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.AuthService;
import it.unical.ea.VintedProject.dto.NewUserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserDao userDao;
    @Override
    public Boolean signin(NewUserDto newUserDto) {
        //todo: PASSWORD IN CHIARO!
        User user = modelMapper.map(newUserDto, User.class);
        if(user != null){
            userDao.save(user);
            return true;
        }
        return false;
    }
}
