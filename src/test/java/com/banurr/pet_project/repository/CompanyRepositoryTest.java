package com.banurr.pet_project.repository;

import com.banurr.pet_project.enums.Industry;
import com.banurr.pet_project.model.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CompanyRepositoryTest
{
    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp()
    {
        Company company1 = Company.builder()
                .name("Apple")
                .description("Sample description")
                .industry(Industry.IT)
                .website("www.basic.com")
                .dateOfRegistration(LocalDate.now())
                .build();
        Company company2 = Company.builder()
                .name("Samsung")
                .description("Sample description")
                .industry(Industry.IT)
                .website("www.basic.com")
                .dateOfRegistration(LocalDate.now())
                .build();
        companyRepository.save(company1);
        companyRepository.save(company2);
    }

    @AfterEach
    void tearDown()
    {
        companyRepository.deleteAll();
    }

    @Test
    void findAll()
    {
        List<Company> companyList = companyRepository.findAll();

        List<Company> expected = companyList.stream().sorted((a,b)-> (int) (a.getId()-b.getId())).toList();

        assertNotNull(companyList);
        assertEquals(2, companyList.size());
        assertEquals(expected,companyList);
    }

    @Test
    void save()
    {
        Company company = Company.builder()
                .name("Apple")
                .description("Sample description")
                .industry(Industry.IT)
                .website("www.basic.com")
                .dateOfRegistration(LocalDate.now())
                .build();

        Company result = companyRepository.save(company);

        assertNotNull(result);
        assertTrue(result.getId()>0);
    }

    @Test
    void findById()
    {
        List<Company> companyList = companyRepository.findAll();
        Long id = companyList.get(0).getId();
        Company company = companyRepository.findById(id).get();

        assertNotNull(company);
        assertEquals(companyList.get(0),company);
    }
}