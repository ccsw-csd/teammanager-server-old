package com.ccsw.teammanager.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccsw.teammanager.user.model.UserEntity;
import com.ccsw.teammanager.user.model.UserRoleEntity;

/**
 * @author pajimene
 *
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity getByUsername(String username) {

        return this.userRepository.getByUsername(username);
    }

    @Override
    public List<UserRoleEntity> getRolesByUsername(String username) {
        return userRoleRepository.findByUser_Username(username);
    }

}
