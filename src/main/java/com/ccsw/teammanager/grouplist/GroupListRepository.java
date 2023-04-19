package com.ccsw.teammanager.grouplist;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ccsw.teammanager.grouplist.model.GroupListEntity;

@Repository
public interface GroupListRepository extends CrudRepository<GroupListEntity, Long>, GroupListCustomRepository {

    @Override
    List<GroupListEntity> findAll();

    /**
     * @param page
     * @return Returns the view v_group_list id name managers users subgroups
     */
    Page<GroupListEntity> findAll(Pageable page);
}
