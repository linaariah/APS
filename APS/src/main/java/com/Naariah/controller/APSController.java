package com.Naariah.controller;
import com.Naariah.dao.EquipmentDao;
import com.Naariah.dao.PartDao;
import com.Naariah.dao.RecordDao;
import com.Naariah.dao.RecordDetailDao;
import com.Naariah.domain.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class APSController {

    //生产排程选择物料功能----------------------------------------------------------------------------------
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


    //设备信息查询-------------------------------------------------------------------------------------------------------------------
    @Autowired
    private EquipmentDao equipmentDao;
    @GetMapping("/equipmentList")
    public Result getEquipmentAll(){
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("equipid","equipname","stationid","processname","ct","level","processorder","partname","starttime","endtime");
        queryWrapper.orderBy(true,false,"level").orderBy(true,true,"processorder");
        List<Equipment> equipmentList = equipmentDao.selectList(queryWrapper);
        Integer code = equipmentList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = equipmentList != null ? "" : "数据查询失败，请重试！";
        return  new Result(code,equipmentList,msg);
    };
    //设备加工时间生产时间更新
    @PostMapping("/equipmentListUpdate")
    public void updateEquipmentList(@RequestBody Equipment equipment){

        UpdateWrapper<Equipment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("equipid",equipment.getEquipid());
        updateWrapper.set("starttime",equipment.getStarttime());
        updateWrapper.set("endtime",equipment.getEndtime());
        equipmentDao.update(null,updateWrapper);
//        int result = equipmentDao.updateById(equipment);
//        boolean flag;
//        if (result == 1){
//            flag = true;
//        }
//        else{
//            flag=false;
//        }
//        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    };
    //查询所属零件菜单
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

    //                                     生产排程计算用
//    @GetMapping("/getEquip")
//    public List<Equipment> getEquip () {
//        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
//        List<Equipment> equipmentList = equipmentDao.selectList(null);
//        return equipmentList;
//    };




//    @GetMapping("/get31")
//    public List<Equipment> get31 () {
//        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
//        wrapper.eq("level",3.1);
//        List<Equipment> equipmentList = equipmentDao.selectList(wrapper);
//        return equipmentList;
//    };
//
//    @GetMapping("/get32")
//    public List<Equipment> get32 () {
//        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
//        wrapper.eq("level",3.2);
//        List<Equipment> equipmentList = equipmentDao.selectList(wrapper);
//        return equipmentList;
//    };
//
//    @GetMapping("/get33")
//    public List<Equipment> get33 () {
//        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
//        wrapper.eq("level",3.3);
//        List<Equipment> equipmentList = equipmentDao.selectList(wrapper);
//        return equipmentList;
//    };
//
//    @GetMapping("/get34")
//    public List<Equipment> get34 () {
//        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
//        wrapper.eq("level",3.4);
//        List<Equipment> equipmentList = equipmentDao.selectList(wrapper);
//        return equipmentList;
//    };
//
//    @GetMapping("/get35")
//    public List<Equipment> get35 () {
//        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
//        wrapper.eq("level",3.5);
//        List<Equipment> equipmentList = equipmentDao.selectList(wrapper);
//        return equipmentList;
//    };
//
//    @GetMapping("/get21")
//    public List<Equipment> get21 () {
//        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
//        wrapper.eq("level",2.1);
//        List<Equipment> equipmentList = equipmentDao.selectList(wrapper);
//        return equipmentList;
//    };
//
//    @GetMapping("/get11")
//    public List<Equipment> get11 () {
//        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
//        wrapper.eq("level",1.1);
//        List<Equipment> equipmentList = equipmentDao.selectList(wrapper);
//        return equipmentList;
//    };





    //生产排程添加记录-------------------------------------------------------------------
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


    //生产排程删除记录------------------------------------------------------------------------------
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

    //生产排程查询记录-----------------------------------------------------------------------
    @GetMapping("/recordList")
    public Result getRecordAll(){
        List<Record> recordList = recordDao.selectList(null);
        Integer code = recordList != null ? Code.GET_OK : Code.GET_ERR;
        String msg =recordList != null ? "" : "数据查询失败，请重试！";
        return  new Result(code,recordList,msg);
    };

    //生产排程添加详细记录----------------------------------------------------------
    @Autowired
    private RecordDetailDao recordDetailDao;
    @PostMapping("/addRecordDetail")
    public Result addRecordDetail(@RequestBody RecordDetail recordDetail){
        int result = recordDetailDao.insert(recordDetail);
        boolean flag;
        if (result == 1){
            flag = true;
        }
        else{
            flag=false;
        }
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    };

    //生产排程删除详细记录----------------------------------------------
    @PostMapping("/deleteRecordDetail")
    public void deleteRecordDetail(@RequestBody Record record){
        QueryWrapper queryWrapper = new QueryWrapper<Record>();
        queryWrapper.eq("production_number",record.getProductionNumber());
        recordDetailDao.delete(queryWrapper);

    };
    //查询所有详细记录
    @GetMapping("/recordDetailList")
    public PageInfo getRecordDetailAll(@RequestParam Long current,@RequestParam String productionNumber){
        //1 创建IPage分页对象,设置分页参数,1为当前页码，10为每页显示的记录数
        IPage<RecordDetail> page=new Page<>(current,10);
        QueryWrapper<RecordDetail> queryWrapper = new QueryWrapper<>();
        QueryWrapper<RecordDetail> queryWrapper2 = new QueryWrapper<>();

        queryWrapper.eq("production_number",productionNumber);
        queryWrapper.orderByAsc("production_number");
//        queryWrapper.orderByAsc("starttime");
        queryWrapper.orderByAsc("equipid");


        queryWrapper2.orderByAsc("production_number");
//        queryWrapper2.orderByAsc("starttime");
        queryWrapper2.orderByAsc("equipid");

        //2 执行分页查询
        if (productionNumber== ""){
            recordDetailDao.selectPage(page,queryWrapper2);
            PageInfo pageInfo = new PageInfo();
            pageInfo.setCurrent(page.getCurrent());
            pageInfo.setPages(page.getPages());
            pageInfo.setTotal(page.getTotal());
            pageInfo.setData(page.getRecords());
            return pageInfo;
        }
        else{
        recordDetailDao.selectPage(page,queryWrapper);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrent(page.getCurrent());
        pageInfo.setPages(page.getPages());
        pageInfo.setTotal(page.getTotal());
        pageInfo.setData(page.getRecords());
            return pageInfo;
        }
    };




}
