package com.laputa.iot.org.service.impl;

import cn.hutool.extra.pinyin.PinyinUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.constant.SecurityConstants;
import com.laputa.iot.common.core.util.FileUtil;
import com.laputa.iot.org.LaputaOrgApplication;
import com.laputa.iot.org.api.entity.*;
import com.laputa.iot.org.mapper.*;
import com.laputa.iot.org.service.StaffService;
import com.laputa.iot.org.utils.HttpClient;
import com.laputa.iot.org.utils.RandomValue;
import com.laputa.iot.upms.api.dto.UserInfo;
import com.laputa.iot.upms.api.feign.RemoteUserService;
import jdk.nashorn.internal.ir.annotations.Ignore;

import lombok.val;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;


import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@SpringBootTest(classes = LaputaOrgApplication.class)
class CompanyServiceImplTest {


    /**
     * 实体类型判断符
     */
//    @Autowired
//    OrgDepartmentMapper orgDepartmentMapper;

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    DepartmentMapper departmentMapper;


    @Autowired
    StaffService staffService;




    @Autowired
    JobGradeTypeMapper jobGradeTypeMapper;
    @Autowired
    JobGradeMapper jobGradeMapper;


    @Autowired
    PositionSeqMapper positionSeqMapper;

    @Autowired
    PositionInfoMapper positionInfoMapper;

    @Test
    @Ignore
    void queryPage() {

//        personals.forEach(System.out::println);
//        companies.forEach(company -> {
//            List<OrgDepartment> orgDepartments = orgDepartmentMapper.selectList(queryWrapper);
//            orgDepartments.stream().forEach(orgDepartment -> {
//                        SnowflakeIdGenerate snowflakeIdGenerate = new SnowflakeIdGenerate(1);
//                        Long id = snowflakeIdGenerate.generate();
//                        String code = company.getCode() + "00" + orgDepartment.getOrderNo();
//                        Department department = Department.builder()
//                                .id(id)
//                                .name(orgDepartment.getName())
//                                .code(code)
//                                .companyId(company.getId())
//                                .note(orgDepartment.getNote())
//                                .orderNo(orgDepartment.getOrderNo())
//                                .createUser(868140350533795873l)
//                                .createTime(LocalDateTime.now())
//                                .updateUser(868140350533795873l)
//                                .updateTime(LocalDateTime.now())
//                                .build();
//                        departmentMapper.insert(department);
//                    }
//            );
//        });


//        SnowflakeIdGenerate snowflakeIdGenerate = new SnowflakeIdGenerate(1);
//        Long id = snowflakeIdGenerate.generate();
//        Company company = Company.builder()
//                .id(id)
//                .name("长电科技")
//                .code("600584")
//                .descr("集成电路、分立器件的封装与测试、以及分立器件的芯片设计")
//                .orderNo(6)
//                .createUser(868140350533795873l)
//                .createTime(LocalDateTime.now())
//                .updateUser(868140350533795873l)
//                .updateTime(LocalDateTime.now())
//                .shortName("JCET")
//                .tName("JCET")
//                .build();
//        companyMapper.insert(company);
//        System.out.println(snowflakeIdGenerate);

    }

    @Test
    @Ignore
    void insertGradeType(){
//        QueryWrapper queryWrapper = new QueryWrapper<>();
//        List<OrgJobGradeType> list = orgJobGradeTypeMapper.selectList(queryWrapper);
//        list.forEach(jobtype->{
//            SnowflakeIdGenerate snowflakeIdGenerate = new SnowflakeIdGenerate(1);
//            Long id = snowflakeIdGenerate.generate();
//            JobGradeType jobGradeType = JobGradeType.builder()
//                    .id(id)
//                    .name(jobtype.getName())
//                    .code(jobtype.getCode())
//                    .orderNo(jobtype.getOrderNo())
//                    .createUser(868140350533795873l)
//                    .createTime(LocalDateTime.now())
//                    .updateUser(868140350533795873l)
//                    .updateTime(LocalDateTime.now())
//                    .build();
//            jobGradeTypeMapper.insert(jobGradeType);

//        });
    }

    @Test
    @Ignore
    void insertPositionSeq(){
        QueryWrapper queryWrapper = new QueryWrapper<>();
//        List<OrgPositionSeq> orgPositionSeqs = orgPositionSeqMapper.selectList(queryWrapper);
//        orgPositionSeqs.forEach(orgPositionSeq -> {
//            SnowflakeIdGenerate snowflakeIdGenerate = new SnowflakeIdGenerate(1);
//            Long id = snowflakeIdGenerate.generate();
//            PositionSeq positionSeq = PositionSeq.builder()
//                    .id(id)
//                    .name(orgPositionSeq.getName())
//                    .code(orgPositionSeq.getCode())
//                    .orderNo(orgPositionSeq.getOrderNo())
//                    .note(orgPositionSeq.getNote())
//                    .createUser(868140350533795873l)
//                    .createTime(LocalDateTime.now())
//                    .updateUser(868140350533795873l)
//                    .updateTime(LocalDateTime.now())
//                    .build();
//            positionSeqMapper.insert(positionSeq);
//        });


    }


