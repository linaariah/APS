package com.Naariah.controller;
import com.Naariah.dao.*;
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


    //生产排程删除记录
    @PostMapping("/deleteRecord")
    public void  deleteRecord(@RequestBody Record record){
//        int result = recordDao.deleteById(record.getProductionNumber());
//        boolean flag;
//        if (result == 1){
//            flag = true;
//        }
//        else{
//            flag=false;
//        }
//        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
        QueryWrapper queryWrapper = new QueryWrapper<Record>();
//        queryWrapper.eq("production_number",record.getProductionNumber());
        queryWrapper.like("date",record.getDate());
        recordDao.delete(queryWrapper);
    };

    //生产排程查询记录
    @GetMapping("/getRecord")
    public Result getRecordAll(){
        List<Record> recordList = recordDao.selectList(null);
        Integer code = recordList != null ? Code.GET_OK : Code.GET_ERR;
        String msg =recordList != null ? "" : "数据查询失败，请重试！";
        return  new Result(code,recordList,msg);
    };
    //生产排程搜索
    @GetMapping("/searchRecord")
    public List<Record> searchRecord(@RequestParam String productionNumber,@RequestParam String date){
        QueryWrapper<Record> wrapper = new QueryWrapper<>();
        wrapper.like("production_number",productionNumber);
        wrapper.like("date",date);
        List<Record> recordList = recordDao.selectList(wrapper);
        return recordList;
    };
    //添加型号-----------------------------------------------------------------------------
    @Autowired
    private ModelDao modelDao;
    @PostMapping("/addModel")
    public void addModel(@RequestBody Model model){
        modelDao.insert(model);
    };

    @GetMapping("/getModel")
    public Result getModel(){
        List<Model> modelList = modelDao.selectList(null);
        Integer code = modelList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = modelList != null ? "" : "数据查询失败，请重试！";
        return  new Result(code, modelList,msg);
    };
    @GetMapping("/deleteModel")
    public void deleteEquipmentBom(@RequestParam String model){
        QueryWrapper<Model> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("model",model);
        modelDao.delete(queryWrapper);
    };



    //选择设备------------------------------------------------------------------------
    @Autowired
    private EquipmentDao equipmentDao;
    @GetMapping("/getEquipment")
    public Result getEquipment(){
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<Equipment>();
        queryWrapper.orderByAsc("equipid");
        List<Equipment> equipmentList = equipmentDao.selectList(queryWrapper);
        Integer code = equipmentList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = equipmentList != null ? "" : "数据查询失败，请重试！";
        return  new Result(code, equipmentList,msg);

    };

    @PostMapping("/updateEquipment")
    public void updateEquipment(@RequestBody Equipment equipment){
        UpdateWrapper<Equipment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("equipid",equipment.getEquipid());
        updateWrapper.set("starttime",equipment.getStarttime());
        updateWrapper.set("endtime",equipment.getEndtime());
        equipmentDao.update(null,updateWrapper);
    };


    //设备信息Bom表编辑-------------------------------------------------------------------
    @Autowired
    private EquipmentBomDao equipmentBomDao;
    //查询EquipmentBom
    @GetMapping("/getEquipmentBom")
    public Result getEquipmentBom(){
        QueryWrapper<EquipmentBom> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderBy(true,true,"model").orderBy(true,true,"level").orderBy(true,true,"levelnumber").orderBy(true,true,"processorder").orderBy(true,true,"equipid");
        List<EquipmentBom> equipmentBomList = equipmentBomDao.selectList(queryWrapper);
        Integer code = equipmentBomList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = equipmentBomList != null ? "" : "数据查询失败，请重试！";
        return  new Result(code, equipmentBomList,msg);
    };

    //添加EquipmentBom
    @PostMapping("/addEquipmentBom")
    public Result addEquipmentBom(@RequestBody EquipmentBom equipmentBom){
        int result = equipmentBomDao.insert(equipmentBom);
        boolean flag;
        if (result == 1){
            flag = true;
        }
        else{
            flag=false;
        }
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    };

    @GetMapping("/deleteEquipmentBom")
    public void deleteEquipmentBom(@RequestParam String model,@RequestParam String equipid,@RequestParam String equipname,@RequestParam String processname,@RequestParam String equipnumber,@RequestParam String part,@RequestParam String ct,@RequestParam String level,@RequestParam String processorder,@RequestParam String levelnumber){
        QueryWrapper queryWrapper = new QueryWrapper<EquipmentBom>();
        queryWrapper.eq("model",model);
        queryWrapper.eq("equipid",equipid);
        queryWrapper.eq("equipname",equipname);
        queryWrapper.eq("processname",processname);
        queryWrapper.eq("equipnumber",equipnumber);
        queryWrapper.eq("ct",ct);
        queryWrapper.eq("level",level);
        queryWrapper.eq("levelnumber",levelnumber);
        queryWrapper.eq("processorder",processorder);
        queryWrapper.eq("part",part);
        equipmentBomDao.delete(queryWrapper);
    };

    //搜索EquipmentBom
    @GetMapping("/searchEquipmentBom")
    public List<EquipmentBom> searchEquipmentBom(@RequestParam String model,@RequestParam String equipid,@RequestParam String equipname,@RequestParam String processname,@RequestParam String equipnumber,@RequestParam String part,@RequestParam String ct,@RequestParam String level,@RequestParam String processorder,@RequestParam String levelnumber){
        QueryWrapper<EquipmentBom> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("model",model);
            queryWrapper.like("equipid",equipid);
            queryWrapper.like("equipname",equipname);
            queryWrapper.like("processname",processname);
            queryWrapper.like("equipnumber",equipnumber);
            queryWrapper.like("ct",ct);
            queryWrapper.like("level",level);
            queryWrapper.like("levelnumber",levelnumber);
            queryWrapper.like("processorder",processorder);
            queryWrapper.like("part",part);
        queryWrapper.orderBy(true,true,"model").orderBy(true,true,"level").orderBy(true,true,"levelnumber").orderBy(true,true,"processorder").orderBy(true,true,"equipid");
            List<EquipmentBom> equipmentBomList = equipmentBomDao.selectList(queryWrapper);
            return equipmentBomList;
    };
    @GetMapping("/searchEquipmentBom2")
    public List<EquipmentBom> searchEquipmentBom2(@RequestParam String model,@RequestParam String equipid,@RequestParam String equipname,@RequestParam String processname,@RequestParam String equipnumber,@RequestParam String part,@RequestParam String ct,@RequestParam String level,@RequestParam String processorder,@RequestParam String levelnumber){
        QueryWrapper<EquipmentBom> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("model",model);
        queryWrapper.eq("equipid",equipid);
        queryWrapper.eq("equipname",equipname);
        queryWrapper.eq("processname",processname);
        queryWrapper.eq("equipnumber",equipnumber);
        queryWrapper.eq("ct",ct);
        queryWrapper.eq("level",level);
        queryWrapper.eq("levelnumber",levelnumber);
        queryWrapper.eq("processorder",processorder);
        queryWrapper.eq("part",part);
        queryWrapper.orderBy(true,true,"model").orderBy(true,true,"level").orderBy(true,true,"levelnumber").orderBy(true,true,"processorder").orderBy(true,true,"equipid");
        List<EquipmentBom> equipmentBomList = equipmentBomDao.selectList(queryWrapper);
        return equipmentBomList;
    };
    @GetMapping("/searchEquipmentBom3")
    public List<EquipmentBom> searchEquipmentBom3(@RequestParam String model,@RequestParam String equipid,@RequestParam String equipname,@RequestParam String processname,@RequestParam String equipnumber,@RequestParam String part,@RequestParam String ct,@RequestParam String level,@RequestParam String processorder,@RequestParam String levelnumber){
        QueryWrapper<EquipmentBom> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("model",model);
        queryWrapper.like("equipid",equipid);
        queryWrapper.like("equipname",equipname);
        queryWrapper.like("processname",processname);
        queryWrapper.like("equipnumber",equipnumber);
        queryWrapper.like("ct",ct);
        queryWrapper.like("level",level);
        queryWrapper.like("levelnumber",levelnumber);
        queryWrapper.like("processorder",processorder);
        queryWrapper.like("part",part);
        queryWrapper.orderBy(true,true,"model").orderBy(true,true,"level").orderBy(true,true,"levelnumber").orderBy(true,true,"processorder").orderBy(true,true,"equipid");
        List<EquipmentBom> equipmentBomList = equipmentBomDao.selectList(queryWrapper);
        return equipmentBomList;
    };
