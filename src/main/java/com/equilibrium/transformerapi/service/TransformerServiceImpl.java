package com.equilibrium.transformerapi.service;


import com.equilibrium.transformerapi.Exception.ResourceNotFoundException;
import com.equilibrium.transformerapi.Repository.TransformerRepository;
import com.equilibrium.transformerapi.handler.BattleHandler;
import com.equilibrium.transformerapi.model.Transformer;
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
    public void battle(ArrayList<Long> idList) {

        Transformer t1 = new Transformer("Decepticon1",TransformerType.Decepticon,8,9,2,6,7,5,6,10);
        Transformer t2 = new Transformer("Autobot1",TransformerType.Autobot,6,6,7,9,5,2,9,7);
        Transformer t3 = new Transformer("Autobot2",TransformerType.Autobot,4,4,4,4,4,4,4,4);
        Transformer t4 = new Transformer("Autobot3",TransformerType.Autobot,5,6,7,7,3,5,3,9);
        Transformer t5 = new Transformer("Decepticon2",TransformerType.Decepticon,7,4,6,8,5,7,9,10);
        Transformer t6 = new Transformer("Decepticon3",TransformerType.Decepticon,3,3,3,3,3,3,7,3);

        List<Transformer> initTransformerList = new ArrayList<>();//findByIdList(idList);
        initTransformerList.add(t1);
        initTransformerList.add(t2);
        initTransformerList.add(t3);
        initTransformerList.add(t4);
        initTransformerList.add(t5);
        initTransformerList.add(t6);


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
        Integer battlecount = 0;
        Integer autobotWinCount = 0;
        Integer decepticonWinCount = 0;
        ArrayList<Transformer> decepticonLosers =new ArrayList<>();
        ArrayList<Transformer> autobotLosers =new ArrayList<>();

        if(autobots.size() < decepticons.size())
            battlecount = autobots.size();
        else
            battlecount = decepticons.size();

        for(int i=0;i<battlecount;i++)
        {
            String oneOnOneResult = BattleHandler.oneOnOneBattle(autobots.get(i),decepticons.get(i));
            if(oneOnOneResult.equals("End Game")){
                autobots.removeAll(autobots);
                decepticons.removeAll(decepticons);
                battlecount = i;
                break;
            }
            switch(oneOnOneResult) {
                case "Autobot Wins":
                    //decepticons.remove(decepticons.get(i));
                    decepticonLosers.add(decepticons.get(i));
                    autobotWinCount++;
                    break;
                case "Decepticon Wins":
                    //autobots.remove(autobots.get(i));
                    autobotLosers.add(autobots.get(i));
                    decepticonWinCount++;
                    break;
            }
        }

        autobots.removeAll(autobotLosers);
        decepticons.removeAll(decepticonLosers);

        System.out.println(initTransformerList);
        System.out.println(autobots);
        System.out.println(decepticons);
        System.out.println(battlecount);
        System.out.println(autobotWinCount);
        System.out.println(decepticonWinCount);
    }

    public List<Transformer> findByIdList(ArrayList<Long> idList){
        List<Transformer> transformers = getAllTransformer()
                .stream()
                .filter(transformer -> idList.contains(transformer.getId()) )
                .collect(Collectors.toList());
        return transformers;
    }
   }
