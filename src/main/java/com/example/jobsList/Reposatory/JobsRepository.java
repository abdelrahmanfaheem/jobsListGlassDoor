package com.example.jobsList.Reposatory;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.jobsList.Model.Entity.JopList;

public interface JobsRepository extends MongoRepository<JopList, String> {
    List<JopList> findByStatus(String status);

}
