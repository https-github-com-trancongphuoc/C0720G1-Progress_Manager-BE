package com.codegym.service;

import com.codegym.entity.GroupAccount;

public interface GroupAccountService {
    void saveGroup(String name, Integer leaderId);

    void acceptJoinGroup (Integer groupId, Integer accountId);

    GroupAccount getGroupById(Integer id);
}
