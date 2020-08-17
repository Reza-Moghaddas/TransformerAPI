package com.equilibrium.transformerapi.service;

import com.equilibrium.transformerapi.model.Transformer;
import com.equilibrium.transformerapi.model.TransformerBattleResult;

import java.util.ArrayList;
import java.util.List;

public interface TransformerService {
    Transformer createTransformer(Transformer transformer);

    Transformer updateTransformer(Transformer transformer);

    List<Transformer> getAllTransformer();

    Transformer getTransformerById(long transformerId);

    List<Transformer> findByIdList(ArrayList<Long> idList);

    void deleteTransformer(long id);

    TransformerBattleResult battle(ArrayList<Long> idList);
}
