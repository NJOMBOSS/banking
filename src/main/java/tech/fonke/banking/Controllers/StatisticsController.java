package tech.fonke.banking.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.fonke.banking.dto.TansactionSumDetails;
import tech.fonke.banking.services.StatisticsService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/sum-by-date/{user-id}")
    public ResponseEntity<List<TansactionSumDetails>> findSumTransactionByDate(
            @PathVariable("user-id") Integer userId,
            @RequestParam("start-date") @DateTimeFormat(pattern = "YYYY-MM-dd") LocalDate stratDate,
            @RequestParam("end-date") @DateTimeFormat(pattern = "YYYY-MM-dd") LocalDate endDate
            ){
        return ResponseEntity.ok(statisticsService.findSumTransactionByDate(stratDate, endDate, userId));
    }

    @GetMapping("/account-balance/{user-id}")
    public ResponseEntity<BigDecimal> getAccountBalance(
            @PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(statisticsService.getAccountBalance(userId));
    }

    @GetMapping("/highest-transfer/{user-id}")
    public ResponseEntity< BigDecimal> highestTransfer(
            @PathVariable("user-id")  Integer userId){
        return ResponseEntity.ok(statisticsService.highestTransfer(userId));
    }

    @GetMapping("/highest-deposit/{user-id}")
    public ResponseEntity<BigDecimal> highestDeposit(
            @PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(statisticsService.highestDeposit(userId));
    }
}
