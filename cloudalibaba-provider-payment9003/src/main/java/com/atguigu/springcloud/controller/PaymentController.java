package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PaymentController
{
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public static HashMap<Long, Payment> hashMap = new HashMap<>();
//    static
//    {
//        hashMap.put(1L,new Payment(1L,"28a8c1e3bc2742d8848569891fb42181"));
//        hashMap.put(2L,new Payment(2L,"bba8c1e3bc2742d8848569891ac32182"));
//        hashMap.put(3L,new Payment(3L,"6ua8c1e3bc2742d8848569891xt92183"));
//    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id)
    {
//        Payment payment = hashMap.get(id);
        Payment payment = jdbcTemplate.queryForObject("select * from payment where id = ?", new RowMapper<Payment>() {
            @Override
            public Payment mapRow(ResultSet resultSet, int i) throws SQLException {
                Payment payment = new Payment();
                payment.setId(resultSet.getLong("id"));
                payment.setSerial(resultSet.getString("serial"));
                return payment;
            }
        }, id);
        CommonResult<Payment> result = new CommonResult(200,"from mysql,serverPort:  "+serverPort,payment);
        return result;
    }



}
