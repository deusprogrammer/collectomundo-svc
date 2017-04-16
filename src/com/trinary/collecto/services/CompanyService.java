package com.trinary.collecto.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.trinary.collecto.entities.Company;

@ApplicationScoped
public interface CompanyService {
	public List<Company> getCompanies();
	public Company getCompany(String id);
	public Company createCompany(Company company);
	public Company updateCompany(String id, Company company);
}