package com.sy.backEndApiAkilina.serviceImpl;

import com.sy.backEndApiAkilina.models.User;
import com.sy.backEndApiAkilina.repository.UserRepository;
import com.sy.backEndApiAkilina.security.services.UserService;
import com.sy.backEndApiAkilina.utils.EmailConstructor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    private EmailConstructor emailConstructor;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JavaMailSender mailSender;
    private final UserRepository userRepository;



    @Override
    public List<User> read() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id_user) {
        return userRepository.findById(id_user).get();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void resetPassword(User user) {
        String password = RandomStringUtils.randomAlphanumeric(10);
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        mailSender.send(emailConstructor.constructResetPasswordEmail(user, password));
    }

    @Override
    public void updateUserPassword(User user, String newPassword) {
        String encryptedPassword = bCryptPasswordEncoder.encode(newPassword);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        mailSender.send(emailConstructor.constructResetPasswordEmail(user, newPassword));

    }


    @Override
    public String delete(Long id_user) {
        userRepository.deleteById(id_user);
        return "Utilisateur supprimé avec succès";
    }

    @Override
    public Optional<User> trouverParemailOrNumeroAndPass(String emailOrNumero, String password) {
        return userRepository.findByEmailOrNumero(emailOrNumero, password);
    }

    @Override
    public User findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

    @Override
    public User ajouter(User user) {
        userRepository.save(user);
        mailSender.send(emailConstructor.constructNewUserEmail(user));
        return user;
    }
}