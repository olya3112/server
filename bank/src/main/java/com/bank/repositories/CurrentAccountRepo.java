package com.bank.repositories;


import com.bank.model.СurrentAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CurrentAccountRepo extends CrudRepository<СurrentAccount, Long> {

    List<СurrentAccount> findByTag(String tag);

}