package com.Naariah.controller;
import com.Naariah.dao.EquipmentDao;
import com.Naariah.dao.PartDao;
import com.Naariah.dao.RecordDao;
import com.Naariah.domain.Equipment;
import com.Naariah.domain.PartBom;
import com.Naariah.domain.Record;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class Controller {

    //选择物料---------------------------------------------------------
    @Autowired
    private PartDao partDao;
    @GetMapping("/partList")
    public Result getPartAll() {
        QueryWrapper<PartBom> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT part_s","part_s_name","model");
        List<PartBom> partList = partDao.selectList(queryWrapper);
        Integer code =partList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = partList != null ? "" : "数据查询失败，请重试！";
        return new Result(code,partList,msg);
    };
    //物料框搜索选择
    @GetMapping("/modelNameList")
    public Result getModelNameAll(){
        QueryWrapper<PartBom> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT model");
        List<PartBom> modelNameList = partDao.selectList(queryWrapper);
        Integer code = modelNameList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = modelNameList != null ? "" : "数据查询失败，请重试！";
        return  new Result(code,modelNameList,msg);
    };
    //搜索功能
    @GetMapping("/searchModelNameList")
    public List<PartBom> searchModelNameList(@RequestParam(value = "modelname",required = false) String modelname){
        QueryWrapper<PartBom> wrapper = new QueryWrapper<>();
        wrapper.eq("model",modelname);
        List<PartBom> modelList = partDao.selectList(wrapper);
        return modelList;
    };


    //设备信息查询---------------------------------------------------------------------
    @Autowired
    private EquipmentDao equipmentDao;
    @GetMapping("/equipmentList")
    public Result getEquipmentAll(){
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("equipid","equipname","workshopid","lineid","stationid","processname","ct","level","processorder","partname");
        List<Equipment> equipmentList = equipmentDao.selectList(queryWrapper);
        Integer code = equipmentList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = equipmentList != null ? "" : "数据查询失败，请重试！";
        return  new Result(code,equipmentList,msg);
    };
    //查询所属零件
    @GetMapping("/partNameList")
    public Result getPartNameAll(){
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT partname");
        List<Equipment> partNameList = equipmentDao.selectList(queryWrapper);
        Integer code = partNameList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = partNameList != null ? "" : "数据查询失败，请重试！";
        return  new Result(code,partNameList,msg);
    };
    //查询功能
    @GetMapping("/searchPartNameList")
    public List<Equipment> searchPartNameList(@RequestParam(value = "partname",required = false) String partname){
        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
        wrapper.eq("partname",partname);
        List<Equipment> equipmentList = equipmentDao.selectList(wrapper);
        return equipmentList;
    };

    //添加记录-------------------------------------------------------------------
    @Autowired
    private RecordDao recordDao;
    @PostMapping("/addRecord")
    public Result addRecord(@RequestBody Record record){
        int result = recordDao.insert(record);
        boolean flag;
        if (result == 1){
            flag = true;
        }
        else{
            flag=false;
        }
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }
//    public int addRecord(@RequestBody Record record) {
//        return recordDao.insert(record);
//    }

    //删除记录------------------------------------------------------------------------------
    @PostMapping("/deleteRecord")
    public Result deleteRecord(@RequestBody Record record){
        int result = recordDao.deleteById(record.getProductionNumber());
        boolean flag;
        if (result == 1){
            flag = true;
        }
        else{
            flag=false;
        }
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

//    public int deleteRecord(@RequestBody Record record) {
//         return recordDao.deleteById(record.getProductionNumber());
//    }

//查询记录-----------------------------------------------------------------------
    @GetMapping("/recordList")
    public Result getRecordAll(){
        List<Record> recordList = recordDao.selectList(null);
        Integer code = recordList != null ? Code.GET_OK : Code.GET_ERR;
        String msg =recordList != null ? "" : "数据查询失败，请重试！";
        return  new Result(code,recordList,msg);
    };






}
