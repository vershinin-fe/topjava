package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.repository.JpaUtil;

public abstract class AbstractOrmUserServiceTest extends AbstractUserServiceTest{
    @Autowired
    protected JpaUtil jpaUtil;

    @Before
    public void setUpOrm() throws Exception {
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
