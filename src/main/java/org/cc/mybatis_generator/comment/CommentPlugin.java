package org.cc.mybatis_generator.comment;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 自定义model注解
 */
public class CommentPlugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, Plugin.ModelClassType modelClassType) {
		// 添加字段注释
		StringBuffer sb = new StringBuffer();
		field.addJavaDocLine("/**");
		field.addJavaDocLine(" * <pre>");
		if (introspectedColumn.getRemarks() != null)
			field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
		sb.append(" * 表字段 : ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		sb.append('.');
		sb.append(introspectedColumn.getActualColumnName());
		field.addJavaDocLine(sb.toString());
		field.addJavaDocLine(" * </pre>");
		field.addJavaDocLine(" * ");
		field.addJavaDocLine(" */");
		return true;
	}
}
