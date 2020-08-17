package com.equilibrium.transformerapi.controller;


import com.equilibrium.transformerapi.model.Transformer;
import com.equilibrium.transformerapi.model.TransformerType;
import com.equilibrium.transformerapi.service.TransformerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransformerControllerTest{

    @MockBean
    private TransformerService transformerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetTransformerByIdFound() throws Exception{
        //Setup our mocked service
        Transformer mockTransformer = new Transformer("Autobot10", TransformerType.Autobot,8,9,
                                                       2,6,7,5,6,10);
        mockTransformer.setId(1);

        when(transformerService.getTransformerById(1)).thenReturn(mockTransformer);

        //Execute the GET Request
        mockMvc.perform(MockMvcRequestBuilders.get("/transformers/{id}",  1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

    }

    @Test
    public void getAllTransformers() throws Exception
    {

        //Setup our mocked service
        Transformer mockTransformer1 = new Transformer("Autobot10", TransformerType.Autobot,8,9,
                2,6,7,5,6,10);
        mockTransformer1.setId(1);
        Transformer mockTransformer2 = new Transformer("Decepticon1", TransformerType.Decepticon,8,9,
                2,6,7,5,6,10);
        mockTransformer2.setId(2);
        List<Transformer> transformers = new ArrayList<>();
        transformers.add(mockTransformer1);
        transformers.add(mockTransformer2);

        when(transformerService.getAllTransformer()).thenReturn(transformers);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/transformers")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*[1].id").isNotEmpty());
    }

    @Test
    public void createTransformer() throws Exception
    {
        //Setup our mocked service
        Transformer mockTransformer = new Transformer("Autobot10", TransformerType.Autobot,8,9,
                2,6,7,5,6,10);
        mockTransformer.setId(1);

        when(transformerService.createTransformer(mockTransformer)).thenReturn(mockTransformer);


        mockMvc.perform( MockMvcRequestBuilders
                .post("/transformers")
                .content(new ObjectMapper().writeValueAsString(mockTransformer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateTransformer() throws Exception
    {

        //Setup our mocked service
        Transformer mockTransformer = new Transformer("Autobot11", TransformerType.Autobot,8,9,
                2,6,7,5,6,10);
        mockTransformer.setId(2);

        when(transformerService.updateTransformer(mockTransformer)).thenReturn(mockTransformer);

        mockMvc.perform( MockMvcRequestBuilders
                .put("/transformers/{id}", 2)
                .content(new ObjectMapper().writeValueAsString(mockTransformer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTransformer() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.delete("/transformers/{id}", 1) )
                .andExpect(status().isOk());
    }

    @Test
    public void battletransformers() throws  Exception{

        Transformer mockTransformer1 = new Transformer("Autobot1", TransformerType.Autobot,8,9,
                2,6,7,5,6,10);
        mockTransformer1.setId(1);
        Transformer mockTransformer2 = new Transformer("Autobot2", TransformerType.Autobot,8,9,
                2,6,7,5,6,10);
        mockTransformer2.setId(2);
        Transformer mockTransformer3 = new Transformer("DEcepticon1", TransformerType.Decepticon,8,9,
                2,6,7,5,6,10);
        mockTransformer3.setId(3);

        List<Transformer> transformerList = new ArrayList<>();
        transformerList.add(mockTransformer1);
        transformerList.add(mockTransformer2);
        transformerList.add(mockTransformer3);

        StringBuilder requestIdList = new StringBuilder().append(mockTransformer1.getId()).append(",")
                                                  .append(mockTransformer2.getId()).append(",")
                                                  .append(mockTransformer3.getId());

        ArrayList<Long> idList = new ArrayList<>();
        idList.add(mockTransformer1.getId());
        idList.add(mockTransformer2.getId());
        idList.add(mockTransformer3.getId());

        when(transformerService.findByIdList(idList)).thenReturn(transformerList);

        mockMvc.perform(MockMvcRequestBuilders.get("/transformers/battle/{idList}",requestIdList)).andDo(print());
    }

}