    @Test
    @Ignore
    void insertGradeJob(){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        List<JobGradeType> gradeTypes = jobGradeTypeMapper.selectList(queryWrapper);
//        List<OrgJobGrade> orgJobGrades = orgJobGradeMapper.selectList(queryWrapper);
//        orgJobGrades.forEach(orgJobGrade -> {
//            System.out.println(orgJobGrade);
//                        SnowflakeIdGenerate snowflakeIdGenerate = new SnowflakeIdGenerate(1);
//            Long id = snowflakeIdGenerate.generate();
//            List<Long> typeList = gradeTypes.stream()
//                    .filter(type->type.getCode().equals(orgJobGrade.getTypeCode()))
//                    .map(JobGradeType -> JobGradeType.getId())
//                    .collect(Collectors.toList());
//
//            JobGrade jobGrade = JobGrade.builder()
//                    .id(id)
//                    .name(orgJobGrade.getName())
//                    .code(orgJobGrade.getCode())
//                    .orderNo(orgJobGrade.getOrderNo())
//                    .typeCode(orgJobGrade.getTypeCode())
//                    .typeId(typeList.get(0))
////                    .jobGradeCode(orgPersonal.getJobGradeCode())
//                    .createUser(868140350533795873l)
//                    .createTime(LocalDateTime.now())
//                    .updateUser(868140350533795873l)
//                    .updateTime(LocalDateTime.now())
//                    .build();
//            jobGradeMapper.insert(jobGrade);
//        });

    }


    @Test
    @Ignore
    void insertPositionInfo(){
        QueryWrapper queryWrapper = new QueryWrapper<>();
//        List<PositionSeq> positionSeqs = positionSeqMapper.selectList(queryWrapper);
//        List<OrgPositionInfo> orgPositionInfos = orgPositionInfoMapper.selectList(queryWrapper);
//        orgPositionInfos.forEach(orgPositionInfo -> {
//            System.out.println(orgPositionInfo);
//            SnowflakeIdGenerate snowflakeIdGenerate = new SnowflakeIdGenerate(1);
//            Long id = snowflakeIdGenerate.generate();
//            List<Long> typeList = positionSeqs.stream()
//                    .filter(positionSeq->positionSeq.getCode().equals(orgPositionInfo.getPositionSeqCode()))
//                    .map(PositionSeq -> PositionSeq.getId())
//                    .collect(Collectors.toList());
//
//            PositionInfo positionInfo = PositionInfo.builder()
//                    .id(id)
//                    .name(orgPositionInfo.getName())
//                    .code(orgPositionInfo.getCode())
//                    .orderNo(orgPositionInfo.getOrderNo())
//                    .positionSeqCode(orgPositionInfo.getPositionSeqCode())
//                    .positionSeqId(typeList.get(0))
////                    .jobGradeCode(orgPersonal.getJobGradeCode())
//                    .createUser(868140350533795873l)
//                    .createTime(LocalDateTime.now())
//                    .updateUser(868140350533795873l)
//                    .updateTime(LocalDateTime.now())
//                    .build();
//            positionInfoMapper.insert(positionInfo);
//        });

    }

