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

    @Test
    @DirtiesContext
    public void test_should_return_case_when_the_case_exist_given_id() {
        //given
        Case cs = new Case();
        cs.setTime(new Date().getTime());
        cs.setName("case1");
        caseRepository.save(cs);
        //when
        Case actual=caseRepository.findById(cs.getId()).get();
        //then
        Assertions.assertEquals(cs.getId(),actual.getId());
        Assertions.assertEquals(cs.getName(),actual.getName());
        Assertions.assertEquals(cs.getTime(),actual.getTime());
    }

    @Test
    @DirtiesContext
    public void test_should_return_all_case_when_the_case_exist() {
        //given
        caseRepository.deleteAll();
        Case cs = new Case();
        cs.setTime(new Date().getTime());
        cs.setName("caseA");
        caseRepository.save(cs);
        Case cs2 = new Case();
        cs2.setTime(new Date().getTime());
        cs2.setName("caseB");
        caseRepository.save(cs2);
        //when
        List<Case> caseList=caseRepository.findAllByTime();
        //then
        Assertions.assertEquals(cs2.getId(),caseList.get(0).getId());
        Assertions.assertEquals(cs2.getName(),caseList.get(0).getName());
        Assertions.assertEquals(cs2.getTime(),caseList.get(0).getTime());
    }

    @Test
    @DirtiesContext
    public void test_should_return_all_case_when_the_case_exist_given_name() {
        //given
        Case cs = new Case();
        cs.setTime(new Date().getTime());
        cs.setName("caseE");
        caseRepository.save(cs);
        Case cs2 = new Case();
        cs2.setTime(new Date().getTime());
        cs2.setName("caseE");
        caseRepository.save(cs2);
        //when
        List<Case> caseList=caseRepository.findAllByName("caseE");
        //then
        Assertions.assertEquals(cs.getId(),caseList.get(0).getId());
        Assertions.assertEquals(cs.getName(),caseList.get(0).getName());
        Assertions.assertEquals(cs.getTime(),caseList.get(0).getTime());

        Assertions.assertEquals(cs2.getId(),caseList.get(1).getId());
        Assertions.assertEquals(cs2.getName(),caseList.get(1).getName());
        Assertions.assertEquals(cs2.getTime(),caseList.get(1).getTime());
    }



}
