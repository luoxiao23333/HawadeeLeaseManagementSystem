package com.group5.hawadeeleasemanagementsystem;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

//自动生成entity,controller,mapper,service
public class CodeGenerator {
    public static void main(String[] args) {
        //构建一个代码生成器对象
        AutoGenerator autoGenerator = new AutoGenerator();
        //配置生成策略
        //1. 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");//获取项目所在目录
        globalConfig.setOutputDir(projectPath+"/src/main/java");//设置代码生成输出目录
        globalConfig.setAuthor("杜小龙");
        globalConfig.setFileOverride(true);
        globalConfig.setServiceName("%sService");
        //globalConfig.setIdType(IdType.ASSIGN_UUID);
        globalConfig.setDateType(DateType.ONLY_DATE);
        //globalConfig.setSwagger2(true);
        autoGenerator.setGlobalConfig(globalConfig);

        //2.设置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/hawadee?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("1234");
        dataSourceConfig.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dataSourceConfig);

        //3.包的配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.group5.hawadeeleasemanagementsystem");
        packageConfig.setEntity("entity");
        packageConfig.setController("controller");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        autoGenerator.setPackageInfo(packageConfig);

        //4.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("contrast");//设置要读取的表名，可以有多个表名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);//自动生成lombok
        autoGenerator.setStrategy(strategy);


        //开始生成
        autoGenerator.execute();
    }
}
