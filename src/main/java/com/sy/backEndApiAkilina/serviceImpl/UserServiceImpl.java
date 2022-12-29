package com.sy.backEndApiAkilina.serviceImpl;

import com.sy.backEndApiAkilina.models.User;
import com.sy.backEndApiAkilina.repository.UserRepository;
import com.sy.backEndApiAkilina.security.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public List<User> read() {
        return userRepository.findAll();
    }

    @Override
    public User update(Long id_user, User user) {
        return userRepository.findById(id_user)
                .map(user1 -> {
                    user1.setUsername(user1.getUsername());
                    user1.setEmail(user1.getEmail());
                    user1.setNumero(user1.getNumero());
                    user1.setPassword(user1.getPassword());
                    return userRepository.save(user1);
                }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé !"));
    }

    @Override
    public String delete(Long id_user) {
        userRepository.deleteById(id_user);
        return "Utilisateur supprimé avec succès";
    }
}
