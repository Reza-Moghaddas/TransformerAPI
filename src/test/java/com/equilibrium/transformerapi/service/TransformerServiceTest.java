package com.equilibrium.transformerapi.service;

import com.equilibrium.transformerapi.Repository.TransformerRepository;
import com.equilibrium.transformerapi.model.Transformer;
import com.equilibrium.transformerapi.model.TransformerType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class TransformerServiceTest {

    @InjectMocks
    TransformerService transformerService;

    @MockBean
    TransformerRepository transformerRepository;

    Transformer mockTransformer1,mockTransformer2,mockTransformer3;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockTransformer1 = new Transformer("Autobot1", TransformerType.Autobot,8,9,
                2,6,7,5,6,10);
        mockTransformer2 = new Transformer("Autobot2", TransformerType.Autobot,8,9,
                2,6,7,5,6,10);
        mockTransformer3 = new Transformer("Decpticon1", TransformerType.Decepticon,8,9,
                2,6,7,5,6,10);
    }

    @Test
    void createTransformer() throws Exception{

        when(transformerRepository.save(mockTransformer1)).thenReturn(mockTransformer1);
    }

    @Test
    void updateTransformer() {
    }

    @Test
    void getAllTransformer() {
        List<Transformer> list = new ArrayList<>();

        list.add(mockTransformer1);
        list.add(mockTransformer2);
        list.add(mockTransformer3);

        when(transformerRepository.findAll()).thenReturn(list);

        //test
        List<Transformer> transformerList = transformerService.getAllTransformer();

        Assert.assertEquals(3, list.size());
        verify(transformerRepository, times(1)).findAll();
    }

    @Test
    void getTransformerById() {
    }

    @Test
    void deleteTransformer() {
    }

    @Test
    void battle() {
    }
}