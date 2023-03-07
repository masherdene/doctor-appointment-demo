package org.example.repository;

import org.example.model.Treatment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreatmentRepositoryImpl implements TreatmentRepository {

    private final Map<String, Treatment> treatmentDb = new HashMap<>();

    @Override
    public Treatment findTreatmentById(String treatmentId){
        return treatmentDb.get(treatmentId);
    }

    @Override
    public List<Treatment> findAllTreatments(){
        return new ArrayList<>(treatmentDb.values());
    }

    @Override
    public List<Treatment> findTreatmentsByIds(List<String> treatmentIds){
        List<Treatment> result = new ArrayList<>();
        for(String id : treatmentIds){
            if(treatmentDb.containsKey(id))
                result.add(treatmentDb.get(id));
        }
        return result;
    }

    @Override
    public void addTreatment(Treatment treatment){
        this.treatmentDb.put(treatment.getTreatmentId(),treatment);
    }

    @Override
    public void deleteTreatmentById(String treatmentId){
        treatmentDb.remove(treatmentId);
    }

}
