package com.talentflow.MeatPriceTracker.Service;

import com.talentflow.MeatPriceTracker.Entity.HistoryLog;
import com.talentflow.MeatPriceTracker.Repository.HistoryLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HistoryLogService {

    @Autowired
    private HistoryLogRepo historyLogRepo;

    public void log(long userId, String actionType, String section, String details){
        HistoryLog log = new HistoryLog();
        log.setUserId(userId);
        log.setActionType(actionType);
        log.setSection(section);
        log.setDetails(details);

        historyLogRepo.save(log);
    }


    public List<HistoryLog> getAllHistoryLogs(){
        return historyLogRepo.findAll();
    }

    public List<HistoryLog> getByUser(long userId){
        return historyLogRepo.findByUserId(userId);
    }

    public List<HistoryLog>getBySection(String section){
        return historyLogRepo.findBySection(section);
    }
}