    @Test
    void getAvatar(){
        for (int i = 0; i < 100; i++) {
            Map map = RandomValue.getMap();
            CloseableHttpClient client = null;
            String sex = map.get("sex").equals("男")? "male": "female";
            String file = "E:/image/"+sex+ "/"+ i+".svg";
            try {
                client = HttpClients.createDefault();//写循环外
                HttpClient.downImage(client, "https://avatars.dicebear.com/api/"+sex+ "/"+ i+".svg", file);
                String s = FileUtil.encodeBase64File(file);
                System.out.println(s);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (client != null) {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    @Test
    @Ignore
    void updateStaff() {
        try {
            Random random = new Random();
            final File[] avatarFiles = FileUtil.getFileDir("E:\\LAPUTA-IOT-PLATFORM\\laputa-iot-dashboard\\src\\assets\\images\\avatar");
            List<String> avatarList = new ArrayList<>();
            for (int i = 0; i < avatarFiles.length; i++) {
//                System.out.println(avatarFiles[i]);
                final byte[] bytes = FileUtil.getContent(avatarFiles[i].getAbsolutePath());
                final String avatarStr = FileUtil.getPicBase64Str(bytes, "png");
//                System.out.println(avatarStr);
                avatarList.add(avatarStr);
            }

//            System.out.println(avatarList);

            staffService.list().stream().forEach(staff -> {
                System.out.println(staff);
                int j1 = random.nextInt(avatarList.size())%(avatarList.size()+1);
                String avatar = avatarList.get(j1);
                staff.setAvatar(avatar);
                staffService.updateStaff(staff);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

//        staffService.list().stream().forEach(staff -> {
////            System.out.println(staff);
////            System.out.println(staff.getAvatar());
////            if(!staff.getAvatar().contains("data:image/jpeg;base64,")){
////                staff.setAvatar("data:image/jpeg;base64,"+staff.getAvatar());
////                staffService.updateStaff(staff);
////            }
//            CloseableHttpClient client = null;
//            String sexstr =staff.getSex()==0 ? "male": "female";
//            String file = "E:/image/"+sexstr+ "/"+ staff.getId()+".svg";
//                        String avatar = "";
//            String url="https://source.unsplash.com/random";//一个随机图片接口
//            try {
//                client = HttpClients.createDefault();//写循环外
//                HttpClient.downImage(client, url, file);
//                avatar = "data:image/svg;base64,"+ FileUtil.encodeBase64File(file);
//                System.out.println(avatar);
//                staff.setAvatar(avatar);
//                staffService.updateStaff(staff);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (client != null) {
//                    try {
//                        client.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        });
    }

    @Resource
    RemoteUserService remoteUserService;




    @Test
    void updateStaff2() {


            staffService.list().stream().forEach(staff -> {
//                staffService.updateStaff(staff);
                String pinyin = PinyinUtil.getPinyin(staff.getName());
                pinyin = pinyin.replace(" ","");
                final R<UserInfo> info = remoteUserService.info(pinyin, SecurityConstants.FROM_IN);
                System.out.println(info.getData().getSysUser().getId());
                staff.setUserId(info.getData().getSysUser().getId());
                staffService.updateStaff(staff);
            });

    }



    @Test
    @Ignore
    void insertStaff() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
//        Integer jobGradeTotal = jobGradeMapper.selectCount(queryWrapper);
//        Random random = new Random();
//        int j1 = random.nextInt(jobGradeTotal)%(jobGradeTotal+1);
//        List<JobGrade> jobGrades = jobGradeMapper.selectList(queryWrapper);
//        JobGrade jobGrade = jobGrades.get(j1);
//        Integer positiontotal = positionInfoMapper.selectCount(queryWrapper);
//        int p1 = random.nextInt(positiontotal)%(positiontotal+1);
//        List<PositionInfo> positionInfos = positionInfoMapper.selectList(queryWrapper);
//        PositionInfo positionInfo = positionInfos.get(p1);
        Random random = new Random();
        Integer jobGradeTotal = jobGradeMapper.selectCount(queryWrapper);
        List<JobGrade> jobGrades = jobGradeMapper.selectList(queryWrapper);
        Integer positiontotal = positionInfoMapper.selectCount(queryWrapper);
        List<PositionInfo> positionInfos = positionInfoMapper.selectList(queryWrapper);
        Integer departmentTotal = departmentMapper.selectCount(queryWrapper);
        List<Department> departments = departmentMapper.selectList(queryWrapper);

//        List<OrgPersonal> personals = orgPersonalMapper.selectList(queryWrapper);
//        for (int i = 0; i < 100; i++) {
//            Map map = RandomValue.getMap();
//            SnowflakeIdGenerate snowflakeIdGenerate = new SnowflakeIdGenerate(1);
//            Long id = snowflakeIdGenerate.generate();
//            int j1 = random.nextInt(jobGradeTotal)%(jobGradeTotal+1);
//            JobGrade jobGrade = jobGrades.get(j1);
//            int p1 = random.nextInt(positiontotal)%(positiontotal+1);
//            PositionInfo positionInfo = positionInfos.get(p1);
//
//            int d1 = random.nextInt(departmentTotal)%(departmentTotal+1);
//            Department department = departments.get(d1);
//
//            System.out.println(jobGrade);
//            System.out.println(positionInfo);
//
//            Integer sex = map.get("sex").equals("男")? 1: 0;
//            CloseableHttpClient client = null;
//            String sexstr = map.get("sex").equals("男")? "male": "female";
//            String file = "E:/image/"+sexstr+ "/"+ i+".svg";
//            String avatar = "";
//            try {
//                client = HttpClients.createDefault();//写循环外
//                HttpClient.downImage(client, "https://avatars.dicebear.com/api/"+sexstr+ "/"+ i+".svg", file);
//                avatar = "data:image/jpeg;base64,"+ FileUtil.encodeBase64File(file);
//                System.out.println(avatar);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (client != null) {
//                    try {
//                        client.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            Staff staff = Staff.builder()
//                    .id(id)
//                    .name((String) map.get("name"))
//                    .code("10000"+i)
//                    .companyId(894167637796323361l)
//                    .companyName("长电科技")
//                    .address((String) map.get("road"))
//                    .mobile((String) map.get("tel"))
//                    .sex(sex)
//                    .avatar(avatar)
//                    .email((String) map.get("email"))
//                    .jobGradeCode(jobGrade.getCode())
//                    .positionCode(positionInfo.getCode())
//                    .deptId(department.getId())
//                    .deptName(department.getName())
////                    .jobGradeCode(orgPersonal.getJobGradeCode())
//                    .createUser(868140350533795873l)
//                    .createTime(LocalDateTime.now())
//                    .updateUser(868140350533795873l)
//                    .updateTime(LocalDateTime.now())
//                    .build();
//            staffService.saveStaff(staff);
//        }
    }




    @Test
    void selectCompanyById() {
    }
}