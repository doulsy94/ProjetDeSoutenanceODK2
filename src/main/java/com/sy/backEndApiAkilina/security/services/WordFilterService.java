package com.sy.backEndApiAkilina.security.services;

import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.models.Ministere;
import com.sy.backEndApiAkilina.models.User;

public interface WordFilterService {
    String filterIdee(Idee idee);

    String filterCommentaire(String content);
}
