package com.Naariah;

import com.Naariah.dao.EquipmentDao;
import com.Naariah.dao.RecordDao;
import com.Naariah.dao.RecordDetailDao;
import com.Naariah.domain.Equipment;
import com.Naariah.domain.Record;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    @Test
    void testSearchAll(){
        LambdaQueryWrapper<Equipment> lqw = new LambdaQueryWrapper<Equipment>();
        lqw.eq(Equipment::getPartname, "转子");
        List<Equipment> equipmentList = equipmentDao.selectList(lqw);
        System.out.println(equipmentList);
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

  };

    @Autowired
    private RecordDetailDao recordDetailDao;
    @Test
    void deleteDetail(){
        recordDetailDao.deleteById("E00001");
    }



}
