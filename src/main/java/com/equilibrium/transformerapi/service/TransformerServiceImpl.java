package com.equilibrium.transformerapi.service;


import com.equilibrium.transformerapi.Exception.ResourceNotFoundException;
import com.equilibrium.transformerapi.Repository.TransformerRepository;
import com.equilibrium.transformerapi.handler.BattleHandler;
import com.equilibrium.transformerapi.model.Transformer;
import com.equilibrium.transformerapi.model.TransformerBattleResult;
import com.equilibrium.transformerapi.model.TransformerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransformerServiceImpl implements  TransformerService{

    @Autowired
    private TransformerRepository transformerRepository;

    @Override
    public Transformer createTransformer(Transformer transformer) {
        transformer.setOverallRate();
        return transformerRepository.save(transformer);
    }

    @Override
    public Transformer updateTransformer(Transformer transformer) {
        Optional<Transformer> transformerDb = this.transformerRepository.findById(transformer.getId());
        if(transformerDb.isPresent()) {
            Transformer transformerUpdate = transformerDb.get();
            transformerUpdate.setId(transformer.getId());
            transformerUpdate.setName(transformer.getName());
            transformerUpdate.setStrength(transformer.getStrength());
            transformerUpdate.setIntelligence(transformer.getIntelligence());
            transformerUpdate.setSpeed(transformer.getSpeed());
            transformerUpdate.setEndurance(transformer.getEndurance());
            transformerUpdate.setCourage(transformer.getCourage());
            transformerUpdate.setRank(transformer.getRank());
            transformerUpdate.setFirepower(transformer.getFirepower());
            transformerUpdate.setSkill(transformer.getSkill());
            transformerRepository.save(transformerUpdate);
            return transformerUpdate;
        }else {
            throw new ResourceNotFoundException("Record not found with id : " + transformer.getId());
        }
    }

    @Override
    public List<Transformer> getAllTransformer() {
        return this.transformerRepository.findAll();
    }

    @Override
    public Transformer getTransformerById(long transformerId) {
        Optional<Transformer> transformerDb = this.transformerRepository.findById(transformerId);

        if(transformerDb.isPresent()) {
            return transformerDb.get();
        }else {
            throw new ResourceNotFoundException("Record not found with id : " + transformerId);
        }
    }

    @Override
    public void deleteTransformer(long id) {
        Optional<Transformer> transformerDb = this.transformerRepository.findById(id);
        if(transformerDb.isPresent()) {
            transformerRepository.delete(transformerDb.get());
        }else {
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }
    }

    @Override
    public TransformerBattleResult battle(ArrayList<Long> idList) {

        int battleCount;
        int autobotWinCount = 0;
        int decepticonWinCount = 0;

        List<Transformer> decepticonLosers =new ArrayList<>();
        List<Transformer> autobotLosers =new ArrayList<>();
        List<Transformer> initTransformerList = findByIdList(idList);
        TransformerBattleResult battleResult = new TransformerBattleResult();

        //Split transformers into two teams based on their types and sort each list by Rank
        List<Transformer> autobots = initTransformerList
                .stream()
                .filter(transformer -> transformer.getType() == TransformerType.Autobot)
                .sorted(Comparator.comparingInt(Transformer::getRank))
                .collect(Collectors.toList());

        List<Transformer> decepticons = initTransformerList
                .stream()
                .filter(transformer -> transformer.getType() == TransformerType.Decepticon)
                .sorted(Comparator.comparingInt(Transformer::getRank))
                .collect(Collectors.toList());

        //Battles count would be equal to the size of the list with lower member count
        //Except the time "End Game" Happens. In this case, the battle count is equal to the for loop counter.
        //Loser of each battle added to a list.
        battleCount = Math.min(autobots.size(), decepticons.size());

        for(int i=0;i<battleCount;i++)
        {
            String oneOnOneResult = BattleHandler.oneOnOneBattle(autobots.get(i),decepticons.get(i));
            if(oneOnOneResult.equals("End Game")){
                autobots.removeAll(autobots);
                decepticons.removeAll(decepticons);
                battleCount = i;
                break;
            }
            switch(oneOnOneResult) {
                case "Autobot Wins":
                    decepticonLosers.add(decepticons.get(i));
                    autobotWinCount++;
                    break;
                case "Decepticon Wins":
                    autobotLosers.add(autobots.get(i));
                    decepticonWinCount++;
                    break;
            }
        }

        // At the end of the battles, the losers will be removed from the original lists.
        autobots.removeAll(autobotLosers);
        decepticons.removeAll(decepticonLosers);

        //Set up the result for API response payload
        battleResult.setBattleCount(battleCount);
        if (autobotWinCount > decepticonWinCount){
            battleResult.setWinnerTeam(autobots);
            battleResult.setLoserSurvival(decepticons);
        }
        else
        {
            battleResult.setWinnerTeam(decepticons);
            battleResult.setLoserSurvival(autobots);
        }
        return battleResult;
    }

    @Override
    public List<Transformer> findByIdList(ArrayList<Long> idList){
        return getAllTransformer()
                .stream()
                .filter(transformer -> idList.contains(transformer.getId()) )
                .collect(Collectors.toList());
    }
   }
