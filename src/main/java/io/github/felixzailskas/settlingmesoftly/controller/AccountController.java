package io.github.felixzailskas.settlingmesoftly.controller;

import io.github.felixzailskas.settlingmesoftly.api.AccountsApi;
import io.github.felixzailskas.settlingmesoftly.entity.Account;
import io.github.felixzailskas.settlingmesoftly.model.AccountResponse;
import io.github.felixzailskas.settlingmesoftly.model.CreateAccountRequest;
import io.github.felixzailskas.settlingmesoftly.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AccountController implements AccountsApi {

  private final AccountRepository accountRepository;

  public AccountController(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public ResponseEntity<AccountResponse> createAccount(CreateAccountRequest request) {
    Account account = new Account(request.getName(), request.getType().getValue());
    account = accountRepository.save(account);
    return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(account));
  }

  @Override
  public ResponseEntity<AccountResponse> getAccount(Long id) {
    Account account =
        accountRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    return ResponseEntity.ok(toResponse(account));
  }

  private AccountResponse toResponse(Account account) {
    return new AccountResponse()
        .id(account.getId())
        .name(account.getName())
        .type(account.getType())
        .balance(account.getBalance().doubleValue())
        .createdAt(account.getCreatedAt().atOffset(java.time.ZoneOffset.UTC));
  }
}
