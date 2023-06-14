package com.fpbinar6.code.services.implementation;

import com.fpbinar6.code.models.Class;
import com.fpbinar6.code.repository.ClassRepository;
import com.fpbinar6.code.services.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {
    final ClassRepository classRepository;

    @Override
    public List<Class> getAllClass() {
        return classRepository.findAll();
    }

    @Override
    public Class getClassById(int id) {
        return classRepository.findById(id).orElseThrow();
    }

    @Override
    public Class saveClass(Class kelas) {
        return classRepository.save(kelas);
    }

    @Override
    public Class updateClass(Class updateClass) {
        Optional<Class> result = classRepository.findById(updateClass.getClassId());
        Class kelas;
        if(result.isPresent()){
            kelas = result.get();
            kelas.setName(updateClass.getName());
            kelas.setPrice(updateClass.getPrice());
            return classRepository.save(kelas);
        }
        else {
            throw new RuntimeException("Data tidak ditemukan");
        }
    }

    @Override
    public void deleteClassById(int id) {
        Optional<Class> result = classRepository.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Data tidak ditemukan");
        }
        classRepository.delete(result.get());
    }
}
