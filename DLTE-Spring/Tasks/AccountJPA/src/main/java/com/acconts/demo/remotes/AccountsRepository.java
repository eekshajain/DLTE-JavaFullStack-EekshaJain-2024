package com.acconts.demo.remotes;

import com.acconts.demo.model.Accounts;
import org.springframework.data.repository.CrudRepository;

public interface AccountsRepository extends CrudRepository<Accounts,Long> {
}
