package com.ha.fhir.modelmapper;

import com.github.dozermapper.core.Mapper;
import com.ha.fhir.FhirApplication;
import com.ha.fhir.modelmapper.pojo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FhirApplication.class)
public class ModelMapperTest {
    public static Apple apple;
    public static List<Apple> apples;

    public static Patients patients;

    @Autowired
    private Mapper mapper;

    /**
     * 模拟数据
     */
    static {
        apple = new Apple();
        apple.setName("black");
        apple.setCreateAge("22");
        apple.setId("1");
        apple.setCodes("ccc");
        apples = new ArrayList<>(16);
        Apple apple1 = new Apple("1", "red", "21","sss");
        Apple apple2 = new Apple("2", "blue", "22","ccc");
        Apple apple3 = new Apple("3", "yellow", "23","sss");
        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);

        patients =new Patients();
        patients.setId("123456");
        patients.setName("name");
        patients.setTexts("HKID");
        patients.setSystem("http://snomed.info/sct");
        patients.setGenders("male");
        patients.setCodes("260385009");
        Identifier identifier=new Identifier();
        identifier.setSystem("dadad");
        patients.setIdentifier(identifier);
    }

    /**
     *
     *  属性名保持一致，采用默认规则
     */
    @Test
    public  void defaultAppleMappingAppleDTO() {
        ModelMapper modelMapper = new ModelMapper();
        AppleDTO AppleDTO = modelMapper.map(apple, AppleDTO.class);
        System.out.println(AppleDTO.toString());
    }

    /**
     * LOOSE松散策略
     */
    @Test
    public  void strategyAppleMappingAppleDTO() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                /**  LOOSE松散策略 */
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        AppleDTO appleDto = modelMapper.map(apple, AppleDTO.class);
        System.out.println(appleDto.toString());
    }

    private  static PropertyMap customField(){
        /**
         * 自定义映射规则
         */
        return  new PropertyMap<Apple, AppleDTO>() {
            @Override
            protected void configure() {
                /**使用自定义转换规则*/
                map(source.getCreateAge(),destination.getCreate_age());
            }
        };
    }

    @Test
    public void customAppleMappingAppleDTO(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(customField()) ;
        List<AppleDTO> userDTOS = modelMapper.map(apples,new TypeToken<List<AppleDTO>>() {}.getType());
        System.out.println(userDTOS);
    }

    /**
     * xml配置映射关系
     */
    @Test
    public void xmlCustomAppleMappingAppleDTO(){
        AppleDTO destObject = mapper.map(apple, AppleDTO.class,"apple");
        System.out.println(destObject);
    }

    /**
     * xml配置映射关系
     */
    @Test
    public void xmlCustomPatientsMappingPatientDTO(){
        PatientDTO patientDTO = mapper.map(patients, PatientDTO.class);
        System.out.println(patientDTO);
    }
}
