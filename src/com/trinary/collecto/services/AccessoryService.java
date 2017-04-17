package com.trinary.collecto.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.trinary.collecto.entities.Accessory;
import com.trinary.collecto.exceptions.CollectomundoBusinessException;

@ApplicationScoped
public interface AccessoryService {
	public List<Accessory> getAccessories(String console);
	public Accessory getAccessory(String id);
	public Accessory createAccessory(Accessory accessory) throws CollectomundoBusinessException;
	public Accessory updateAccessory(String id, Accessory accessory) throws CollectomundoBusinessException;
}
