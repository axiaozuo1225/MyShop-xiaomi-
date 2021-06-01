package com.qf.dao;

import com.qf.bean.Address;

import java.util.List;

public interface AddressDao {

    List<Address> showAddressByUid(int uid);

    void addAddress(Address address);

    void delete(int aid);

    void setDefault(int uid, int aid);

    Address showAddressByAid(int aid);

    void updateAddress(Address address);
}
