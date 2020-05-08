package com.jinfei.jfmm.common.web.service;

import com.jinfei.jfmm.model.kSeat;
import com.jinfei.jfmm.services.IKSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("seat")
public class SeatService {

    @Autowired
    private IKSeatService seatService;

    public List<kSeat> getSeatList(){
        return seatService.list();
    }
}
