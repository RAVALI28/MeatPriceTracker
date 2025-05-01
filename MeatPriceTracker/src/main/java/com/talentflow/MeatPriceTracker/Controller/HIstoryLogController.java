package com.talentflow.MeatPriceTracker.Controller;

import com.talentflow.MeatPriceTracker.Entity.HistoryLog;
import com.talentflow.MeatPriceTracker.Service.HistoryLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class HIstoryLogController {

    @Autowired
    private HistoryLogService historyLogService;

    @GetMapping
    public List<HistoryLog> getAll(){
        return historyLogService.getAllHistoryLogs();
    }

    @GetMapping("/{userId}")
    public List<HistoryLog> getByUser(@PathVariable long userId){
        return historyLogService.getByUser(userId);
    }

    @GetMapping("/section/{section}")
    public List<HistoryLog> getBySection(@PathVariable String section){
        return historyLogService.getBySection(section);
    }


}
