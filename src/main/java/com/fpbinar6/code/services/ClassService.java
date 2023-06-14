package com.fpbinar6.code.services;

import com.fpbinar6.code.models.Class;

import java.util.List;

public interface ClassService {
    public List<Class> getAllClass();
    public Class getClassById(int id);
    public Class saveClass(Class kelas);
    public Class updateClass(Class kelas);
    public void deleteClassById(int id);
}
