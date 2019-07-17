package com.tw.apistackbase;

import com.tw.apistackbase.entity.Case;
import com.tw.apistackbase.repository.CaseRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CaseRepositoryTest {

    @Autowired
    private CaseRepository caseRepository;

    @Test
    @DirtiesContext
    public void test_should_return_Exception_when_the_name_is_null() {
        //given
        Case cs = new Case();
        cs.setTime(new Date().getTime());
        //when

        //then
        Assertions.assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                caseRepository.saveAndFlush(cs);
            }
        });
    }

    @Test
    @DirtiesContext
    public void test_should_return_Exception_when_the_time_is_null() {
        //given
        Case cs = new Case();
        cs.setName("case1");
        //when

        //then
        Assertions.assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                caseRepository.saveAndFlush(cs);
            }
        });
    }

    @Test
    @DirtiesContext
    public void test_should_return_Exception_when_the_case_exist() {
        //given
        Case cs = new Case();
        cs.setTime(new Date().getTime());
        cs.setName("case1");
        //when
        caseRepository.save(cs);
        //then
        Assertions.assertEquals(1,caseRepository.findAll().size());
    }






}
