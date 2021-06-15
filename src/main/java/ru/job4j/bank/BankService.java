package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        if (!users.containsKey(user)) {
            users.put(user, new ArrayList<>());
        }
    }

    public void addAccount(String passport, Account account) {
        if (findByPassport(passport) != null) {
            if (!users.get(findByPassport(passport)).contains(account)) {
                users.get(findByPassport(passport)).add(account);
            }
        }
    }

    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> list = users.get(user);
            for (Account account : list) {
                if (account.getRequisite().equals(requisite)) {
                    return account;
                }
            }
        }
        return null;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        if (findByRequisite(srcPassport, srcRequisite) != null
                && findByRequisite(destPassport, destRequisite) != null
                && findByRequisite(srcPassport, srcRequisite).getBalance() >= amount) {
            findByRequisite(srcPassport, srcRequisite).setBalance(
                    findByRequisite(srcPassport, srcRequisite).getBalance() - amount
            );
            findByRequisite(destPassport, destRequisite).setBalance(
                    findByRequisite(destPassport, destRequisite).getBalance() + amount
            );
            rsl = true;
        }
        return rsl;
    }
}