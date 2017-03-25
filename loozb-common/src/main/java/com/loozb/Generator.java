package com.loozb;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成 注意：不生成service接口 注意：不生成service接口 注意：不生成service接口
 * 
 * @author ShenHuaJie
 */
public class Generator {
	/**
	 * 测试 run 执行 注意：不生成service接口 注意：不生成service接口 注意：不生成service接口
	 * <p>
	 * </p>
	 */
	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir("D://");
		gc.setFileOverride(true);
		gc.setActiveRecord(false);
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setOpen(false);
		gc.setAuthor("龙召碧");
		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		// gc.setMapperName("%sDao");
		// gc.setXmlName("%sDao");
		gc.setServiceName("%sService");
		// gc.setServiceImplName("%sServiceDiy");
		// gc.setControllerName("%sAction");
		mpg.setGlobalConfig(gc);
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("root_123");
		dsc.setUrl("jdbc:mysql://127.0.0.1:3306/loozb?characterEncoding=utf8");
		mpg.setDataSource(dsc);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		 strategy.setTablePrefix(new String[] { "tb_" });// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		strategy.setInclude(new String[] { "tb_classification" }); // 需要生成的表
//		 strategy.setExclude(new String[]{"sys_user"}); // 排除生成的表
		// 字段名生成策略
		strategy.setFieldNaming(NamingStrategy.underline_to_camel);
		// 自定义实体父类
		strategy.setSuperEntityClass("com.loozb.core.base.BaseModel");
		// 自定义实体，公共字段
		strategy.setSuperEntityColumns(
				new String[] { "id", "available", "ctime", "mtime", "create_id" });
		// 自定义 mapper 父类
		strategy.setSuperMapperClass("com.loozb.core.base.BaseMapper");
		// 自定义 service 父类
		strategy.setSuperServiceClass("com.loozb.core.base.BaseService");
		// 自定义 service 实现类父类
		 strategy.setSuperServiceImplClass("com.loozb.core.base.BaseServiceImpl");
		// 自定义 controller 父类
		strategy.setSuperControllerClass("com.loozb.core.base.AbstractController");
		// 【实体】是否生成字段常量（默认 false）
		// public static final String ID = "test_id";
		// strategy.setEntityColumnConstant(true);
		// 【实体】是否为构建者模型（默认 false）
		// public User setName(String name) {this.name = name; return this;}
		// strategy.setEntityBuliderModel(true);
		mpg.setStrategy(strategy);
		String module = "blog";
		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.loozb");
		pc.setEntity("model."+module);
		pc.setMapper("mapper."+module);
		pc.setXml("mapper."+module+".xml");
		pc.setServiceImpl("service."+module+".impl");
		pc.setService("service."+module);
		pc.setController("web."+module);
		mpg.setPackageInfo(pc);
		// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
		InjectionConfig cfg = new InjectionConfig() {
			public void initMap() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("serviceClass", "ISysProvider");
				this.setMap(map);
			}
		};
		mpg.setCfg(cfg);
		// 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
		// 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
		TemplateConfig tc = new TemplateConfig();
		tc.setEntity("template/entity.java.vm");
		tc.setMapper("template/mapper.java.vm");
		tc.setXml("template/mapper.xml.vm");
		tc.setService("template/service.java.vm");
		tc.setController("template/controller.java.vm");
		mpg.setTemplate(tc);
		// 执行生成
		mpg.execute();
	}
}
