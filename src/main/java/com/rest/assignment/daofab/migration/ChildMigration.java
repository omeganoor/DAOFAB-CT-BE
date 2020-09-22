package com.rest.assignment.daofab.migration;

import com.rest.assignment.daofab.entity.Child;
import com.rest.assignment.daofab.entity.Parent;
import com.rest.assignment.daofab.repository.ChildRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChildMigration {
    private ChildRepository childRepository;

    public ChildMigration(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public void migration() {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("src\\main\\resources\\json\\Child.json"));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray data = (JSONArray) jsonObject.get("data");

            Iterator<JSONObject> it = data.iterator();
            List<Child> childList = new ArrayList<>();
            while (it.hasNext()) {
                JSONObject pObj = (JSONObject) it.next();
                Child child = Child.builder()
                        .id((long)pObj.get("id"))
                        .parentId((long) pObj.get("parentId"))
                        .paidAmount((long) pObj.get("paidAmount"))
                        .build();
                childList.add(child);
                System.out.println(pObj);
            }
            childRepository.saveAll(childList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
