package com.sy.backEndApiAkilina.serviceImpl;

import com.sy.backEndApiAkilina.models.*;
import com.sy.backEndApiAkilina.repository.JaimeRepository;
import com.sy.backEndApiAkilina.security.services.JaimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class JaimeServiceImpl implements JaimeService {
    private final JaimeRepository jaimeRepository;

    @Override
    public Jaime add(Jaime jaime, User user, Idee idee) {
        return jaimeRepository.save(jaime);
    }

    @Override
    public List<Jaime> read() {
        return jaimeRepository.findAll();
    }

    @Override
    public String delete(Long id) {
        jaimeRepository.deleteById(id);
        return "J'aime supprimé avec succès";
    }

    @Override
    public List<Jaime> AfficherJaimeParIdIdee(Idee idee) {
        return jaimeRepository.findByIdee(idee);
    }

}
