package org.example.repository;

import org.example.model.Treatment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreatmentRepositoryImpl implements TreatmentRepository {

    private final Map<String, Treatment> treatmentDb = new HashMap<>();

    public TreatmentRepositoryImpl() {
        this.treatmentDb.put("0010",new Treatment("0010","bridges","prosthodontist"));
        this.treatmentDb.put("0011",new Treatment("0011","fillings","endodontist"));
        this.treatmentDb.put("0012",new Treatment("0012","root canal","endodontist"));
        this.treatmentDb.put("0013",new Treatment("0013","braces","orthodontist"));
        this.treatmentDb.put("0014",new Treatment("0014","gum treatment","periodontist"));
        this.treatmentDb.put("0010",new Treatment("0015","crowns","prosthodontist"));
    }

    @Override
    public Treatment findTreatmentById(String treatmentId){
        return treatmentDb.get(treatmentId);
    }

    @Override
    public Treatment findTreatmentByName(String treatmentName){
        return treatmentDb.get(treatmentName);
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
