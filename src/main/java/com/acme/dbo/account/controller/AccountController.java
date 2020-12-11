package com.acme.dbo.account.controller;

import com.acme.dbo.account.domain.Account;
import com.acme.dbo.account.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PUBLIC;

@ConditionalOnProperty(name = "features.account", havingValue = "true", matchIfMissing = true)
@RestController
@RequestMapping(value = "/api/account", headers = "X-API-VERSION=1")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PUBLIC)
@Slf4j
public class AccountController {
    @Autowired AccountService accountService;
//    @Autowired AccountRepository accounts;

    @GetMapping
    @ApiOperation(value = "GetAccounts", notes = "Returned all created address of selected currency name")
    public Collection<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @ApiOperation(value = "AccCreation", notes = "Created new account", response = Account.class)
    @ApiResponse(code = 201, message = "Account created", response = Account.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody @Valid final Account account) {
        return accountService.createAccount(account);
    }
}
