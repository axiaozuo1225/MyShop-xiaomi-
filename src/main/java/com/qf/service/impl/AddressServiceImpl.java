package com.qf.service.impl;

import com.qf.dao.AddressDao;
import com.qf.dao.impl.AddressDaoImpl;
import com.qf.service.AddressService;
import com.qf.bean.Address;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    private AddressDao addressDao = new AddressDaoImpl();

    public List<Address> showAddressByUid(int uid) {
        return addressDao.showAddressByUid(uid);
    }

    @Override
    public void addAddress(Address address) {
        addressDao.addAddress(address);
    }

    @Override
    public void delete(int aid) {
        addressDao.delete(aid);
    }

    @Override
    public void setDefault(int uid, int aid) {
        addressDao.setDefault(uid, aid);
    }

    @Override
    public void updateAddress(Address address) {
        addressDao.updateAddress(address);
    }
}
