package com.villa_agency.role.impl;

import java.util.List;

import com.villa_agency.role.Role;
import com.villa_agency.role.RoleRepository;
import com.villa_agency.role.RoleService;

public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepo;

	public RoleServiceImpl(RoleRepository roleRepo) {
		super();
		this.roleRepo = roleRepo;
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepo.findAll();
	}

}
