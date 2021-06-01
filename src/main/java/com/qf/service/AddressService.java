package com.qf.service;

import com.qf.bean.Address;

import java.util.List;

public interface AddressService {

    List<Address> showAddressByUid(int uid);

    void addAddress(Address address);

    void delete(int aid);

    void setDefault(int uid, int aid);

    void updateAddress(Address address);
}
