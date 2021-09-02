package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vasiliy Orlov
 * @version 1.0
 */
public class BankService {
    /**
     * Пользовательли с их аккаунтами хранятся в коллекции типа HashMap
     */
    private Map<User, List<Account>> users = new HashMap<>();

    /**
     * Добавляет пользователя в карту users
     * @param user - пользователь
     */
    public void addUser(User user) {
        if (!users.containsKey(user)) {
            users.put(user, new ArrayList<>());
        }
    }

    /**
     * Присваивает аккаунт account пользователю с данным passport
     * @param passport - номер паспорта
     * @param account - аккаунт пользователя банка
     */
    public void addAccount(String passport, Account account) {
        if (findByPassport(passport) != null) {
            if (!users.get(findByPassport(passport)).contains(account)) {
                users.get(findByPassport(passport)).add(account);
            }
        }
    }

    /**
     * Ищет в карте users пользователя с номером паспорта passport
     * @param passport - номер паспорта
     * @return пользователя банка
     */
    public User findByPassport(String passport) {
        return users.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**
     * Ищет аккаунт у пользователя с номером паспорта passport
     * по реквизитам requisite
     * @param passport - номер паспорта
     * @param requisite - реквизиты аккаунта
     * @return аккаунт пользователя банка
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            return users.get(user).stream()
                    .filter(account -> account.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Осуществляет перевод от пользователя с паспортными данными srcPassport
     * с аккаунта с реквизитами srcRequisite
     * пользователю с паспортными данными destPassport
     * на аккаунт с реквизитами destRequisite
     * сумму amount.
     * @param srcPassport - паспортные данные
     * @param srcRequisite - реквизиты аккаунта
     * @param destPassport - паспортные данные
     * @param destRequisite - реквизиты аккаунта
     * @param amount - сумма перевода
     * @return ответ, выполнена ли операция
     */
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