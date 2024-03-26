package com.othman.onlinebookstore.service;

import org.springframework.stereotype.Service;

import com.othman.onlinebookstore.entity.Role;
import com.othman.onlinebookstore.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
    
    private final RoleRepository roleRepository;

    public Role findByName(String name){
        return roleRepository.findByName(name).orElse(null);
    }

}
