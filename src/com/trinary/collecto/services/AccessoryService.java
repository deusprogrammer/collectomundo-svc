package com.trinary.collecto.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.trinary.collecto.entities.Accessory;

@ApplicationScoped
public interface AccessoryService {
	public List<Accessory> getAccessories(String console);
	public Accessory getAccessory(String id);
	public Accessory createAccessory(Accessory accessory);
}
