package com.lorisensori.application;

import com.lorisensori.application.DAO_interfaces.MedewerkerRepository;
import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.service.BedrijfService;
import com.lorisensori.application.service.MedewerkerService;
import com.lorisensori.application.service.MedewerkerServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MedewerkerServiceTest {

    @InjectMocks
    MedewerkerServiceImpl medewerkerService;

    @Mock
    MedewerkerRepository medewerkerRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_Medewerker_findAll(){
        List<Medewerker> list = new ArrayList<>();
        Medewerker medewerker = new Medewerker("testG", "testV", "testA", "testP", "testE", "testT", null, null, false, null, true );
        Medewerker medewerker1 = new Medewerker("testG1", "testV1", "testA1", "testP1", "testE1", "testT1", null, null, false, null, true );

        list.add(medewerker);
        list.add(medewerker1);

        when(medewerkerRepository.findAll()).thenReturn(list);

        List<Medewerker> medewerkerList = medewerkerService.findAll();

        Assert.assertEquals(2, medewerkerList.size());
        verify(medewerkerRepository, times(1)).findAll();
    } 

    @Test
    public void test_medewerker_findByVoornaam(){
        when(medewerkerRepository.findByVoornaam("testV")).thenReturn(new Medewerker("testG", "testV", "testA", "testP", "testE", "testT", null, null, false, null, true ));
        Medewerker medewerker = medewerkerService.findByVoornaam("testV");

        Assert.assertEquals("testV", medewerker.getVoornaam());
        Assert.assertEquals("testA", medewerker.getAchternaam());
        Assert.assertEquals("testE", medewerker.getEmail());
    }





}
