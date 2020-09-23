package com.rest.assignment.daofab.migration;

import com.rest.assignment.daofab.entity.Parent;
import com.rest.assignment.daofab.repository.ParentRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParentMigration {
    private ParentRepository parentRepository;

    public ParentMigration(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public void migration(){
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("Parent.json"));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray data = (JSONArray) jsonObject.get("data");

            Iterator<JSONObject> it = data.iterator();
            List<Parent> parentList = new ArrayList<>();
            while (it.hasNext()) {
                JSONObject pObj = (JSONObject) it.next();
                Parent parent = Parent.builder()
                        .id((long)pObj.get("id"))
                        .sender((String) pObj.get("sender"))
                        .receiver((String) pObj.get("receiver"))
                        .totalAmount((Long) pObj.get("totalAmount"))
                        .build();
                parentList.add(parent);
                System.out.println(pObj);
            }
            parentRepository.saveAll(parentList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
