package com.equilibrium.transformerapi.service;

import com.equilibrium.transformerapi.model.Transformer;

import java.util.ArrayList;
import java.util.List;

public interface TransformerService {
    Transformer createTransformer(Transformer transformer);

    Transformer updateTransformer(Transformer transformer);

    List<Transformer> getAllTransformer();

    Transformer getTransformerById(long transformerId);

    void deleteTransformer(long id);

    void battle(ArrayList<Long> idList);

}
