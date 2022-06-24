package com.Naariah;

import com.Naariah.dao.EquipmentDao;
import com.Naariah.dao.RecordDao;
import com.Naariah.domain.Equipment;
import com.Naariah.domain.Record;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
class ApsApplicationTests {

    @Autowired
    private EquipmentDao equipmentDao;

//    private PartDao partDao;

    @Test
    void testGetAll() {
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("equipmentno","equipmentname","processname","ct","processorder","partname");
        List<Equipment> equipmentList = equipmentDao.selectList(queryWrapper);
        System.out.println(equipmentList);
//        List<Part> partList = partDao.selectList(null);
//        System.out.println(partList);
    }

@Autowired
    private RecordDao recordDao;
  @Test
    void testSave(){
      Record record = new Record();
      record.setProductionNumber("001");
      record.setOrderNumber("002");
      record.setCustomerName("aaa");
      record.setNumber(100);
      record.setPartNo("000001");
      record.setPartName("qqqq");
      record.setCreateTime("2022-06-21 20:09:01");
      recordDao.insert(record);

  }
  @Test
    void deleteByid(){
      recordDao.deleteById(002);

  }

}
