package com.talentflow.MeatPriceTracker.Repository;

import com.talentflow.MeatPriceTracker.Entity.HistoryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface HistoryLogRepo extends JpaRepository<HistoryLog, Long> {

    List<HistoryLog> findByUserId(long userId);
    List<HistoryLog> findBySection(String section);
}
