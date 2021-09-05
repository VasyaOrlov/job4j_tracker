package ru.job4j.bank;

import java.util.*;

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
        Optional<User> find = findByPassport(passport);
        if (find.isPresent()) {
            if (!users.get(find.get()).contains(account)) {
                users.get(find.get()).add(account);
            }
        }
    }

    /**
     * Ищет в карте users пользователя с номером паспорта passport
     * @param passport - номер паспорта
     * @return пользователя банка
     */
    public Optional<User> findByPassport(String passport) {
        return users.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
    }

    /**
     * Ищет аккаунт у пользователя с номером паспорта passport
     * по реквизитам requisite
     * @param passport - номер паспорта
     * @param requisite - реквизиты аккаунта
     * @return аккаунт пользователя банка
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            return users.get(user.get()).stream()
                    .filter(rq -> rq.getRequisite().equals(requisite))
                    .findFirst();
        }
        return Optional.empty();
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
        Optional<Account> findDest = findByRequisite(destPassport, destRequisite);
        Optional<Account> findSrc = findByRequisite(srcPassport, srcRequisite);
        if (findSrc.isPresent()
                && findDest.isPresent()
                && findSrc.get().getBalance() >= amount) {
            findSrc.get().setBalance(
                    findSrc.get().getBalance() - amount
            );
            findDest.get().setBalance(
                    findDest.get().getBalance() + amount
            );
            rsl = true;
        }
        return rsl;
    }
}