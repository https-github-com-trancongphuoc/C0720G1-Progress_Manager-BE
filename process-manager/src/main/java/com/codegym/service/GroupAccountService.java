package com.codegym.service;




public interface GroupAccountService {
    void saveGroup(String name, Integer leaderId);

    void acceptJoinGroup (Integer groupId, Integer accountId);
}
