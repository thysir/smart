<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.${company!''}.${project!''}.<#if module??>${module}.</#if>dao.${model}Dao">
	<select id="get" parameterType="java.lang.Integer" resultType="${model}">
     	SELECT * FROM ${tableName} WHERE id = &{id}
    </select>
    
	<insert id="save" parameterType="${model}" statementType="PREPARED" useGeneratedKeys="true" keyProperty="id">
		INSERT INTO ${tableName}(
			<#list fieldList as field>
			${field.fieldName}<#if field_has_next>,</#if>
			</#list>
		) 
		VALUES (
			<#list fieldList as field>
			&{${field.fieldName}}<#if field_has_next>,</#if>
			</#list>
		)
	</insert>
	
    <update id="update" parameterType="${model}" statementType="PREPARED">
		UPDATE ${tableName} SET
			<#list fieldList as field>
			  ${field.fieldName} = &{${field.fieldName}}<#if field_has_next>,</#if>
			</#list>
		WHERE id = &{id}
	</update>
	
	<delete id="deleteById" parameterType="list" statementType="PREPARED">
		DELETE FROM ${tableName}
		<choose>
			<when test="list == null or list.size() == 0">
				WHERE 1 != 1
			</when>
			<when test="list.size() == 1">
				WHERE `id` = <foreach collection="list" item="id">&{id}</foreach>
			</when>
			<otherwise>
				AND id in <foreach collection="list" item="item" open="(" separator="," close=")">&{item}</foreach>
			</otherwise>
		</choose>
	</delete>
	
	<select id="findByAll" parameterType="map" resultType="${model}">   
		SELECT * FROM ${tableName}
	</select>
	
	<select id="findListByPropertys" parameterType="com.smart.mvc.model.mybatis.QueryPropertys" resultType="${model}">   
		SELECT * FROM ${tableName} WHERE
     	<if test="propertys != null">
     		<foreach collection="propertys" item="property" separator="AND">
     			<choose>
     				<when test="property.value != null">
     					<choose>
     						<when test="property.value.class.array">
     							<foreach collection="property.value" item="item" open="(" close=")" separator="OR">
     								<choose>
     									<when test="orItem != null">
     										${r'${property.key}'} = &{item}
     									</when>
     									<otherwise>
     										${r'${property.key}'} IS NULL
     									</otherwise>
     								</choose>
     							</foreach>
     						</when>
     						<otherwise>
		     					${r'${property.key}'} = &{property.value}
     						</otherwise>
     					</choose>
     				</when>
     				<otherwise>
     					${r'${property.key}'} IS NULL
     				</otherwise>
     			</choose>
     		</foreach>
		</if>
     	<if test="sort != null and sort != ''">
			ORDER BY ${r'${sort}'}
		</if>
	</select>
</mapper>