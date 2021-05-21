package com.bank;
import com.bank.model.СurrentAccount;
import com.bank.repositories.CurrentAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private CurrentAccountRepo currentAccountRepo;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<СurrentAccount> messages = currentAccountRepo.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag, @RequestParam Integer count, Map<String, Object> model) {
        СurrentAccount message = new СurrentAccount(text, tag, count);

        currentAccountRepo.save(message);

        Iterable<СurrentAccount> messages = currentAccountRepo.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<СurrentAccount> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = currentAccountRepo.findByTag(filter);
        } else {
            messages = currentAccountRepo.findAll();
        }

        model.put("messages", messages);

        return "main";
    }
}