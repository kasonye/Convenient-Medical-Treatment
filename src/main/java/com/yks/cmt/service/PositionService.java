package com.yks.cmt.service;

import com.yks.cmt.entity.Department;
import com.yks.cmt.entity.Position;
import com.yks.cmt.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;

    public ResponseEntity<?> getAllPostion(){
        return ResponseEntity.ok(positionRepository.findAll());
    }

    public ResponseEntity<?> addPostion(Position position){
        return ResponseEntity.ok(positionRepository.save(position));
    }

    public ResponseEntity<?> getAllPositionByIsDeleted(){
        return ResponseEntity.ok(positionRepository.getPositionByIsDeleted());
    }

    public ResponseEntity<?> getPositionById(int id){
        return ResponseEntity.ok(positionRepository.findById(id).get());
    }

    public ResponseEntity<?> updatePosition(Position position){
        return ResponseEntity.ok(positionRepository.save(position));
    }

    public ResponseEntity<?> deletePosition(int id){
        return ResponseEntity.ok(positionRepository.deletedById(id));
    }
}
