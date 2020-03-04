package com.panda.diary.service;

import com.panda.diary.model.SessionModel;
import com.panda.diary.repository.SessionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    final
    SessionRepo sessionRepo;

    public SessionService(SessionRepo sessionRepo) {
        this.sessionRepo = sessionRepo;
    }

    public void updateSession(int id) {
        List<SessionModel> sessions = sessionRepo.findAll();
        if (sessions == null || sessions.size() == 0) {
            SessionModel session = new SessionModel();
            session.setTitle("activeSession");
            session.setUserId(id);
            sessionRepo.save(session);
        } else {
            for (SessionModel session : sessions) {
                if (session.getTitle().equals("activeSession")) {
                    session.setUserId(id);
                    sessionRepo.save(session);
                }
            }
        }
    }

    public int findActiveUserByTitle(){
        List<SessionModel> sessions = sessionRepo.findAll();
        for (SessionModel session : sessions) {
            if (session.getTitle().equals("activeSession")) {
                return session.getUserId();
            }
        }
        return 0;
    }
}