//-------------------------------------------------------暂时不用
//    @GetMapping("/searchEquipmentBomForModel")
//    public List<EquipmentBom> searchEquipmentBomForSchedule(@RequestParam String model,@RequestParam String level,@RequestParam String levelnumber){
//        QueryWrapper<EquipmentBom> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("model",model);
//        queryWrapper.like("level",level);
//        queryWrapper.like("levelnumber",levelnumber);
////        queryWrapper.like("processorder",processorder);
//        queryWrapper.orderByAsc("level").orderByAsc("levelnumber").orderByAsc("processorder").orderByAsc("equipid");
//        List<EquipmentBom> equipmentBomList = equipmentBomDao.selectList(queryWrapper);
//        return equipmentBomList;
//    };
//
//    @GetMapping("/searchEquipmentBomForLevelnumber")
//    public List<EquipmentBom> searchEquipmentBomForLevelnumber(@RequestParam String model,@RequestParam String level,@RequestParam String levelnumber){
//        QueryWrapper<EquipmentBom> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("model",model);
//        queryWrapper.eq("level",level);
//        queryWrapper.like("levelnumber",levelnumber);
////        queryWrapper.like("processorder",processorder);
//        queryWrapper.orderByAsc("level").orderByAsc("levelnumber").orderByAsc("processorder").orderByAsc("equipid");
//        List<EquipmentBom> equipmentBomList = equipmentBomDao.selectList(queryWrapper);
//        return equipmentBomList;
//    };
//    @GetMapping("/searchEquipmentBomForProcessorder")
//    public List<EquipmentBom> searchEquipmentBomForProcessorder(@RequestParam String model,@RequestParam String level,@RequestParam String levelnumber){
//        QueryWrapper<EquipmentBom> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("model",model);
//        queryWrapper.eq("level",level);
//        queryWrapper.eq("levelnumber",levelnumber);
//        queryWrapper.orderByAsc("level").orderByAsc("levelnumber").orderByAsc("processorder").orderByAsc("equipid");
//        List<EquipmentBom> equipmentBomList = equipmentBomDao.selectList(queryWrapper);
//        return equipmentBomList;
//    };
    //-------------------------------------------------------暂时不用





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
//        queryWrapper.eq("production_number",record.getProductionNumber());
        queryWrapper.like("starttime",record.getDate());
        recordDetailDao.delete(queryWrapper);

    };
    //查询所有详细记录
    @GetMapping("/getRecordDetail")
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


    //搜索所有详细记录
    @GetMapping("/searchRecordDetail")
    public PageInfo getRecordDetailSearch(@RequestParam Long current,@RequestParam String productionNumber,@RequestParam String model,@RequestParam String number,@RequestParam String equipid,@RequestParam String equipname,@RequestParam String starttime,@RequestParam String endtime){
        //1 创建IPage分页对象,设置分页参数,1为当前页码，10为每页显示的记录数
        IPage<RecordDetail> page=new Page<>(current,10);
        QueryWrapper<RecordDetail> queryWrapper = new QueryWrapper<>();

        queryWrapper.like("production_number",productionNumber);
        queryWrapper.like("model",model);
        queryWrapper.like("number",number);
        queryWrapper.like("equipid",equipid);
        queryWrapper.like("equipname",equipname);
        queryWrapper.like("starttime",starttime);
        queryWrapper.like("endtime",endtime);

        queryWrapper.orderByAsc("production_number");
        queryWrapper.orderByAsc("equipid");


        //2 执行分页查询
            recordDetailDao.selectPage(page,queryWrapper);
            PageInfo pageInfo = new PageInfo();
            pageInfo.setCurrent(page.getCurrent());
            pageInfo.setPages(page.getPages());
            pageInfo.setTotal(page.getTotal());
            pageInfo.setData(page.getRecords());
            return pageInfo;

    };


    //查询所有的详细记录用于安排当天第二单以后的排程
    //查询所有详细记录
    @GetMapping("/recordDetailListForSchedule")
    public Result getRecordDetailAllForSchedule(@RequestParam String date){
        QueryWrapper<RecordDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("starttime",date);
        List<RecordDetail> recordDetail = recordDetailDao.selectList(queryWrapper);
        Integer code = recordDetail != null ? Code.GET_OK : Code.GET_ERR;
        String msg =recordDetail != null ? "" : "数据查询失败，请重试！";
        return  new Result(code,recordDetail,msg);
        };




}
